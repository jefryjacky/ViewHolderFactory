package com.jefryjacky.viewholderfactory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import p.com.viewholderinstance.R

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = Adapter()
        rv_item.setHasFixedSize(true)
        adapter.submitList(listOf("item 1", "item 2", "item 3"))
        rv_item.adapter = adapter
    }
}