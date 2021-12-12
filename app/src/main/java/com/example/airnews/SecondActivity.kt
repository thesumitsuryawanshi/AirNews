package com.example.airnews

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.airnews.Fragments.F_Keyword
import com.example.airnews.databinding.ActivitySecondBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondActivity : AppCompatActivity() {

    lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        passKeywordToFragment()
    }

    fun passKeywordToFragment() {

        val keyword = intent.getStringExtra("keyword")!!

        val a = supportFragmentManager
        a.beginTransaction()
            .add(binding.fragmentContainerView.id, F_Keyword(keyword))
            .commit()
    }

}