package com.worldkonnect.matsmart.presentation.stage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.worldkonnect.matsmart.RideSelected
import com.worldkonnect.matsmart.StageSelected
import com.worldkonnect.matsmart.UserTypeSelected
import com.worldkonnect.matsmart.models.Ride
import com.worldkonnect.matsmart.models.Route
import com.worldkonnect.matsmart.models.Stage
import com.worldkonnect.matsmart.presentation.profile.userSwitch
import com.worldkonnect.matsmart.presentation.rides.RidesViewModel
import com.worldkonnect.matsmart.presentation.routes.CardItem
import com.worldkonnect.matsmart.presentation.routes.route_State
import com.worldkonnect.matsmart.sealed.DataStateRide
import com.worldkonnect.matsmart.sealed.DataStateStage
import com.worldkonnect.matsmart.ui.theme.grey_fade


@Composable
fun setStageData(viewmodel: StageViewModel){

    when(val result = viewmodel.response.value){
        is DataStateStage.Failure->{

            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center)
            {
                Text(
                    text = result.message,
                    fontSize = MaterialTheme.typography.h5.fontSize)
            }
        }
        is DataStateStage.Loading->{
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center)
            {
                CircularProgressIndicator()
            }
        }
        is DataStateStage.Success->{
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {
                ShowLazyList(result.data)
            }


        }
        else->{
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center)
            {
                Text(
                    text = "Error fetching data",
                    fontSize = MaterialTheme.typography.h5.fontSize)
            }
        }
    }
}


@Composable
fun ShowLazyList(stage: MutableList<Stage>)
{

}





