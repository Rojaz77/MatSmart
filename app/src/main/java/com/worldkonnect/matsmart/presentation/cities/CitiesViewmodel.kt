package com.worldkonnect.matsmart.presentation.cities

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.worldkonnect.matsmart.CitySelected
import com.worldkonnect.matsmart.models.City
import com.worldkonnect.matsmart.models.Route
import com.worldkonnect.matsmart.sealed.DataStateCity

class CitiesViewmodel:ViewModel() {

    val response: MutableState<DataStateCity> = mutableStateOf(DataStateCity.Empty)


    init {
        fetchDataFromFirebase()
    }

    private fun fetchDataFromFirebase() {
        val templist = mutableListOf<City>()
        response.value = DataStateCity.Loading
        FirebaseDatabase.getInstance().getReference("Cities")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (DataSnap in snapshot.children){
                        val routeItem = DataSnap.getValue(City::class.java)
                        if(routeItem != null)
                            templist.add(routeItem)
                    }
                    response.value = DataStateCity.Success(templist)
                }

                override fun onCancelled(error: DatabaseError) {
                    response.value = DataStateCity.Failure(error.message)
                }

            }
            )
    }

}