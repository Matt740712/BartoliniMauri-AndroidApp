package com.example.bartolini_mauri_login

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.button.MaterialButton

class PreventiviActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preventivi)

        val materialButton = findViewById<MaterialButton>(R.id.materialButton)
        materialButton.setOnClickListener {
            val callIntent = Intent(Intent.ACTION_DIAL)
            callIntent.data = Uri.parse("tel:011 7410 958")
            startActivity(callIntent)
        }
    }
    fun onLayoutProfiloClick(view: View) {
        // Avvia l'Activity HomeActivity
        val intent = Intent(this, ProfiloActivity::class.java)
        startActivity(intent)
    }

    fun onLayoutAssistanceClick(view: View) {
        // Avvia l'Activity HomeActivity
        val intent = Intent(this, AssistanceActivity::class.java)
        startActivity(intent)
    }

    fun onLayoutHomeClick(view: View) {
        // Avvia l'Activity HomeActivity
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}
