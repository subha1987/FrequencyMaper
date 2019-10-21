package org.subha.frequencymaper.view_model

interface IDirectoryView {

    fun onDirectoryList(list: MutableList<Directory>)
    fun onScrollToPosition(position: Int)
    fun showProgress()
    fun hideProgress()
    fun showMessage(message : String)

}