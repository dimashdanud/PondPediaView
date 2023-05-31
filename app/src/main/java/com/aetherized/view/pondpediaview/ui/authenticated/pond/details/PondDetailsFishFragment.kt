package com.aetherized.view.pondpediaview.ui.authenticated.pond.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.aetherized.view.pondpediaview.R
import com.aetherized.view.pondpediaview.data.local.entity.PondEntity
import com.aetherized.view.pondpediaview.data.model.PondFish
import com.aetherized.view.pondpediaview.data.model.PondWater
import com.aetherized.view.pondpediaview.ui.authenticated.home.add.AddViewModel
import com.aetherized.view.pondpediaview.ui.authenticated.home.pond.PondViewModel
import com.aetherized.view.pondpediaview.ui.authenticated.pond.logs.PondLogViewModel
import com.aetherized.view.pondpediaview.utils.customview.MyButton
import com.aetherized.view.pondpediaview.utils.helper.Constants
import com.aetherized.view.pondpediaview.utils.helper.ViewModelFactory

class PondDetailsFishFragment : Fragment() {
    private lateinit var view: View
    private val addViewModel  by activityViewModels<AddViewModel>()

    private val viewModelFactory by lazy { ViewModelFactory.getInstance(requireContext()) }
    private val pondViewModel by viewModels<PondViewModel> { viewModelFactory }
    private val pondLogViewModel by viewModels<PondLogViewModel> { viewModelFactory }


    private lateinit var pondData: PondFish

    private lateinit var fishType: TextView
    private lateinit var fishAmount: TextView
    private lateinit var fishHarvest: TextView
    private lateinit var fishWeight: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        view = inflater.inflate(R.layout.fragment_pond_details_fish, container, false)

        arguments?.getParcelable<PondFish>(Constants.PARCELABLE_KEY)?.let {
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
        fishType.text = pondData?.name ?: ""
        fishAmount.text = pondData?.amount.toString()
        fishHarvest.text = pondData?.harvestWeight.toString()
        fishWeight.text = pondData?.currentWeight.toString()
    }

    private fun initializeView() {
        fishType = view.findViewById(R.id.fishTypeEditText)
        fishAmount = view.findViewById(R.id.fishAmountEditText)
        fishHarvest = view.findViewById(R.id.fishHarvestEditText)
        fishWeight = view.findViewById(R.id.fishWeightEditText)
    }


}