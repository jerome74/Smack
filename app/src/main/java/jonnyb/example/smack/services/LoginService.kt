package jonnyb.example.smack.services

import android.content.Context
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import jonnyb.example.smack.model.BaseStringRequest
import jonnyb.example.smack.model.User
import jonnyb.example.smack.utilities.Constants
import org.json.JSONObject

object LoginService {


    fun loginUser(context: Context, user : User, complete : (Boolean, String) -> Unit ){

        var uri : String = Constants.URI_LOGIN

        val baseStringRequest : BaseStringRequest = BaseStringRequest(
            uri
            ,user
            , "application/json; charset=utf-8"
            , Response.Listener<String> {
                    response -> complete(true, response)}
            , Response.ErrorListener { error -> complete(false, error.message!!) } , null )

        Volley.newRequestQueue(context).add(baseStringRequest)

    }


}