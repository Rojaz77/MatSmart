package com.worldkonnect.matsmart.presentation.rides

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.worldkonnect.matsmart.CitySelected
import com.worldkonnect.matsmart.RideSelected
import com.worldkonnect.matsmart.models.City
import com.worldkonnect.matsmart.models.Ride
import com.worldkonnect.matsmart.presentation.cities.CitiesViewmodel
import com.worldkonnect.matsmart.sealed.DataStateCity
import com.worldkonnect.matsmart.sealed.DataStateRide
import com.worldkonnect.matsmart.ui.theme.grey_fade


@Composable
fun SetAvailableRidesData(viewmodel: RidesViewModel, navController: NavController){

    when(val result = viewmodel.response.value){
        is DataStateRide.Failure->{

            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center)
            {
                Text(
                    text = result.message,
                    fontSize = MaterialTheme.typography.h5.fontSize)
            }
        }
        is DataStateRide.Loading->{
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center)
            {
                CircularProgressIndicator()
            }
        }
        is DataStateRide.Success->{
            ShowLazyList(result.data,navController = navController)

        }

        //is DataStateCity.Failure->{}

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
fun ShowLazyList(ride: MutableList<Ride>, navController: NavController)
{
    LazyColumn{

        items(ride){ride->
            CardItem(ride,navController = navController)
        }

    }
}

var ride_State by mutableStateOf(RideSelected(rideName = Ride()))
    private set

private @Composable
fun CardItem(ride: Ride, navController: NavController)
{

    Card(modifier = Modifier.fillMaxSize().height(70.dp)
        .clickable
        {
            ride_State.rideName.Name = ride.Name
            println(ride_State.rideName.Name)
            //Toast.makeText(context,city.Name, LENGTH_SHORT)
            navController.navigate(route= "ride_view")

        }


    )
    {

        Box(modifier = Modifier.fillMaxSize())
        {
            Text(text = ride.Name!!,
                fontSize = MaterialTheme.typography.h5.fontSize,
                modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth().background(
                    grey_fade
                ),
                textAlign = TextAlign.Center,
                color = Color.White

            )
        }
    }

}