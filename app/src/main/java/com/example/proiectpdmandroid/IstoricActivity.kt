package com.example.proiectpdmandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proiectpdmandroid.room.BugetViewModel

class IstoricActivity : AppCompatActivity() {

    private lateinit var bugetViewModel : BugetViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_istoric)

        //Recyclerview
        val adapter = IstoricAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        bugetViewModel = ViewModelProvider(this).get(BugetViewModel::class.java)
        bugetViewModel.readAllData.observe(this, Observer {buger ->
            adapter.setData(buger)
        })


    }



}