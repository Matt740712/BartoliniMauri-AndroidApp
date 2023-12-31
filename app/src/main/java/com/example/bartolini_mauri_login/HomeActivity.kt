package com.example.bartolini_mauri_login

import APIService
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.bartolini_mauri_login.ViewModels.MainViewModel
import com.example.bartolini_mauri_login.databinding.ActivityHomeBinding
import com.example.bartolini_mauri_login.models.policy.PolicyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.view.View
import android.widget.Button
import com.google.android.material.button.MaterialButton

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("customerName")
        binding.nameText.text = "Ciao, $name"

        val sharedPref = getSharedPreferences("shared", MODE_PRIVATE)
        val id = sharedPref.getString("id", "")
        val token = sharedPref.getString("token", "")

        if (id != null && token != null) {
            getContracts(id, token)
        }
        val materialButton = findViewById<MaterialButton>(R.id.materialButton)
        materialButton.setOnClickListener {
            val callIntent = Intent(Intent.ACTION_DIAL)
            callIntent.data = Uri.parse("tel:011 7410 958")
            startActivity(callIntent)
        }
    }

    //Todo: viene ottenuto il nome dell'utente dalla Intent e viene impostato il testo di un TextView con il relativo valore.
    private fun getContracts(id: String, token: String) {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.bartoliniemauri.com/api/")
            .build()
            .create(APIService::class.java)

        val headers = HashMap<String, String>()
        headers.put("Authorization", "Bearer $token")
        val retrofitData = retrofitBuilder.getContracts(headers, id)

        //Todo:  viene ottenuto l'ID dell'utente e il token di autenticazione dalle SharedPreferences.

        retrofitData.enqueue(object : Callback<PolicyResponse?> {
            override fun onResponse(
                call: Call<PolicyResponse?>,
                response: Response<PolicyResponse?>
            ) {
                val responseBody = response.body()

                //Todo : viene controllato se l'ID e il token sono validi e, in caso affermativo, viene chiamato il metodo "getContracts" per i dati Utente

                if (responseBody?.data !== null) {
                    viewModel.policies.value = responseBody.data
                }
            }

            override fun onFailure(call: Call<PolicyResponse?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    fun onLayoutAssistanceClick(view: View) {
        val intent = Intent(this, AssistanceActivity::class.java)
        startActivity(intent)
    }

    fun onLayoutPreventiviClick(view: View) {
        val intent = Intent(this, PreventiviActivity::class.java)
        startActivity(intent)
    }

    fun onLayoutProfiloClick(view: View) {
        val intent = Intent(this, ProfiloActivity::class.java)
        startActivity(intent)
    }
}