package com.example.flashlight_app

import android.content.pm.ActivityInfo
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*
import androidx.test.espresso.matcher.ViewMatchers.withId

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.flashlight_app", appContext.packageName)
    }

    @Test
    fun flingToToggleFlashlight() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        Espresso.onView(ViewMatchers.withId(R.id.switchId)).check(ViewAssertions.matches(ViewMatchers.isNotChecked()))
        Espresso.onView(ViewMatchers.withId(R.id.switchId)).perform(ViewActions.swipeUp())
        Espresso.onView(ViewMatchers.withId(R.id.switchId)).check(ViewAssertions.matches(ViewMatchers.isChecked()))

        Espresso.onView(ViewMatchers.withId(R.id.switchId)).perform(ViewActions.swipeDown())
        Espresso.onView(ViewMatchers.withId(R.id.switchId)).check(ViewAssertions.matches(ViewMatchers.isNotChecked()))

        activityScenario.close()
    }

}
