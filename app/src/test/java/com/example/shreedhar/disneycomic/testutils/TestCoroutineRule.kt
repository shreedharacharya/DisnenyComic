package com.example.shreedhar.disneycomic.testutils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class TestCoroutineRule : TestRule {

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testCoroutineDispatcher = UnconfinedTestDispatcher()

    val testCoroutineScope = TestScope(testCoroutineDispatcher)

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun apply(base: Statement, description: Description?) = object : Statement() {
        @Throws(Throwable::class)
        override fun evaluate() {
            Dispatchers.setMain(testCoroutineDispatcher)

            base.evaluate()

            Dispatchers.resetMain()
        }
    }

    fun runTest(block: suspend TestScope.() -> Unit) =
        testCoroutineScope.runTest { block() }
}
