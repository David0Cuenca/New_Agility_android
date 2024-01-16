package com.example.proyect_newagility

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


class LoginScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)
    }
}

@Composable
fun MainLogin(){
    Box(
        Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.primary))
            .padding(9.dp)
    ){
        Header(modifier = Modifier.align(Alignment.TopStart))
        Body(modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun Body(modifier: Modifier) {
    Row(modifier) {
        ImageLogo()
    }
}

@Composable
fun ImageLogo() {
    Image(painter = painterResource(id = R.drawable.sidebar_logo),
        contentDescription = "Logo Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(100.dp)
            .clip(CircleShape))
}

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
