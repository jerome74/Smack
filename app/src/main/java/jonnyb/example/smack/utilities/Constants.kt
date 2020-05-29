package jonnyb.example.smack.utilities



object Constants {

    /**
     * URI
     */

    //######################BASE_URI####################################
    //const val BASE_URL : String = "https://wlproject01.herokuapp.com"
    const val BASE_URL : String = "http://192.168.1.6:3005"
    //const val BASE_URL : String = "http://192.168.43.135:3005"
    //-------------------------------------------------------------------

    const val URI_REGISTER : String = "$BASE_URL/v1/account/register"
    const val URI_LOGIN : String = "$BASE_URL/v1/account/login"
    const val URI_CREATE_USER : String = "$BASE_URL/v1/user/add"
    const val URI_FIND_BY_EMAIL : String = "$BASE_URL/v1/user/byEmail"
    const val URI_FIND_CHANNELS : String = "$BASE_URL/v1/channel"

    /**
     * BROADCAST
     */

    const val BROADCAST_LOGIN = "BROADCAST_9999"
}