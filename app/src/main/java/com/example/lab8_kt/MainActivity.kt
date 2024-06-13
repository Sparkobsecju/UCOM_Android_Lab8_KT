package com.example.lab8_kt

import android.app.ProgressDialog
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
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
        button2.setOnClickListener { displayDialog4() }
    }

    private fun displayDialog4() {
        val d = ProgressDialog(this)
        d.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
        d.setMessage("loading...")
        //d.setButton(DialogInterface.BUTTON_POSITIVE, "OK", null)
        d.show()
        Thread() {
            var i = 0
            for (i in 0..100) {
                Thread.sleep(100)
                d.progress = i
            }
        }.start()
        //d.progress = 36
    }


    private fun displayDialog1() {
        val builder = AlertDialog.Builder(this)
            .setTitle("商店選單")
            .setMessage("是否選擇支付")
            .setPositiveButton("OK") { d, w -> displayDialog2()}
            .setNegativeButton("Quit") { d, w -> finish() }
            .setNeutralButton("Wait") { d, w ->
                Toast.makeText(
                    this,
                    "waiting",
                    Toast.LENGTH_SHORT
                ).show()
                displayDialog3()
            }
            .show()

    }

    private fun displayDialog3() {
        val d = ProgressDialog(
            this
        )
        d.setTitle("剛才設的標題")
        d.setMessage("要再等一會兒")
        //d.setCancelable(true)
        d.setCancelable(false)
        d.show()
        Thread {
            Thread.sleep(5000)
            runOnUiThread {
                d.cancel()
            }
        }.start()
    }

    private fun displayDialog2() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("多重選擇題")
        //builder.setMessage("選項在此")
        builder.setItems(R.array.stores) { d, w ->
            d.dismiss()
            val t: TextView = findViewById(R.id.textView1)
            t.text = "第${1 + w}個選項被點擊"
            //Toast.makeText(this,"第${w}個選項被點擊",Toast.LENGTH_SHORT).show()
        }
        builder.show()

    }

}