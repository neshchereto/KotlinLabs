package com.example.lab2

import android.content.Intent
import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Html
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val coalInput = findViewById<EditText>(R.id.coal)
        val oilInput = findViewById<EditText>(R.id.oil)
        val resultsView = findViewById<TextView>(R.id.results)

        val calculateButton = findViewById<Button>(R.id.calculateButton)
        calculateButton.setOnClickListener {
            val coal = coalInput.text.toString().toDoubleOrNull() ?: 0.0
            val oil = oilInput.text.toString().toDoubleOrNull() ?: 0.0

            val coalRes = 10.0.pow(-6) * 150 * coal * 20.47
            val oilRes  = 10.0.pow(-6) * 0.57 * oil * 39.48
            val result   = coalRes + oilRes

            resultsView.text = Html.fromHtml("""
                Валовий викид при спалюванні вугілля: <b>${"%.2f".format(coalRes)} т.</b><br>
                Валовий викид при спалюванні мазуту:  <b>${"%.2f".format(oilRes)} т.</b><br>
                Сумарно:                              <b>${"%.2f".format(result)} т.</b>
            """.trimIndent(),
                Html.FROM_HTML_MODE_LEGACY)
        }
    }
}
