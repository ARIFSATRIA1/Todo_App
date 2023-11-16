package com.dicoding.todoapp.ui.detail


import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.todoapp.R
import com.dicoding.todoapp.databinding.ActivityTaskDetailBinding
import com.dicoding.todoapp.ui.ViewModelFactory
import com.dicoding.todoapp.utils.DateConverter
import com.dicoding.todoapp.utils.TASK_ID


class DetailTaskActivity : AppCompatActivity() {

    private lateinit var detailTaskViewModel: DetailTaskViewModel
    private lateinit var binding: ActivityTaskDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityTaskDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //TODO 11 : Show detail task and implement delete action
        val edTitle = binding.detailEdTitle
        val edDescription = binding.detailEdDescription
        val edDueDate = binding.detailEdDueDate
        val btnDelete = binding.btnDeleteTask

        val factory = ViewModelFactory.getInstance(this)
        detailTaskViewModel = ViewModelProvider(this,factory).get(DetailTaskViewModel::class.java)

        val id = intent.getIntExtra(TASK_ID,0)


        detailTaskViewModel.setTaskId(id)

        btnDelete.setOnClickListener {
            detailTaskViewModel.deleteTask()
            finish()
        }

        detailTaskViewModel.task.observe(this){
            if (it != null) {
                edTitle.setText(it.title)
                edDescription.setText(it.description)
                edDueDate.setText(DateConverter.convertMillisToString(it.dueDateMillis))
            }

        }
    }
}