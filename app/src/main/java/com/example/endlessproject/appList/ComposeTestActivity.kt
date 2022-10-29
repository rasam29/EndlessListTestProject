package com.example.endlessproject.appList

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class ComposeTestActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            mainComponent()
        }
    }
}

@Composable
@Preview("main page")
fun mainComponent() {
    Row {
        Text("salam")
        Text("Bye")
        Text("Hi")
    }

    Column {
        Text("Highway")
        Text("test")
        Text("Highway")
    }
}
