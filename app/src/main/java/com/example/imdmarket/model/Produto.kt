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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Produto

        if (codigoProduto != other.codigoProduto) return false
        if (nomeProduto != other.nomeProduto) return false
        if (descricaoProduto != other.descricaoProduto) return false
        if (estoque != other.estoque) return false

        return true
    }

    override fun hashCode(): Int {
        var result = codigoProduto.hashCode()
        result = 31 * result + nomeProduto.hashCode()
        result = 31 * result + descricaoProduto.hashCode()
        result = 31 * result + estoque
        return result
    }
}

var listaDeProdutos = mutableListOf<Produto>()