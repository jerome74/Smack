package jonnyb.example.smack.ui.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import jonnyb.example.smack.R

class UserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
    }

    fun onCreateUserBtnClicked(view: View) {}
    fun onGenBkColorBtnClicked(view: View) {}
    fun onAvtAddImgClicked(view: View) {}
}
