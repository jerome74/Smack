package jonnyb.example.smack.utilities

import android.graphics.Color
import java.util.*

object UtilString
{
    fun stringToColor(str : String) : Int? {

        var rgb : Int? = null

        try {
            var scanner = Scanner(str.replace("[", "").replace("]", "").replace(",", ""))

            if (scanner.hasNext())
                rgb =  Color.rgb(
                    (scanner.nextDouble() * 255).toInt(),
                    (scanner.nextDouble() * 255).toInt(),
                    (scanner.nextDouble() * 255).toInt()
                )

        }catch (e : Exception)
        {
            rgb =  Color.rgb((0.5 * 255).toInt(),(0.5 * 255).toInt(),(0.5 * 255).toInt())
        }

        return rgb!!
    }
}