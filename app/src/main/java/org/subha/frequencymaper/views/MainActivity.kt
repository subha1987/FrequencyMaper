package org.subha.frequencymaper.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.subha.frequencymaper.R
import org.subha.frequencymaper.view_model.Directory
import org.subha.frequencymaper.view_model.DirectoryViewModel
import org.subha.frequencymaper.view_model.IDirectoryView


class MainActivity : AppCompatActivity(), IDirectoryView {


    private val directoryViewModel by lazy { DirectoryViewModel(this, this) }

    private lateinit var directoryAdapter: DirectoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initAdapter()
    }

    override fun onStart() {
        super.onStart()
        directoryViewModel.onStart()
    }

    private fun initAdapter() {
        directoryAdapter = DirectoryAdapter()
        rvDirectory.layoutManager = LinearLayoutManager(this)
        rvDirectory.adapter = directoryAdapter
    }


    private fun initView() {
        progressBar.visibility = View.GONE

        tvSpeak.setOnClickListener {
            directoryViewModel.onSpeakClicked()
        }

        iv_mic.setOnClickListener {
            directoryViewModel.onSpeakClicked()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        directoryViewModel.onActivityResult(requestCode, resultCode, data)
    }


    override fun onDirectoryList(list: MutableList<Directory>) {
        directoryAdapter.setData(list)
    }

    override fun onScrollToPosition(position: Int) {
        rvDirectory.layoutManager?.scrollToPosition(position)
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun showMessage(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        directoryViewModel.onPause()
    }

}
