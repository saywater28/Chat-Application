package com.example.chatapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnSignUp: Button

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        edtEmail = findViewById(R.id.edt_email)
        edtPassword = findViewById(R.id.edt_password)
        btnLogin = findViewById(R.id.btnLogin)
        btnSignUp = findViewById(R.id.btnSignUp)

        mAuth = FirebaseAuth.getInstance()

        btnSignUp.setOnClickListener{
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener{
            val email = edtEmail.text.toString()
            val password = edtPassword.toString()

            login(email, password);
        }
    }

    private fun login(email: String, password: String){
        // logic for logging in user
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){ task ->
                if (task.isSuccessful){
                    // code to login user
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else{
                    Toast.makeText(this@LoginActivity, "User doesn't exist", Toast.LENGTH_SHORT).show()

                }
            }
    }
}