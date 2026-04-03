package com.example.testeoenclase

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.testeoenclase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {
        private const val TAG = "MainActivity"
    }
    val btnConversion: Button = findViewById(R.id.btnConversion)
    btnConversion.setOnClickListener {
        val intent = Intent(this, ConversionActivity::class.java)
        startActivity(intent)
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

