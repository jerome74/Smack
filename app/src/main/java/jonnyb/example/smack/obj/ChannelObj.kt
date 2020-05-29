package jonnyb.example.smack.obj

import jonnyb.example.smack.model.Channel

object ChannelObj
{
    var listChannel = ArrayList<Channel>()
    var nameListChannel = ArrayList<String>()

    fun clear() {
        listChannel.clear()
        nameListChannel.clear()
    }
}