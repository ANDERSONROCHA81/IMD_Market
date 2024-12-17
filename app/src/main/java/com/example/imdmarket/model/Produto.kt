package com.example.imdmarket.model

data class Produto(
    var codigoProduto: String,
    var nomeProduto: String,
    var descricaoProduto: String,
    var estoque: Int

) {
    override fun toString(): String {
        return "Código = $codigoProduto\nNome = $nomeProduto\nDescrição = $descricaoProduto\nEstoque = $estoque\n"
    }
}

var listaDeProdutos = mutableListOf<Produto>()