package din.don.musicapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R
            .layout.activity_login)

        goToSignup.setOnClickListener{
            startActivity(Intent(this, Signup::class.java))
        }

        loginBtn.setOnClickListener{
            if(emailText.text.isNullOrBlank() || passText.text.isNullOrBlank()){
                Toast.makeText(this,"Erroe", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            val email = emailText.text.toString()
            val password = passText.text.toString()

            auth = FirebaseAuth.getInstance()
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this){task ->
                    if(!task.isSuccessful){
                        Toast.makeText(this, task.exception.toString(), Toast.LENGTH_LONG).show()
                        return@addOnCompleteListener
                    }
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
        }
    }
}
