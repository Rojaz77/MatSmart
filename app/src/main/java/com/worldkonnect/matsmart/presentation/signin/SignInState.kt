package com.worldkonnect.matsmart.presentation.signin

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)
