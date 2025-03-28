package edu.fullerton.fz.cs411.simpleapp_spring24

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

const val LOG_TAG = "My Application"
const val COUNTER_KEY = "MyCounterKey"

class MainActivity : AppCompatActivity() {

    private lateinit var increaseBtn: Button
    private lateinit var counterText: TextView
    private lateinit var imageView: ImageView
    private lateinit var secondButton: Button

    private val myCounterViewModel: MyCounterViewModel by lazy {
        ViewModelProvider(this).get(MyCounterViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        myCounterViewModel.setCounter(savedInstanceState?.getInt(COUNTER_KEY, 0) ?: 0)

        Log.d(LOG_TAG, "onCreate was called")

        setContentView(R.layout.activity_main)

        increaseBtn = findViewById(R.id.increase)
        counterText = findViewById(R.id.counter)
        imageView = findViewById(R.id.imageView)
        increaseBtn.setOnClickListener { btn: View ->
            val button = btn as Button
            println("button ${button.text} clicked")
            var counter = myCounterViewModel.getCounter()
            counter += 1
            myCounterViewModel.setCounter(counter)
            counterText.text = counter.toString()
            imageView.setImageResource(R.drawable.man_with_hat)
        }
        counterText.text = myCounterViewModel.getCounter().toString()

        if (this.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            secondButton = findViewById(R.id.second_button)
            secondButton.setOnClickListener {
                Log.d(LOG_TAG, "Second button was clicked")
            }
        }

    }

    override fun onStart() {
        super.onStart()
        Log.d(LOG_TAG, "onStart was called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(LOG_TAG, "onResume was called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(LOG_TAG, "onPause was called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(LOG_TAG, "onStOP was called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(LOG_TAG, "onDestroy was called")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(LOG_TAG, "OnSaveInstaceState was called")
        outState?.putInt(COUNTER_KEY, myCounterViewModel.getCounter())
    }

}