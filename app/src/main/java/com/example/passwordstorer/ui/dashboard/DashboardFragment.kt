package com.example.passwordstorer.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.passwordstorer.R
import com.example.passwordstorer.common.utils.getMainActivity
import com.example.passwordstorer.databinding.FragmentDashboardBinding
import com.example.passwordstorer.room.entity.CategoryEntity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private lateinit var binding: FragmentDashboardBinding
    private val dashboardViewModel by viewModels<DashBoardViewModel>()
    private lateinit var dashBoardAdapter: DashBoardAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDashboardBinding.bind(view)
        setUpViewmodel()
        initViews()
    }

    private fun setUpViewmodel() {
        dashboardViewModel.categoriesListLiveData.observe(viewLifecycleOwner, { categoriesList ->
            if (categoriesList.isNotEmpty()) {
                updateRecyclerView(categoriesList)
            }
        })
        dashboardViewModel.getAllCategories()
    }

    private fun updateRecyclerView(categoriesList: List<CategoryEntity>) {
        dashBoardAdapter.updateData(categoriesList)
    }

    private fun initViews() {
        binding.rvCategoryAdd.apply {
            layoutManager = LinearLayoutManager(getMainActivity())
            dashBoardAdapter = DashBoardAdapter(requireContext(), arrayListOf())
            adapter = dashBoardAdapter
        }
    }
}