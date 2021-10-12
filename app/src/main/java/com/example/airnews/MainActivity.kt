package com.example.airnews

import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.browser.customtabs.CustomTabsIntent
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.airnews.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ItemsCLicked {

    lateinit var binding: ActivityMainBinding
    lateinit var appSettingPrefs: SharedPreferences
    lateinit var sharedprefedit: SharedPreferences.Editor
    var isNightModeOn: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTheme(R.style.Theme_AppCompat)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        appSettingPrefs = getSharedPreferences("darkModeSettings", MODE_PRIVATE)
        sharedprefedit = appSettingPrefs.edit()
        isNightModeOn = appSettingPrefs.getBoolean("NightMode", true)

        if (isNightModeOn) {
            AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
        }


        val bottomNavigationView = binding.btmNavBar
        val navController = findNavController(R.id.fc_FragmentContainer)
        bottomNavigationView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.mi_devInfo ->
                AlertDialog.Builder(this)
                    .setTitle(" -: Developer Info :- ")
                    .setMessage(

                        "Hello \uD83D\uDC4B , \n " +
                                "Nice to meet you \uD83D\uDE03 \n\n  " +
                                " I'm Sumit Suryawanshi. \uD83D\uDD2E \n" +
                                "Currently I am pursuing Bachelor of Computer Science (TY) Course and my graduation is about to complete in next couple of months. Along with Bachelor's degree , I love to go in a Flow state of Learning new Languages , frameworks & Working on new projects \uD83D\uDE00 \uD83D\uDCBB\n" +
                                "\n" +
                                "My current goal is to gain knowledge (Core) in the field of Software Development \uD83D\uDCBB\n" +
                                "I am Self-motivated & Passionated Soul about Learning.\n" +
                                "  \n\n" +
                                " Visit my personal website to have more information about me. \uD83C\uDF10 \n"
                    )
                    .setIcon(R.drawable.ic_baseline_sentiment_satisfied_alt_24)
                    .setPositiveButton("visit my Website")
                    { dialog, _ ->
                        CustomTabsIntent.Builder()
                            .build()
                            .launchUrl(this, Uri.parse("https://thesumitsuryawanshi.github.io"))
                    }
                    .setNegativeButton("Close") { dialog, _ -> dialog.dismiss() }
                    .create()
                    .show()

            R.id.mi_darkMode -> {

                if (isNightModeOn) {
                    AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
                    sharedprefedit.putBoolean("NightMode", false)
                    sharedprefedit.apply()
                    Toast.makeText(this, "Night Mode activated", Toast.LENGTH_SHORT).show()
                } else {
                    AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
                    sharedprefedit.putBoolean("NightMode", true)
                    sharedprefedit.apply()
                }


            }
            R.id.mi_Exit -> {
                Toast.makeText(
                    this,
                    "Bbye, \n  See you soon buddy. \uD83D\uDE03",
                    Toast.LENGTH_SHORT
                )
                    .show()
                finish()
            }


        }
        return true
    }


}