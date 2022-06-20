package com.amvlabs.androidkotlinapps.warecover

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.amvlabs.androidkotlinapps.databinding.ActivityMainBinding
import com.amvlabs.androidkotlinapps.databinding.ActivityMainWarecoverBinding
import com.amvlabs.androidkotlinapps.warecover.adapter.PagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

private const val TAG = "tag"
class MainActivityWArecovery : AppCompatActivity() {

    private val registerActivityForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
        //
    }
    private lateinit var binding: ActivityMainWarecoverBinding
    lateinit var tabLayout: TabLayout
    lateinit var viewPager:ViewPager2
    lateinit var toolbar:Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainWarecoverBinding.inflate(layoutInflater)
        setContentView(binding.root)
        checkNotificationPermission()
        //Initiating viewModel

        toolbar = binding.watoolbar
        val actionbar = setSupportActionBar(toolbar)
        tabLayout = binding.tabLout
        viewPager = binding.viewPager

        addTabLayout(tabLayout,viewPager)

        val currentDate = Calendar.getInstance().time
        Log.d(TAG, "onCreate: $currentDate")

    }

    private fun addTabLayout(tabLayout: TabLayout, viewPager: ViewPager2) {
        val titles = arrayListOf<String>("Chats","Status")
        val adapter = PagerAdapter(supportFragmentManager,lifecycle,toolbar)

        viewPager.adapter = adapter
        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                toolbar.title = titles[tab!!.position]
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
        TabLayoutMediator(tabLayout,viewPager,true,true,TabLayoutMediator.TabConfigurationStrategy(){tab,positon ->
            when(positon){
                0 -> {tab.text = "chats"}
                1 -> {tab.text = "status" }
            }
        }).attach()
    }


    private fun checkNotificationPermission() {
        if(!isNotificationServiceGranted()){
            registerActivityForResult.launch(Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS))
        }else{
            //
        }
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf( Manifest.permission.READ_EXTERNAL_STORAGE),1)
        }
    }

    private fun isNotificationServiceGranted():Boolean{
        val packageName = packageName
        val flat = NotificationManagerCompat.getEnabledListenerPackages( this)
        if(flat.isNotEmpty()){
            flat.forEach{
                val pn = packageName.equals(it)
                if(pn){
                    return true
                }
            }
        }
        return false
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}