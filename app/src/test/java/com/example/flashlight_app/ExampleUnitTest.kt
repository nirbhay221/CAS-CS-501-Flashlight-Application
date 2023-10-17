package com.example.flashlight_app

import androidx.lifecycle.SavedStateHandle
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    private lateinit var viewModel: flashLightViewModel

    @Before
    fun setup() {
        // Create an instance of the ViewModel with a mock SavedStateHandle
        val savedStateHandle = SavedStateHandle()
        viewModel = flashLightViewModel(savedStateHandle)
    }
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    @Test
    fun testSubtractVariables() {
        // Test the subtractVariables function
        val result = viewModel.subtractVariables(5.0f, 3.0f)
        assert(result == 2.0f)
    }

    @Test
    fun testLowerCaseQuery() {
        // Test the lowerCaseQuery function
        val result = viewModel.lowerCaseQuery("AbCdEfG")
        assert(result == "abcdefg")
    }
}