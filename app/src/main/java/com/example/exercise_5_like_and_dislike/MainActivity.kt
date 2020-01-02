package com.example.exercise_5_like_and_dislike

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var countViewModel: CountViewModel

    lateinit var sharedPreferences: SharedPreferences

    lateinit var imglike:ImageView
    lateinit var imgdislike:ImageView
    lateinit var txtlike:TextView
    lateinit var txtdislike:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        countViewModel = ViewModelProviders.of(this).get(CountViewModel::class.java)

        sharedPreferences = getPreferences(Context.MODE_PRIVATE)

        imglike=findViewById(R.id.imgLike)
        imgdislike=findViewById(R.id.imgDislike)
        txtlike=findViewById(R.id.txtLike)
        txtdislike=findViewById(R.id.txtDislike)

        imglike.setOnClickListener {
            countViewModel.countLike++
            txtlike.text = countViewModel.countLike.toString()
        }

        imgdislike.setOnClickListener {
            countViewModel.countDislike++
            txtdislike.text = countViewModel.countDislike.toString()
        }
    }

    override fun onStart() {
        Log.d("MainActivity" , "onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d("MainActivity" , "onResume")
        countViewModel.countLike = sharedPreferences.getInt(getString(R.string.like), 0)//retrieve value

        countViewModel.countDislike = sharedPreferences.getInt(getString(R.string.dislike), 0)

        txtlike.text = countViewModel.countLike.toString()
        txtdislike.text = countViewModel.countDislike.toString()
        super.onResume()
    }

    override fun onPause() {
        Log.d("MainActivity" , "onPause")
        with(sharedPreferences.edit()){
            putInt(getString(R.string.like), countViewModel.countLike)//store value
            putInt(getString(R.string.dislike), countViewModel.countDislike)
            commit()
        }
        super.onPause()
    }

    override fun onStop() {
        Log.d("MainActivity" , "onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("MainActivity" , "onDestroy")
        super.onDestroy()
    }

    fun clearData(v: View){
        countViewModel.countLike = 0
        txtlike.text = countViewModel.countLike.toString()
        countViewModel.countDislike = 0
        txtdislike.text = countViewModel.countDislike.toString()
    }
}
