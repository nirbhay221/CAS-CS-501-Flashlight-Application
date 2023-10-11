package com.example.flashlight_app

import android.view.KeyEvent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.pressImeActionButton
import androidx.test.espresso.matcher.ViewMatchers.withId

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    private lateinit var scenario: ActivityScenario<MainActivity>
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.flashlight_app", appContext.packageName)
    }

    @Test
    fun textLightOn(){
        scenario = launch(MainActivity::class.java)

        Espresso.onView(withId(R.id.searchView)).perform(ViewActions.typeText("on"),
            ViewActions.pressKey(KeyEvent.KEYCODE_ENTER))
        Thread.sleep(5000)
        scenario.close()
    }

    @Test
    fun textLightOff(){
        scenario = launch(MainActivity::class.java)

        Espresso.onView(withId(R.id.searchView)).perform(ViewActions.typeText("off"),
            ViewActions.pressKey(KeyEvent.KEYCODE_ENTER))

        scenario.close()
    }
}