package com.worldkonnect.matsmart.presentation.profile

import android.widget.ToggleButton
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.RadioButton
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.worldkonnect.matsmart.CitySelected
import com.worldkonnect.matsmart.UserTypeSelected
import com.worldkonnect.matsmart.models.City
import com.worldkonnect.matsmart.models.User
import com.worldkonnect.matsmart.presentation.cities.state
import com.worldkonnect.matsmart.presentation.signin.UserData

var userSwitch by
    mutableStateOf(
        UserTypeSelected(userType = "Customer", isChecked = false, text = "User Type")
    )

@Composable
fun ProfileScreen(
    userData: UserData?,
    onSignOut: () -> Unit,
    onRoutesClick: () -> Unit,
    //viewModel: ProfileViewModel
){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if(userData?.profilePictureUrl != null) {
            AsyncImage(
                model = userData.profilePictureUrl,
                contentDescription = "Profile picture",
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        if(userData?.username != null) {
            Text(
                text = userData.username,
                textAlign = TextAlign.Center,
                fontSize = 36.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        Button(onClick = onSignOut) {
            Text(text = "Sign out")
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = userSwitch.text
            )
            Switch(checked = userSwitch.isChecked,

                    onCheckedChange = {isChecked ->
                        userSwitch = userSwitch.copy(isChecked = isChecked)
                    }
            )
            Spacer(modifier = Modifier.weight(1f))
            if(userSwitch.isChecked)
            {
                Text(text = "Driver")
                userSwitch.userType = "Driver"
            }
            else{
                Text(text = "Customer")
                userSwitch.userType = "Customer"
            }

        }
        
        Row(verticalAlignment = Alignment.CenterVertically) {
            Button(onClick = onRoutesClick ){
                Text(
                    text = "Search Route",
                    color = Color.Black
                )
            }
        }


    }
}
