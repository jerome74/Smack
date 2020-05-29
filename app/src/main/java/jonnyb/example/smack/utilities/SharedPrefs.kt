package jonnyb.example.smack.utilities

import android.content.Context
import android.util.Log
import jonnyb.example.smack.model.UserProfile
import kotlinx.coroutines.newFixedThreadPoolContext

class SharedPrefs(val context : Context)
{
    fun isLoggIn() : Boolean{
        Log.d("#isLoggIn","get ${context.getSharedPreferences("prefs",0).getBoolean("IsLoggIn",false)}")
        return context.getSharedPreferences("prefs",0).getBoolean("IsLoggIn",false)
    }

    fun isLoggIn(value : Boolean) : Unit{
        context.getSharedPreferences("prefs",0).edit().putBoolean("IsLoggIn", value).apply()
        Log.d("#isLoggIn", "set ${value}")
    }

    fun token() : String{
        Log.d("#token", "get ${context.getSharedPreferences("prefs",0).getString("token","")!!}")
        return context.getSharedPreferences("prefs",0).getString("token","")!!
    }

    fun token(value : String) : Unit{
        context.getSharedPreferences("prefs",0).edit().putString("token", value).apply()
        Log.d("#token", "set ${value}")
    }

    fun email() : String{
        Log.d("#email", "get ${context.getSharedPreferences("prefs",0).getString("email","")!!}")
        return context.getSharedPreferences("prefs",0).getString("email","")!!
    }

    fun email(value : String) : Unit{
        context.getSharedPreferences("prefs",0).edit().putString("email", value).apply()
        Log.d("#email", "set ${value}")

    }

    fun userProfile() : UserProfile{
        Log.d("#userProfile", "get ${context.getSharedPreferences("prefs",0).getString("email","")!!}")
        return UserProfile(context.getSharedPreferences("prefs",0).getString("userProfile.name","")!!
                           , context.getSharedPreferences("prefs",0).getString("userProfile.email","")!!
                           , context.getSharedPreferences("prefs",0).getString("userProfile.avatarName","")!!
                           , context.getSharedPreferences("prefs",0).getString("userProfile.avatarColor","")!!)
    }

    fun userProfile(value : UserProfile) : Unit{
        context.getSharedPreferences("prefs",0).edit().putString("userProfile.name", value.name).apply()
        context.getSharedPreferences("prefs",0).edit().putString("userProfile.email", value.email).apply()
        context.getSharedPreferences("prefs",0).edit().putString("userProfile.avatarName", value.avatarName).apply()
        context.getSharedPreferences("prefs",0).edit().putString("userProfile.avatarColor", value.avatarColor).apply()
        Log.d("#userProfile", "set ${value}")

    }

}