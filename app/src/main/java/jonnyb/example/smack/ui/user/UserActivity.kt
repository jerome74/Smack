package jonnyb.example.smack.ui.user

import android.content.res.Resources
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import jonnyb.example.smack.R
import kotlinx.android.synthetic.main.activity_user.*
import java.util.*

class UserActivity : AppCompatActivity() {

    var userAvatar : String = "profileDefault"
    var avatarColor : String = "[0.5, 0.5, 0.5, 1]"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
    }

    fun onCreateUserBtnClicked(view: View) {}
    fun onGenBkColorBtnClicked(view: View)
    {
        val r : Int= Random().nextInt(255)
        val g : Int= Random().nextInt(255)
        val b : Int= Random().nextInt(255)

        avtAddImg.setBackgroundColor(Color.rgb(r,g,b))

        avatarColor = "[${r.toDouble() / 255}, ${g.toDouble() / 255}, ${b.toDouble() / 255}, 1]"
    }

    /**
     *
     */
    fun onAvtAddImgClicked(view: View) {


        var resourceId : Int

        val color = Random().nextInt(2)
        val avatar = Random().nextInt(28)

        if(color > 0)
             userAvatar = "dark$avatar"
        else
             userAvatar = "light$avatar"

        resourceId = resources.getIdentifier(userAvatar, "drawable", packageName)
        avtAddImg.setImageResource(resourceId)
    }
}
