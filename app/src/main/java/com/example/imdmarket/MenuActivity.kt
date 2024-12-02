package com.example.imdmarket

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.imdmarket.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCadastrar.setOnClickListener {
            val telaCadastro  = Intent(this, CadastroActivity::class.java)
            startActivity(telaCadastro)
        }

        binding.btnListar.setOnClickListener {
            val telaListar = Intent(this, ListagemActivity::class.java)
            startActivity(telaListar)
        }

        binding.btnAlterar.setOnClickListener {
            val telaAlterar = Intent(this, AlteracaoActivity::class.java)
            startActivity(telaAlterar)
        }

        binding.btnDeletar.setOnClickListener {
            val telaDeletar = Intent(this, ExclusaoActivity::class.java)
            startActivity(telaDeletar)
        }

    }
}