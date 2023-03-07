package com.zabava.drawapp

import android.app.Dialog
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.get

class MainActivity : AppCompatActivity() {

    private var drawingView: DrawingView? = null
    private var mImageButtonCurrentPaint: ImageButton? = null
    private var ibSkin : ImageButton? = null
    private var ibBlack : ImageButton? = null
    private var ibRed : ImageButton? = null
    private var ibGreen : ImageButton? = null
    private var ibBlue : ImageButton? = null
    private var ibYellow : ImageButton? = null
    private var ibPurple : ImageButton? = null
    private var ibWhite : ImageButton? = null
    private var ibRandom : ImageButton? = null

    private var palletButtons : ArrayList<ImageButton>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawingView = findViewById(R.id.drawView)
        drawingView?.setSizeForBrush(20.toFloat())

        ibSkin = findViewById(R.id.ib_skin)
        ibBlack = findViewById(R.id.ib_black)
        ibRed = findViewById(R.id.ib_red)
        ibGreen = findViewById(R.id.ib_green)
        ibBlue = findViewById(R.id.ib_blue)
        ibYellow = findViewById(R.id.ib_yellow)
        ibPurple = findViewById(R.id.ib_purple)
        ibWhite = findViewById(R.id.ib_white)
        ibRandom = findViewById(R.id.ib_random)

        palletButtons = arrayListOf(
            ibSkin!!,ibBlack!!,ibRed!!,ibGreen!!,ibBlue!!,ibYellow!!,ibPurple!!,ibWhite!!,ibRandom!!)

        for (button in palletButtons!!){
            button?.setOnClickListener{
                paintClicked(button)
            }
        }

        val linearLayoutPaintColors = findViewById<LinearLayout>(R.id.ll_paint_colors)

        mImageButtonCurrentPaint = linearLayoutPaintColors[1] as ImageButton
        mImageButtonCurrentPaint!!.setImageDrawable(
            ContextCompat.getDrawable(this,R.drawable.pallet_pressed)
        )

        val ibBrush : ImageButton = findViewById(R.id.ib_brush)
        ibBrush.setOnClickListener{
            showBrushSizeChooserDialog()
        }

    }

    private fun showBrushSizeChooserDialog(){
        var brushDialog = Dialog(this)
        brushDialog.setContentView(R.layout.dialog_brush_size)
        brushDialog.setTitle("Brush size: ")
        val smallBtn = brushDialog.findViewById<ImageButton>(R.id.ib_small_brush)
        smallBtn.setOnClickListener{
            drawingView?.setSizeForBrush(10.toFloat())
            brushDialog.dismiss()
        }
        val mediumBtn = brushDialog.findViewById<ImageButton>(R.id.ib_medium_brush)
        mediumBtn.setOnClickListener{
            drawingView?.setSizeForBrush(20.toFloat())
            brushDialog.dismiss()
        }
        val largeBtn = brushDialog.findViewById<ImageButton>(R.id.ib_large_brush)
        largeBtn.setOnClickListener{
            drawingView?.setSizeForBrush(30.toFloat())
            brushDialog.dismiss()
        }

        brushDialog.show()
    }

    private fun paintClicked(view: View) {
        if(view!== mImageButtonCurrentPaint){
            val imageButton = view as ImageButton
            val colorTag = imageButton.tag.toString()
            drawingView?.setColor(colorTag)

            imageButton.setImageDrawable(
                ContextCompat.getDrawable(this,R.drawable.pallet_pressed)
            )
            mImageButtonCurrentPaint?.setImageDrawable(
                ContextCompat.getDrawable(this,R.drawable.pallet_normal)
            )

            mImageButtonCurrentPaint = view

        }
    }
}