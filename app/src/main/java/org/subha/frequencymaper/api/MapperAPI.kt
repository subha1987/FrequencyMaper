package org.subha.frequencymaper.api

import retrofit2.http.GET
import rx.Observable
import subhabrata.service_caller.getRetroAdapter
import subhabrata.service_caller.getServiceCaller


private val BASE_URL = "http://a.galactio.com/"

interface MapperAPI {

    @GET("interview/dictionary-v2.json")
    fun dictionary(): Observable<DictionaryResponse>

}

private val DEFAULT_TIME_OUT: Long = 3000L

/**
 * crete a retrofit client and return API class
 */
fun getRestCaller(connectionTime: Long = DEFAULT_TIME_OUT): MapperAPI {
    val adapter = getRetroAdapter(BASE_URL, connectionTime)
    return getServiceCaller(adapter, MapperAPI::class.java)
}