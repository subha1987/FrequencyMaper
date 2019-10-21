package org.subha.frequencymaper.api

import android.content.Context
import android.util.Log
import com.google.gson.JsonObject
import org.subha.frequencymaper.utility.isNetworkAvailable
import subhabrata.service_caller.applySchedulers

/**
 * This method is used do the network request to get list of directory data from server
 */
fun Context.dictionary(
    onSuccess: (dictionary: List<RepoDirectoryModel>) -> Unit,
    onError: (message: String) -> Unit
) {

    if (isNetworkAvailable()) {
        onError("No Network")
    }

    getRestCaller(connectionTime = 3000).dictionary().compose(applySchedulers<DictionaryResponse>())
        .subscribe({ response ->
            if (response != null) {
                onSuccess(response.dictionary)
            } else {
                onSuccess(ArrayList())
            }
        }, { error ->
            Log.d("Error:" , Log.getStackTraceString(error))
            onError(error?.message ?: "Network error : " + Log.getStackTraceString(error) ?: "")
        })
}