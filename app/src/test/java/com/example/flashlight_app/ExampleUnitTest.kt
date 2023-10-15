package com.example.flashlight_app

import org.junit.Test

import org.junit.Assert.*
import com.example.flashlight_app.MainActivity

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testTurnOnLight() {
        lateinit var mainActivity: MainActivity
        mainActivity.turnOnLight()
        assertTrue(mainActivity.flashLightSwitch.isChecked)
    }

    @Test
    fun testTurnOffLight() {
        lateinit var mainActivity: MainActivity
        mainActivity.turnOffLight()
        assertFalse(mainActivity.flashLightSwitch.isChecked)
    }

}