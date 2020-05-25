package com.example.music.Activities

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.music.AppDatabase
import com.example.music.Objects.User
import com.example.music.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    private val auth by lazy { FirebaseAuth.getInstance() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setupView()
    }
    private fun setupView(){
        sign_up_button.setOnClickListener {
            val email = email_input_r.text.toString()
            val name = name_input_r.text.toString()
            val password = password_input_r.text.toString()
            if (email.length>0 && name.length>0 && password.length>0) {
                signUp(email = email, password = password, name = name)
            }
        }
    }
    private fun signUp(email: String, password: String, name: String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener{task ->
                if (task.isSuccessful){
                    val userId = auth.uid.toString()
                    val user = User(user_id = userId, email = email, name =name, password = password)
                    addUser(user)
                    Toast.makeText(this, "Successfully registered!", Toast.LENGTH_LONG).show()
                    val loginIntent = Intent(this@RegisterActivity, LoginActivity::class.java)
                    startActivity(loginIntent)
                    return@addOnCompleteListener

                }

                Toast.makeText(this, task.exception?.message, Toast.LENGTH_LONG).show()
            }
    }
    private fun addUser(user: User){
        AsyncTask.execute {
            AppDatabase.getInstance(applicationContext)!!
                .getItemDao().insertUser(user)
            runOnUiThread {
                Toast.makeText(
                    applicationContext,
                    "Successfully registered",
                    Toast.LENGTH_LONG
                ).show()
                val go_register = Intent(this@RegisterActivity, LoginActivity::class.java)
                startActivity(go_register)
            }
        }
    }
}
