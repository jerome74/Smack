package jonnyb.example.smack.services

import android.content.Context
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import jonnyb.example.smack.model.Account
import jonnyb.example.smack.model.BaseStringPostRequest
import jonnyb.example.smack.utilities.Constants

object AuthService {


    fun registerUser(context: Context, account : Account , complete : (Boolean, String) -> Unit ){

        var uri : String = Constants.URI_REGISTER

        val baseStringRequest : BaseStringPostRequest = BaseStringPostRequest(
                uri
                ,account
                , "application/json; charset=utf-8"
                ,Response.Listener { it -> complete(true, "OK")}
                ,Response.ErrorListener { error -> complete(false, error.message!!) } , null )

        Volley.newRequestQueue(context).add(baseStringRequest)

    }

}