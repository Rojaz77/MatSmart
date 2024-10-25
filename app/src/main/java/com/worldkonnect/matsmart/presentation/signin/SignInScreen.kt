package com.worldkonnect.matsmart.presentation.signin

import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.SquareCap
import com.worldkonnect.matsmart.R


@Composable
fun SignInScreen(
    state: SignInState,
    onSignInClick: () -> Unit
){
    val context = LocalContext.current
    LaunchedEffect(key1 = state.signInError){
        state.signInError?.let { error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize().background(Color.Gray)
            .padding(16.dp),
        contentAlignment = Alignment.BottomCenter,

    )
    {

        /*Image(
            modifier = Modifier
                .size(700.dp)
                .padding(16.dp)
                .clip(CircleShape),
            painter = painterResource(id = R.drawable.background_1),
            contentDescription = "Matsmart Sign in Image",
            contentScale = ContentScale.Crop
        )*/
        Button(
            onClick = onSignInClick,
            colors = ButtonDefaults.buttonColors(Color.Blue),
        )
        {
            Text(
                text = "SignIn With Google",
                color = Color.White,
            )
        }
    }



}