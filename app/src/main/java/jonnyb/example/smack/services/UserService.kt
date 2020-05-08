package jonnyb.example.smack.services

import android.content.Context
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import jonnyb.example.smack.model.BaseStringRequest
import jonnyb.example.smack.model.User
import jonnyb.example.smack.model.UserProfile
import jonnyb.example.smack.obj.AuthObj
import jonnyb.example.smack.utilities.Constants

object UserService
{
    fun addUser(context: Context, userProfile : UserProfile, complete : (Boolean, String) -> Unit ){

        var uri : String = Constants.URI_CREATE_USER

        var mapHeader : MutableMap<String,String> = mutableMapOf();
        mapHeader.put("Authorization","Bearer ${AuthObj.token}")

        val baseStringRequest : BaseStringRequest = BaseStringRequest(
            uri
            ,userProfile
            , "application/json; charset=utf-8"
            , Response.Listener<String> {
                    response -> complete(true, response)}
            , Response.ErrorListener { error -> complete(false, error.message!!) } , mapHeader )

        Volley.newRequestQueue(context).add(baseStringRequest)

    }
}