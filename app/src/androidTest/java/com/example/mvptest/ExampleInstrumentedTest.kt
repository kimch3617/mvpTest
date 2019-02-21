package com.example.mvptest

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented com.example.deliveryhero.test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under com.example.deliveryhero.test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.example.deliveryhero", appContext.packageName)
    }
}
