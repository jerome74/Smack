package jonnyb.example.smack.utilities

import android.graphics.Color
import java.util.*

object UtilString
{
    fun stringToColor(str : String) : Int {

        var scanner = Scanner(str.replace("[","").replace("]","").replace(",",""))

        if(scanner.hasNext())
            return Color.rgb((scanner.nextDouble() * 255).toInt(),(scanner.nextDouble() * 255).toInt(),(scanner.nextDouble() * 255).toInt())

        return 0

    }
}