package com.adammcneilly.tasklist

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.adammcneilly.tasklist.adapters.TaskAdapter
import com.adammcneilly.tasklist.databinding.ActivityPaidBinding
import com.adammcneilly.tasklist.mvi.TaskState
import com.adammcneilly.tasklist.viewmodels.PaidStateViewModel
import com.adammcneilly.tasklist.viewmodels.PaidViewModelFactory
import com.adammcneilly.tasklist.viewmodels.TaskListViewModel
import kotlinx.android.synthetic.main.activity_paid.*

class PaidActivity : AppCompatActivity() {

    private val tasksViewModel: TaskListViewModel by viewModels { PaidViewModelFactory }
    private val viewModel: PaidStateViewModel by viewModels { PaidViewModelFactory }

    private val taskAdapter = TaskAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPaidBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        setupViewModel(binding)
        initializeRecyclerView(binding)
    }

    private fun setupViewModel(binding: ActivityPaidBinding) {

        viewModel.init()
        tasksViewModel.init()

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        fab.setOnClickListener {
            tasksViewModel.addButtonClicked()
        }

        viewModel.state.observe(this, Observer { state ->
            when (state.task) {
                is TaskState.Loading -> {
                    Toast.makeText(this, "Loading Ad", Toast.LENGTH_SHORT).show()
                }
                is TaskState.Loaded -> state.task.tasks.let(taskAdapter::tasks::set)
                is TaskState.Error -> {
                    Toast.makeText(this, "Error Ad", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun initializeRecyclerView(binding: ActivityPaidBinding) {
        binding.taskList.adapter = taskAdapter
        binding.taskList.layoutManager = LinearLayoutManager(this)
    }

}