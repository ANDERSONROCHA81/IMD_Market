package com.example.imdmarket

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.imdmarket.databinding.ActivityExclusaoBinding
import com.example.imdmarket.model.Produto
import com.example.imdmarket.model.listaDeProdutos
import com.google.gson.Gson

class ExclusaoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExclusaoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityExclusaoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDeletar.setOnClickListener {
            val codigo = binding.etCodigo.text.toString()

            if (listaDeProdutos.removeIf {
                it.codigoProduto == codigo
            }){
                saveArrayList(this, "produtos", listaDeProdutos)
                Toast.makeText(this, "Produto excluído com sucesso!", Toast.LENGTH_LONG).show()
                val telaMenu  = Intent(this, MenuActivity::class.java)
                startActivity(telaMenu)
            }else{
                Toast.makeText(this, "Produto não encontrado!", Toast.LENGTH_LONG).show()
            }
        }

        binding.btnLimpar.setOnClickListener {
            binding.etCodigo.setText(getString(R.string.campo_vazio))
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