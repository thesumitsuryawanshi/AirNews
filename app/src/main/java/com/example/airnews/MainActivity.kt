package com.example.airnews

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.util.Log.d
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.airnews.Adapters.viewPagerAdapter
import com.example.airnews.Fragments.F_Business
import com.example.airnews.Fragments.F_Science
import com.example.airnews.Fragments.F_Technology
import com.example.airnews.Model.NewsApi
import com.example.airnews.databinding.ActivityMainBinding
import com.example.airnews.databinding.NavdrawerHeaderLayoutBinding
import com.example.airnews.repository.repository
import com.example.airnews.viewmodel.mainViewModel
import com.example.airnews.viewmodel.viewModelFactory
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ItemsCLicked {

    @Inject
    lateinit var repository: repository

    private lateinit var binding: ActivityMainBinding
    private lateinit var appSettingPrefs: SharedPreferences
    private lateinit var sharedprefedit: SharedPreferences.Editor
    private var isNightModeOn: Boolean = false
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


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

        viewPagerInit()
        setUpDrawerLayout()
        topToolBarItemClicks()


    }

    private fun setUpDrawerLayout() {
        setSupportActionBar(binding.topAppToolBar)
        actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            binding.mainDrawerLayout,
            R.string.app_name,
            R.string.app_name
        )
        actionBarDrawerToggle.syncState()

        binding.navDrawer.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.mi_science -> Toast.makeText(this, "Science", Toast.LENGTH_SHORT).show()
                R.id.mi_business -> Toast.makeText(this, "business", Toast.LENGTH_SHORT).show()
                R.id.mi_technology -> Toast.makeText(this, "tech", Toast.LENGTH_SHORT).show()
            }
            true
        }


        NavDrawerHeaderLayoutClicks()


    }

    private fun NavDrawerHeaderLayoutClicks() {
        val headerview = binding.navDrawer.getHeaderView(0)
        val NavDrawerHeaderViewRef = NavdrawerHeaderLayoutBinding.bind(headerview)

        NavDrawerHeaderViewRef.let {
            it.btnSearch.setOnClickListener {
                val searchKeyword = NavDrawerHeaderViewRef.etSearchNews.text.toString()

                val i = Intent(this, SecondActivity::class.java)
                i.putExtra("keyword", searchKeyword)
                startActivity(i)

            }

            it.chipBitcoin.setOnClickListener {
                Intent(this, SecondActivity::class.java).also {
                    it.putExtra("keyword", "bitcoin")
                    startActivity(it)
                }

            }
            it.chipIndia.setOnClickListener {
                Intent(this, SecondActivity::class.java).also {
                    it.putExtra("keyword", "India")
                    startActivity(it)
                }
            }
            it.chipMicrosoft.setOnClickListener {
                Intent(this, SecondActivity::class.java).also {
                    it.putExtra("keyword", "microsoft")
                    startActivity(it)
                }
            }
            it.chipTesla.setOnClickListener {
                Intent(this, SecondActivity::class.java).also {
                    it.putExtra("keyword", "tesla")
                    startActivity(it)
                }
            }
            it.chipModi.setOnClickListener {
                Intent(this, SecondActivity::class.java).also {
                    it.putExtra("keyword", "modi")
                    startActivity(it)
                }
            }
            it.chipGoogle.setOnClickListener {
                Intent(this, SecondActivity::class.java).also { i ->
                    i.putExtra("keyword", "google")
                    startActivity(i)
                }
            }

        }
    }

    private fun topToolBarItemClicks() {
        binding.topAppToolBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {

                R.id.mi_devInfo -> {
                    AlertDialog.Builder(this)
                        .setTitle(" -: Developer Info :- ")
                        .setMessage(

                            "Hello \uD83D\uDC4B , \n " +
                                    "Nice to meet you \uD83D\uDE03 \n\n  " +
                                    "I'm Sumit Suryawanshi. " +
                                    "A guy who is done " +
                                    "with his Bachelor of Computer Science degree & love to learn new Languages,frameworks. \n\n " +
                                    "Working on new projects and honing my programming skills is something Im very passionate about.\uD83D\uDE00" +
                                    "\uD83D\uDCBB\n\n" +
                                    "My current goal is to\n" +
                                    "gain knowledge (Core) in the field of Software Development \uD83D\uDCBB" +
                                    "\n\n\n" +
                                    " Visit my personal website to have more information about my work "
                        )
                        .setIcon(R.drawable.ic_code)
                        .setPositiveButton("visit my Website")
                        { _, _ ->
                            CustomTabsIntent.Builder()
                                .build()
                                .launchUrl(this, Uri.parse("https://thesumitsuryawanshi.github.io"))
                        }
                        .setNegativeButton("Close") { dialog, _ -> dialog.dismiss() }
                        .create()
                        .show()
                }
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
                        "Bbye, \n  See you soon. \uD83D\uDE03",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                    finish()
                }
            }
            true
        }
    }

    private fun viewPagerInit() {

        val tabLayout = binding.tlTabLayout
        val viewpager = binding.vpViewpager
        val tabNamesArray = arrayListOf("Business", "Science", "Tech")
        val fragmentArray = arrayListOf<Fragment>(F_Business(), F_Science(), F_Technology())
        val adapter = viewPagerAdapter(fragmentArray, this)

        viewpager.adapter = adapter
        TabLayoutMediator(tabLayout, viewpager) { tab, position ->
            tab.text = tabNamesArray[position]
        }.attach()
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_toolbar_menu, menu)
        return true
    }

}