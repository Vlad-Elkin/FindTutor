package com.example.findtutor.ui.maps

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Spinner
import androidx.lifecycle.ViewModelProvider
import com.example.findtutor.R
import com.example.findtutor.databinding.FragmentMapsBinding

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.Marker

class MapsFragment : Fragment(),OnMapReadyCallback, OnMarkerClickListener {
    private var _binding: FragmentMapsBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel: MapsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[MapsViewModel::class.java]
        _binding = FragmentMapsBinding.inflate(inflater,container,false)
        val root:View = binding.root
        filtering()
        return root
    }
    private fun filtering(){
        with(binding.mapSpinnerSubject){
            onItemSelectedListener = object:OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    viewModel.filterBySubject(p2)
                }
                override fun onNothingSelected(p0: AdapterView<*>?) {}
            }
        }
        with(binding.mapSpinnerExp){
            onItemSelectedListener = object:OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    viewModel.filterByExperience(p2)
                }
                override fun onNothingSelected(p0: AdapterView<*>?) {}
            }
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onMapReady(googleMap: GoogleMap) {
        //location tracking

        // adding markers
        viewModel.markers.observe(this){ list ->
            list.forEach { googleMap.addMarker(it) }
        }
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        viewModel.selectMarker(marker)
        return false
    }
}