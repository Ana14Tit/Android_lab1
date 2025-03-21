package com.example.lab1

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.lab1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val adapter = ToDoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        startWork()
        delToDoBySwipe()
    }

    private fun startWork() {
        binding.apply {
            RCView.layoutManager = GridLayoutManager(this@MainActivity, 1)
            RCView.adapter = adapter
            AddButton.setOnClickListener {
                if (EditText.text.toString() != "") {
                    val toDoItem = ToDoItem(EditText.text.toString())
                    adapter.addToDo(toDoItem)
                    EditText.setText("")
                }
            }
        }
    }
    private fun delToDoBySwipe() {
        binding.apply {
            ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    adapter.deleteToDo(viewHolder.adapterPosition)
                }
            }).attachToRecyclerView(RCView)
        }
    }
}