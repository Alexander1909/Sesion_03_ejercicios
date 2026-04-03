package com.example.testeoenclase

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ConversionActivity : AppCompatActivity() {

    companion object {
        const val TIMER_RUNTIME = 3000L   // 3 segundos de "carga"
    }

    private lateinit var etAmount: EditText
    private lateinit var spinnerCurrency: Spinner
    private lateinit var tvResult: TextView
    private lateinit var progressBar: ProgressBar

    // Tasas de cambio respecto al USD (valores de referencia)
    private val rates = mapOf(
        "EUR" to 0.92, "PEN" to 3.74, "GBP" to 0.79,
        "JPY" to 157.5, "BRL" to 5.05
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversion)

        etAmount       = findViewById(R.id.etAmount)
        spinnerCurrency = findViewById(R.id.spinnerCurrency)
        tvResult       = findViewById(R.id.tvResult)
        progressBar    = findViewById(R.id.progressBarConv)

        val currencies = rates.keys.toList()
        val adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_item, currencies)
        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item)
        spinnerCurrency.adapter = adapter
    }

    fun onConvertir(view: View) {
        val amountStr = etAmount.text.toString()
        if (amountStr.isEmpty()) {
            Toast.makeText(this, "Ingrese un monto", Toast.LENGTH_SHORT).show()
            return
        }
        val amount = amountStr.toDouble()
        val currency = spinnerCurrency.selectedItem.toString()
        val rate = rates[currency] ?: 1.0

        // Mostrar ProgressBar y simular carga
        progressBar.visibility = View.VISIBLE
        progressBar.progress = 0

        var elapsed = 0
        val handler = Handler(Looper.getMainLooper())
        val step = 200L

        val runnable = object : Runnable {
            override fun run() {
                elapsed += step.toInt()
                val progress = (progressBar.max * elapsed / TIMER_RUNTIME).toInt()
                progressBar.progress = progress
                if (elapsed < TIMER_RUNTIME) {
                    handler.postDelayed(this, step)
                } else {
                    progressBar.progress = progressBar.max
                    val result = amount * rate
                    tvResult.text = String.format(
                        "%.2f USD = %.2f %s", amount, result, currency)
                    Toast.makeText(applicationContext,
                        "Carga completa", Toast.LENGTH_SHORT).show()
                }
            }
        }
        handler.post(runnable)
    }
}
