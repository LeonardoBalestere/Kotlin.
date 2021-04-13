package com.example.coffee_drinked

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.coffee_drinked.databinding.ActivityMainBinding
import com.example.coffee_drinked.viewmodel.CoffeeDrinkedViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mViewModel: CoffeeDrinkedViewModel

    private val coffeeGif: ImageView by lazy{
        binding.ivCoffee
    }

    private val coffeeCounter: TextView by lazy {
        binding.tvCoffeeCounter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mViewModel = ViewModelProvider(this).get(CoffeeDrinkedViewModel::class.java)

        Glide.with(this).load(R.drawable.coffe).asGif().into(coffeeGif)

        initQuantity(mViewModel)
        initObserver()

    }

    private fun initObserver() {
        mViewModel.coffeeCounter.observe(this, {
            coffeeCounter.text = it.toString()
        })
    }

    private fun initQuantity(mViewModel: CoffeeDrinkedViewModel) {
        coffeeCounter.text = mViewModel.coffeeCounter.value.toString()
    }

}