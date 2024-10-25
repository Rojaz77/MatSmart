package com.worldkonnect.matsmart

import com.worldkonnect.matsmart.models.User

data class UserTypeSelected(
    val isChecked: Boolean,
    val text:String,
    var userType:String
)
