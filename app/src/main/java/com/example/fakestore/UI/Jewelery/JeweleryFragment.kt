package com.example.fakestore.UI.Jewelery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fakestore.databinding.FragmentJeweleryBinding

class JeweleryFragment : Fragment() {

    private lateinit var binding : FragmentJeweleryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentJeweleryBinding.inflate(inflater, container, false)
        return binding.root
    }
}