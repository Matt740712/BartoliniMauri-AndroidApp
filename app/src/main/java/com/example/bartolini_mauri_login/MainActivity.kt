package com.example.bartolini_mauri_login

import APIService
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import com.example.bartolini_mauri_login.NetworkManager.Companion.getCustomer
import com.example.bartolini_mauri_login.NetworkManager.Companion.login
import com.example.bartolini_mauri_login.ViewModels.MainViewModel
import com.example.bartolini_mauri_login.models.LoginResponse
import com.example.bartolini_mauri_login.models.customer.Customer
import com.example.bartolini_mauri_login.models.customer.CustomerResponse
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    private var isLoading = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Todo : verificato se l'utente ha già effettuato il login salvando l'id e il token dell'utente nelle preferenze
        installSplashScreen().apply {
            val sharedPref = getSharedPreferences("shared", Context.MODE_PRIVATE)
            val id = sharedPref.getString("id", "")
            val token = sharedPref.getString("token", "")
            if (id != null && token != null) {
                getCustomer(id, token)
            }
            this.setKeepOnScreenCondition {
                isLoading
            }
        }

        setContentView(R.layout.activity_main)
        val username = findViewById<TextInputEditText>(R.id.Username)
        val password = findViewById<TextInputEditText>(R.id.Password)
        val ButtonLogin = findViewById<Button>(R.id.ButtonLogin)

        ButtonLogin.setOnClickListener {
            login()
        }
        val contactButton: MaterialButton = findViewById(R.id.ContactButton)

        contactButton.setOnClickListener {
            val websiteUrl = "https://bartoliniemauri.com/contatti/"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(websiteUrl))
            startActivity(intent)
        }

    }

    fun getCustomer(id: String, token: String) {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.bartoliniemauri.com/api/")
            .build()
            .create(APIService::class.java)


        //Todo: utilizza Retrofit per effettuare una chiamata di rete per ottenere le informazioni del cliente.
        // Vengono impostati gli header della richiesta e vengono gestite le risposte e gli errori.

        val headers = HashMap<String, String>()
        headers.put("Authorization", "Bearer $token")
        headers.put("Content-Type", "application/json; charset=utf-8")
        headers.put("Cache-Control", "no-cache")
        headers.put("Accept-Encoding", "gzip, deflate, br")
        headers.put("Connection", "keep-alive")

        val retrofitData = retrofitBuilder.getCustomer(headers, id)

        retrofitData.enqueue(object : Callback<CustomerResponse?> {
            override fun onResponse(
                call: Call<CustomerResponse?>,
                response: Response<CustomerResponse?>
            ) {
                val data = response.body()

                if (data === null) {
                    // To login
                    isLoading = false
                } else {
                    isLoading = false
                    val intent = Intent(this@MainActivity, HomeActivity::class.java)
                    val customer = data.data as Customer
                    intent.putExtra("customerName", customer.name)
                    startActivity(intent)
                    finish()
                }
            }

            override fun onFailure(call: Call<CustomerResponse?>, t: Throwable) {
                isLoading = false
            }
        })

    }

    fun login() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.bartoliniemauri.com/api/")
            .build()
            .create(APIService::class.java)

        val username = "TRNVTR90T07Z611O" // da sostituire con il campo per l'username
        val password = "YvYx3PYy" // da sostituire con  il campo per la password

        val retrofitData = retrofitBuilder.login(username, password)

        retrofitData.enqueue(object : Callback<LoginResponse?> {
            override fun onResponse(
                call: Call<LoginResponse?>,
                response: Response<LoginResponse?>
            ) {
                val responseBody = response.body()

                if (responseBody?.data !== null) {
                    val sharedPref =
                        this@MainActivity.getSharedPreferences("shared", Context.MODE_PRIVATE)
                    sharedPref.edit().putString("token", responseBody!!.data.jwt).apply()
                    sharedPref.edit().putString("id", responseBody!!.data.idCustomer).apply()

                    getCustomer(responseBody!!.data.idCustomer, responseBody!!.data.jwt)
                } else {
                    // TODO: Mostrare nella UI che username o password non sono validi
                }
            }

            override fun onFailure(call: Call<LoginResponse?>, t: Throwable) {
                // TODO: Mostrare nella UI che username o password non sono validi
            }
        })
    }
}



