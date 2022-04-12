package id.idzhami.meowbottomnavigationkotlin

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.google.android.material.snackbar.Snackbar
import id.idzhami.meowbottomnavigationkotlin.Home.HomeFragment
import id.idzhami.meowbottomnavigationkotlin.Setting.SettingFragment
import id.idzhami.meowbottomnavigationkotlin.Transaction.TransactionFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        window.apply {
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
        }
        val homeFragment = HomeFragment()
        val settingFragment = SettingFragment()
        val transactionFragment = TransactionFragment()
        setFragment(homeFragment)

        val btn_nav = findViewById<MeowBottomNavigation>(R.id.btn_nav)

        try {
            btn_nav.add(MeowBottomNavigation.Model(1, R.drawable.icon_trx))
            btn_nav.add(MeowBottomNavigation.Model(2, R.drawable.icon_home))
            btn_nav.add(MeowBottomNavigation.Model(3, R.drawable.icon_setting))

            btn_nav.setOnClickMenuListener {
                when (it.id) {
                    1 -> setFragment(transactionFragment)
                    2 -> setFragment(homeFragment)
                    3 -> setFragment(settingFragment)

                    else -> " "
                }
                true
            }
            btn_nav.show(2)

        } catch (e: Exception) {

        }

    }

    fun setFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction().apply {
                replace(R.id.nav_host_fragment_container, fragment, "bottomNavigation")
                commit()
            }

    }

    private var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }
        this.doubleBackToExitPressedOnce = true
        this.let { it1 ->
            Snackbar.make(
                it1.findViewById(android.R.id.content),
                getString(R.string.label_close_app),
                Snackbar.LENGTH_INDEFINITE
            ).setDuration(8000).show()
        }
        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }
}