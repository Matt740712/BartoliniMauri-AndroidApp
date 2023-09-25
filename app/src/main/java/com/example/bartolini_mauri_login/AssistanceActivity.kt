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
            callIntent.data = Uri.parse("tel:3770905411")
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
    }

    fun onLayoutAssistanceClick(view: View) {
        // Avvia l'Activity HomeActivity
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}