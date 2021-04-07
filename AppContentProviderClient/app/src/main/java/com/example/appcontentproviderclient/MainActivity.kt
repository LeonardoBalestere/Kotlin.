package com.example.appcontentproviderclient

import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    lateinit var notesRecycler: RecyclerView
    lateinit var noteRefreshButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        noteRefreshButton = findViewById(R.id.client_button_refresh)
        notesRecycler = findViewById(R.id.client_list)
        getContentProvider()

        noteRefreshButton.setOnClickListener{ getContentProvider() }
    }
    private fun getContentProvider(){
        try{
            val url = "content://com.example.applicationcontentprovider.provider/notes"
            val data: Uri = Uri.parse(url)
            val cursor: Cursor? = contentResolver.query(data,null,null,null,"title")
            notesRecycler.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = ClientAdapter(cursor as Cursor)
            }
        } catch (ex: Exception){
            ex.printStackTrace()
        }
    }
}