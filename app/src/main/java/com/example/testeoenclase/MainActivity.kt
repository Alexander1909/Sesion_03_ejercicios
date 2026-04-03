package com.example.testeoenclase

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.testeoenclase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializar ViewBinding correctamente
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        logLifecycle("onCreate")

        setupListeners()
    }

    private fun setupListeners() {
        binding.btnMostrar.setOnClickListener {
            startActivity(Intent(this, SegundaActividad::class.java))
        }
        binding.btnConversion.setOnClickListener {
            val intent = Intent(this, ConversionActivity::class.java)
            startActivity(intent)
        }
    }

    fun onIrASegunda(view: View) {
        val progressBar: ProgressBar = findViewById(R.id.progressBarMain)
        progressBar.visibility = View.VISIBLE
        progressBar.progress = 0
        var elapsed = 0
        val handler = Handler(Looper.getMainLooper())
        val step = 200L
        val total = 2000L  // 2 segundos

        val r = object : Runnable {
            override fun run() {
                elapsed += step.toInt()
                progressBar.progress = (progressBar.max * elapsed / total).toInt()
                if (elapsed < total) {
                    handler.postDelayed(this, step)
                } else {
                    progressBar.progress = progressBar.max
                    Toast.makeText(applicationContext,
                        "Carga completa", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@MainActivity,
                        SegundaActividad::class.java))
                    progressBar.visibility = View.INVISIBLE
                }
            }
        }
        handler.post(r)
    }


    private fun logLifecycle(event: String) {
        Log.d(TAG, "Evento: $event")
    }

    override fun onStart() {
        super.onStart()
        logLifecycle("onStart")
    }

    override fun onRestart() {
        super.onRestart()
        logLifecycle("onRestart")
    }

    override fun onResume() {
        super.onResume()
        logLifecycle("onResume")
    }

    override fun onPause() {
        logLifecycle("onPause")
        super.onPause()
    }

    override fun onStop() {
        logLifecycle("onStop")
        super.onStop()
    }

    override fun onDestroy() {
        logLifecycle("onDestroy")
        super.onDestroy()
    }
}
