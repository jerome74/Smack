package jonnyb.example.smack.model

import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import org.json.JSONArray

class BaseArrayStringPostRequest constructor(
    uri: String
    , val model: IModel?
    , val contentType: String
    , responseOK: Response.Listener<JSONArray>
    , responseError: Response.ErrorListener
    , val mapHeaders: MutableMap<String, String>?
) :
    JsonArrayRequest(
        Method.GET
        , uri
        ,null
        , responseOK
        , responseError
    ) {

    override fun getParams(): MutableMap<String, String> {
        return super.getParams()
    }

    override fun getBody(): ByteArray {
        if (model == null)
            return model?.toRequest()!!.toByteArray()
        else
            return super.getBody()
    }

    override fun getBodyContentType(): String {
        return contentType
    }

    override fun getHeaders(): MutableMap<String, String> {
        if (mapHeaders == null)
            return super.getHeaders()
        else
            return mapHeaders!!
    }
}