package com.example.lab5

import android.content.Intent
import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Html
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonToSecond = findViewById<Button>(R.id.button_to_second)
        buttonToSecond.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        val w1Input = findViewById<EditText>(R.id.w_1)
        val w2Input = findViewById<EditText>(R.id.w_2)
        val w3Input = findViewById<EditText>(R.id.w_3)
        val w4Input = findViewById<EditText>(R.id.w_4)
        val w5Input = findViewById<EditText>(R.id.w_5)

        val t1Input = findViewById<EditText>(R.id.t_1)
        val t2Input = findViewById<EditText>(R.id.t_2)
        val t3Input = findViewById<EditText>(R.id.t_3)
        val t4Input = findViewById<EditText>(R.id.t_4)
        val t5Input = findViewById<EditText>(R.id.t_5)

        val resultsView = findViewById<TextView>(R.id.results)

        val calculateButton = findViewById<Button>(R.id.calculateButton)
        calculateButton.setOnClickListener {
            val w1 = w1Input.text.toString().toDoubleOrNull() ?: 0.0
            val w2 = w2Input.text.toString().toDoubleOrNull() ?: 0.0
            val w3 = w3Input.text.toString().toDoubleOrNull() ?: 0.0
            val w4 = w4Input.text.toString().toDoubleOrNull() ?: 0.0
            val w5 = w5Input.text.toString().toDoubleOrNull() ?: 0.0

            val t1 = t1Input.text.toString().toDoubleOrNull() ?: 0.0
            val t2 = t2Input.text.toString().toDoubleOrNull() ?: 0.0
            val t3 = t3Input.text.toString().toDoubleOrNull() ?: 0.0
            val t4 = t4Input.text.toString().toDoubleOrNull() ?: 0.0
            val t5 = t5Input.text.toString().toDoubleOrNull() ?: 0.0

            val w1System = w1 + w2 + w3 + w4 + 6 * w5
            val t1System = (w1 * t1 + w2 * t2 + w3 * t3 + w4 * t4 + 6 * w5 * t5) / w1System

            val ka = w1System * t1System / 8760
            val kp = 1.2 * 43 / 8760
            val w2System = 2 * w1System * (ka + kp)
            val w2FullSystem = w2System + 0.02

            resultsView.text = Html.fromHtml("""
                Частота відмови одноколової системи: w<sub>ос</sub> = <b>${"%.3f".format(w1System)} рік<sup>-1</sup></b><br><br>
	            Частота відмови двоколової системи (з урахуванням секційного вимикача): w<sub>дс</sub> = <b>${"%.3f".format(w2FullSystem)} рік<sup>-1</sup></b><br><br>
	            Отже, двоколова система надійніша за одноколову у <b>${"%.2f".format(w1System / w2FullSystem)}</b>
            """.trimIndent(),
                Html.FROM_HTML_MODE_LEGACY)
        }
    }
}
