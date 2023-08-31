package com.example.proiectpdmandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val navView:NavigationView = findViewById(R.id.navView)
        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.btnCalculator -> {val intent = Intent(this, LunaActivity::class.java)
                    startActivity(intent)}
                R.id.btnLocatieBanci -> {val intent = Intent(this, ListaBanciActivity::class.java)
                    startActivity(intent)}
                R.id.btnIstoric -> {val intent = Intent(this, IstoricActivity::class.java)
                    startActivity(intent)}
                R.id.btnSetari -> {val intent = Intent(this, SetariActivity::class.java)
                    startActivity(intent)}
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}


