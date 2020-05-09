package jonnyb.example.smack.obj

import jonnyb.example.smack.model.UserProfile

object UserObj
{
    var userAvatar : String = "profileDefault"
    var avatarColor : String = "[0.5, 0.5, 0.5, 1]"
    var userProfile : UserProfile? = null;
    var IdUserProfile : String? = null;

    fun reset() {
         userAvatar    = "profileDefault"
         avatarColor   = "[0.5, 0.5, 0.5, 1]"
         userProfile   = null;
         IdUserProfile = null;
    }
}