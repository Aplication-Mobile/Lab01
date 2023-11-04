package com.bananas.lab01


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSponsor: Button = findViewById(R.id.btnPatrocinador)

        btnSponsor.setOnClickListener {
            startActivity(Intent(this, SponsorActivity::class.java))
        }

        val logoutButton: ImageButton = findViewById(R.id.logoutButton)
        logoutButton.setOnClickListener {

            AlertDialog.Builder(this@MainActivity)
                .setIcon(R.drawable.baseline_logout_24)
                .setTitle(getString(R.string.dialog_title))
                .setMessage(getString(R.string.dialog_message))
                .setPositiveButton(getString(R.string.dialog_positive_button)) { dialog, which ->

                    saveLoginState(false)


                    val intent = Intent(this@MainActivity, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)


                    finish()
                }
                .setNegativeButton(getString(R.string.dialog_negative_button), null)
                .show()
        }
    }

    private fun saveLoginState(isLoggedIn: Boolean) {
        val sharedPref = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        sharedPref.edit().putBoolean("isLoggedIn", isLoggedIn).apply()
    }

    override fun onStop() {
        super.onStop()

        if (!isChangingConfigurations && !isTaskRoot) {

            saveLoginState(false)
        }
    }




}









