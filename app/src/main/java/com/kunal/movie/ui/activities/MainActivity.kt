package com.kunal.movie.ui.activities

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.kunal.movie.R
import com.kunal.movie.databinding.ActivityMainBinding
import com.kunal.movie.ui.base.BaseActivity
import com.kunal.movie.ui.viewmodels.MainViewModel
import com.kunal.movie.utils.showToastShort
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    private val mainViewModel: MainViewModel by viewModels()

    private var navController: NavController? = null

    var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
    }

    override fun initializeViews() {
        setUpNavigation()
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (doubleBackToExitPressedOnce) {
                    finish()
                    return
                }
                doubleBackToExitPressedOnce = true
                showToastShort("Press Back Again To Exit")
                lifecycleScope.launch {
                    delay(2000L)
                    doubleBackToExitPressedOnce = false
                }
            }
        })
    }

    override fun initializeObservers() {

    }

    private fun setUpNavigation() {
        val args = Bundle()
        navController = Navigation.findNavController(this, R.id.fragmentNavHost)
        val graphInflater = navController?.navInflater
        val navGraph = graphInflater?.inflate(R.navigation.nav_graph_main)
        navGraph?.setStartDestination(R.id.homeFragment)
        navGraph?.let { graph ->
            navController?.setGraph(graph, args)
        }
    }
}