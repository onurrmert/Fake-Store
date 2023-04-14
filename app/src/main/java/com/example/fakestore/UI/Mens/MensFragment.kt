package com.example.fakestore.UI.Mens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fakestore.UI.Adapter.MyRecyclerAdapter
import com.example.fakestore.Util.Extension.Companion.backpress
import com.example.fakestore.databinding.FragmentMensBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MensFragment : Fragment() {

    private lateinit var binding: FragmentMensBinding

    private val viewModel by lazy {
        ViewModelProvider(this, defaultViewModelProviderFactory).get(MensViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMensBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().backpress(0L, viewLifecycleOwner)
        initRecycler()
    }

    private fun initRecycler(){
        viewModel.storeModelItem.observe(viewLifecycleOwner, {
                item->
            if (item.size > 0){
                binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
                binding.recyclerView.adapter = MyRecyclerAdapter(item)
            }
        })
    }
}