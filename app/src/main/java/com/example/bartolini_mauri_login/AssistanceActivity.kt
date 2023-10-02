package com.example.bartolini_mauri_login

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton

class AssistanceActivity : AppCompatActivity() {


    private lateinit var positionButton: MaterialButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assistance)


        val agenziaButton = findViewById<MaterialButton>(R.id.agenziaButton)
        agenziaButton.setOnClickListener {
            val callIntent = Intent(Intent.ACTION_DIAL)
            callIntent.data = Uri.parse("tel:011 7410 958")
            startActivity(callIntent)
        }
        //val mapIntent alternativo con https perche l'altro non funziona
        positionButton = findViewById(R.id.PositionButton)
        positionButton.setOnClickListener(View.OnClickListener {
            val gmmIntentUri = Uri.parse("geo:45.071074,7.712098")
            val mapIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.google.com/maps/dir/45.071074,7.712098")
            )
            startActivity(mapIntent)
        })
        val emailButton = findViewById<MaterialButton>(R.id.EmailButton)
        emailButton.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "azienda@bartoliniemauri.com", null))
            startActivity(Intent.createChooser(emailIntent, "Invia email"))
        }
    }

    fun onLayoutProfiloClick(view: View) {
        // Avvia l'Activity HomeActivity
        val intent = Intent(this, ProfiloActivity::class.java)
        startActivity(intent)
    }

    fun onLayoutPreventiviClick(view: View) {
        // Avvia l'Activity HomeActivity
        val intent = Intent(this, PreventiviActivity::class.java)
        startActivity(intent)
    }

    fun onLayoutHomeClick(view: View) {
        // Avvia l'Activity HomeActivity
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}