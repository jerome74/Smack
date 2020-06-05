package jonnyb.example.smack.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import jonnyb.example.smack.R
import jonnyb.example.smack.model.Message
import jonnyb.example.smack.obj.MessageObj
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

        fun bindingMessage(context: Context, message: Message)
        {
            val resourceId = context.resources.getIdentifier(message.userAvatar,"drawable", context.packageName)
            userImage.setImageResource(resourceId)
            userImage.setBackgroundColor(UtilString.stringToColor(message.userAvatarColor)!!)

            userTxt.text = message.userName

            dateTxt.text =  SimpleDateFormat("E, dd - HH:mm", Locale.getDefault()).format(dateFormat.parse(message.timeStamp))
            contentTxt.text = message.messageBody


        }

    }
}