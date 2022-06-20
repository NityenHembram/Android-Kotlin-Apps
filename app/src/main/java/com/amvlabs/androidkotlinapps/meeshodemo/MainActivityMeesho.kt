package com.amvlabs.androidkotlinapps.meeshodemo

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amvlabs.androidkotlinapps.R
import com.amvlabs.androidkotlinapps.databinding.ActivityMainBinding
import com.amvlabs.androidkotlinapps.databinding.ActivityMainMeeshoBinding
import org.json.JSONArray
import org.json.JSONObject

private const val TAG = "main"
class MainActivityMeesho : AppCompatActivity() {
    lateinit var binding: ActivityMainMeeshoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMeeshoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val action = setSupportActionBar(binding.includedToolbar.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val btmNavigationView = binding.btmNav
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        btmNavigationView.setupWithNavController(navController)
        btmNavigationView.selectedItemId = R.id.homeFragment2

//        firebaseSubscribe()
    }

//    private fun firebaseSubscribe() {
//       FirebaseMessaging.getInstance().subscribeToTopic("category")
//            .addOnCompleteListener {
//                Log.d(TAG, "firebaseSubscribe: ")
//            }.addOnFailureListener {
//                Log.d(TAG, "firebase fail: ")
//            }
//
//        FirebaseMessaging.getInstance().token.addOnCompleteListener {
//            Log.d("token", "token: ${it.result} ")
//        }.addOnFailureListener {
//            Log.d(TAG, "fail token: $it ")
//        }
//    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.item_menu_fav ->    Toast.makeText(this,"Favorite",Toast.LENGTH_LONG).show()
            R.id.item_menu_notif ->  Toast.makeText(this,"Notification",Toast.LENGTH_LONG).show()
            R.id.item_menu_cart ->   Toast.makeText(this,"Cart",Toast.LENGTH_LONG).show()
        }
        return super.onOptionsItemSelected(item)
    }
}