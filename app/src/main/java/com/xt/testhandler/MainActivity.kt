package com.xt.testhandler

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private lateinit var mHandler: Handler
    private val MSG_UPDATE_NUMBER = 100
    private val MSG_UPDATE_NUMBER_DONE = 101

    private lateinit var mTextNumber: TextView
    private lateinit var mButtonStart: Button
    private var mIsCounting = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        listenerHandler()

        mButtonStart.setOnClickListener {
            if (!mIsCounting) countNumbers()
        }
    }

    private fun initViews() {
        mTextNumber = findViewById(R.id.text_number)
        mButtonStart = findViewById(R.id.button_count)
    }

    private fun countNumbers() {
        Thread {
            for (i in 0..10) {
                val message = Message()
                message.what = MSG_UPDATE_NUMBER
                message.arg1 = i
                mHandler.sendMessage(message)
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
            mHandler.sendEmptyMessage(MSG_UPDATE_NUMBER_DONE)
        }.start()
    }

    private fun listenerHandler() {
        mHandler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                when (msg.what) {
                    MSG_UPDATE_NUMBER -> {
                        mIsCounting = true
                        mTextNumber.text = msg.arg1.toString()
                    }
                    MSG_UPDATE_NUMBER_DONE -> {
                        mTextNumber.text = "Done!"
                        mIsCounting = false
                    }
                    else -> {}
                }
            }
        }
    }

}