package jonnyb.example.smack.services

import android.content.Context
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import jonnyb.example.smack.model.BaseArrayStringPostRequest
import jonnyb.example.smack.utilities.Constants
import org.json.JSONArray

object ChannelService {


    fun findChannels(context: Context, complete : (Boolean, JSONArray) -> Unit ){

        var uri : String = Constants.URI_FIND_CHANNELS

        val baseArrayStringPostRequest : BaseArrayStringPostRequest = BaseArrayStringPostRequest(
            uri
            ,null
            , "application/json; charset=utf-8"
            , Response.Listener<JSONArray> {
                    response -> complete(true, response)}
            , Response.ErrorListener { error -> complete(false, JSONArray(){error}) } , null )

        Volley.newRequestQueue(context).add(baseArrayStringPostRequest)

    }


}