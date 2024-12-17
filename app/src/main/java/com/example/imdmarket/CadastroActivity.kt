package com.example.imdmarket

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.imdmarket.databinding.ActivityCadastroBinding
import com.example.imdmarket.databinding.ActivityListagemBinding
import com.example.imdmarket.model.Produto
import com.example.imdmarket.model.listaDeProdutos
import com.google.gson.Gson

class CadastroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroBinding
    private lateinit var bindingListagem: ActivityListagemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //var produtos = getArrayList(this, "produto")
//        var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaDeProdutos)
//        bindingListagem.listaProdutos.adapter = adapter

        binding.btnSalvar.setOnClickListener {
            var codigo = binding.etCodigo.text.toString()
            var nome = binding.etNome.text.toString()
            var descricao = binding.etDescricao.text.toString()
            var estoque = binding.etEstoque.text.toString()

            if (codigo.isNotEmpty() && nome.isNotEmpty() && descricao.isNotEmpty() && estoque.isNotEmpty()) {
                listaDeProdutos.add(Produto(codigo, nome, descricao, estoque.toInt()))
                //adapter.notifyDataSetChanged()
                saveArrayList(this, "produtos", listaDeProdutos)
                Toast.makeText(this, "Salvo com sucesso!", Toast.LENGTH_LONG).show()
                val telaMenu  = Intent(this, MenuActivity::class.java)
                startActivity(telaMenu)
            }else{
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_LONG).show()
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
//
//    fun getArrayList(context: Context, key: String): ArrayList<Produto> {
//        val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
//        val gson = Gson()
//
//        // Recupera o JSON
//        val json = sharedPreferences.getString(key, null)
//
//        // Converte o JSON de volta para um ArrayList
//        val type = object : TypeToken<ArrayList<Produto>>() {}.type
//        return gson.fromJson(json, type) ?: ArrayList()
//    }
}