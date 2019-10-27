package org.subha.frequencymaper

import org.junit.Assert
import org.junit.Test
import org.subha.frequencymaper.view_model.Directory
import org.subha.frequencymaper.view_model.DirectoryViewModelDomain

class DirectoryViewModelDomainUnitTest {

    private val directoryViewModelDomain = DirectoryViewModelDomain()
    private val directoryList: MutableList<Directory> = mutableListOf(
        Directory(word = "Hello", frequency = 4, color = android.R.color.transparent),
        Directory(word = "Hello world", frequency = 3, color = android.R.color.transparent),
        Directory(word = "quantum inventions", frequency = 8, color = android.R.color.transparent)
    )

    @Test
    fun testGetIndexListByWord_WithValidWord() {
        val isTrue =
            directoryViewModelDomain.getIndexListByWord("hello", directoryList).isNotEmpty()
        Assert.assertTrue(isTrue)
    }

    @Test
    fun testGetIndexListByWord_WithInvalidWord() {
        val isTrue = directoryViewModelDomain.getIndexListByWord("test", directoryList).isNotEmpty()
        Assert.assertFalse(isTrue)
    }


    @Test
    fun testHighlightItemByIndex_WithValidIndex(){
        val isTrue = directoryViewModelDomain.highlightItemByIndex(0,directoryList) != null
        Assert.assertTrue(isTrue)
    }


    @Test
    fun testHighlightItemByIndex_WithInvalidIndex(){
        val isTrue = directoryViewModelDomain.highlightItemByIndex(9,directoryList) != null
        Assert.assertFalse(isTrue)
    }


    @Test
    fun testIncrementFrequencyCount_WithValidIndex(){
        val isTrue = directoryViewModelDomain.highlightItemByIndex(0,directoryList) != null
        Assert.assertTrue(isTrue)
    }


    @Test
    fun testIncrementFrequencyCount_WithInvalidIndex(){
        val isTrue = directoryViewModelDomain.incrementFrequencyCount(-1,directoryList) != null
        Assert.assertFalse(isTrue)
    }

}