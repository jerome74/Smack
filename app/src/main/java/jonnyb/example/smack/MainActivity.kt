package jonnyb.example.smack

import android.app.AlertDialog
import android.app.Dialog
import android.content.*
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
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
import jonnyb.example.smack.model.Message
import jonnyb.example.smack.model.User
import jonnyb.example.smack.model.UserProfile
import jonnyb.example.smack.obj.*
import jonnyb.example.smack.services.ChannelService
import jonnyb.example.smack.services.EmailService
import jonnyb.example.smack.services.MessageService
import jonnyb.example.smack.ui.login.LoginActivity
import jonnyb.example.smack.utilities.Constants
import jonnyb.example.smack.utilities.UtilString
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.nav_header_main.*
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    val socket = IO.socket(Constants.BASE_URL);

    lateinit var  nameChannelAdapter : ArrayAdapter<Channel>

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

        ChannelObj.clear()

        LocalBroadcastManager.getInstance(this).registerReceiver(loginReceiver, IntentFilter(
            Constants.BROADCAST_LOGIN))

        setupAdapterChannel();
        notifyEventuallyLogin();

        channel_list.setOnItemClickListener { parent, view, position, id ->
            updateChannelNameTxt(ChannelObj.listChannel[position].name);
            ChannelObj.channelSelected =ChannelObj.listChannel[position]
                drawer_layout.closeDrawer(GravityCompat.START)
        }

    }

    fun notifyEventuallyLogin() {
        LocalBroadcastManager.getInstance(this).sendBroadcast(Intent(Constants.BROADCAST_LOGIN))
    }

    fun setupAdapterChannel()
    {
        nameChannelAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, ChannelObj.listChannel)
        channel_list.adapter = nameChannelAdapter

        nameChannelAdapter.notifyDataSetChanged()
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
            ChannelObj.clear()

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

                            nameChannelAdapter.notifyDataSetChanged()

                            if(ChannelObj.listChannel.count() > 0)
                            {
                                ChannelObj.channelSelected = ChannelObj.listChannel[0]
                                updateChannelNameTxt("#${args[0] as String}")
                            }

                        }
                    })

                })
                .setNegativeButton("cancel", {dialog: DialogInterface?, which: Int ->


                }).show()
        }

    }

    fun updateChannelNameTxt(text : String)
    {
        channelNameTxt.text = text
    }

    var messageCreated = socket.on("messageCreated" , {args ->
        runOnUiThread {
            MessageObj.listMessage.add(Message(args[0] as String
                                    ,args[1] as String
                                    ,args[2] as String
                                    ,args[3] as String
                                    ,args[4] as String
                                    ,args[5] as String
                                    ,args[6] as String
                                    ,args[7] as String))

        }
    })

    fun onArrowImgBtnClicked(view: View)
    {
        var messageBody = msgMultilineTxt.text.toString()
        var userId = UserObj.userProfile._id
        var channelId  = ChannelObj.channelSelected!!.id
        var userName = UserObj.userProfile.name
        var userAvatar = UserObj.userProfile.avatarName
        var userAvatarColor = UserObj.userProfile.avatarColor

        socket.emit("newMessage"
            , messageBody
            , userId
            , channelId
            , userName
            , userAvatar
            , userAvatarColor
        )

        Log.d("newMessage" , "messageBody:${messageBody}"+
                ", userId:${userId}"+
                ", channelId:${channelId}"+
                ", userName:${userName}"+
                ", userAvatar:${userAvatar}"+
                ", userAvatarColor:${userAvatarColor}" )

        msgMultilineTxt.text.clear()
        hideKeyboard()
    }

    val loginReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?)
        {
            if (AuthObj.isLoggIn)
            {
                wchatTxt.text = "Log-In"
                emailTxt.text = UserObj.userProfile?.email
                channelNameTxt.text = UserObj.userProfile?.name
                loginBtn.text = "Log-out"

                val identifier = resources.getIdentifier(UserObj.userProfile?.avatarName,"drawable",packageName)

                userImg.setImageResource(identifier)
                userImg.setBackgroundColor(UtilString.stringToColor( UserObj.userProfile?.avatarColor!!))

                callFindChannels()

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


    fun callFindChannels()
    {
        ChannelService.findChannels(this
            ,
            {
                    esito: Boolean, response: JSONArray ->
                if(esito)
                {
                    for (x in 0  until response.length())
                    {
                        val responseJson : JSONObject = response.getJSONObject(x)

                        val channel : Channel = Channel(responseJson.getString("name")
                            , responseJson.getString("description")
                            , responseJson.getString("_id"))

                        ChannelObj.listChannel.add(channel)
                        ChannelObj.channelSelected = channel
                        nameChannelAdapter.notifyDataSetChanged()

                        updateChannelNameTxt(channel.name)

                        callFindMessages()
                    }


                    Toast.makeText(this, "Channels found successfully", Toast.LENGTH_SHORT).show()
                }
                else
                {
                    Toast.makeText(this, "Channels found error", Toast.LENGTH_SHORT).show()
                }

                CompleteObj.esitoLoginUser = esito
            })

    }

    fun callFindMessages()
    {
        MessageService.findMessages(this, ChannelObj.channelSelected!!
            ,
            {
                    esito: Boolean, response: JSONArray ->
                if(esito)
                {
                    for (x in 0  until response.length())
                    {
                        val responseJson : JSONObject = response.getJSONObject(x)

                        var _id = responseJson.getString("_id")
                        var messageBody = responseJson.getString("messageBody")
                        var userId = responseJson.getString("userId")
                        var channelId = responseJson.getString("channelId")
                        var userName = responseJson.getString("userName")
                        var userAvatar = responseJson.getString("userAvatar")
                        var userAvatarColor = responseJson.getString("userAvatarColor")
                        var timeStamp = responseJson.getString("timeStamp")

                        val message : Message = Message(messageBody
                        ,  userId
                        ,  channelId
                        ,  userName
                        ,  userAvatar
                        ,  userAvatarColor
                        ,  _id
                        ,  timeStamp)

                        Log.d("callFindMessages" ,message.toString())

                            MessageObj.listMessage.add(message)
                    }


                    Toast.makeText(this, "Messages found successfully", Toast.LENGTH_SHORT).show()
                }
                else
                {
                    Toast.makeText(this, "Messages found error", Toast.LENGTH_SHORT).show()
                }

                CompleteObj.esitoLoginUser = esito
            })

    }


    override fun onDestroy() {
        ChannelObj.clear()
        super.onDestroy()
    }


}
