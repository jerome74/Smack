package jonnyb.example.smack.services

import android.content.Context
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import jonnyb.example.smack.model.BaseStringPostRequest
import jonnyb.example.smack.model.User
import jonnyb.example.smack.utilities.Constants
import java.lang.Exception

object LoginService {


    fun loginUser(context: Context, user : User, complete : (Boolean, String) -> Unit ){

        var uri : String = Constants.URI_LOGIN

        val baseStringRequest : BaseStringPostRequest = BaseStringPostRequest(
            uri
            ,user
            , "application/json; charset=utf-8"
            , Response.Listener<String> {
                    response -> complete(true, response)}
            , Response.ErrorListener { error ->
                try {
                    complete(false, error.message!!)
                }catch (e : Exception){
                    //Toast.makeText(context, "email and/or password incorrect.", Toast.LENGTH_SHORT).show()
                    complete(false, "email and/or password incorrect.")
                }
                     } , null )

        Volley.newRequestQueue(context).add(baseStringRequest)

    }


}