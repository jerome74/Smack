package jonnyb.example.smack

import android.app.AlertDialog
import android.app.Dialog
import android.content.*
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
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
import io.socket.client.IO
import jonnyb.example.smack.model.Channel
import jonnyb.example.smack.obj.AuthObj
import jonnyb.example.smack.obj.ChannelObj
import jonnyb.example.smack.obj.CompleteObj
import jonnyb.example.smack.obj.UserObj
import jonnyb.example.smack.ui.login.LoginActivity
import jonnyb.example.smack.utilities.Constants
import jonnyb.example.smack.utilities.UtilString
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.nav_header_main.*

class MainActivity : AppCompatActivity() {

    val socket = IO.socket(Constants.BASE_URL);

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
            channelNameTxt.text = "Please Log-in"
            loginBtn.text = "Log-In"
            userImg.setImageResource(R.drawable.profiledefault)
            userImg.setBackgroundColor(Color.TRANSPARENT)

            UserObj.reset()
            CompleteObj.reset()
            AuthObj.reset()

        }

    }

    fun onAddChannelImgBntClicked(view : View){

        if(AuthObj.isLoggIn)
        {
            val builder = AlertDialog.Builder(this)
            val dialogView = layoutInflater.inflate(R.layout.add_channel_dialog,null)

            builder.setView(dialogView)
                .setPositiveButton("add",{dialog: DialogInterface?, which: Int ->
                val addChannelTxt = dialogView.findViewById<TextView>(R.id.addChannelTxT)
                val addChannelDescTxt = dialogView.findViewById<TextView>(R.id.addChannelDescTxt)
                val channelName = addChannelTxt.text.toString()
                val channelDesc = addChannelDescTxt.text.toString()

                socket.emit("newChannel" , channelName,channelDesc)
                    socket.on("channelCreated" , {args ->
                        runOnUiThread {
                            ChannelObj.listChannel.add(Channel(args[0] as String,args[1] as String,args[2] as String))
                        }
                    })

                })
                .setNegativeButton("cancel", {dialog: DialogInterface?, which: Int ->


                }).show()
        }

    }

    fun onArrowImgBtnClicked(view: View)
    {
        hideKeyboard()
    }

    val loginReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?)
        {
            if (AuthObj.isLoggIn)
            {
                wchatTxt.text = UserObj.userProfile?.name
                emailTxt.text = UserObj.userProfile?.email
                channelNameTxt.text = UserObj.userProfile?.name
                loginBtn.text = "Log-out"

                val identifier = resources.getIdentifier(UserObj.userProfile?.avatarName,"drawable",packageName)

                userImg.setImageResource(identifier)
                userImg.setBackgroundColor(UtilString.stringToColor( UserObj.userProfile?.avatarColor!!))
            }
        }
    }

    fun hideKeyboard(){
        val inputManager : InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        if(inputManager.isAcceptingText)
            inputManager.hideSoftInputFromWindow(currentFocus?.windowToken,0)
    }


    override fun onRestart() {

        super.onRestart()

        if(socket == null || !socket.connected())
            socket.connect();
    }

    override fun onPause() {
        super.onPause()

        if(socket == null || !socket.connected())
            socket.connect();
    }

    override fun onResume() {
        super.onResume()

        if(socket == null || !socket.connected())
            socket.connect();
    }



}
