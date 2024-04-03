@file:Suppress("DEPRECATION")

package com.kgb.notehindu.ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.kgb.notehindu.Model.Notes
import com.kgb.notehindu.R
import com.kgb.notehindu.ViewModel.NotesViewModel
import com.kgb.notehindu.databinding.FragmentHomeBinding
import com.kgb.notehindu.ui.Adapter.NotesAdapter


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    val viewModel: NotesViewModel by viewModels()
    var oldMyNotes = arrayListOf<Notes>()
    lateinit var adapter: NotesAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)

        //setHasOptionsMenu(true)


        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
        binding.rcvAllNotes.layoutManager = staggeredGridLayoutManager


        viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->
            oldMyNotes = notesList as ArrayList<Notes>
            adapter = NotesAdapter(requireContext(),notesList)
            binding.rcvAllNotes.adapter = adapter

        }

        binding.filterHigh.setOnClickListener {
            viewModel.getHighNotes().observe(viewLifecycleOwner) { notesList ->
                oldMyNotes = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(),notesList)
                binding.rcvAllNotes.adapter = adapter
            }
        }
        binding.allNotes.setOnClickListener {
            viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->
                oldMyNotes = notesList as ArrayList<Notes>

                adapter = NotesAdapter(requireContext(),notesList)
                binding.rcvAllNotes.adapter = adapter
            }
        }
        binding.filterMedium.setOnClickListener {
            viewModel.getMediumNotes().observe(viewLifecycleOwner) { notesList ->
                oldMyNotes = notesList as ArrayList<Notes>

                adapter = NotesAdapter(requireContext(),notesList)
                binding.rcvAllNotes.adapter = adapter
            }
        }
        binding.filterLow.setOnClickListener {
            viewModel.getLowNotes().observe(viewLifecycleOwner) { notesList ->
                oldMyNotes = notesList as ArrayList<Notes>

                adapter = NotesAdapter(requireContext(),notesList)
                binding.rcvAllNotes.adapter = adapter
            }
        }
        binding.btnAddNotes.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createNotesFragment)

        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.search_menu,menu)

        val item = menu.findItem(R.id.app_bar_search)
        val searchView = MenuItemCompat.getActionView(item) as SearchView

        searchView.queryHint = "Enter Notes Here..."

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                NoteFiltering(newText)
                return true
            }
        })



        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun NoteFiltering(newText: String?) {

        val newFilteredList = arrayListOf<Notes>()

        for(i in oldMyNotes){
            if(i.title.contains(newText!!) || i.subTitle.contains(newText!!)){
                newFilteredList.add(i)
            }
        }

        adapter.filtering(newFilteredList)

    }


}

