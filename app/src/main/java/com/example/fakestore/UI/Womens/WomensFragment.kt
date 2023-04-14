package com.example.fakestore.UI.Womens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fakestore.R
import com.example.fakestore.databinding.FragmentMensBinding
import com.example.fakestore.databinding.FragmentWomensBinding

class WomensFragment : Fragment() {

    private lateinit var binding: FragmentWomensBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWomensBinding.inflate(inflater, container, false)
        return binding.root
    }
}