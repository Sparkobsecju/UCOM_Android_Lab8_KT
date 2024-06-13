package com.example.lab8_kt

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        textView = findViewById(R.id.textView1)
        button1.setOnClickListener { displayDialog1() }
    }


    private fun displayDialog1() {
        val builder = AlertDialog.Builder(this)
            .setTitle("這是我的對話框")
            .setMessage("細節放這")
            .setPositiveButton("OK") { d, w -> }
            .setNegativeButton("Quit") { d, w -> finish() }
            .setNeutralButton("Wait") { d, w ->
                Toast.makeText(
                    this,
                    "waiting",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .show()
    }

}