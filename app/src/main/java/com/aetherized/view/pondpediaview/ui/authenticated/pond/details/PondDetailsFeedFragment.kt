package com.aetherized.view.pondpediaview.ui.authenticated.pond.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.aetherized.view.pondpediaview.R
import com.aetherized.view.pondpediaview.data.local.entity.PondEntity
import com.aetherized.view.pondpediaview.data.model.PondFeed
import com.aetherized.view.pondpediaview.data.model.PondWater
import com.aetherized.view.pondpediaview.ui.authenticated.home.add.AddViewModel
import com.aetherized.view.pondpediaview.ui.authenticated.home.pond.PondViewModel
import com.aetherized.view.pondpediaview.ui.authenticated.pond.logs.PondLogViewModel
import com.aetherized.view.pondpediaview.utils.customview.MyButton
import com.aetherized.view.pondpediaview.utils.helper.Constants
import com.aetherized.view.pondpediaview.utils.helper.ViewModelFactory

class PondDetailsFeedFragment : Fragment() {
    private lateinit var view: View
    private val addViewModel  by activityViewModels<AddViewModel>()

    private val viewModelFactory by lazy { ViewModelFactory.getInstance(requireContext()) }
    private val pondViewModel by viewModels<PondViewModel> { viewModelFactory }
    private val pondLogViewModel by viewModels<PondLogViewModel> { viewModelFactory }


    private lateinit var pondData: PondFeed

    private lateinit var feedAmount: TextView
    private lateinit var feedProtein: TextView
    private lateinit var feedLipid: TextView
    private lateinit var feedCarbohydrate: TextView
    private lateinit var feedOthers: TextView
    private lateinit var feedFrequency: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        view = inflater.inflate(R.layout.fragment_pond_details_feed, container, false)

        arguments?.getParcelable<PondFeed>(Constants.PARCELABLE_KEY)?.let {
            pondData = it
        }

        initializeView()
        initializeValue()

        return view
    }

    override fun onResume() {
        super.onResume()
        initializeValue()
    }


    private fun initializeValue() {

        feedProtein.text = pondData?.proteintPercentage.toString()
        feedLipid.text = pondData?.lipidPercentage.toString()
        feedCarbohydrate.text = pondData?.carbohydratePercentage.toString()
        feedOthers.text = pondData?.othersPercentage.toString()
        feedFrequency.text = pondData?.feedingFrequencyDaily.toString()

    }

    private fun initializeView() {
        feedAmount = view.findViewById(R.id.amountEditText)
        feedProtein = view.findViewById(R.id.proteinEditText)
        feedLipid =  view.findViewById(R.id.lipidEditText)
        feedCarbohydrate =  view.findViewById(R.id.carbohydrateEditText)
        feedOthers =  view.findViewById(R.id.othersEditText)
        feedFrequency =  view.findViewById(R.id.frequencyEditText)

    }

}