package com.example.calculator


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


// Calculator Buttons Text
val buttonList = listOf(
    "C", "(", ")", "/",
    "7", "8", "9", "*",
    "4", "5", "6", "+",
    "1", "2", "3", "-",
    "AC", "0", ".", "="
)


fun getColor(buttonText: String) : Color {

    if (buttonText == "C" || buttonText == "AC") {
        return Color(0xFFF44336)
    } else if(buttonText == "(" || buttonText == ")") {
        return Color.Gray
    } else if (buttonText == "/" || buttonText == "*" || buttonText == "+" || buttonText == "-" || buttonText == "=") {
        return Color(0xFFFF9800)
    } else {
        return Color(0xFF00C8C9)
    }

}


@Composable
fun CalculatorButtons(buttonText: String, onClick : () -> Unit) {

    Box(modifier = Modifier.padding(10.dp)) {
        FloatingActionButton(
            onClick = onClick,
            modifier = Modifier.size(80.dp),
            shape = CircleShape,
            containerColor = getColor(buttonText)
        ) {
           Text(
               text = buttonText,
               fontSize = 30.sp,
               fontWeight = FontWeight.Bold
           )
        }
    }

}


@Composable
fun Calculator(modifier: Modifier = Modifier, viewModel: CalculatorViewModel) {

    val equationText by viewModel.equationText
    val resultText by viewModel.resultText

    Box(modifier = modifier) {

        Column (
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.End

        ) {

            // Inputted math expression
            Text(
                text = equationText,
                style = TextStyle(
                    fontSize = 30.sp,
                    textAlign = TextAlign.End
                ),
                maxLines = 5,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.weight(1f))

            // Math expression results
            Text(
                text = resultText,
                style = TextStyle(
                    fontSize = 60.sp,
                    textAlign = TextAlign.End
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )


            Spacer(modifier = Modifier.height(10.dp))


            // Render Buttons
            LazyVerticalGrid(
                columns = GridCells.Fixed(4)
            ) {
                items(items = buttonList){
                    CalculatorButtons(buttonText = it, onClick = {
                        viewModel.onButtonClick(it);
                    })
                }
            }
        }
    }
}