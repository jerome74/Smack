package jonnyb.example.smack.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import jonnyb.example.smack.R
import jonnyb.example.smack.model.User
import jonnyb.example.smack.model.UserProfile
import jonnyb.example.smack.obj.AuthObj
import jonnyb.example.smack.obj.CompleteObj
import jonnyb.example.smack.obj.UserObj
import jonnyb.example.smack.services.EmailService
import jonnyb.example.smack.services.LoginService
import jonnyb.example.smack.ui.user.UserActivity
import jonnyb.example.smack.utilities.Constants
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_user.*
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        manageSpinner(true, View.INVISIBLE)
    }


    fun onLoginBtnClicked(view : View){

        val user : User = User(nameLoginTxt.text.toString(),passLoginTxt.text.toString())
        manageSpinner(false, View.VISIBLE)
        callLoginUser(user)

    }

    fun onSignupBtnClicked(view : View){
        val intentSingup : Intent = Intent(this, UserActivity::class.java)
        startActivity(intentSingup)
    }

    /**
     *
     */

    fun callLoginUser(user : User)
    {
        LoginService.loginUser(this
            ,user,
            {
                    esito: Boolean, messaggio: String ->
                if(esito)
                {
                    val responseJson : JSONObject = JSONObject(messaggio)
                    AuthObj.isLoggIn = true
                    AuthObj.token = responseJson.getString("token")

                    Toast.makeText(this, "user Login successfully", Toast.LENGTH_SHORT).show()
                    callFindProfileByEmail(user)
                }
                else
                {
                    Toast.makeText(this, "login error : $messaggio", Toast.LENGTH_SHORT).show()
                }

                CompleteObj.esitoLoginUser = esito
            })

    }

    fun callFindProfileByEmail(user : User)
    {
        EmailService.findProfileByEmail(this
            ,user,
            {
                    esito: Boolean, messaggio: String ->
                if(esito)
                {
                    val responseJson : JSONObject = JSONObject(messaggio)

                    val userProfile : UserProfile = UserProfile(responseJson.getString("name")
                                                                , responseJson.getString("email")
                                                                , responseJson.getString("avatarName")
                                                                , responseJson.getString("avatarColor"))
                    UserObj.userProfile = userProfile
                    manageSpinner(true, View.INVISIBLE)
                    LocalBroadcastManager.getInstance(this).sendBroadcast(Intent(Constants.BROADCAST_LOGIN))
                    finish()

                    Toast.makeText(this, "profile found successfully", Toast.LENGTH_SHORT).show()

                }
                else
                {
                    Toast.makeText(this, "profile found error : $messaggio", Toast.LENGTH_SHORT).show()
                }

                CompleteObj.esitoLoginUser = esito
            })

    }

    fun manageSpinner(enable: Boolean, visibility : Int)
    {
        logInpssBar.visibility = visibility;

        nameLoginTxt.isEnabled    = enable
        passLoginTxt.isEnabled   = enable
        loginBtn.isEnabled = enable
        signupBtn.isEnabled = enable
    }
}
