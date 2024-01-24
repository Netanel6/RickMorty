package com.netanel.rickmorty.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.netanel.rickmorty.databinding.FragmentHomeBinding
import com.netanel.rickmorty.domain.model.character.Character
import com.netanel.rickmorty.gernericRecyclerView.GenericRecyclerViewAdapter
import com.netanel.rickmorty.gernericRecyclerView.Model
import com.netanel.rickmorty.gernericRecyclerView.model.CharacterModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), CharacterModel.ClickListener {
    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.getCharacters()
        // Loading state
        viewModel._state.observe(viewLifecycleOwner) { state ->
            when (state) {
                HomeState.Loading -> binding.progressBar.visibility = View.VISIBLE
                HomeState.Loaded -> binding.progressBar.visibility = View.GONE
                HomeState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                }
                else -> {}
            }
        }
        // Data
        viewModel._listOfCharacters.observe(viewLifecycleOwner) { listOfCharacters ->
            if (listOfCharacters != null) {
                val list = arrayListOf<Model>()
                for (i in listOfCharacters) {
                    list.add(CharacterModel(i))
                }
                binding.charactersRecyclerView.adapter =
                    GenericRecyclerViewAdapter(items = list, listener = this)
            }
        }
    }

    // {"id":6,"name":"Abadango Cluster Princess","status":"Alive","species":"Alien","type":"","gender":"Female","origin":{"name":"Abadango","url":"https://rickandmortyapi.com/api/location/2"},"location":{"name":"Abadango","url":"https://rickandmortyapi.com/api/location/2"},"image":"https://rickandmortyapi.com/api/character/avatar/6.jpeg","episode":["https://rickandmortyapi.com/api/episode/27"],"url":"https://rickandmortyapi.com/api/character/6","created":"2017-11-04T19:50:28.250Z"},{"id":7,"name":"Abradolf Lincler","status":"unknown","species":"Human","type":"Genetic experiment","gender":"Male","origin":{"name":"Earth (Replacement Dimension)","url":"https://rickandmortyapi.com/api/location/20"},"location":{"name":"Testicle Monster Dimension","url":"https://rickandmortyapi.com/api/location/21"},"image":"https://rickandmortyapi.com/api/character/avatar/7.jpeg","episode":["https://rickandmortyapi.com/api/episode/10","https://rickandmortyapi.com/api/episode/11"],"url":"https://rickandmortyapi.com/api/character/7","created":"2017-11-04T19:59:20.523Z"}

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun onClick(character: Character) {
        // TODO: Continue from here 23-01-24
        Toast.makeText(context, character.name, Toast.LENGTH_SHORT).show()
    }
}
