package com.aetherized.view.pondpediaview.ui.authenticated.pond.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.aetherized.view.pondpediaview.R
import com.aetherized.view.pondpediaview.data.local.entity.PondEntity
import com.aetherized.view.pondpediaview.data.local.entity.PondLogEntity
import com.aetherized.view.pondpediaview.data.model.PondFeed
import com.aetherized.view.pondpediaview.data.model.PondFish
import com.aetherized.view.pondpediaview.data.model.PondWater
import com.aetherized.view.pondpediaview.ui.authenticated.home.add.AddViewModel
import com.aetherized.view.pondpediaview.ui.authenticated.home.pond.PondViewModel
import com.aetherized.view.pondpediaview.ui.authenticated.pond.logs.PondLogViewModel
import com.aetherized.view.pondpediaview.utils.customview.MyButton
import com.aetherized.view.pondpediaview.utils.helper.Constants
import com.aetherized.view.pondpediaview.utils.helper.ResetListener
import com.aetherized.view.pondpediaview.utils.helper.ViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.sql.Timestamp

class PondDetailsWaterFragment : Fragment() {
    private lateinit var view: View
    private val addViewModel  by activityViewModels<AddViewModel>()

    private val viewModelFactory by lazy { ViewModelFactory.getInstance(requireContext()) }
    private val pondViewModel by viewModels<PondViewModel> { viewModelFactory }
    private val pondLogViewModel by viewModels<PondLogViewModel> { viewModelFactory }


    private lateinit var pondData: PondWater

    private lateinit var pondTemperature: TextView
    private lateinit var pondTurbidity: TextView
    private lateinit var pondDissolvedOxygen: TextView
    private lateinit var pondPH: TextView
    private lateinit var pondAmmonia: TextView
    private lateinit var pondNitrate: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        view = inflater.inflate(R.layout.fragment_pond_details_water, container, false)

        arguments?.getParcelable<PondWater>(Constants.PARCELABLE_KEY)?.let {
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
        pondTemperature.text = pondData?.temperature.toString()
        pondTurbidity.text = pondData?.turbidity.toString()
        pondDissolvedOxygen.text = pondData?.dissolvedOxygen.toString()
        pondPH.text = pondData?.pH.toString()
        pondAmmonia.text = pondData?.ammonia.toString()
        pondNitrate.text = pondData?.nitrate.toString()

    }

    private fun initializeView() {
        pondTemperature = view.findViewById(R.id.temperatureEditText)
        pondTurbidity = view.findViewById(R.id.turbidityEditText)
        pondDissolvedOxygen = view.findViewById(R.id.oxygenEditText)
        pondPH = view.findViewById(R.id.phEditText)
        pondAmmonia = view.findViewById(R.id.ammoniaEditText)
        pondNitrate = view.findViewById(R.id.nitrateEditText)

    }

}