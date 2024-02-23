package edu.fullerton.fz.cs411.simpleapp_spring24

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.drawable.toDrawable

class MainActivity : AppCompatActivity() {

    private var counter: Int = 0

    private lateinit var increaseBtn: Button
    private lateinit var counterText: TextView
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        increaseBtn = findViewById(R.id.increase)
        counterText = findViewById(R.id.counter)
        imageView = findViewById(R.id.imageView)
        increaseBtn.setOnClickListener { btn: View ->
            val button = btn as Button
            println("button ${button.text} clicked")
            counter += 1
            counterText.text = counter.toString()
            imageView.setImageResource(R.drawable.man_with_hat)
        }
    }

}