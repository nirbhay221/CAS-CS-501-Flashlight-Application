package com.example.flashlight_app

import android.widget.Toast
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class flashLightViewModel (private val savedStateHandle: SavedStateHandle): ViewModel() {
    private var isFlashlightOn: Boolean = false

    fun isFlashlightOn(): Boolean {
        return isFlashlightOn
    }

    fun setFlashlightState(on: Boolean) {
        isFlashlightOn = on
    }

    fun subtractVariables(x: Float, y: Float): Float {
        return x - y
    }

    fun lowerCaseQuery(x: String): String {
        return x.toLowerCase()
    }

}