package com.andela.d2_news_application.ui

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import com.andela.d2_news_application.R
import com.andela.d2_news_application.databinding.ActivityMainBinding
import com.andela.d2_news_application.ui.fashion.FashionFragment
import com.andela.d2_news_application.ui.food.FoodFragment
import com.andela.d2_news_application.ui.home.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mOnNavigationItemSelectedListener
            = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        val transaction = supportFragmentManager.beginTransaction()

        when (item.itemId) {
            R.id.navigation_home -> {
                transaction.replace(R.id.frame_container,  HomeFragment.newInstance())
            }
            R.id.navigation_dashboard -> {
                transaction.replace(R.id.frame_container,  FoodFragment.newInstance())
            }
            R.id.navigation_notifications -> {
                transaction.replace(R.id.frame_container,  FashionFragment.newInstance())
            }
        }
        transaction.commit()
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        supportFragmentManager
                .beginTransaction()
                .add(R.id.frame_container, HomeFragment.newInstance())
                .commit()
    }
}
