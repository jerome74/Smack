package jonnyb.example.smack.obj

object AuthObj
{
    var token : String = ""
    var isLoggIn : Boolean = false

    fun reset()
    {
         token  = ""
         isLoggIn = false
    }
}

