package com.example.bartolini_mauri_login

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.button.MaterialButton

class ProfiloActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profilo)

        val materialButton = findViewById<MaterialButton>(R.id.materialButton)
        materialButton.setOnClickListener {
            val callIntent = Intent(Intent.ACTION_DIAL)
            callIntent.data = Uri.parse("tel:3474536349")
            startActivity(callIntent)
        }

        val transparentButton = findViewById<Button>(R.id.transparentButton)
        transparentButton.setOnClickListener {
            performLogout()
        }
    }

    private fun performLogout() {
        // Rimuovi le informazioni di accesso dal SharedPreferences
        val sharedPref = getSharedPreferences("shared", Context.MODE_PRIVATE)
        sharedPref.edit()
            .remove("token")
            .remove("id")
            .apply()

        // Avvia l'activity di login
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish() // Chiudi l'activity corrente
    }
}