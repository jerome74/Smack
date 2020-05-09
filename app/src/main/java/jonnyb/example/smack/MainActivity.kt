package jonnyb.example.smack

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
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
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import jonnyb.example.smack.obj.AuthObj
import jonnyb.example.smack.obj.CompleteObj
import jonnyb.example.smack.obj.UserObj
import jonnyb.example.smack.ui.login.LoginActivity
import jonnyb.example.smack.utilities.Constants
import jonnyb.example.smack.utilities.UtilString
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_header_main.*

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

        LocalBroadcastManager.getInstance(this).registerReceiver(loginReceiver, IntentFilter(
            Constants.BROADCAST_LOGIN))

    }

    override fun onBackPressed()  {
       if(drawer_layout.isDrawerOpen(GravityCompat.START))
           drawer_layout.closeDrawer(GravityCompat.START)
        else
           super.onBackPressed()
    }

    fun onLoginBntClicked(view : View){

        if (!AuthObj.isLoggIn) {
            val intentLogin : Intent = Intent(this, LoginActivity::class.java)
            startActivity(intentLogin)
        }
        else
        {
            wchatTxt.text = ""
            emailTxt.text = ""
            loginBtn.text = "Log-In"
            userImg.setImageResource(R.drawable.profiledefault)
            userImg.setBackgroundColor(Color.TRANSPARENT)

            UserObj.reset()
            CompleteObj.reset()
            UserObj.reset()

        }

    }

    fun onAddProfileImgBntClicked(view : View){

    }

    fun onArrowImgBtnClicked(view: View){

    }

    val loginReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?)
        {
            if (AuthObj.isLoggIn)
            {
                wchatTxt.text = UserObj.userProfile?.name
                emailTxt.text = UserObj.userProfile?.email
                loginBtn.text = "Log-out"

                val identifier = resources.getIdentifier(UserObj.userProfile?.avatarName,"drawable",packageName)

                userImg.setImageResource(identifier)
                userImg.setBackgroundColor(UtilString.stringToColor( UserObj.userProfile?.avatarColor!!))
            }
        }
    }
}
