package com.example.recylerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val list: RecyclerView by lazy {
        findViewById<RecyclerView>(R.id.list)
    }

    private  val adapter = ContactAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindViews()
    }

    private fun bindViews(){
        list.adapter = adapter
        list.layoutManager = LinearLayoutManager(this)
    }
    fun updateList(){
        adapter.updateList(
            arrayListOf(
                Contact(
                    "Guy",
                    "(00) 90000-0000",
                    "img.png"
                )
            )
        )
    }
}