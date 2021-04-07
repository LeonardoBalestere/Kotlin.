package com.example.vmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    var nCounter = MutableLiveData<String>().apply { value= counter.toString() }

    private var counter: Int = 0

    private fun setCounter(){
        nCounter.value = counter.toString()
    }

    private fun counterReseter() {
        counter++
        if (counter > 5) {
            counter = 0
        }
        setCounter()
    }
    fun counter(){
        counterReseter()

        }
}
