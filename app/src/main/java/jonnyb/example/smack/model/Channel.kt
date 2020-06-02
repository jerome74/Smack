package jonnyb.example.smack.model

class Channel(var name : String , var description : String , var id : String) {

    override fun toString(): String {
        return "#${name}"
    }

}