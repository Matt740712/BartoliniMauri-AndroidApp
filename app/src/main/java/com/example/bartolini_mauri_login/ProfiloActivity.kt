package com.example.bartolini_mauri_login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class ProfiloActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profilo)

        //Todo: File quando clcci i tuoi documenti
        val documentiButton = findViewById<MaterialButton>(R.id.DocumentiButton)
        documentiButton.setOnClickListener {
            val pdfUrl = "https://www.its-ictpiemonte.it/wp-content/uploads/2023/10/1_RiaperturaAvvisoPubblicoSelezioneStudenti_bf23-25.pdf"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(pdfUrl)
            startActivity(intent)
        }
        //Todo: File della privacy
        val privacyButton = findViewById<Button>(R.id.privacyButton)
        privacyButton.setOnClickListener {
            val pdfUrl =
                "https://bartoliniemauri.com/wp-content/uploads/2021/04/Allegato-3-fac-simile.pdf"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://docs.google.com/gview?embedded=true&url=$pdfUrl")
            startActivity(intent)
        }

        val transparentButton = findViewById<Button>(R.id.transparentButton)
        transparentButton.setOnClickListener {
            performLogout()
            finishAffinity()
        }
        //TODO Bottone Conatto N.telefono
        val materialButton = findViewById<MaterialButton>(R.id.materialButton)
        materialButton.setOnClickListener {
            val callIntent = Intent(Intent.ACTION_DIAL)
            callIntent.data = Uri.parse("tel:011 7410 958")
            startActivity(callIntent)
        }
    }

    private fun performLogout() {
        //Todo Rimuovi le informazioni di accesso dal SharedPreferences
        val sharedPref = getSharedPreferences("shared", MODE_PRIVATE)
        sharedPref.edit()
            .remove("token")
            .remove("id")
            .apply()


        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
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