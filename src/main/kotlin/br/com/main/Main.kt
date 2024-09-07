package br.com.main

import br.com.model.Game
import br.com.model.InfoGame
import br.com.services.ApiConsumption
import com.google.gson.Gson
import java.util.Scanner

fun main() {
    val read = Scanner(System.`in`)
    println("Enter the game code:")

    val search = read.nextLine()

    val searchApi = ApiConsumption()

    val json = searchApi.json(search)

    var myGame: Game? = null

    val result = runCatching {
        val myInfoGame = Gson().fromJson(json, InfoGame::class.java)

        myGame = Game(
            myInfoGame.info.title,
            myInfoGame.info.thumb
        )
    }

    result.onFailure{
        println("Game is not found, try other game!")
        println("----------------- \n")
        main()
    }

    result.onSuccess {
        println("Would you like to provide a new description for the game? S/N")
        val option = read.nextLine()
        if (option.equals("s", true)){
            println("Write a new description:")
            val personalizedDescription = read.nextLine()
            myGame?.description = personalizedDescription
        }
        else{
            myGame?.description = myGame?.title

        }
        println(myGame)
    }
}