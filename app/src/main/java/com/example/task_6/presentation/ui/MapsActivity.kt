package com.example.task_6.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.task_6.data.BankItem
import com.example.task_6.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.task_6.databinding.ActivityMapsBinding
import com.example.task_6.domain.Constants
import com.example.task_6.domain.Constants.Companion.homiel
import com.example.task_6.presentation.di.App
import com.example.task_6.presentation.ui.vm.MainViewModel
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import javax.inject.Inject

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel by lazy {
        ViewModelProvider(
            this,
            factory
        ).get(MainViewModel::class.java)
    }
    private lateinit var mMap: GoogleMap
    private val binding: ActivityMapsBinding by lazy { ActivityMapsBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        (application as App).getAppComponent().inject(this)
        mMap = googleMap
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(homiel, 15f))
        viewModel.banksList.observe(this, {
            it?.let {
                addMarkers(it)
            }
        })
    }

    private fun addMarkers(value: ArrayList<BankItem>) {
        value.forEach() {
            mMap.addMarker(
                MarkerOptions().position(LatLng(it.gps_x, it.gps_y))
                    .title(it.type)
                    .snippet("${it.address_type.plus(" ") ?: ""}${it.address.plus(" ") ?: ""}${it.house ?: ""}")
                    .icon(
                        when (it.type) {
                            Constants.ATM -> BitmapDescriptorFactory.defaultMarker(
                                BitmapDescriptorFactory.HUE_BLUE
                            )
                            Constants.FILIAL -> BitmapDescriptorFactory.defaultMarker(
                                BitmapDescriptorFactory.HUE_GREEN
                            )
                            Constants.INFOBOX -> BitmapDescriptorFactory.defaultMarker(
                                BitmapDescriptorFactory.HUE_AZURE
                            )
                            else -> {
                                null
                            }
                        }
                    )
            )
        }
    }
}
