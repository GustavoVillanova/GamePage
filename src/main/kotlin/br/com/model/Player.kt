package br.com.model

import java.util.Scanner
import kotlin.random.Random

data class Player(var name:String,
                  var email:String){
    var dateOfBirth:String? = null
    var userName:String? = null
        set(value) {
            field = value
            if (internalId.isNullOrBlank()){
                createInternalId()
            }
        }

    var internalId:String? = null
        private set

    val searchedGames = mutableListOf<Game?>()

    constructor(name: String, email: String, dateOfBirth:String, userName:String):
            this(name, email){
                this.dateOfBirth = dateOfBirth
                this.userName = userName
                createInternalId()
            }

    init {
        if (name.isNullOrBlank()){
            throw IllegalArgumentException("The name is blank")
        }

        this.email = validatEmail()
    }

    override fun toString(): String {
        return "Player(name='$name', email='$email', dateOfBirth=$dateOfBirth, userName=$userName, internalId=$internalId)"
    }

    fun createInternalId(){
        val number = Random.nextInt(until = 1000)
        val tag = String.format("%04d", number)
        internalId = "$userName$tag"
    }

    fun validatEmail(): String {
        val regex = Regex("[A-Za-z0-9._%+-]+@[A-Za-z0-9._]+\\.[A-Za-z]{2,6}$")
        if (regex.matches(email)){
            return email
        }else{
            throw IllegalArgumentException("Invalid email!!")
        }
    }

    companion object{
        fun createPlayer(read: Scanner): Player{
            println("Welcome, let's get you registered. Enter your name:\n")
            val name = read.nextLine()
            println("Enter your email address:\n")
            val email = read.nextLine()
            println("Do you want to complete your registration with username and date of birth? S/N \n")
            val option = read.nextLine()

            if (option.equals("s", true)){
                println("Enter your date of birth:")
                val dateOfBirth = read.nextLine()

                println("Enter your Username:")
                val username = read.nextLine()

                return Player(name, email, dateOfBirth, username)
            }else{
                return Player(name, email)
            }
        }
    }
}
