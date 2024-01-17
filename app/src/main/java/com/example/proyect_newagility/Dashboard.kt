package com.example.proyect_newagility

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class Dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
    }

}
@Composable
fun DashboardLayout(){
    navbar()
    body()
    footer()
}

@Composable
fun navbar(){

}

@Composable
fun body(){

}

@Composable
fun footer() {
    TODO("Not yet implemented")
}




//Preview
@Composable
@Preview (showBackground = true)
fun DashboardLayoutPreview(){
    DashboardLayout()
}