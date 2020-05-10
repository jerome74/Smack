package jonnyb.example.smack.services

import android.content.Context
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import jonnyb.example.smack.model.BaseStringPostRequest
import jonnyb.example.smack.model.BaseStringQueryRequest
import jonnyb.example.smack.model.User
import jonnyb.example.smack.obj.AuthObj
import jonnyb.example.smack.utilities.Constants

object EmailService {


    fun findProfileByEmail(context: Context, user : User, complete : (Boolean, String) -> Unit ){

        var uri : String = "${Constants.URI_FIND_BY_EMAIL}/${user.email}"

        var mapHeader : MutableMap<String,String> = mutableMapOf();
        mapHeader.put("Authorization","Bearer ${AuthObj.token}")

        val baseStringRequest : BaseStringQueryRequest = BaseStringQueryRequest(
            uri
            ,user
            , "application/json; charset=utf-8"
            , Response.Listener<String> {
                    response -> complete(true, response)}
            , Response.ErrorListener { error -> complete(false, error.message!!) } , mapHeader, null )

        Volley.newRequestQueue(context).add(baseStringRequest)

    }



}