package com.example.calculator

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.mozilla.javascript.Context
import org.mozilla.javascript.Scriptable

class CalculatorViewModel : ViewModel(){

    private val _equationText = MutableLiveData("")
    val equationText : LiveData<String> = _equationText

    private val _resultText = MutableLiveData("0")
    val resultText : LiveData<String> = _resultText

    fun calculateResult(equation: String) : String {
        val context : Context = Context.enter()
        context.optimizationLevel = -1

        val scriptable : Scriptable = context.initStandardObjects()
        val finalResult = context.evaluateString(scriptable, equation, "Javascript", 1, null).toString()

        return finalResult
    }

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