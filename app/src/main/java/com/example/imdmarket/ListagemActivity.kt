package com.example.imdmarket


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.imdmarket.databinding.ActivityListagemBinding
import com.example.imdmarket.model.Produto
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListagemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListagemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityListagemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var produtos = getArrayList(this, "produtos")
        var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, produtos)
        binding.listaProdutos.adapter = adapter
        adapter.notifyDataSetChanged()

        binding.btnVoltar.setOnClickListener {
            val telaMenu  = Intent(this, MenuActivity::class.java)
            startActivity(telaMenu)
        }
    }

    fun getArrayList(context: Context, key: String): MutableList<Produto> {
        val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val gson = Gson()

        // Recupera o JSON
        val json = sharedPreferences.getString(key, null)

        // Converte o JSON de volta para um ArrayList
        val type = object : TypeToken<MutableList<Produto>>() {}.type
        return gson.fromJson(json, type) ?: ArrayList()
    }
}