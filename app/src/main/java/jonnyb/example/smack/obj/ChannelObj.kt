package jonnyb.example.smack.obj

import jonnyb.example.smack.model.Channel

object ChannelObj
{
    var listChannel = ArrayList<Channel>()
    var channelSelected : Channel? = null

    fun clear() {
        listChannel.clear()
        channelSelected = null
    }
}