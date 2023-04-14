package com.example.fakestore.UI.Electronics

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fakestore.Util.Extension.Companion.backpress
import com.example.fakestore.databinding.FragmentElectronicsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ElectronicsFragment : Fragment() {

    private lateinit var binding : FragmentElectronicsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentElectronicsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().backpress(0L, viewLifecycleOwner)
    }
}