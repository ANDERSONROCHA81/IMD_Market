package com.example.imdmarket

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.imdmarket.databinding.ActivityAlteracaoBinding
import com.example.imdmarket.model.Produto
import com.example.imdmarket.model.listaDeProdutos
import com.google.gson.Gson

class AlteracaoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlteracaoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAlteracaoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAlterar.setOnClickListener {
            var codigo = binding.etCodigo.text.toString()
            var nome = binding.etNome.text.toString()
            var descricao = binding.etDescricao.text.toString()
            var estoque = binding.etEstoque.text.toString()

            if (codigo.isNotEmpty()) {
                var produto = listaDeProdutos.find {
                    it.codigoProduto == codigo
                }
                if (produto == null) {
                    Toast.makeText(this, "Produto não encontrado!", Toast.LENGTH_LONG).show()
                } else {
                    if (nome.isNotEmpty() && descricao.isNotEmpty() && estoque.isNotEmpty()) {
                        produto.codigoProduto = codigo
                        produto.nomeProduto = nome
                        produto.descricaoProduto = descricao
                        produto.estoque = estoque.toInt()
                        listaDeProdutos[listaDeProdutos.indexOf(produto)] = produto
                        saveArrayList(this, "produtos", listaDeProdutos)
                        Toast.makeText(this, "Produto alterado com sucesso!", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this, "Preencha todos campos", Toast.LENGTH_LONG).show()
                    }
                }
            } else {
                Toast.makeText(this, "Informe o código", Toast.LENGTH_LONG).show()
            }
        }

        binding.btnLimpar.setOnClickListener {
            binding.etCodigo.setText(getString(R.string.campo_vazio))
            binding.etNome.setText(getString(R.string.campo_vazio))
            binding.etDescricao.setText(getString(R.string.campo_vazio))
            binding.etEstoque.setText(getString(R.string.campo_vazio))
        }
    }

    fun saveArrayList(context: Context, key: String, list: MutableList<Produto>) {
        val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()

        // Converte o ArrayList para JSON
        val json = gson.toJson(list)
        editor.putString(key, json)
        editor.apply() // Salva as alterações
    }
}