package org.subha.frequencymaper.api


data class Directory(val word : String, val frequency : Int)
data class DictionaryResponse(val dictionary : Directory)