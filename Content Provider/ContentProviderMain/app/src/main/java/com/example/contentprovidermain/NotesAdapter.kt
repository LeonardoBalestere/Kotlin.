package com.example.contentprovidermain

import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.contentprovidermain.database.NotesDataBaseHelper.Companion.DESCRIPTION_NOTES
import com.example.contentprovidermain.database.NotesDataBaseHelper.Companion.TITLE_NOTES

class NotesAdapter (private val listener: NoteClickedListener): RecyclerView.Adapter<NotesViewHolder>() {

    private  var mCursor: Cursor? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder =
            NotesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent,false))

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        mCursor?.moveToPosition(position)

        holder.noteTitle.text = mCursor?.getString(mCursor?.getColumnIndex(TITLE_NOTES) as Int)
        holder.notesDescription.text = mCursor?.getString(mCursor?.getColumnIndex(DESCRIPTION_NOTES) as Int)
        holder.noteButtonRemove.setOnClickListener{
            mCursor?.moveToPosition(position)
            listener.noteRemoveItem(mCursor)
            notifyDataSetChanged()
        }

        holder.itemView.setOnClickListener{listener.noteClickedItem(mCursor as Cursor)}
    }

    override fun getItemCount(): Int = if(mCursor != null) mCursor?.count as Int else 0

    fun setCursor(newCursor: Cursor?){
        mCursor = newCursor
        notifyDataSetChanged()
    }
}

class NotesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    val noteTitle = itemView.findViewById(R.id.tv_title) as TextView
    val notesDescription = itemView.findViewById(R.id.tv_description) as TextView
    val noteButtonRemove = itemView.findViewById(R.id.btn_remove) as Button
}