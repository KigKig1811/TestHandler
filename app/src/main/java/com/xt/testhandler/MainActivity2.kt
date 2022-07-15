package com.xt.testhandler

import android.os.AsyncTask
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity2 : AppCompatActivity() {
    private lateinit var mProgressBar: ProgressBar
    private lateinit var mButtonStart: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        mProgressBar = findViewById(R.id.progress_bar)
        mButtonStart =  findViewById(R.id.button_start)

        mButtonStart.setOnClickListener {
            ProgressAsyncTask().execute()
        }

    }

    inner class ProgressAsyncTask: AsyncTask<Void,Int,String>(){
        override fun doInBackground(vararg p0: Void?): String {
            for (i in 0..100){
                publishProgress(i)
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException){
                    e.printStackTrace()
                }
            }
            return "DONE"
        }

        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(values[0])
        }

        override fun onPostExecute(result: String?) {
            Toast.makeText(this@MainActivity2,result,Toast.LENGTH_SHORT).show()
        }

    }


}