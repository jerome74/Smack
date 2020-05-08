package jonnyb.example.smack.ui.user

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import jonnyb.example.smack.R
import jonnyb.example.smack.model.Account
import jonnyb.example.smack.model.User
import jonnyb.example.smack.model.UserProfile
import jonnyb.example.smack.obj.AuthObj
import jonnyb.example.smack.obj.CompleteObj
import jonnyb.example.smack.obj.UserObj
import jonnyb.example.smack.services.AuthService
import jonnyb.example.smack.services.LoginService
import jonnyb.example.smack.services.UserService
import kotlinx.android.synthetic.main.activity_user.*
import org.json.JSONObject
import java.util.*

class UserActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
    }

    fun onCreateUserBtnClicked(view: View)
    {
        val account : Account = Account(emailAddTxt.text.toString(), pwdAddTxt.text.toString())
        val user : User = User(emailAddTxt.text.toString(),pwdAddTxt.text.toString() )
        val userProfile : UserProfile = UserProfile(userAddTxt.text.toString()
            ,emailAddTxt.text.toString()
            ,UserObj.userAvatar
            ,UserObj.avatarColor)

        callregisterUser(account,user,userProfile)


    }


    fun onGenBkColorBtnClicked(view: View)
    {
        val r : Int= Random().nextInt(255)
        val g : Int= Random().nextInt(255)
        val b : Int= Random().nextInt(255)

        avtAddImg.setBackgroundColor(Color.rgb(r,g,b))
        UserObj.avatarColor = "[${r.toDouble() / 255}, ${g.toDouble() / 255}, ${b.toDouble() / 255}, 1]"
    }

    /**
     *
     */
    fun onAvtAddImgClicked(view: View) {


        var resourceId : Int

        val color = Random().nextInt(2)
        val avatar = Random().nextInt(28)

        if(color > 0)
            UserObj.userAvatar = "dark$avatar"
        else
            UserObj.userAvatar = "light$avatar"

        resourceId = resources.getIdentifier(UserObj.userAvatar, "drawable", packageName)
        avtAddImg.setImageResource(resourceId)
    }

    /**
     *
     */

    fun callregisterUser(account : Account, user: User, userProfile: UserProfile)
    {
        AuthService.registerUser(this
            ,account
            , { esito: Boolean, messaggio: String ->
                if(esito)
                {
                    Toast.makeText(this, "user registered successfully", Toast.LENGTH_SHORT).show()
                    callLoginUser(user,userProfile)
                }
                else{
                    Toast.makeText(this, "register error : $messaggio", Toast.LENGTH_SHORT).show()
                }

                CompleteObj.esitoRegisterUser = esito

            })

    }

    /**
     *
     */

    fun callLoginUser(user : User,userProfile: UserProfile)
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
                    callcreateUser(userProfile)
                }
                else
                {
                    Toast.makeText(this, "login error : $messaggio", Toast.LENGTH_SHORT).show()
                }

                CompleteObj.esitoLoginUser = esito
            })

    }

    /**
     *
     */

    fun callcreateUser(userProfile: UserProfile)
    {
        UserService.addUser(this
            ,userProfile,
            {
                    esito: Boolean, messaggio: String ->
                if(esito)
                {
                    val responseJson : JSONObject = JSONObject(messaggio)
                    Toast.makeText(this, "user added successfully", Toast.LENGTH_SHORT).show()
                    UserObj.userProfile = userProfile
                    UserObj.IdUserProfile = responseJson.getString("_id")
                }
                else
                {
                    Toast.makeText(this, "user add error : $messaggio", Toast.LENGTH_SHORT).show()
                }

                CompleteObj.esitoAddUser = esito
                finish()
            })
    }

}
