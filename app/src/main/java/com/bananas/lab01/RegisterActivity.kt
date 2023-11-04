package com.bananas.lab01

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.content.SharedPreferences


class RegisterActivity : AppCompatActivity() {

    lateinit var nameEditText: EditText
    lateinit var emailEditText: EditText
    lateinit var passwordEditText: EditText
    lateinit var confirmPasswordEditText: EditText
    lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        nameEditText = findViewById(R.id.reName)
        emailEditText = findViewById(R.id.reEmail)
        passwordEditText = findViewById(R.id.rePassword)
        confirmPasswordEditText = findViewById(R.id.reConfirmPassword)
        registerButton = findViewById(R.id.btnRegister1)

        registerButton.setOnClickListener {
            if (areFieldsFilled()) {
                val password = passwordEditText.text.toString()
                val confirmPassword = confirmPasswordEditText.text.toString()

                if (password == confirmPassword) {
                    guardarInfo()
                    volverLogin()
                }
                else {
                    confirmPasswordEditText.error = "Las contraseñas no coinciden"

                }
            }
        }
    }

    fun areFieldsFilled(): Boolean {
        if (nameEditText.text.isEmpty()) {
            nameEditText.error = "Falta el nombre"
            return false
        }

        if (emailEditText.text.isEmpty()) {
            emailEditText.error = "Falta el correo"
            return false
        }

        if (passwordEditText.text.isEmpty()) {
            passwordEditText.error = "Falta la contraseña"
            return false
        }

        if (confirmPasswordEditText.text.isEmpty()) {
            confirmPasswordEditText.error = "Falta la confirmación de contraseña"
            return false
        }

        return true
    }

    fun guardarInfo(){
        val sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val name = nameEditText.text.toString()
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()
        editor.putString("name", name)
        editor.putString("email", email)
        editor.putString("password", password)
        editor.apply()
    }

    fun volverLogin(){
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }

}





