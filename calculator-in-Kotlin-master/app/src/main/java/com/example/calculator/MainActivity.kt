package com.example.calculator

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btcalculate:Button = calculate
        val result = result

        btcalculate.setOnClickListener {
            val firstGrade = Integer.parseInt(firstGrade.text.toString())
            val secondGrade = Integer.parseInt(secondGrade.text.toString())
            val average = (firstGrade + secondGrade)/2
            val absense = Integer.parseInt(absence.text.toString())

            if(average >=6 && absense <=10){
                result.setText("Student have been accepted!" + "\n" + "Final grade: " + average + "\n" + "Absenses: " + absense)
                result.setTextColor(Color.GREEN)
            }
            else{
                result.setText("Student have not been accepted!" + "\n" + "Final grade: " + average + "\n" + "Absenses: " + absense)
                result.setTextColor(Color.RED)
            }
        }
    }
}