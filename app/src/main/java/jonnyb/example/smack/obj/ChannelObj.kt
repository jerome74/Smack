package jonnyb.example.smack.obj

import jonnyb.example.smack.model.Channel

object ChannelObj
{
    var listChannel = ArrayList<Channel>()
    var nameListChannel : List<String> =  ArrayList<String>()

    fun getArrayOfString()
    {
        nameListChannel = listChannel.map { channel -> channel.name }
    }
}