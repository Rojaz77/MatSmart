package com.worldkonnect.matsmart.presentation.cities

import android.content.Context
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
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
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import com.worldkonnect.matsmart.models.City
import com.worldkonnect.matsmart.sealed.DataStateCity
import com.worldkonnect.matsmart.ui.theme.grey_fade


@Composable
fun SetCityData(viewmodel: CitiesViewmodel,navController: NavController){

    when(val result = viewmodel.response.value){
        is DataStateCity.Failure->{

            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center)
            {
                Text(
                    text = result.message,
                    fontSize = MaterialTheme.typography.h5.fontSize)
            }
        }
        is DataStateCity.Loading->{
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center)
            {
                CircularProgressIndicator()
            }
        }
        is DataStateCity.Success->{
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
fun ShowLazyList(city: MutableList<City>,navController: NavController)
{
    LazyColumn{

        items(city){city->
            CardItem(city,navController = navController)
        }

    }
}

var state by mutableStateOf(CitySelected(cityName = City()))
    private set

private @Composable
fun CardItem(city: City,navController: NavController)
{

   Card(modifier = Modifier.fillMaxSize()
                            .height(70.dp)
                            .clickable
        {
            state.cityName.Name = city.Name
            println(state.cityName.Name)
            //Toast.makeText(context,city.Name, LENGTH_SHORT)
            navController.navigate(route= "route_select")

        }


        )
    {

        Box(modifier = Modifier.fillMaxSize())
        {
            Text(text = city.Name!!,
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
