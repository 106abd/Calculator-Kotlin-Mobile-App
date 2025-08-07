package com.example.calculator

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.mozilla.javascript.Context
import org.mozilla.javascript.Scriptable

class CalculatorViewModel : ViewModel(){

    private val _equationText = mutableStateOf("")
    val equationText : State<String> get() = _equationText

    private val _resultText = mutableStateOf("0")
    val resultText : State<String> get() = _resultText

    fun calculateResult(equation: String) : String {
        val context : Context = Context.enter()
        context.optimizationLevel = -1

        val scriptable : Scriptable = context.initStandardObjects()
        var finalResult = context.evaluateString(scriptable, equation, "Javascript", 1, null).toString()

        if (finalResult.endsWith(".0")) {
            finalResult = finalResult.replace(".0", "")
        }

        return finalResult
    }

    fun onButtonClick(buttonText: String) {
        Log.i("Clicked Button", buttonText)

        // Lambda function (if equation text != null then)
        _equationText.value?.let {

            // If button is all clear
            if (buttonText == "AC") {
                _equationText.value = ""
                _resultText.value = "0"
                return

            // If button is clear
            } else if (buttonText == "C") {

                if (it.isNotEmpty()) {
                    _equationText.value = it.substring(0, it.length - 1)
                    return
                }

            // // If button is =
            } else if (buttonText == "=") {
                _equationText.value = _resultText.value
                return

            // If button is the numbers or brackets or operators
            } else {

                _equationText.value = it + buttonText

              // Calculate Result
                try {
                    _resultText.value = calculateResult(_equationText.value.toString())

                } catch (exceptionMsg : Exception){}
            }
        }
    }
}