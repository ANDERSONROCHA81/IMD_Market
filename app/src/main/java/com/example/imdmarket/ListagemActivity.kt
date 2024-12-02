package com.example.imdmarket

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.imdmarket.databinding.ActivityListagemBinding

class ListagemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListagemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityListagemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnVoltar.setOnClickListener {
            val telaMenu  = Intent(this, MenuActivity::class.java)
            startActivity(telaMenu)
        }

    }
}