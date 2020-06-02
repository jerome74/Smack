package jonnyb.example.smack.obj

import jonnyb.example.smack.model.UserProfile
import jonnyb.example.smack.utilities.ThisApplication

object UserObj
{
    var userAvatar : String = "profileDefault"
    var avatarColor : String = "[0.5, 0.5, 0.5, 1]"

    //var userProfile : UserProfile? = null;
    var userProfile
        get() = ThisApplication.shardPrefs.userProfile()!!
        set(value) = ThisApplication.shardPrefs.userProfile(value)



    fun reset() {
         userAvatar    = "profileDefault"
         avatarColor   = "[0.5, 0.5, 0.5, 1]"
    }
}