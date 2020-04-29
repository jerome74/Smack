package jonnyb.example.smack.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import jonnyb.example.smack.R
import jonnyb.example.smack.ui.user.UserActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }


    fun onLoginBtnClicked(view : View){

    }

    fun onSignupBtnClicked(view : View){
        val intentSingup : Intent = Intent(this, UserActivity::class.java)
        startActivity(intentSingup)
    }
}
