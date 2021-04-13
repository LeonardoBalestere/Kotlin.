package com.example.coffee_drinked.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CoffeeDrinkedViewModel: ViewModel() {
    private val mCoffeeCounter = MutableLiveData<Int>()
    val coffeeCounter: LiveData<Int> get() = mCoffeeCounter

    init {
        mCoffeeCounter
    }

    fun incrementCounter(){
        mCoffeeCounter.value = mCoffeeCounter.value?.plus(1)
    }

    fun subtractCounter(){
        mCoffeeCounter.value = mCoffeeCounter.value?.minus(1)
    }
}