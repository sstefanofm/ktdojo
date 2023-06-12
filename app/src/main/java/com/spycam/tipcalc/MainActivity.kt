@file:OptIn(ExperimentalMaterial3Api::class)

package com.spycam.tipcalc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spycam.tipcalc.ui.theme.TipCalcTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipCalcTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TipTimeLayout()
                }
            }
        }
    }
}

@Composable
private fun calculateTip(
    amount: Double,
    tipPercent: Double = 15.0,
    roundUp: Boolean
): Double {
    var tip = amount * (tipPercent / 100)

    if (roundUp) {
        tip = kotlin.math.ceil(tip)
    }

    return tip
}

@Preview
@Composable
fun TipTimeLayout() {
    var amountInput by remember { mutableStateOf("") }
    var tipInput by remember { mutableStateOf("") }
    var roundUp by remember { mutableStateOf(false) }

    var amount = amountInput.toDoubleOrNull() ?: 0.0
    var tipPercent = tipInput.toDoubleOrNull() ?: 0.0
    var tip = calculateTip(amount, tipPercent, roundUp)

    Column (
        modifier = Modifier
            .padding(40.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text (
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(alignment = Alignment.Start),
            text = stringResource(id = R.string.calculate_tip),
        )


        EditNumberField (
            R.string.bill_amount,
            R.drawable.money,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next,
            ),
            value = amountInput,
            onValueChanged = {
                amountInput = it
            },
        )

        EditNumberField (
            R.string.tip_amount,
            R.drawable.percent,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done,
            ),
            value = tipInput,
            onValueChanged = {
                tipInput = it
            },
        )

        CeilTipRow (
            roundUp = roundUp,
            onRoundUpChanged = {
                roundUp = it
            },
        )

        Text (
            text = stringResource(id = R.string.tip_amount, tip),
            style = MaterialTheme.typography.displaySmall,
        )

        Spacer (modifier = Modifier.height(150.dp))
    }
}

@Composable
fun EditNumberField (
    @StringRes label: Int,
    @DrawableRes leadingIcon: Int,
    keyboardOptions: KeyboardOptions,
    value: String,
    onValueChanged: (String) -> Unit,
): Unit {
    TextField (
        modifier = Modifier
            .padding(bottom = 32.dp)
            .fillMaxWidth(),
        label = {
            Text (text = stringResource(label))
        },
        leadingIcon = {
            Icon (
                painter = painterResource(id = leadingIcon),
                null
            )
        },
        keyboardOptions = keyboardOptions,
        value = value,
        onValueChange = onValueChanged,
        singleLine = true,
    )
}

@Composable
fun CeilTipRow (
    modifier: Modifier = Modifier,
    roundUp: Boolean,
    onRoundUpChanged: (Boolean) -> Unit,
): Unit {
    Row (
        modifier = modifier
            .fillMaxWidth()
            .size(48.dp),
    ) {
        Text (
            text = stringResource(id = R.string.round_up_tip)
        )

        Switch (
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End),
            checked = roundUp,
            onCheckedChange = onRoundUpChanged,
        )
    }
}
