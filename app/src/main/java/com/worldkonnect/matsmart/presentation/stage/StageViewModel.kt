package com.worldkonnect.matsmart.presentation.stage

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.worldkonnect.matsmart.models.Route
import com.worldkonnect.matsmart.models.Stage
import com.worldkonnect.matsmart.presentation.cities.state
import com.worldkonnect.matsmart.presentation.routes.route_State
import com.worldkonnect.matsmart.sealed.DataStateRoute
import com.worldkonnect.matsmart.sealed.DataStateStage

class StageViewModel:ViewModel() {
    val response: MutableState<DataStateStage> = mutableStateOf(DataStateStage.Empty)
    init {
        fetchDataFromFirebase()
    }
    private fun fetchDataFromFirebase() {
        val templist = mutableListOf<Stage>()
        response.value = DataStateStage.Loading
        println(route_State.routeName.Name)
            FirebaseDatabase.getInstance().getReference(route_State.routeName.Name)
                .addListenerForSingleValueEvent(
                    object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            for (DataSnap in snapshot.children){
                                val routeStageItem = DataSnap.getValue(Stage::class.java)
                                if(routeStageItem != null)
                                    templist.add(routeStageItem)
                            }
                            response.value = DataStateStage.Success(templist)
                        }

                        override fun onCancelled(error: DatabaseError) {
                            response.value = DataStateStage.Failure(error.message)
                        }

                    }
                )




    }
}