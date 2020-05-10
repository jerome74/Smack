package jonnyb.example.smack.model

import com.android.volley.Response
import com.android.volley.toolbox.StringRequest

class BaseStringPostRequest constructor(
    uri: String
    , val model: IModel
    , val contentType: String
    , responseOK: Response.Listener<String>
    , responseError: Response.ErrorListener
    , val mapHeaders: MutableMap<String, String>?
) :
    StringRequest(
        Method.POST
        , uri
        , responseOK
        , responseError
    ) {

    override fun getParams(): MutableMap<String, String> {
        return super.getParams()
    }

    override fun getBody(): ByteArray {
        return model.toRequest().toByteArray()
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