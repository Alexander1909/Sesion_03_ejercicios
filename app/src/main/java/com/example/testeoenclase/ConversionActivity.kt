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