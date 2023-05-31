package com.aetherized.view.pondpediaview.ui.authenticated.pond.details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.aetherized.view.pondpediaview.R
import com.aetherized.view.pondpediaview.data.local.entity.PondEntity
import com.aetherized.view.pondpediaview.ui.authenticated.home.pond.PondViewModel
import com.aetherized.view.pondpediaview.ui.authenticated.pond.PondDetailsPagerAdapter
import com.aetherized.view.pondpediaview.utils.helper.Constants
import com.aetherized.view.pondpediaview.utils.helper.ViewModelFactory
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class PondDetailsFragment : Fragment() {

    private lateinit var view: View
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var progressBar: ProgressBar
    private lateinit var pond: PondEntity

    private val viewModelFactory by lazy { ViewModelFactory.getInstance(requireContext()) }
    private val viewModel by viewModels<PondViewModel> { viewModelFactory }


    private var msg = "NONE"

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.details_tab1,
            R.string.details_tab2,
            R.string.details_tab3,
            R.string.details_tab4
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view =  inflater.inflate(R.layout.fragment_pond_details, container, false)


        @Suppress("DEPRECATION")
        pond = arguments?.getParcelable(Constants.PARCELABLE_KEY)!!
        findView()
        val detailsPagerAdapter = PondDetailsPagerAdapter(requireActivity())
        detailsPagerAdapter.pond = pond
        Log.d("PondDetailsFragment","${pond.pondFish.name}")
        viewPager.adapter = detailsPagerAdapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()


        bindUser(pond)


        return view
    }

    private fun findView(){
        viewPager = view.findViewById(R.id.view_pager)
        tabLayout = view.findViewById(R.id.tab_layout)
    }
    private fun bindUser(pond: PondEntity){
        view.findViewById<TextView>(R.id.pond_name_tv).text = pond.pondName
        view.findViewById<TextView>(R.id.fish_name_tv).text = pond.pondFish.name
        view.findViewById<TextView>(R.id.fish_scientific_name_tv).text = pond.pid.toString()
        view.findViewById<TextView>(R.id.left_tv).text = pond.pondLength.toString()
        view.findViewById<TextView>(R.id.middle_tv).text = pond.pondWidth.toString()
        view.findViewById<TextView>(R.id.right_tv).text = pond.pondName
        Glide.with(this).load(R.drawable.floating_island_11563055345).into(view.findViewById(R.id.pond_image_iv))
    }


}