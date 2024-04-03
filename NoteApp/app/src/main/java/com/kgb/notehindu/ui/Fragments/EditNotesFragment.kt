package com.kgb.notehindu.ui.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.kgb.notehindu.Model.Notes
import com.kgb.notehindu.R
import com.kgb.notehindu.ViewModel.NotesViewModel

import com.kgb.notehindu.databinding.FragmentEditNotesBinding
import java.util.*


class EditNotesFragment : Fragment() {

    val oldNotes by navArgs<EditNotesFragmentArgs>()
    lateinit var binding: FragmentEditNotesBinding
    var priority: String = "1"
    val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentEditNotesBinding.inflate(layoutInflater,container,false)
        setHasOptionsMenu(true)


        binding.editTitle.setText(oldNotes.data.title)
        binding.editsubTitle.setText(oldNotes.data.subTitle)
        binding.editNotes.setText(oldNotes.data.notes)

        when(oldNotes.data.priority){
            "1" -> {

                priority = "1"
                binding.pGreen.setImageResource(R.drawable.ic_done)
                binding.pYellow.setImageResource(0)
                binding.pRed.setImageResource(0)
            }

            "2" ->{

                priority = "2"
                binding.pYellow.setImageResource(R.drawable.ic_done)
                binding.pGreen.setImageResource(0)
                binding.pRed.setImageResource(0)
            }
            "3" ->{

                priority = "3"
                binding.pRed.setImageResource(R.drawable.ic_done)
                binding.pYellow.setImageResource(0)
                binding.pGreen.setImageResource(0)
            }
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

        binding.btnEditSaveNotes.setOnClickListener {
            updateNotes(it)
        }

        return binding.root
    }

    private fun updateNotes(it: View?) {
        val title = binding.editTitle.text.toString()
        val subTitle = binding.editsubTitle.text.toString()
        val notes = binding.editNotes.text.toString()

        val d = Date()
        val notesDate: CharSequence = DateFormat.format("MMM d, yyyy ", d.time)

        val data = Notes(
            oldNotes.data.id,
            title = title,
            subTitle = subTitle,
            notes = notes,
            date = notesDate.toString(),
            priority
        )
        viewModel.updateNotes(data)

        Toast.makeText(requireContext(),"Notes Update Successfully", Toast.LENGTH_SHORT).show()

        Navigation.findNavController(it!!).navigate(R.id.action_editNotesFragment_to_homeFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.menu_delete){

            val bottomSheet: BottomSheetDialog = BottomSheetDialog(requireContext(),R.style.BottomSheetStyle)
            bottomSheet.setContentView(R.layout.dialog_delete)

            val textviewYes = bottomSheet.findViewById<TextView>(R.id.dialog_yes)
            val textViewNo = bottomSheet.findViewById<TextView>(R.id.dialog_no)

            textviewYes?.setOnClickListener {

                viewModel.deleteNotes(oldNotes.data.id!!)
                bottomSheet.dismiss()

            }

            textViewNo?.setOnClickListener {

                bottomSheet.dismiss()
            }

            bottomSheet.show()
        }

        return super.onOptionsItemSelected(item)
    }

}