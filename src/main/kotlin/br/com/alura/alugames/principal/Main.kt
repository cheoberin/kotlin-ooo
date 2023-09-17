package br.com.alura.alugames.principal

import br.com.alura.alugames.modelo.Gamer
import br.com.alura.alugames.modelo.Jogo
import br.com.alura.alugames.servicos.ConsumoApi
import java.util.*


fun main() {

    val leitura = Scanner(System.`in`)
    val gamer = Gamer.criarGamer(leitura)
    do {

        println("Digite um código de jogo para buscar:")
        val busca = leitura.nextLine()

        var meuJogo: Jogo? = null

        val resultado = runCatching {
            val buscaApi = ConsumoApi()
            val informacaoJogo = buscaApi.buscaJogo(busca)
            meuJogo = Jogo(
                informacaoJogo.info.title, informacaoJogo.info.thumb
            )
        }

        resultado.onFailure {
            println("Jogo inexistente. Tente outro id.")
        }

        resultado.onSuccess {
            println("Deseja inserir uma descrição personalizada? S/N")
            val opcao = leitura.nextLine()
            if (opcao.equals("s", true)) {
                println("Insira a descrição personalizado para o jogo:")
                val descricaoPersonalizada = leitura.nextLine()
                meuJogo?.descricao = descricaoPersonalizada
            } else {
                meuJogo?.descricao = meuJogo?.titulo
            }
            gamer.jogos.add(meuJogo)
        }
        println("Deseja buscar um novo jogo? S/N")
        val pergunta = leitura.nextLine()
    } while (pergunta.equals("S", true))

    println("Jogos buscados:")
    println(gamer.jogos)
    println("Jogos ordenados por título")

    gamer.jogos.sortBy {
        it?.titulo
    }

    gamer.jogos.forEach {
        println("Título: " + it?.titulo)
    }

    for (jogo in gamer.jogos) {
        println("Título: " + jogo?.titulo)
    }

    val jogosFiltrados = gamer.jogos.filter {
        it?.titulo?.contains("Batman", true) ?: false
    }

    println(jogosFiltrados)

    println("Busca finalizada com sucesso.")
}