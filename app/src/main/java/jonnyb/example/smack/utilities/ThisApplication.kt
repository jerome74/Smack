package jonnyb.example.smack.utilities

import android.app.Application

class ThisApplication : Application()
{
    companion object{
        lateinit var shardPrefs : SharedPrefs
    }

    override fun onCreate() {
        shardPrefs = SharedPrefs(applicationContext)
        super.onCreate()

    }
}