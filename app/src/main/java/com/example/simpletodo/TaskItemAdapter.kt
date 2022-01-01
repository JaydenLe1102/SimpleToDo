package com.example.simpletodo

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//A bridge that tells the recyclerView how ot display the data we give it

class TaskItemAdapter(val listOfItems: List<String>,
                      val longClickLister: OnLongClickListener):
    RecyclerView.Adapter<TaskItemAdapter.ViewHolder>() {

    interface OnLongClickListener{
        fun onItemLongClicked(position: Int)
    }

    // Usually involves inflating a layout from XML and returning the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }
    // Involves populating data into the item through holder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        // Get the data model based on position
        val item = listOfItems.get(position)
//        val contact: Contact = mContacts.get(position)
//        // Set item views based on your views and data model
        holder.textView.text = item
//        val textView = viewHolder.nameTextView

//        textView.setText(contact.name)
//        val button = viewHolder.messageButton
//        button.text = if (contact.isOnline) "Message" else "Offline"
//        button.isEnabled = contact.isOnline


    }

    override fun getItemCount(): Int {
        return listOfItems.size
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //store references to elements in our layout view
        val textView: TextView

        init{
            textView = itemView.findViewById(android.R.id.text1)

            itemView.setOnLongClickListener(){
                longClickLister.onItemLongClicked(adapterPosition)
                true
            }
        }

    }
}