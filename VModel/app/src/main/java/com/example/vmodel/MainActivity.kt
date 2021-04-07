package com.example.vmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    lateinit var txtCounter: EditText
    lateinit var btnData: Button
    lateinit var btnShow: Button

    lateinit var nViewModel: MainViewModel

    var counter: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initData()
        initClick()
    }

    private fun initData() {
        nViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        nViewModel.nCounter.observe(this, Observer { value -> txtCounter.setText(value) })

        txtCounter = findViewById(R.id.txtcounter)
        btnData = findViewById(R.id.btnData)
        btnShow = findViewById(R.id.btnShow)
    }

    private fun initClick() {
        btnData.setOnClickListener {
            nViewModel.counter()
        }
        btnShow.setOnClickListener {
            Toast.makeText(applicationContext, "Counter value: ${nViewModel.nCounter.value}", Toast.LENGTH_SHORT).show()
        }
    }
}