package jonnyb.example.smack.obj

import android.content.Context
import android.content.SharedPreferences
import jonnyb.example.smack.utilities.ThisApplication

object AuthObj
{
    var token
        get() = ThisApplication.shardPrefs.token()!!
        set(value) = ThisApplication.shardPrefs.token(value)

    var isLoggIn
        get() = ThisApplication.shardPrefs.isLoggIn()!!
        set(value) = ThisApplication.shardPrefs.isLoggIn(value)

    var email
        get() = ThisApplication.shardPrefs.email()!!
        set(value) = ThisApplication.shardPrefs.email(value)

    fun reset()
    {
         token  = ""
         isLoggIn = false
    }
}

