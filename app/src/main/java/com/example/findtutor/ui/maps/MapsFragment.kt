package com.example.findtutor.ui.maps

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.util.toRange
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.findtutor.R
import com.example.findtutor.databinding.FragmentMapsBinding
import com.google.android.gms.maps.*

import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker

class MapsFragment : Fragment(),OnMapReadyCallback, OnMarkerClickListener,LocationListener {
    private lateinit var binding: FragmentMapsBinding

    private lateinit var viewModel: MapsViewModel

    private var map: GoogleMap? = null
    private var cameraPosition: CameraPosition? = null

    private lateinit var locationManager: LocationManager
    private var lastLocation: Location? = null
    private val locationPermissionCode = 2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[MapsViewModel::class.java]
        binding = FragmentMapsBinding.inflate(inflater,container,false)
        val root:View = binding.root
        filtering()
        getLocation()
        return root
    }

    private fun filtering(){
        with(viewModel){
            with(binding.mapSpinnerSubject){
                onItemSelectedListener = object:OnItemSelectedListener{
                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        subjectFilter.postValue(p2+1)
                    }
                    override fun onNothingSelected(p0: AdapterView<*>?) {}
                }
            }
            with(binding.mapSpinnerExp){
                onItemSelectedListener = object:OnItemSelectedListener{
                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        when(p2){
                            0 ->{ expFilter.postValue((0.0..0.9).toRange()) }
                            1 ->{ expFilter.postValue((1.0..2.9).toRange()) }
                            2 ->{ expFilter.postValue((3.0..4.9).toRange()) }
                            3 ->{ expFilter.postValue((5.0..9.9).toRange()) }
                            4 ->{ expFilter.postValue((10.0..100.0).toRange()) }
                        }
                    }
                    override fun onNothingSelected(p0: AdapterView<*>?) {}
                }
            }
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)

        binding.mapProfile.setOnClickListener {
            findNavController().navigate(R.id.MapsToProfilte,arguments)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        //location tracking
        lastLocation?.let { map!!.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(it.latitude,it.latitude),15f)) }
        // adding markers
        viewModel.markers.observe(this){ list ->
            map!!.clear()
            list?.forEach { map!!.addMarker(it) }
        }
        map!!.setOnMarkerClickListener(this)
    }


    override fun onMarkerClick(marker: Marker): Boolean {
        viewModel.selectMarker(marker).observe(this){
            val bundle = bundleOf("tutor" to it)
            findNavController().navigate(R.id.profileFragment,bundle)
        }
        return false
    }

    private fun getLocation(){
        locationManager = requireContext()
            .getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (ContextCompat.checkSelfPermission(requireContext(),ACCESS_FINE_LOCATION)!=
            PERMISSION_GRANTED ){
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(ACCESS_FINE_LOCATION),
                locationPermissionCode)
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,5f,this)
    }

    override fun onLocationChanged(location: Location) {
        lastLocation = location
    }
}