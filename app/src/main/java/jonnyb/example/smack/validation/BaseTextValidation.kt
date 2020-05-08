package jonnyb.example.smack.validation

import android.content.Context
import android.content.res.Resources
import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import android.widget.Toast

class BaseTextValidation constructor(val context : Context, val textViews: List<TextView> , val field : List<String>)
{
    fun validate() : Boolean
    {
        var i = 0;
        for( textView in textViews )
        {
            textView.text.toString().ifEmpty {

                Toast.makeText(context, "Alert, insert value on : ${field.get(i)}", Toast.LENGTH_SHORT).show()
                return false
            }
            i++
        }

        return true;
    }
}