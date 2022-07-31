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
import androidx.core.os.bundleOf
import androidx.core.util.toRange
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.map
import androidx.navigation.fragment.findNavController
import com.example.findtutor.R
import com.example.findtutor.databinding.FragmentMapsBinding

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.Marker

class MapsFragment : Fragment(),OnMapReadyCallback, OnMarkerClickListener {
    private lateinit var binding: FragmentMapsBinding

    private lateinit var viewModel: MapsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[MapsViewModel::class.java]
        binding = FragmentMapsBinding.inflate(inflater,container,false)
        val root:View = binding.root
        filtering()

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
                            0 ->{
                                expFilter.postValue((0.0..0.9).toRange())
                            }
                            1 ->{
                                expFilter.postValue((1.0..2.9).toRange())
                            }
                            2 ->{
                                expFilter.postValue((3.0..4.9).toRange())
                            }
                            3 ->{
                                expFilter.postValue((5.0..9.9).toRange())
                            }
                            4 ->{
                                expFilter.postValue((10.0..100.0).toRange())
                            }
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
            val bundle = Bundle()
            findNavController().navigate(R.id.MapsToProfilte)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        //location tracking

        // adding markers
        viewModel.markers.observe(this){ list ->
            googleMap.clear()
            list?.forEach { googleMap.addMarker(it) }
        }
        googleMap.setOnMarkerClickListener(this)
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        viewModel.selectMarker(marker).observe(this){
            val bundle = bundleOf("tutor" to it)
            findNavController().navigate(R.id.profileFragment,bundle)
        }
        return false
    }
}