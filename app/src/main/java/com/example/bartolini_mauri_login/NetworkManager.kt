package com.example.bartolini_mauri_login

import APIService
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.bartolini_mauri_login.models.LoginResponse
import com.example.bartolini_mauri_login.models.customer.CustomerResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkManager {
    companion object {


        // Todo: prende in input un oggetto Context, una stringa "username" e una stringa "password".
        //  Questo metodo effettua una richiesta di login al server utilizzando l'API di Retrofit.
        //  Vengono impostati l'URL di base dell'API e un convertitore Gson per convertire la risposta in oggetti Java.
        //  Viene costruito un oggetto APIService tramite Retrofit.Builder()
        fun login(context : AppCompatActivity, username: String, password: String) {
            val retrofitBuilder = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.bartoliniemauri.com/api/")
                .build()
                .create(APIService::class.java)

            val retrofitData = retrofitBuilder.login(username, password)

            retrofitData.enqueue(object : Callback<LoginResponse?> {
                override fun onResponse(
                    call: Call<LoginResponse?>,
                    response: Response<LoginResponse?>
                ) {
                    val responseBody = response.body()

                    if(responseBody?.data !== null){
                        val sharedPref = context.getSharedPreferences("shared", Context.MODE_PRIVATE)
                        sharedPref.edit().putString("token", responseBody.data.jwt).apply()
                        sharedPref.edit().putString("id", responseBody.data.idCustomer).apply()

                        context.startActivity(Intent(context, HomeActivity::class.java))
                    } else {
                        // TODO: Mostra nella UI che username o password non sono validi
                    }
                }

                override fun onFailure(call: Call<LoginResponse?>, t: Throwable) {
                    // TODO: Mostra nella UI che username o password non sono validi
                }
            })
        }

        //Todo: Prende in input un oggetto Context, una stringa "id" e una stringa "token".
        // Viene costruito un oggetto APIService e viene chiamato il metodo "getCustomer" sull'oggetto APIService,
        // passando l'ID del cliente e il token come parametri della richiesta. Viene aggiunta una Callback per gestire la risposta della richiesta.
        // Nella funzione onResponse, viene recuperato la risposta.
        fun getCustomer(context: AppCompatActivity, id: String, token: String) {
            val retrofitBuilder = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.bartoliniemauri.com/api/")
                .build()
                .create(APIService::class.java)

            val headers = HashMap<String, String>()
            headers.put("Authorization", "Bearer $token")

            val retrofitData = retrofitBuilder.getCustomer(headers, id)

            retrofitData.enqueue(object : Callback<CustomerResponse?> {
                override fun onResponse(
                    call: Call<CustomerResponse?>,
                    response: Response<CustomerResponse?>
                ) {
                    val data = response.body()

                    if(data === null){
                        // To login
                        context.startActivity(Intent(context, MainActivity::class.java))
                    } else {
                        context.startActivity(Intent(context, HomeActivity::class.java))
                    }
                }

                override fun onFailure(call: Call<CustomerResponse?>, t: Throwable) {
                    // TODO: Mostra nella UI che username o password non sono validi
                }
            })
        }
    }
}