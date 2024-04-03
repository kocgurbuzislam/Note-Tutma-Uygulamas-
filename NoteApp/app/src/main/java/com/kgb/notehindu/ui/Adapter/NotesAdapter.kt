package com.kgb.notehindu.ui.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.kgb.notehindu.Model.Notes
import com.kgb.notehindu.R
import com.kgb.notehindu.databinding.ItemNotesBinding
import com.kgb.notehindu.ui.Fragments.HomeFragmentDirections

class NotesAdapter(val requireContext: Context,var notesList: List<Notes>) :
    RecyclerView.Adapter<NotesAdapter.notesViewHolder>() {

        fun filtering(newFilteredList: ArrayList<Notes>) {

            notesList = newFilteredList
            notifyDataSetChanged()

        }

    class notesViewHolder(val binding: ItemNotesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesViewHolder {
        return notesViewHolder(ItemNotesBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount() = notesList.size

    override fun onBindViewHolder(holder: notesViewHolder, position: Int) {
        val data = notesList[position]
        holder.binding.notesTitle.text = data.title
        holder.binding.notesSubTitle.text = data.subTitle
        holder.binding.notesDate.text = data.date

        when(data.priority){
            "1" -> {

                holder.binding.viewPriority.setBackgroundResource(R.drawable.green_dot)
            }

            "2" ->{
                holder.binding.viewPriority.setBackgroundResource(R.drawable.yellow_dot)

            }

            "3" ->{

                holder.binding.viewPriority.setBackgroundResource(R.drawable.red_dot)
            }
        }

        holder.binding.root.setOnClickListener{

            val action = HomeFragmentDirections.actionHomeFragmentToEditNotesFragment(data)
            Navigation.findNavController(it).navigate(action)

        }
    }


}