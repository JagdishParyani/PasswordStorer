package com.example.passwordstorer.ui.home

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.passwordstorer.R
import com.example.passwordstorer.common.utils.*
import com.example.passwordstorer.databinding.FragmentHomeBinding
import com.example.passwordstorer.room.entity.CategoryEntity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    lateinit var binding: FragmentHomeBinding
    lateinit var childNavController: NavController
    private val TAG = this::class.simpleName.toString()
    val timeInterval = 2000
    var mBackPressed = 0L
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        setUpViewModel()
        initViews()
    }

    private fun setUpViewModel() {
        homeViewModel.categoriesListLiveData.observe(viewLifecycleOwner, { categoriesList ->
            if (categoriesList.isEmpty()){
                insertCategories()
            }
        })
        homeViewModel.categoriesInsertLiveData.observe(viewLifecycleOwner,{ insertedRowIdsList ->
            if (insertedRowIdsList.isNotEmpty() && insertedRowIdsList.contains(-1)){
                checkThisField(insertedRowIdsList.indexOf(-1))
            }
        })
        homeViewModel.getAllCategories()
    }

    private fun checkThisField(index: Int) {
        val categoryNameList = getStringArray(R.array.category_array)
        TAG.eLog(categoryNameList[index])
    }

    private fun insertCategories() {
        val categoryNameList = getStringArray(R.array.category_array)
        val categoryTypeList = getIntArray(R.array.category_type)
        val categoryList = arrayListOf<CategoryEntity>()
        for (i in categoryNameList.indices){
            categoryList.add(CategoryEntity(categoryTypeList[i],categoryNameList[i]))
        }
        homeViewModel.insertCategories(categoryList)
    }

    private fun initViews() {
        binding.bottomNavigationView.apply {
            enableAnimation(false)
            setRippleColor(ColorStateList.valueOf(getColor(R.color.coal_black)))
            disableTooltipText()
            setTypeface(getCustomTypeFace(getString(R.string.poppins_regular)))
            setLetterSpacing(0.1f)
            setIconSize(22f)
            setTextSize(14f)
        }
        val childNavHostFragment = childFragmentManager.fragments.first() as NavHostFragment
        childNavController = childNavHostFragment.findNavController()

        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            val isNavigated = NavigationUI.onNavDestinationSelected(item, childNavController)
            TAG.eLog("$isNavigated")
            return@setOnNavigationItemSelectedListener true
        }

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (childNavController.currentDestination?.id != R.id.dashboardFragment) {
                    binding.bottomNavigationView.currentItem = 0
                } else {
                    if (mBackPressed + timeInterval > System.currentTimeMillis()) {
                        getMainActivity()?.finish()
                    } else {
                        toast(getString(R.string.click_again_to_exit))
                    }
                    mBackPressed = System.currentTimeMillis()
                }
            }
        }
        getMainActivity()?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, callback)
    }
}