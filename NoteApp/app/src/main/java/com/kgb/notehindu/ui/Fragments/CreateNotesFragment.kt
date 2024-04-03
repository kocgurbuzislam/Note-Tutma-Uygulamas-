package com.kgb.notehindu.ui.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.kgb.notehindu.Model.Notes
import com.kgb.notehindu.R
import com.kgb.notehindu.ViewModel.NotesViewModel
import com.kgb.notehindu.databinding.FragmentCreateNotesBinding

import java.util.*



class CreateNotesFragment : Fragment() {

    lateinit var binding: FragmentCreateNotesBinding
    var priority: String = "1"
    val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentCreateNotesBinding.inflate(layoutInflater,container,false)
        binding.pGreen.setImageResource(R.drawable.ic_done)


        binding.btnSaveNotes.setOnClickListener{
            createNotes(it)
        }

        binding.pGreen.setOnClickListener {
            priority = "1"
            binding.pGreen.setImageResource(R.drawable.ic_done)
            binding.pYellow.setImageResource(0)
            binding.pRed.setImageResource(0)

        }

        binding.pYellow.setOnClickListener {
            priority = "2"
            binding.pYellow.setImageResource(R.drawable.ic_done)
            binding.pGreen.setImageResource(0)
            binding.pRed.setImageResource(0)

        }

        binding.pRed.setOnClickListener {
            priority = "3"
            binding.pRed.setImageResource(R.drawable.ic_done)
            binding.pYellow.setImageResource(0)
            binding.pGreen.setImageResource(0)

        }

        binding.btnSaveNotes.setOnClickListener{
            createNotes(it)
        }

        return binding.root
    }

    private fun createNotes(it: View?) {
        val title = binding.editTitlec.text.toString()
        val subTitle = binding.editSubTitlec.text.toString()
        val notes = binding.editNotesc.text.toString()

        val d = Date()
        val notesDate: CharSequence = DateFormat.format("MMM d, yyyy ", d.time)

        val data = Notes(
            null,
            title = title,
            subTitle = subTitle,
            notes = notes,
            date = notesDate.toString(),
            priority
        )
        viewModel.addNotes(data)

        Toast.makeText(requireContext(),"Notes Created Successfully",Toast.LENGTH_SHORT).show()

        Navigation.findNavController(it!!).navigate(R.id.action_createNotesFragment_to_homeFragment)


    }


}