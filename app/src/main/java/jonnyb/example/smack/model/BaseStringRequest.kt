package jonnyb.example.smack.model

import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest

class BaseStringRequest constructor(var uri: String
                                    ,val model : IModel
                                    ,val contentType : String
                                    ,val responseOK : Response.Listener<String>
                                    ,val responseError : Response.ErrorListener) :
                                    StringRequest(Request.Method.POST
                                                    ,uri
                                                    , responseOK
                                                    , responseError) {

    override fun getParams(): MutableMap<String, String> {
        return super.getParams()
    }

    override fun getBody(): ByteArray {
        return model.toRequest().toByteArray()
    }

    override fun getBodyContentType(): String {
        return contentType
    }
}