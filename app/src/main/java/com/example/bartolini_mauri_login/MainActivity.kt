package com.example.bartolini_mauri_login

import APIService
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.bartolini_mauri_login.models.LoginResponse
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    val BASE_URL = "https://api.bartoliniemauiri.com/api/login"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val username = findViewById<TextInputEditText>(R.id.Username)
        val password = findViewById<TextInputEditText>(R.id.Password)
        val ButtonLogin = findViewById<Button>(R.id.ButtonLogin)


        ButtonLogin.setOnClickListener {
            login(this)

        }


    }

    private fun login(context : AppCompatActivity) {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.bartoliniemauri.com/api/")
            .build()
            .create(APIService::class.java)

        // TODO: Username e password da sostituire con i campi
        val retrofitData = retrofitBuilder.login("TRNVTR90T07Z611O", "YvYx3PYy")

        retrofitData.enqueue(object : Callback<LoginResponse?> {
            override fun onResponse(
                call: Call<LoginResponse?>,
                response: Response<LoginResponse?>
            ) {
                val responseBody = response.body()

                val sharedPref = context.getSharedPreferences("shared", Context.MODE_PRIVATE)
                sharedPref.edit().putString("token", responseBody!!.data.jwt).apply()


                startActivity(Intent(context, HomeActivity::class.java))
            }

            override fun onFailure(call: Call<LoginResponse?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}



