package com.example.lab1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.lab1.databinding.TodoItemBinding

class ToDoAdapter: RecyclerView.Adapter<ToDoAdapter.ToDoHolder>() {
    val toDoList = ArrayList<ToDoItem>()
    class ToDoHolder(item: View): RecyclerView.ViewHolder(item) {
        val binding = TodoItemBinding.bind(item)
        fun bind(toDoItem: ToDoItem) = with(binding) {
            Task.text = toDoItem.task
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_item, parent, false)
        return ToDoHolder(view)
    }

    override fun getItemCount(): Int {
        return  toDoList.size
    }

    override fun onBindViewHolder(holder: ToDoHolder, position: Int) {
        holder.bind(toDoList[position])
    }

    fun addToDo(toDoItem: ToDoItem) {
        toDoList.add(toDoItem)
        notifyDataSetChanged()
    }

    fun deleteToDo(position: Int) {
        toDoList.removeAt(position)
        notifyItemRemoved(position)
    }
}