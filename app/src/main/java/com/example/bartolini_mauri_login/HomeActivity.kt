package com.example.bartolini_mauri_login

import APIService
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
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

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("customerName")
        binding.nameText.text = "Ciao, $name"

        val sharedPref = getSharedPreferences("shared", Context.MODE_PRIVATE)
        val id = sharedPref.getString("id", "")
        val token = sharedPref.getString("token", "")

        if (id != null && token != null) {
            getContracts(id, token)
        }
    }

    private fun getContracts(id: String, token: String) {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.bartoliniemauri.com/api/")
            .build()
            .create(APIService::class.java)

        val headers = HashMap<String, String>()
        headers.put("Authorization", "Bearer $token")
        val retrofitData = retrofitBuilder.getContracts(headers, id)

        retrofitData.enqueue(object : Callback<PolicyResponse?> {
            override fun onResponse(
                call: Call<PolicyResponse?>,
                response: Response<PolicyResponse?>
            ) {
                val responseBody = response.body()

                if (responseBody?.data !== null) {
                    viewModel.policies.value = responseBody.data
                }
            }

            override fun onFailure(call: Call<PolicyResponse?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    @SuppressLint("MissingPermission")
    fun onLayoutAssistanceClick(view: View) {
        // Avvia l'Activity AssistanceActivity
        val intent = Intent(this, AssistanceActivity::class.java)
        startActivity(intent)
    }

    @SuppressLint("MissingPermission")
    fun onLayoutPreventiviClick(view: View) {
        // Avvia l'Activity PreventiviActivity
        val intent = Intent(this, PreventiviActivity::class.java)
        startActivity(intent)
    }

    @SuppressLint("MissingPermission")
    fun onLayoutProfiloClick(view: View) {
        // Avvia l'Activity PreventiviActivity
        val intent = Intent(this, ProfiloActivity::class.java)
        startActivity(intent)
    }
}