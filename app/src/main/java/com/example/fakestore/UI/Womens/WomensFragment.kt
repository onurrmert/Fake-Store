package com.example.fakestore.UI.Womens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.fakestore.R
import com.example.fakestore.Util.Constant
import com.example.fakestore.Util.Constant.Companion.WomenUrl
import com.example.fakestore.Util.Extension.Companion.backpress
import com.example.fakestore.databinding.FragmentMensBinding
import com.example.fakestore.databinding.FragmentWomensBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WomensFragment : Fragment() {

    private lateinit var binding: FragmentWomensBinding

    private val viewModel by lazy {
        ViewModelProvider(this, defaultViewModelProviderFactory).get(WomenViewModel::class.java)
    }

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().backpress(0L, viewLifecycleOwner)

        viewModel.storeModelItem.observe(viewLifecycleOwner, {
            item->
            item.forEach {
                println(it.image)
            }
        })
    }
}