package com.example.proyect_newagility

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyect_newagility.R.color.primary
import com.example.proyect_newagility.R.color.secundary
import com.example.proyect_newagility.R.color.blue
import com.example.proyect_newagility.R.color.pink


class LoginScreen : AppCompatActivity() {
    private val secondaryColor = R.color.secundary
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)
    }
}

//Main Layout
@Composable
fun MainLogin(){
    Box(
        Modifier
            .fillMaxSize()
            .background(color = colorResource(id = primary))
            .padding(9.dp)
    ){
        Header(modifier = Modifier.align(Alignment.TopEnd))
        Body(modifier = Modifier.align(Alignment.Center))
    }
}

//Head
@Composable
fun Header(modifier: Modifier) {
    Box (modifier){
        val activity = LocalContext.current as? Activity
        Icon(imageVector = ImageVector.vectorResource(R.drawable.x),
            contentDescription = "Close App",
            tint = colorResource(id = R.color.blue),
            modifier = Modifier.clickable { activity?.finish() })
    }
}

//Body
@Composable
fun Body(modifier: Modifier) {
    val action = true
    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        ImageLogo(modifier)
        Spacer()
        User()
        Spacer()
        Password()
        Spacer()
        BtnConfirm(onClick = action)
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



@OptIn(ExperimentalMaterial3Api::class)
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
            disabledTextColor = colorResource(id = blue),
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
            disabledTextColor = colorResource(id = blue),
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
fun BtnConfirm(onClick: Boolean) {

    OutlinedButton(
        onClick = {
            onClick })
    {
        Text(text = "Iniciar Sesion", color = colorResource(id = blue))
    }
}

//Preview of the Main Layout
@Preview (showBackground = true)
@Composable
fun MainLoginPreview(){
    MainLogin()
}

/*
val (Logo, User, Password)=createRefs()
val TopGuide = createGuidelineFromTop(0.1f)
val StartGuide = createGuidelineFromStart(0.15f)
val EndGuide = createGuidelineFromEnd(0.15f)
val BottomGuide = createGuidelineFromBottom(0.1f)

Box(modifier = Modifier
.size(100.dp)
.constrainAs(Logo) {
    top.linkTo(TopGuide)
    start.linkTo(StartGuide)
    end.linkTo(EndGuide)
})
{
    Image(painter = painterResource(id = R.drawable.sidebar_logo), contentDescription = "LogoLogin")
    Spacer(modifier = Modifier.size(20.dp))
    Text(text = "TEXTO", color = (colorResource(id = R.color.secundary)))
}
Box(modifier = Modifier
.constrainAs(User){
    top.linkTo(Logo.bottom)
    start.linkTo(StartGuide)
    end.linkTo(EndGuide)
})
Row (Modifier
.fillMaxWidth()){

}*/
