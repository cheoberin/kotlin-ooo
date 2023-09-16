package br.com.alura.alugames.principal

import br.com.alura.alugames.modelo.Gamer

fun main() {

    val gamer1 = Gamer("Airton", "airton@bobbamail.com")
    val gamer2 = Gamer("Luara", "luara@bobbamail.com", "20/12/2023", "luarinhaGEMAPLS")

    gamer1.let {
        it.dataNascimento = "20/12/1995"
        it.usuario = "cheobs"
    }.also {
        println(gamer1.idInterno)
    }

    with(gamer2) {
        println(this)
    }
}