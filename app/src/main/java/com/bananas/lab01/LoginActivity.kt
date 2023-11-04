package com.bananas.lab01

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if (isLoggedIn()) {
            startMainActivity()
            return
        }

        emailEditText = findViewById(R.id.etEmailLogin)
        passwordEditText = findViewById(R.id.etPasswordLogin)
        val loginButton: Button = findViewById(R.id.btnLogin)
        val btnRegister: Button = findViewById(R.id.btnRegister2)

        btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        loginButton.setOnClickListener {
            if (validarCredenciales(emailEditText.text.toString(), passwordEditText.text.toString())) {
                onLoginSuccess()
            } else {
                Toast.makeText(this, "Correo o contraseña incorrecta", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun validarCredenciales(email: String, password: String): Boolean {
        val sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val savedEmail = sharedPreferences.getString("email", "")
        val savedPassword = sharedPreferences.getString("password", "")
        return email == savedEmail && password == savedPassword
    }

    private fun onLoginSuccess() {
        saveLoginState(true)
        startMainActivity()
    }

    private fun saveLoginState(isLoggedIn: Boolean) {
        val sharedPref = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        sharedPref.edit().putBoolean("isLoggedIn", isLoggedIn).apply()
    }

    private fun isLoggedIn(): Boolean {
        val sharedPref = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("isLoggedIn", false)
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
        finish()
    }
}
