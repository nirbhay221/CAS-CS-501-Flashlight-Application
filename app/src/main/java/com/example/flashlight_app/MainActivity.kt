package com.example.flashlight_app

import android.content.Context
import android.hardware.camera2.CameraCharacteristics.FLASH_INFO_AVAILABLE
import android.hardware.camera2.CameraManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.SearchView
import android.widget.Switch
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.ContentInfoCompat.Flags
import java.lang.Math.abs

class MainActivity : AppCompatActivity() , GestureDetector.OnGestureListener{
    lateinit var flashLightSwitch: Switch;
    private lateinit var searchFlashLightOption:SearchView
    private lateinit var gestureDetector:GestureDetector
    var x1:Float = 0.0f
    var x2:Float = 0.0f
    var y1:Float = 0.0f
    var y2:Float = 0.0f
    companion object{
        const val MIN_DISTANCE = 150
    }
    val camManager by lazy {
        getSystemService(Context.CAMERA_SERVICE) as CameraManager
    }
    var cameraIdWithFlash : String = "0"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gestureDetector = GestureDetector(this, this)
        flashLightSwitch = findViewById(R.id.switchId)
        searchFlashLightOption = findViewById(R.id.searchView)

        try {
            val camerasList = camManager.cameraIdList
            for (cameraId in camerasList) {
                val characteristics = camManager.getCameraCharacteristics(cameraId)
                val doesCameraHasFlash = characteristics.get(FLASH_INFO_AVAILABLE)
                if (doesCameraHasFlash == true) {
                    cameraIdWithFlash = cameraId
                    break
                }
            }

            if (cameraIdWithFlash != null) {
                camManager.setTorchMode(cameraIdWithFlash!!, true)

                flashLightSwitch.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        turnOnLight()
                    } else {
                        turnOffLight()
                    }
                }

                searchFlashLightOption.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        if (query != null) {
                            val lowercaseQuery = query.lowercase()
                            if (lowercaseQuery == "on") {
                                turnOnLight()
                            } else if (lowercaseQuery == "off") {
                                turnOffLight()
                            } else {
                                showToast("Please type 'on' or 'off' for enabling or disabling Flashlight.")
                            }
                        } else {
                            showToast("Type something.")
                        }
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        return true
                    }
                })
            } else {
                showToast("Flashlight is not available on this device.")
                flashLightSwitch.isEnabled = false
                searchFlashLightOption.isEnabled = false
            }
        } catch (e: Exception) {
            showToast("An error occurred: ${e.message}")
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event != null) {
            gestureDetector.onTouchEvent(event)
        }
        when(event?.action){
            0 ->{
                x1= event.x
                y1 = event.y
            }
            1 ->
            {
                x2= event.x
                y2 = event.y
                val valueX : Float = x2 - x1
                val valueY : Float = y2 - y1

                if(abs(valueY)> MIN_DISTANCE){
                    if(y2>y1){
                        turnOffLight()
                    }
                    else{
                        turnOnLight()
                    }
                }
            }
        }
        return super.onTouchEvent(event)
    }
    fun turnOnLight(){
        camManager.setTorchMode(cameraIdWithFlash,true)
        Toast.makeText(this,"Flash is on",Toast.LENGTH_LONG).show()
        flashLightSwitch.isChecked = true;
    }
    fun turnOffLight(){
        camManager.setTorchMode(cameraIdWithFlash,false)
        Toast.makeText(this,"Flash is off",Toast.LENGTH_LONG).show()
        flashLightSwitch.isChecked = false;
    }
    private fun showToast(message:String){

        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }


    override fun onPause() {
        super.onPause()
        camManager.setTorchMode(cameraIdWithFlash,false)
    }

    override fun onDown(e: MotionEvent): Boolean {
//        TODO("Not yet implemented")
        return false
    }

    override fun onShowPress(e: MotionEvent) {
//        TODO("Not yet implemented")
    }

    override fun onSingleTapUp(e: MotionEvent): Boolean {
//        TODO("Not yet implemented")
        return false
    }

    override fun onScroll(
        e1: MotionEvent,
        e2: MotionEvent,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
//        TODO("Not yet implemented")
        return false
    }

    override fun onLongPress(e: MotionEvent) {
//        TODO("Not yet implemented")
    }

    override fun onFling(
        e1: MotionEvent,
        e2: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        val deltaX = e2.x - e1.x
        val deltaY = e2.y - e1.y
        val posVelocityX = Math.abs(velocityX)
        val posVelocityY = Math.abs(velocityY)
        val minFlingVelocity = 1000
        if(posVelocityY > minFlingVelocity){
            if(deltaY>0){
                turnOffLight()
            }
            else{
                turnOffLight()
            }
            return true
        }
        return false
    }

}