package com.example.bank

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvlist: RecyclerView
    private var adapter = MenuItemAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startViews()
        setListItem()
    }

    private fun startViews() {
        rvlist = findViewById(R.id.rv_list)
        rvlist.adapter = adapter
        rvlist.layoutManager = GridLayoutManager(this,2)
    }

    private fun setListItem() {
        adapter.setItemList(
            arrayListOf(
                MenuItemModel(
                "Cards"
                ),
                MenuItemModel(
                "My vouchers "
                ),
                MenuItemModel(
                "Invest"
                ),
                MenuItemModel(
                "Portability"
                ),
                MenuItemModel(
                        "Cards"
                ),
                MenuItemModel(
                        "My vouchers "
                ),
                MenuItemModel(
                        "Invest"
                ),
                MenuItemModel(
                        "Portability"
                )
            )
        )
    }
}