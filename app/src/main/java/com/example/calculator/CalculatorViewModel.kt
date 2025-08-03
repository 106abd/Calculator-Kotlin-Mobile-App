package com.example.calculator

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel(){

    private val _equationText = MutableLiveData("")
    val equationText : LiveData<String> = _equationText

    private val _resultText = MutableLiveData("0")
    val resultText : LiveData<String> = _resultText

    fun onButtonClick(buttonText: String) {
        Log.i("Clicked Button", buttonText)

        _equationText.value?.let {

            if (buttonText == "AC") {
                _equationText.value = ""
                _resultText.value = "0"
                return

            } else if (buttonText == "C") {

                if (it.isNotEmpty()) {
                    _equationText.value = it.substring(0, it.length - 1)
                    return
                }

            } else if (buttonText == "=") {
                _equationText.value = _resultText.value
                return

            } else {
                _equationText.value = it + buttonText

              // Calculate Result
                Log.i("Equation", _equationText.value.toString())
            }
        }
    }
}