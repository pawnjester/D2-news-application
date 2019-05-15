package com.andela.d2_news_application.ui

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.andela.d2_news_application.R
import com.andela.d2_news_application.application.BaseApplication
import com.andela.d2_news_application.databinding.ActivityMainBinding
import com.andela.d2_news_application.ui.fashion.FashionFragment
import com.andela.d2_news_application.ui.food.FoodFragment
import com.andela.d2_news_application.ui.home.HomeFragment
import com.facebook.stetho.Stetho
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController


//    private val mOnNavigationItemSelectedListener
//            = BottomNavigationView.OnNavigationItemSelectedListener { item ->
//
////        val transaction = supportFragmentManager.beginTransaction()
////
////        when (item.itemId) {
////            R.id.navigation_home -> {
////                transaction.replace(R.id.frame_container,  HomeFragment.newInstance())
////            }
////            R.id.navigation_dashboard -> {
////                transaction.replace(R.id.frame_container,  FoodFragment.newInstance())
////            }
////            R.id.navigation_notifications -> {
////                transaction.replace(R.id.frame_container,  FashionFragment.newInstance())
////            }
////        }
////        transaction.commit()
//        true
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.my_nav_host_fragment)

        bottom_nav.setupWithNavController(navController)
        NavigationUI.setupActionBarWithNavController(this, navController )
        Stetho.initializeWithDefaults(this)
        BaseApplication.appComponent.inject(this)
//        navHostFragment = supportFragmentManager
//                .findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment
//        navController = navHostFragment.navController


//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
//
//        supportFragmentManager
//                .beginTransaction()
//                .add(R.id.frame_container, HomeFragment.newInstance())
//                .commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }
}
