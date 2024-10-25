package com.worldkonnect.matsmart.presentation.routes

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
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.worldkonnect.matsmart.RouteSelected
import com.worldkonnect.matsmart.UserTypeSelected
import com.worldkonnect.matsmart.models.City
import com.worldkonnect.matsmart.models.Route
import com.worldkonnect.matsmart.presentation.cities.state
import com.worldkonnect.matsmart.presentation.profile.userSwitch
import com.worldkonnect.matsmart.sealed.DataStateCity
import com.worldkonnect.matsmart.sealed.DataStateRoute
import com.worldkonnect.matsmart.ui.theme.grey_fade

@Composable
fun SetRouteData(viewmodel: RoutesViewmodel,navController: NavController,){
    when(val resultRoute = viewmodel.response.value){
        is DataStateRoute.Failure->{
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center)
            {
                Text(
                    text = resultRoute.messageRoute,
                    fontSize = MaterialTheme.typography.h5.fontSize)
            }

        }
        is DataStateRoute.Loading->{
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center)
            {
                CircularProgressIndicator()
            }
        }
        is DataStateRoute.Success->{
            ShowLazyList(resultRoute.dataRoute,navController = navController)

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
fun ShowLazyList(route: MutableList<Route>,navController: NavController) {
    LazyColumn{
        items(route){route->
            CardItem(route,navController = navController)
        }
    }
}
var route_State by mutableStateOf(RouteSelected(routeName = Route()))

@Composable
fun CardItem(route: Route,navController: NavController)
{
    Card(modifier = Modifier.fillMaxSize().height(150.dp)
        .clickable
             {

                 route_State.routeName.Name = route.Name
                 println(route_State.routeName.Name)
                 println(userSwitch.userType)
                 //Toast.makeText(context,city.Name, LENGTH_SHORT)
                 if (userSwitch.userType == "Customer"){
                     navController.navigate(route= "map")
                 }
                 else{
                     navController.navigate(route= "map")
                 }

             }
         )
    {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(text = route.Name!!,
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