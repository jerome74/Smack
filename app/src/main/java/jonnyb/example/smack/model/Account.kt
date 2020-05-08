package jonnyb.example.smack.model

import org.json.JSONObject

data class Account constructor(var email: String, var password : String) : IModel
 {
     override fun toRequest() : String {
         return toString()
     }

     override fun toString(): String {

         val jsonObject : JSONObject = JSONObject();

         jsonObject.put("email", email)
         jsonObject.put("password", password)

         return jsonObject.toString()

     }
 }