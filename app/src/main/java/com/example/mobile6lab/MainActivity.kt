package com.example.mobile6lab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import com.example.mobile6lab.ui.theme.Mobile6LabTheme
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Mobile6LabTheme {
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    Start(

                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Start (modifier: Modifier = Modifier) {
    var name by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var country by remember { mutableStateOf("") }
    var age by remember { mutableFloatStateOf(18f) }
    val countries = listOf(
        stringResource(R.string.Country_for_list_1),
        stringResource(R.string.Country_for_list_2),
        stringResource(R.string.Country_for_list_3),
        stringResource(R.string.Country_for_list_4),
        stringResource(R.string.Country_for_list_5)
    )
    var expanded by remember { mutableStateOf(false) }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(stringResource(R.string.name_str_show)) },
            modifier = Modifier.fillMaxWidth()
        )
        Text(stringResource(R.string.gender_field))
        Row {
            RadioButton(
                selected = gender == stringResource(R.string.male_gender),
                onClick = { gender = context.getString(R.string.male_gender) }
            )
            Text(context.getString(R.string.male_gender), modifier = Modifier.padding(end = 16.dp))

            RadioButton(
                selected = gender == stringResource(R.string.female_gender),
                onClick = { gender = context.getString(R.string.female_gender) }
            )
            Text(context.getString(R.string.female_gender))
        }

        Text("Страна: $country")
        Box {
            OutlinedTextField(
                value = country,
                onValueChange = {},
                readOnly = true,
                label = { Text(stringResource(R.string.choose_country_text_in_field)) },
                modifier = Modifier.fillMaxWidth()
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                countries.forEach { countryOption ->
                    DropdownMenuItem(
                        text = { Text(countryOption) },
                        onClick = {
                            country = countryOption
                            expanded = false
                        }
                    )
                }
            }
            Button(
                onClick = { expanded = true },
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Text(stringResource(R.string.choose_on_butt_drop_menu_country))
            }
        }

        Text(stringResource(R.string.age_in_str_to_us_slider, age.toInt().toString()))
        Slider(
            value = age,
            onValueChange = { age = it },
            valueRange = 0f..100f,
            steps = 99, // Для целых чисел от 0 до 100
            modifier = Modifier.fillMaxWidth()
        )


        Button(
            onClick = {
                if (name.isNotEmpty() && gender.isNotEmpty() && country.isNotEmpty()) {
                    Toast.makeText(
                        context,
                        context.getString(
                            R.string.data_save_to_user,
                            name,
                            gender,
                            country,
                            age.toInt().toString()
                        ),
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(context,
                        context.getString(R.string.for_user_warn_not_all_fields), Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.name_but_save))
        }
    }
}
