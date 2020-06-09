package jonnyb.example.smack.adapter

import android.content.Context
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import jonnyb.example.smack.R
import jonnyb.example.smack.model.Message
import jonnyb.example.smack.obj.UserObj
import jonnyb.example.smack.utilities.UtilString
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MessageAdapter(val context: Context, val messages : ArrayList<Message>) : RecyclerView.Adapter<MessageAdapter.ViewHolder>() {

    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.message_list_view,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return messages.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindingMessage(context, messages[position])
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {
        val userImage = itemView.findViewById<ImageView>(R.id.imgListMessage)
        val userTxt = itemView.findViewById<TextView>(R.id.tvOwnListMessage)
        val dateTxt = itemView.findViewById<TextView>(R.id.tvDateListMessage)
        val contentTxt = itemView.findViewById<TextView>(R.id.tvContentListMessage)

        val userImageO = itemView.findViewById<ImageView>(R.id.imgListMessageO)
        val userTxtO = itemView.findViewById<TextView>(R.id.tvOwnListMessageO)
        val dateTxtO = itemView.findViewById<TextView>(R.id.tvDateListMessageO)
        val contentTxtO = itemView.findViewById<TextView>(R.id.tvContentListMessageO)

        fun bindingMessage(context: Context, message: Message)
        {
            if(UserObj.userProfile?.name == message.userName)
            {
                setVisibility(View.VISIBLE, View.INVISIBLE)

                val resourceId = context.resources.getIdentifier(message.userAvatar,"drawable", context.packageName)
                userImage.setImageResource(resourceId)
                userImage.setBackgroundColor(UtilString.stringToColor(message.userAvatarColor)!!)

                userTxt.text = message.userName

                dateTxt.text =  SimpleDateFormat("E, dd - HH:mm", Locale.getDefault()).format(dateFormat.parse(message.timeStamp))
                contentTxt.text = message.messageBody

            }
            else
            {
                setVisibility(View.INVISIBLE, View.VISIBLE)

                val resourceId = context.resources.getIdentifier(message.userAvatar,"drawable", context.packageName)
                userImageO.setImageResource(resourceId)
                userImageO.setBackgroundColor(UtilString.stringToColor(message.userAvatarColor)!!)

                userTxtO.text = message.userName

                dateTxtO.text =  SimpleDateFormat("E, dd - HH:mm", Locale.getDefault()).format(dateFormat.parse(message.timeStamp))
                contentTxtO.text = message.messageBody
            }

        }

        private fun setVisibility(left : Int , right : Int)
        {
            userImage.visibility = left
            userTxt.visibility = left
            dateTxt.visibility = left
            contentTxt.visibility = left

            userImageO.visibility = right
            userTxtO.visibility = right
            dateTxtO.visibility = right
            contentTxtO.visibility = right
        }

    }
}