package com.example.testeoenclase

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ConversionActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private var progreso = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversion)
        val etMonto = findViewById<EditText>(R.id.etMonto)
        val btnConvertir = findViewById<Button>(R.id.btnConvertir)
        val tvResultado = findViewById<TextView>(R.id.tvResultado)
        progressBar = findViewById(R.id.progressBar)

        btnConvertir.setOnClickListener {
            val monto = etMonto.text.toString().toDoubleOrNull()

            if (monto != null) {
                val resultado = monto * 0.27  // Ejemplo Soles a USD
                tvResultado.text = "USD: $resultado"
                iniciarCarga()
            } else {
                Toast.makeText(this, "Ingrese un valor válido", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun iniciarCarga() {
        progreso = 0
        val handler = Handler()

        Thread {
            while (progreso < 100) {
                progreso += 10
                Thread.sleep(200)

                handler.post {
                    progressBar.progress = progreso
                }
            }
            Log.d("Carga", "Carga completa")
        }.start()
    }
}