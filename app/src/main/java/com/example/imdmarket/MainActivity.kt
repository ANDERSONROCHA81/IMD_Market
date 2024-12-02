package com.example.imdmarket

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.imdmarket.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEntrar.setOnClickListener {
            val login = binding.etLogin.text.toString()
            val senha = binding.etSenha.text.toString()

            if (login.isNotEmpty() && senha.isNotEmpty()) {
                if (login == "admin" && senha == "admin"){
                    val telaMenu = Intent(this, MenuActivity::class.java)
                    startActivity(telaMenu)
                }else{
                    Toast.makeText(this, "Senha ou Login incorretos!", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_LONG).show()
            }
        }
    }
}