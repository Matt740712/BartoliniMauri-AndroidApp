package com.example.bartolini_mauri_login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.textfield.TextInputEditText
import org.json.JSONException
import java.nio.charset.Charset
import javax.security.auth.login.LoginException



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


       val  Username = findViewById<TextInputEditText>(R.id.Username)
       val  password = findViewById<TextInputEditText>(R.id.Password)
        val ButtonLogin = findViewById<Button>(R.id.ButtonLogin)


        ButtonLogin.setOnClickListener {
        }

        val url = "https://api.bartoliniemauiri.com/api/login"
    }


  }



