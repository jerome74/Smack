package jonnyb.example.smack.services

import android.content.Context
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import jonnyb.example.smack.model.BaseArrayStringPostRequest
import jonnyb.example.smack.model.Channel
import jonnyb.example.smack.obj.AuthObj
import jonnyb.example.smack.utilities.Constants
import org.json.JSONArray

object MessageService {


    fun findMessages(context: Context, channel: Channel ,complete : (Boolean, JSONArray) -> Unit ){

        var uri : String = Constants.URI_FIND_MESSAGES + "/${channel.id}"

        var mapHeader : MutableMap<String,String> = mutableMapOf();
        mapHeader.put("Authorization","Bearer ${AuthObj.token}")

        val baseArrayStringPostRequest : BaseArrayStringPostRequest = BaseArrayStringPostRequest(
            uri
            ,null
            , "application/json; charset=utf-8"
            , Response.Listener<JSONArray> {
                    response -> complete(true, response)}
            , Response.ErrorListener { error ->
                try {
                    complete(false, JSONArray(){error.message!!})
                }catch (e : Exception){
                    //Toast.makeText(context, "email and/or password incorrect.", Toast.LENGTH_SHORT).show()
                    complete(false, JSONArray(){"\"error\":${error.message!!}"})
                }
            } , mapHeader )

        Volley.newRequestQueue(context).add(baseArrayStringPostRequest)

    }


}