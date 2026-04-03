package com.example.testeoenclase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar

class SegundaActividad : AppCompatActivity() {

    companion object {
        const val TIMER_RUNTIME = 10000
    }

    private var nbActivo: Boolean = false
    private lateinit var nProgressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_segunda_actividad)

        nProgressBar = findViewById(R.id.progressBar)

        val timerThread = Thread {
            nbActivo = true
            try {
                var espera1 = 0
                while (nbActivo && espera1 < TIMER_RUNTIME) {
                    Thread.sleep(200)
                    if (nbActivo) {
                        espera1 += 200
                        runOnUiThread {
                            actualizarProgress(espera1)
                        }
                    }
                }
            } catch (e: InterruptedException) {
                e.printStackTrace()
            } finally {
                runOnUiThread {
                    onContinuar()
                }
            }
        }

        timerThread.start()
    }

    private fun actualizarProgress(timePassed: Int) {
        if (::nProgressBar.isInitialized) {
            val progress = nProgressBar.max * timePassed / TIMER_RUNTIME
            nProgressBar.progress = progress
        }
    }

    private fun onContinuar() {
        Log.d("mensajeFinal", "Su barra de progreso acaba de cargar")
    }
}