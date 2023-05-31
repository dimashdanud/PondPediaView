package com.aetherized.view.pondpediaview.ui.authenticated.pond

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.aetherized.view.pondpediaview.data.local.entity.PondEntity
import com.aetherized.view.pondpediaview.ui.authenticated.pond.details.PondDetailsAnalyticsFragment
import com.aetherized.view.pondpediaview.ui.authenticated.pond.details.PondDetailsFeedFragment
import com.aetherized.view.pondpediaview.ui.authenticated.pond.details.PondDetailsFishFragment
import com.aetherized.view.pondpediaview.ui.authenticated.pond.details.PondDetailsWaterFragment
import com.aetherized.view.pondpediaview.utils.helper.Constants

class PondDetailsPagerAdapter (activity: FragmentActivity) : FragmentStateAdapter(activity) {
    var pond: PondEntity? = null
    override fun createFragment(position: Int): Fragment {

        Log.d("PondDetailsPagerAdapter","${pond!!.pondFish.name}")
        val fragment = when (position) {
            0 -> PondDetailsFishFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(Constants.PARCELABLE_KEY, pond?.pondFish)
                }
            }
            1 -> PondDetailsFeedFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(Constants.PARCELABLE_KEY, pond?.pondFeed)
                }
            }
            2 -> PondDetailsWaterFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(Constants.PARCELABLE_KEY, pond?.pondWater)
                }
            }
            3 -> PondDetailsAnalyticsFragment()
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
        return fragment
    }

    override fun getItemCount(): Int {
        return 4
    }
}