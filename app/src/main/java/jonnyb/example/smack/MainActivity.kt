package jonnyb.example.smack

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import jonnyb.example.smack.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar
            , R.string.open_navigation_drawer
            , R.string.close_navigation_drawer)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener { true }


    }

    override fun onBackPressed()  {
       if(drawer_layout.isDrawerOpen(GravityCompat.START))
           drawer_layout.closeDrawer(GravityCompat.START)
        else
           super.onBackPressed()
    }

    fun onLoginBntClicked(view : View){
        val intentLogin : Intent = Intent(this, LoginActivity::class.java)
        startActivity(intentLogin)
    }

    fun onAddProfileImgBntClicked(view : View){

    }

    fun onArrowImgBtnClicked(view: View){

    }
}
