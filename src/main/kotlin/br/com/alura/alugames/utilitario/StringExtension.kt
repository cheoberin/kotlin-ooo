package br.com.alura.alugames.utilitario

import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

fun String.transformarEmIdade(): Int? {

    if (this.isBlank()) {
        return null
    }

    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val dataNascimento = LocalDate.parse(this, formatter)
    val hoje = LocalDate.now()
    return Period.between(dataNascimento, hoje).years
}