package com.worldkonnect.matsmart.presentation.rides

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.worldkonnect.matsmart.models.Ride
import com.worldkonnect.matsmart.presentation.routes.route_State
import com.worldkonnect.matsmart.sealed.DataStateRide

class RidesViewModel: ViewModel() {
    val response: MutableState<DataStateRide> = mutableStateOf(DataStateRide.Empty)

    init {
        fetchDataFromFirebase()
    }

    private fun fetchDataFromFirebase() {
        val templist = mutableListOf<Ride>()
        response.value = DataStateRide.Loading
        route_State.routeName.Name
        FirebaseDatabase.getInstance().getReference("BuruburuMatatus")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (DataSnap in snapshot.children){
                        val availableRides = DataSnap.getValue(Ride::class.java)
                        if(availableRides != null)
                            templist.add(availableRides)
                    }
                    response.value = DataStateRide.Success(templist)
                }

                override fun onCancelled(error: DatabaseError) {
                    response.value = DataStateRide.Failure(error.message)
                }

            })
    }
}

