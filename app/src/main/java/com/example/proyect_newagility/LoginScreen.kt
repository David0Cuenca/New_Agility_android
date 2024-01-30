package com.example.proyect_newagility

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proyect_newagility.ui.theme.Blue
import com.example.proyect_newagility.ui.theme.Primary
import model.Screens


@Composable
fun MainLogin(navigationController: NavController){
    Box(
        Modifier
            .fillMaxSize()
            .background(color = Primary)
            .padding(9.dp)
    ){
        Header(modifier = Modifier.align(Alignment.TopEnd))
        Body(modifier = Modifier.align(Alignment.Center), navigationController)
    }
}

//Head
@Composable
fun Header(modifier: Modifier) {
    Box (modifier){
        val activity = LocalContext.current as? AppCompatActivity
        Icon(imageVector = ImageVector.vectorResource(R.drawable.x),
            contentDescription = "Close App",
            tint = Blue,
            modifier = Modifier.clickable { activity?.finish() })
    }
}

//Body
@Composable
fun Body(modifier: Modifier, navigationController: NavController) {
    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        ImageLogo(modifier)
        Spacer()
        User()
        Spacer()
        Password()
        Spacer()
        BtnConfirm(navigationController)
    }
}
//Body Components
@Composable
fun ImageLogo(modifier: Modifier) {
    Image(painter = painterResource(id = R.drawable.sidebar_logo),
        contentDescription = "Logo Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(100.dp)
            .clip(CircleShape))
}



@Composable
fun User(){
    var user by rememberSaveable { mutableStateOf("") }
    TextField(
        value = user,
        onValueChange ={ user = it },
        maxLines = 1,
        singleLine = true,
        label = {Text("Usuario")},
        colors = TextFieldDefaults.colors(
            disabledTextColor = Blue,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledLabelColor = Color.Blue,
            focusedLabelColor = Color.Blue,

        ),
        modifier = Modifier.fillMaxWidth(),

    )

}

@Composable
fun Password(){
    var password by rememberSaveable { mutableStateOf("") }
    TextField(
        value = password,
        onValueChange = { password = it },
        modifier = Modifier.fillMaxWidth(),
        maxLines = 1,
        singleLine = true,
/*        leadingIcon = painterResource(id = R.drawable.user),*/
        label = {Text("Contraseña")},
        colors = TextFieldDefaults.colors(
            disabledTextColor = Blue,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledLabelColor = Color.Blue,
            focusedLabelColor = Color.Blue,))

}

@Composable
fun Spacer(){
    Spacer(modifier = Modifier.size(20.dp))
}

@Composable
fun BtnConfirm(navigationController: NavController) {

    OutlinedButton(
        onClick = {
            navigationController.navigate(Screens.Dashboard.route)
        })
    {
        Text(text = "Iniciar Sesion", color=Blue)
    }
}