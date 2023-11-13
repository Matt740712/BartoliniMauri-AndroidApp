package com.example.bartolini_mauri_login
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.DigitsKeyListener
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class PreventiviActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var annoAutoEditText: EditText
    private lateinit var targaAutoEditText: EditText
    private lateinit var vehicleSpinner: Spinner
    private lateinit var radioGroup: RadioGroup
    private lateinit var preventivoButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preventivi)

        annoAutoEditText = findViewById(R.id.annoAuto)
        targaAutoEditText = findViewById(R.id.targaAuto)
        vehicleSpinner = findViewById(R.id.veicoloSpinner)
        radioGroup = findViewById(R.id.radio_group)
        preventivoButton = findViewById(R.id.Preventivo)

        preventivoButton.setOnClickListener {
            calcolaPreventivo()
        }

        val annoAutoTextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val annoAuto = s.toString().toIntOrNull()
                preventivoButton.isEnabled = annoAuto != null && annoAuto in 1900..2023
                if (annoAuto != null && annoAuto > 2023) {
                    Toast.makeText(applicationContext, "Anno non valido", Toast.LENGTH_SHORT).show()
                }
            }
        }

        annoAutoEditText.addTextChangedListener(annoAutoTextWatcher)

        val targaAutoEditText = findViewById<EditText>(R.id.targaAuto)
        targaAutoEditText.keyListener = DigitsKeyListener.getInstance("ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890")

        val materialButton = findViewById<MaterialButton>(R.id.materialButton)
        materialButton.setOnClickListener {
            val callIntent = Intent(Intent.ACTION_DIAL)
            callIntent.data = Uri.parse("tel:011 7410 958")
            startActivity(callIntent)
        }

        vehicleSpinner.onItemSelectedListener = this
    }

    private fun calcolaPreventivo() {
        var costoAssicurazione = 0

        val annoAuto = annoAutoEditText.text.toString().toIntOrNull()
        val targaAuto = targaAutoEditText.text.toString()
        val vehicleType = vehicleSpinner.selectedItem.toString()
        val selectedRadioButtonId = radioGroup.checkedRadioButtonId

        if (annoAuto != null && annoAuto < 2000) {
            costoAssicurazione += 500
        } else if (annoAuto != null && annoAuto < 2023) {
            costoAssicurazione += 300
        }

        when (vehicleType) {
            "Automobile" -> costoAssicurazione += 200
            "Motociclo" -> costoAssicurazione += 100
            "Autocarro" -> costoAssicurazione += 400
        }

        when (selectedRadioButtonId) {
            R.id.GPL1 -> costoAssicurazione += 100
            R.id.Benzina2 -> costoAssicurazione += 200
            R.id.Eletrico3 -> costoAssicurazione += 10
        }

        val preventivo = "Futuro Preventivo di: $costoAssicurazione €"
        Toast.makeText(this, preventivo, Toast.LENGTH_SHORT).show()
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {

    }

    override fun onNothingSelected(parent: AdapterView<*>) {

    }
    fun onLayoutAssistanceClick(view: View) {
        val intent = Intent(this, AssistanceActivity::class.java)
        startActivity(intent)
    }

    fun onLayoutHomeClick(view: View?) {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
    fun onLayoutProfiloClick(view: View) {
        val intent = Intent(this, ProfiloActivity::class.java)
        startActivity(intent)
    }
}