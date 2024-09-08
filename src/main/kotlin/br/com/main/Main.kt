package br.com.main

import br.com.model.Game
import br.com.model.InfoGame
import br.com.model.Player
import br.com.services.ApiConsumption
import com.google.gson.Gson
import transformIntoAge
import java.util.Scanner

fun main() {
    val read = Scanner(System.`in`)
    val player = Player.createPlayer(read)

    println("\n Player's age:" + player.dateOfBirth?.transformIntoAge())


    do {
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

        val onFailure = result.onFailure {
            println("Game is not found, try other game!")
            println("----------------- \n")

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
            player.searchedGames.add(myGame)
        }

        println("Would you like to find a new game? S/N")
        val response = read.nextLine()

    }while (response.equals("s", true))

    println("Games search:\n")
    println(player.searchedGames)

    println("\n Games sorted by titles:")
    player.searchedGames.sortBy {
        it?.title
    }

    player.searchedGames.forEach{
        println("Title: " + it?.title)
    }

    println("Search completed!")

}