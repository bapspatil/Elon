package com.bapspatil.elon.rule

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule
import org.junit.rules.TestRule

/*
** Created by Bapusaheb Patil {@link https://bapspatil.com}
*/

open class BaseRule {

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val schedulerRule: TestRule = SchedulerRule()

}