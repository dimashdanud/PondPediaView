package com.aetherized.view.pondpediaview.ui.authenticated.pond

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.FragmentManager
import com.aetherized.view.pondpediaview.R
import com.aetherized.view.pondpediaview.data.local.entity.PondEntity
import com.aetherized.view.pondpediaview.data.model.PondFeed
import com.aetherized.view.pondpediaview.data.model.PondFish
import com.aetherized.view.pondpediaview.data.model.PondWater
import com.aetherized.view.pondpediaview.databinding.ActivityHomeBinding
import com.aetherized.view.pondpediaview.databinding.ActivityPondBinding
import com.aetherized.view.pondpediaview.ui.authenticated.home.HomeViewModel
import com.aetherized.view.pondpediaview.ui.authenticated.home.pond.PondViewModel
import com.aetherized.view.pondpediaview.ui.authenticated.pond.details.PondDetailsFragment
import com.aetherized.view.pondpediaview.ui.authenticated.settings.SettingsActivity
import com.aetherized.view.pondpediaview.ui.authenticated.settings.SettingsViewModel
import com.aetherized.view.pondpediaview.ui.unauthenticated.main.MainActivity
import com.aetherized.view.pondpediaview.utils.helper.Constants
import com.aetherized.view.pondpediaview.utils.helper.ReplaceListener
import com.aetherized.view.pondpediaview.utils.helper.ResetListener
import com.aetherized.view.pondpediaview.utils.helper.ViewModelFactory

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class PondActivity : AppCompatActivity(), ResetListener {
    private lateinit var binding: ActivityPondBinding
    private lateinit var pond: PondEntity

    private val viewModelFactory by lazy { ViewModelFactory.getInstance(this) }
    private val viewModel by viewModels<PondViewModel> { viewModelFactory }
    private val settingsViewModel by viewModels<SettingsViewModel> { viewModelFactory }

    private lateinit var toolBar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pond = intent.extras?.getParcelable<PondEntity>(Constants.PARCELABLE_KEY) ?: PondEntity(
            pondName = "",
            pondLength = 0f,
            pondWidth = 0f,
            pondDepth = 0f,
            pondFish = PondFish(0,"", 0f, 0f),
            pondFeed = PondFeed("",0f, 0f, 0f, 0f, 0f, 0),
            pondWater = PondWater(0f, 0f, 0f, 0f, 0f, 0f),
            createdAt = null,
            updatedAt = null
        )
        initializeValue()
        setupView()
        observeSettings()

    }

    private fun initializeValue(){

    }
    private fun observeSettings() {

        settingsViewModel.getThemeSettings().observe(this) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
    private fun setupView(){
        toolBar = findViewById(R.id.toolbar)
        setSupportActionBar(toolBar)
        toolBar.title = "PondActivity"

        val bundle = Bundle()
        bundle.putParcelable(Constants.PARCELABLE_KEY, pond)
        val fragment = PondDetailsFragment()
        fragment.arguments = bundle

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            commit()
        }
    }

    override fun onReset() {
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        MenuInflater(this).inflate(R.menu.pond_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_update -> {

            }
            R.id.action_delete -> {

            }
        }
        return super.onOptionsItemSelected(item)
    }
}