package org.subha.frequencymaper.repo

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.subha.frequencymaper.api.RepoDirectoryModel
import org.subha.frequencymaper.api.dictionary


private val PREF_NAME = "DIRECTORY"
private val KEY_NAME = "DIRECTORY"

/**
 * provide list of directory in  onSuccess
 */
fun Context.getDirectoryList(onSuccess: (directory: List<RepoDirectoryModel>) -> Unit, onError: (message : String) -> Unit) {
    val directoryList = getDirectory()
    if(directoryList.isEmpty()){
        dictionary(onSuccess = { directory ->
            saveDirectory(directory)
        }, onError = {
            onError(it)
        })
    }else{
        onSuccess(directoryList)
    }
}

/**
 * save the directory into the preference as json string format
 */
private fun Context.saveDirectory(list: List<RepoDirectoryModel>) {
    if (list.isEmpty()) return
    val gso = Gson()
    val type = object : TypeToken<List<RepoDirectoryModel>>() {}.type
    val directory = gso.toJson(list, type).toString()
    val pref = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    pref.edit().putString(KEY_NAME, directory).apply()
}

/**
 * get saved directory list from preference
 * @return List of RepoDirectoryModel or empty list
 */
private fun Context.getDirectory(): List<RepoDirectoryModel> {
    val gso = Gson()
    val type = object : TypeToken<List<RepoDirectoryModel>>() {}.type
    val pref = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    val stringDirectory = pref.getString(KEY_NAME, "")
    if (stringDirectory != null && stringDirectory != "") {
        val directory: List<RepoDirectoryModel> = gso.fromJson(stringDirectory, type)
        return directory
    }
    return ArrayList()
}