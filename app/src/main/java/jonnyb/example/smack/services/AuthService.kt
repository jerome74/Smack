package jonnyb.example.smack.services

import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import jonnyb.example.smack.model.BaseStringRequest
import jonnyb.example.smack.model.User
import jonnyb.example.smack.utilities.Constants
import org.json.JSONObject

object AuthService {


    fun registerUser(context: Context, user : User , complete : (Boolean) -> Unit ){

        var uri : String = Constants.URI_REGISTER

        val baseStringRequest : BaseStringRequest = BaseStringRequest(
                uri
                ,user
                , "application/json; charset=utf-8"
                ,Response.Listener { it -> complete(true)}
                ,Response.ErrorListener { error -> complete(false) } )

        Volley.newRequestQueue(context).add(baseStringRequest)

    }

}