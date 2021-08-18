package com.example.myapplication

import android.R.attr
import android.content.Context
import android.graphics.Bitmap
import android.icu.text.AlphabeticIndex
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.MotionEvent
import android.widget.ImageView
import android.widget.TextView
import android.view.View
import android.graphics.Matrix
import android.graphics.drawable.BitmapDrawable
import androidx.core.graphics.get


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val vib = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        val img = findViewById<ImageView>(R.id.imageView2)
        val label = findViewById<TextView>(R.id.textView2)

        val bitmap = (img.drawable as BitmapDrawable).bitmap
        img.layoutParams.width = 201//.toDouble() // img.width.toDouble()
        img.layoutParams.height = 520//.toDouble()// / img.height.toDouble();
        img.setOnTouchListener{ v: View, e: MotionEvent ->
            when(e.action){
                MotionEvent.ACTION_DOWN -> {
                    val inverse = Matrix()
                    v.matrix.invert(inverse);
                    val touchPoint = floatArrayOf(e.getX(), e.getY())
                    inverse.mapPoints(touchPoint)
                    val xCoord = Integer.valueOf((touchPoint[0]).toInt())
                    val yCoord = Integer.valueOf((touchPoint[1]).toInt())

                    label.setText("$xCoord, $yCoord ${bitmap.getPixel(xCoord, yCoord)}")
                }
            }
            true
        }
    }
}