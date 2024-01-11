package com.netanel.rickmorty.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.netanel.rickmorty.databinding.FragmentHomeBinding
import com.netanel.rickmorty.gernericRecyclerView.GenericRecyclerViewAdapter
import com.netanel.rickmorty.gernericRecyclerView.Model
import com.netanel.rickmorty.gernericRecyclerView.model.CharacterModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeListOfCharacters()
    }

    private fun observeListOfCharacters() {
        viewModel.getCharacters()
        viewModel._listOfCharacters.observe(viewLifecycleOwner) { listOfCharacters ->
            if (listOfCharacters != null) {
                val list = arrayListOf<Model>()
                for (i in listOfCharacters) {
                    list.add(CharacterModel(i))
                }
                binding.charactersRecyclerView.adapter = GenericRecyclerViewAdapter(items = list)
            }
        }
    }

    fun newInstance() = HomeFragment()
        companion object {
    }

}