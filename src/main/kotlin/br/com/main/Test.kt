package br.com.main
import br.com.model.Player

fun main(){
    val gamer1 = Player(name="Gustavo", email="fdfdf@fdfd.com", dateOfBirth = "18/04/1999", userName = "fdfdfdffd")

    println(gamer1)

    gamer1.let{
        it.dateOfBirth ="18/04/1999"
        it.userName = "gugudeboc"
    }
    println(gamer1)

}