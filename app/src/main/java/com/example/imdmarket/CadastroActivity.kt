package com.example.imdmarket

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.imdmarket.databinding.ActivityCadastroBinding
import com.example.imdmarket.model.Produto
import com.example.imdmarket.model.listaDeProdutos
import com.google.gson.Gson

class CadastroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSalvar.setOnClickListener {
            var codigo = binding.etCodigo.text.toString()
            var nome = binding.etNome.text.toString()
            var descricao = binding.etDescricao.text.toString()
            var estoque = binding.etEstoque.text.toString()

            if (codigo.isNotEmpty() && nome.isNotEmpty() && descricao.isNotEmpty() && estoque.isNotEmpty()) {
                listaDeProdutos.add(Produto(codigo, nome, descricao, estoque.toInt()))
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
}