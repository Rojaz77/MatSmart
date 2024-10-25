package com.worldkonnect.matsmart.presentation.routes

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.worldkonnect.matsmart.CitySelected
import com.worldkonnect.matsmart.models.City
import com.worldkonnect.matsmart.models.Route
import com.worldkonnect.matsmart.presentation.cities.state
import com.worldkonnect.matsmart.sealed.DataStateRoute

class RoutesViewmodel:ViewModel() {
    val response: MutableState<DataStateRoute> = mutableStateOf(DataStateRoute.Empty)

    init {
        fetchDataFromFirebase()
    }

    private fun fetchDataFromFirebase() {
        val templist = mutableListOf<Route>()
        response.value = DataStateRoute.Loading
        println(state.cityName.Name)
        FirebaseDatabase.getInstance().getReference(state.cityName.Name)
            .addListenerForSingleValueEvent(
                object : ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for (DataSnap in snapshot.children){
                            val cityRouteItem = DataSnap.getValue(Route::class.java)
                            if(cityRouteItem != null)
                                templist.add(cityRouteItem)
                        }
                        response.value = DataStateRoute.Success(templist)
                    }

                    override fun onCancelled(error: DatabaseError) {
                        response.value = DataStateRoute.Failure(error.message)
                    }

                }
            )


    }
}