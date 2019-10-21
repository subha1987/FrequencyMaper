package org.subha.frequencymaper.view_model

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.speech.RecognizerIntent
import android.util.Log
import android.widget.Toast
import org.subha.frequencymaper.R
import org.subha.frequencymaper.repo.getDirectoryList
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList


/**
 * Contains All Business Logic
 */
class DirectoryViewModelDomain {


    fun refreshDirectorySelection(directoryList: MutableList<Directory>) {
        directoryList?.forEach { directory ->
            directory.color = android.R.color.transparent
        }
    }

    /*
     * sort the directory list in descending order
     */
    fun sortByFrequencyDescending(directoryList: MutableList<Directory>) {
        directoryList.sortWith(Comparator { d1, d2 -> d2.frequency.compareTo(d1.frequency) })
    }

    /**
     * search the list by word
     * @return index if word found in directoryList else -1
     */
    fun getIndexByWord(text: String, directory: MutableList<Directory>): Int {
        var index = -1

        for (i in directory.indices) {
            if (directory[i].word.toLowerCase() == text) {
                val frequency = directory[i].frequency + 1
                directory[i].frequency = frequency
                directory[i].color = R.color.colorGreenLite
                index = i
            }
        }

        return index
    }

}


class DirectoryViewModel(val iDirectoryView: IDirectoryView, val activity: Activity) {

    private val domain = DirectoryViewModelDomain()

    private var isViewLive = false
    private var directory: MutableList<Directory> = ArrayList()

    fun onSpeakClicked() {
        activity.promptSpeechInput()
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            REQ_CODE_SPEECH_INPUT -> {
                if (resultCode == Activity.RESULT_OK && null != data) {
                    val result = data
                        .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    if (result != null && result.isNotEmpty()) {
                        processWord(word = result[0])
                    }
                }
            }
        }
    }

    private fun processWord(word: String) {
        domain.refreshDirectorySelection(directory)
        val index = domain.getIndexByWord(word, directory)


        if (index != -1) {
            emmitDirectList(index)
        } else {
            emmitDirectList(0)
            showMessage("No match found")
        }
    }

    fun onStart() {
        isViewLive = true
        hideProgress()
        getDirectoryList()
    }

    private fun emmitDirectList(index: Int = 0) {
        domain.sortByFrequencyDescending(directory)
        if (isViewLive) {
            iDirectoryView.onDirectoryList(directory)
            iDirectoryView.onScrollToPosition(index)
        }
    }

    private fun showProgress() {
        if (isViewLive) {
            iDirectoryView.showProgress()
        }
    }

    private fun hideProgress() {
        if (isViewLive) {
            iDirectoryView.hideProgress()
        }
    }

    private fun showMessage(message: String) {
        if (isViewLive) {
            iDirectoryView.showMessage(message)
        }
    }


    private fun getDirectoryList() {
        showProgress()
        activity.getDirectoryList({
            it.forEach { repoDirectory ->
                val mDirectory =
                    Directory(word = repoDirectory.word, frequency = repoDirectory.frequency)
                directory.add(mDirectory)
            }
            emmitDirectList()
            hideProgress()
        }, { errorMessage ->
            showMessage(errorMessage)
        })
    }


    fun onPause() {
        isViewLive = false
    }


    val REQ_CODE_SPEECH_INPUT = 15447
    /**
     * Showing google speech input dialog
     */
    private fun Activity.promptSpeechInput() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        intent.putExtra(
            RecognizerIntent.EXTRA_PROMPT,
            "Hi :) Please Speak Up"
        )
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT)
        } catch (a: Exception) {
            Toast.makeText(
                this,
                "Speech Not Supported",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

}