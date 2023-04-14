package com.example.fakestore.UI.Jewelery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.fakestore.UI.Womens.WomenViewModel
import com.example.fakestore.Util.Extension.Companion.backpress
import com.example.fakestore.databinding.FragmentJeweleryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JeweleryFragment : Fragment() {

    private lateinit var binding : FragmentJeweleryBinding

    private val viewModel by lazy {
        ViewModelProvider(this, defaultViewModelProviderFactory).get(JeweleryViewModel::class.java)
    }

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().backpress(0L, viewLifecycleOwner)
    }
}