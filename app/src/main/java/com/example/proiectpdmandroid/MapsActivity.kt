package com.example.proiectpdmandroid

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
   lateinit var tvSucursaleMaps : TextView
    lateinit var ivLogoMaps : ImageView
    private lateinit var map: GoogleMap

    private lateinit var denumire: String

    private val LOCATION_PERMISSION_REQUEST=1

    private fun getLocationAccess() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            map.isMyLocationEnabled = true
        }
        else
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST)
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == LOCATION_PERMISSION_REQUEST) {
            if (grantResults.contains(PackageManager.PERMISSION_GRANTED)) {
                map.isMyLocationEnabled = true
            }
            else {
                Toast.makeText(this, getString(R.string.eroare_locatie_curenta), Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        denumire = intent.getStringExtra("denumire").toString()
        tvSucursaleMaps = findViewById(R.id.tvMapsSucursale)
        tvSucursaleMaps.setText(denumire)

        ivLogoMaps = findViewById(R.id.ivLogoBancaMaps)
        when (denumire) {
            getString(R.string.raiffeisen) -> {
                ivLogoMaps.setImageDrawable(getResources().getDrawable(R.drawable.raiffeisen))
            }
            getString(R.string.bcr) -> {
                ivLogoMaps.setImageDrawable(getResources().getDrawable(R.drawable.bcr))
            }
            getString(R.string.brd) -> {
                ivLogoMaps.setImageDrawable(getResources().getDrawable(R.drawable.brd))
            }
            getString(R.string.alpha_bank) -> {
                ivLogoMaps.setImageDrawable(getResources().getDrawable(R.drawable.alphabank))
            }
        }
    }
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        when(denumire){
            getString(R.string.alpha_bank) -> {

                val m1 = LatLng(44.46276040422609, 26.076304159178527)
                map.addMarker(MarkerOptions().position(m1).title(getString(R.string.alpha_bank)).snippet(getString(R.string.locatie_m1)))
                val m2 = LatLng(44.44963692983101, 26.099607792296002)
                map.addMarker(MarkerOptions().position(m2).title(getString(R.string.alpha_bank)).snippet(getString(R.string.locatie_m2)))
                val m3 = LatLng(44.44555636626071, 26.134515888635057)
                map.addMarker(MarkerOptions().position(m3).title(getString(R.string.alpha_bank)).snippet(getString(R.string.locatie_m3)))
                val m4 = LatLng(44.434479113902974, 26.092667001211048)
                map.addMarker(MarkerOptions().position(m4).title(getString(R.string.alpha_bank)).snippet(getString(R.string.locatie_m4)))
                val m5 = LatLng(44.43287565339494, 26.106752724295227)
                map.addMarker(MarkerOptions().position(m5).title(getString(R.string.alpha_bank)).snippet(getString(R.string.locatie_m5)))
            }
            getString(R.string.bcr) -> {
                val m6 = LatLng(44.50042858687058, 26.02989532397145)
                map.addMarker(MarkerOptions().position(m6).title(getString(R.string.bcr)).snippet(getString(R.string.locatie_m6)))
                val m7 = LatLng(44.47600910333635, 26.048588292704757)
                map.addMarker(MarkerOptions().position(m7).title(getString(R.string.bcr)).snippet(getString(R.string.locatie_m7)))
                val m8 = LatLng(44.4574565864149, 26.12830130173571)
                map.addMarker(MarkerOptions().position(m8).title(getString(R.string.bcr)).snippet(getString(R.string.locatie_m8)))
                val m9 = LatLng(44.440855502073845, 26.106797997787588)
                map.addMarker(MarkerOptions().position(m9).title(getString(R.string.bcr)).snippet(getString(R.string.locatie_m9)))
                val m10 = LatLng(44.39108625388125, 26.118071404568273)
                map.addMarker(MarkerOptions().position(m10).title(getString(R.string.bcr)).snippet(getString(R.string.locatie_m10)))
            }
            getString(R.string.brd) -> {
                val m11 = LatLng(44.48863372636098, 26.038607203495257)
                map.addMarker(MarkerOptions().position(m11).title(getString(R.string.brd)).snippet(getString(R.string.locatie_m11)))
                val m12 = LatLng(44.486209063588426, 26.09687100214452)
                map.addMarker(MarkerOptions().position(m12).title(getString(R.string.brd)).snippet(getString(R.string.locatie_m12)))
                val m13 = LatLng(44.4510050289067, 26.07655064606008)
                map.addMarker(MarkerOptions().position(m13).title(getString(R.string.brd)).snippet(getString(R.string.locatie_m13)))
                val m14 = LatLng(44.41427128323016, 26.07405107960528)
                map.addMarker(MarkerOptions().position(m14).title(getString(R.string.brd)).snippet(getString(R.string.locatie_m14)))
                val m15 = LatLng(44.41485455025183, 26.105284641510398)
                map.addMarker(MarkerOptions().position(m15).title(getString(R.string.brd)).snippet(getString(R.string.locatie_m15)))
            }
            getString(R.string.raiffeisen) -> {
                val m16 = LatLng(44.40973205244747, 26.064479611648355)
                map.addMarker(MarkerOptions().position(m16).title(getString(R.string.raiffeisen)).snippet(getString(R.string.locatie_m16)))
                val m17 = LatLng(44.44132614076785, 26.027840129603067)
                map.addMarker(MarkerOptions().position(m17).title(getString(R.string.raiffeisen)).snippet(getString(R.string.locatie_m17)))
                val m18 = LatLng(44.39040231102038, 26.11725082931477)
                map.addMarker(MarkerOptions().position(m18).title(getString(R.string.raiffeisen)).snippet(getString(R.string.locatie_m18)))
                val m19 = LatLng(44.431286927747884, 26.134846101972187)
                map.addMarker(MarkerOptions().position(m19).title(getString(R.string.raiffeisen)).snippet(getString(R.string.locatie_m19)))
                val m20 = LatLng(44.44144886633044, 26.10007510768973)
                map.addMarker(MarkerOptions().position(m20).title(getString(R.string.raiffeisen)).snippet(getString(R.string.locatie_m20)))
            }
        }

        val center = LatLng(44.432561253088245, 26.103031967947935)
        val zoomLevel=10.5f
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(center, zoomLevel))

        getLocationAccess()
    }
}