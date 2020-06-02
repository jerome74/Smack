package jonnyb.example.smack.model

class Message(
    var messageBody: String
    , var userId: String
    , var channelId: String
    , var userName: String
    , var userAvatar: String
    , var userAvatarColor: String
    , var id: String
    , var timeStamp: String
) {

    override fun toString(): String {
        return "messageBody: ${messageBody}" +
                ", userId: ${userId}" +
                ", channelId: ${channelId}" +
                ", userName: ${userName}" +
                ", userAvatar: ${userAvatar}" +
                ", userAvatarColor: ${userAvatarColor}" +
                ", id: ${id}" +
                ", timeStamp: ${timeStamp}"
    }
}