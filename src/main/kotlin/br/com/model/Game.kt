package br.com.model

data class Game (val title:String,
                 val thumb:String){
    var description:String? = null

    override fun toString(): String {
        return "My game:\n" +
                "Title: $title \n" +
                "Cover: $thumb \n"+
                "Description: $description \n"
    }

}