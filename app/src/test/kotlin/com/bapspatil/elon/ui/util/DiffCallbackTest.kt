package com.bapspatil.elon.ui.util

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import com.bapspatil.elon.util.DiffCallback
import io.reactivex.observers.TestObserver
import io.reactivex.subjects.PublishSubject
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

/*
** Created by Bapusaheb Patil {@link https://bapspatil.com}
*/

@RunWith(MockitoJUnitRunner::class)
class DiffCallbackTest {

    private val onChangeSubject = PublishSubject.create<Int>()
    private val onMoveSubject = PublishSubject.create<Int>()
    private val onInsertedSubject = PublishSubject.create<Int>()
    private val onRemoveSubject = PublishSubject.create<Int>()

    private val diffCallback = DiffCallback()

    private lateinit var onChangeSubjectTest: TestObserver<Int>
    private lateinit var onMoveSubjectTest: TestObserver<Int>
    private lateinit var onInsertedSubjectTest: TestObserver<Int>
    private lateinit var onRemoveSubjectTest: TestObserver<Int>

    @Before
    fun setup() {
        onChangeSubjectTest = onChangeSubject.test()
        onMoveSubjectTest = onMoveSubject.test()
        onInsertedSubjectTest = onInsertedSubject.test()
        onRemoveSubjectTest = onRemoveSubject.test()
    }

    @After
    fun tearsDown() {
        onChangeSubjectTest.dispose()
        onMoveSubjectTest.dispose()
        onInsertedSubjectTest.dispose()
        onRemoveSubjectTest.dispose()
    }

    @Test
    fun `Given 2 equals lists then no difference is found`() {
        val list1 = listOf("a", "b", "c")

        compare(list1, list1)

        onChangeSubjectTest.assertNoValues()
        onMoveSubjectTest.assertNoValues()
        onInsertedSubjectTest.assertNoValues()
        onRemoveSubjectTest.assertNoValues()
    }

    @Test
    fun `Given a list with a removed item then find a removed item`() {
        val list1 = listOf("a", "b", "c")

        val value = 2

        compare(list1, list1.dropLast(value))

        onChangeSubjectTest.assertNoValues()
        onMoveSubjectTest.assertNoValues()
        onInsertedSubjectTest.assertNoValues()
        onRemoveSubjectTest.assertValue(value)
    }

    @Test
    fun `Given a list with newitems then find those new items`() {
        val list1 = listOf("a", "b", "c")

        val list2 = arrayListOf(list1)
        val toAdd = listOf("d", "e")
        list2.add(toAdd)

        compare(list1, list2)

        onChangeSubjectTest.assertNoValues()
        onMoveSubjectTest.assertNoValues()
        onInsertedSubjectTest.assertValue(toAdd.size)
        onRemoveSubjectTest.assertValue(list1.size)
    }

    @Test
    fun `Given a list with swap items then swap those new items`() {
        val list1 = listOf("a", "b", "c")

        val list2 = listOf(list1.reversed())

        compare(list1, list2)

        onChangeSubjectTest.assertNoValues()
        onMoveSubjectTest.assertNoValues()
        onInsertedSubjectTest.assertValue(1)
        onRemoveSubjectTest.assertValue(3)
    }


    @Test
    fun `Given a modified with swap  then find those modified items`() {
        val list1 = listOf("a", "b", "c")
        val list2 = listOf("b", "a", "c")

        compare(list1, list2)

        onChangeSubjectTest.assertNoValues()
        onMoveSubjectTest.assertValue(-1)
        onInsertedSubjectTest.assertNoValues()
        onRemoveSubjectTest.assertNoValues()
    }

    private fun compare(list1: List<Any>, list2: List<Any>) {
        diffCallback.compareLists(list1, list2)

        val diffFound = DiffUtil.calculateDiff(diffCallback)
        diffFound.dispatchUpdatesTo(object : ListUpdateCallback {
            override fun onChanged(position: Int, count: Int, payload: Any?) {
                onChangeSubjectTest.onNext(count)
            }

            override fun onMoved(fromPosition: Int, toPosition: Int) {
                onMoveSubjectTest.onNext(toPosition - fromPosition)
            }

            override fun onInserted(position: Int, count: Int) {
                onInsertedSubjectTest.onNext(count)
            }

            override fun onRemoved(position: Int, count: Int) {
                onRemoveSubjectTest.onNext(count)
            }
        })
    }

}