package org.subha.frequencymaper

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.RecognizerIntent.*
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        tvSpeak.setOnClickListener {

        }
    }


    val REQ_CODE_SPEECH_INPUT = 15447
    /**
     * Showing google speech input dialog
     */
    private fun Context.promptSpeechInput() {
        val intent = Intent(ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(
            EXTRA_LANGUAGE_MODEL,
            LANGUAGE_MODEL_FREE_FORM
        )
        intent.putExtra(EXTRA_LANGUAGE, Locale.getDefault())
        intent.putExtra(
            RecognizerIntent.EXTRA_PROMPT,
            "Hi :) Please Speak Up"
        )
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT)
        } catch (a: ActivityNotFoundException) {
            Toast.makeText(
                this,
                "Speech Not Supported",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    /**
     * Receiving speech input
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            REQ_CODE_SPEECH_INPUT -> {
                if (resultCode == Activity.RESULT_OK && null != data) {
                    val result = data
                        .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    Log.d("Speak", "Text: " + result[0] )
                }
            }
        }
    }


}
