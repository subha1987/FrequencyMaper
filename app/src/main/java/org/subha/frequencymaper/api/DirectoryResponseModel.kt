package org.subha.frequencymaper.api


data class RepoDirectoryModel(val word : String, val frequency : Int)
data class DictionaryResponse(val dictionary : List<RepoDirectoryModel>)