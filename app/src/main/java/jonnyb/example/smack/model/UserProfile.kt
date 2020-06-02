package jonnyb.example.smack.model

import org.json.JSONObject

data class UserProfile constructor(   var _id : String
                                    , var name : String
                                    , var email : String
                                    , var avatarName : String
                                    , var avatarColor : String) : IModel


{
    override fun toRequest() : String {
        return toString()
    }

    override fun toString(): String {

        val jsonObject : JSONObject = JSONObject();


        jsonObject.put("name", name)
        jsonObject.put("email", email)
        jsonObject.put("avatarName", avatarName)
        jsonObject.put("avatarColor", avatarColor)

        return jsonObject.toString()

    }
}
