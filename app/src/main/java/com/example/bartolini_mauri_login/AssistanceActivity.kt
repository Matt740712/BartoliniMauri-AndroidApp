package com.example.bartolini_mauri_login

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class AssistanceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assistance)


        @SuppressLint("MissingPermission")
        fun onLayoutAssistanceClick(view: View) {
            // Avvia l'Activity AssistanceActivity
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
    }
