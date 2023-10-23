package com.example.bartolini_mauri_login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class ProfiloActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profilo)

        val privacyButton = findViewById<Button>(R.id.privacyButton)
        privacyButton.setOnClickListener {
            val pdfUrl = "https://bartoliniemauri.com/wp-content/uploads/2021/04/Allegato-3-fac-simile.pdf"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://docs.google.com/gview?embedded=true&url=$pdfUrl")
            startActivity(intent)
        }
        //a quaaa

        val request = Request.Builder()
            .url("https://api.bartoliniemauri.com/api/Customer?idCustomer=BDC66BBADD867DA7DA9C966BAAAAABABCB7AACD69B6BCA9C79B8C76BBCC76CB7")
            .build()

        val client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Gestisci l'errore di connessione
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call?, response: Response) {
                val responseData = response.body()!!.string()
                runOnUiThread {
                    try {
                        val jsonObj = JSONObject(responseData)
                        val name = jsonObj.getString("name")
                        val cognome = jsonObj.getString("cognome")
                        val telefono = jsonObj.getString("telefono")
                        val nomeTextView = findViewById<TextView>(R.id.nomeTextView)
                        nomeTextView.text = "Name: $name"
                        // Fai qualcosa con i dati
                    } catch (e: Exception) {
                        // Gestisci l'errore di parsing JSON
                    }
                }

                val materialButton = findViewById<MaterialButton>(R.id.materialButton)
                materialButton.setOnClickListener {
                    val callIntent = Intent(Intent.ACTION_DIAL)
                    callIntent.data = Uri.parse("tel:011 7410 958")
                    startActivity(callIntent)
                }

                val transparentButton = findViewById<Button>(R.id.transparentButton)
                transparentButton.setOnClickListener {
                    performLogout()
                    finishAffinity()
                }
            }
        })
    }
//da qua in su
    private fun performLogout() {
        // Rimuovi le informazioni di accesso dal SharedPreferences
        val sharedPref = getSharedPreferences("shared", MODE_PRIVATE)
        sharedPref.edit()
            .remove("token")
            .remove("id")
            .apply()

        // Avvia l'activity di login
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish() // Chiudi l'activity corrente
    }

    fun onLayoutPreventiviClick(view: View?) {
        // Avvia l'Activity PreventiviActivity
        val intent = Intent(this, PreventiviActivity::class.java)
        startActivity(intent)
    }

    fun onLayoutAssistanceClick(view: View?) {
        // Avvia l'Activity AssistanceActivity
        val intent = Intent(this, AssistanceActivity::class.java)
        startActivity(intent)
    }

    fun onLayoutHomeClick(view: View?) {
        // Avvia l'Activity HomeActivity
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}