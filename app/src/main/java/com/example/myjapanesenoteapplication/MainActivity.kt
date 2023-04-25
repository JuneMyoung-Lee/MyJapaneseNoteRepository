package com.example.myjapanesenoteapplication

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.myjapanesenoteapplication.databinding.ActivityMainBinding
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private var lastBackPressedTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        installSplashScreen()
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "김동현 찌끄레기년", Snackbar.LENGTH_SHORT)
                .setAnchorView(R.id.fab)
                .setAction("Action", null).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {

        if (!onBackPressedDispatcher.hasEnabledCallbacks()) {

            val currentTime = System.currentTimeMillis()
            val diffTime = currentTime - lastBackPressedTime

            if (diffTime in 0..2000) {
                systemFinish()
            } else {
                Toast.makeText(this, "'이전' 버튼을 한 번 더 누르면 종료 됩니다.", Toast.LENGTH_SHORT).show()
            }
            lastBackPressedTime = currentTime
        } else {
            super.onBackPressed()
        }
    }

    private fun systemFinish() {
        finishAffinity()
        exitProcess(0)
    }
}