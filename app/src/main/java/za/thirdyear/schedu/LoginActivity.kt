package za.thirdyear.schedu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class LoginActivity : AppCompatActivity()
{
    private lateinit var btnRegister : Button
    private lateinit var btnLogin : Button
    private lateinit var txtEmailAddress : EditText
    private lateinit var txtPassword : EditText
    private lateinit var binding: LoginActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        /******Hooks******/
        btnRegister = findViewById(R.id.btnRegisterRedirect)
        btnLogin = findViewById(R.id.btnLogin)
        txtEmailAddress = findViewById(R.id.txtEmailAddress)
        txtPassword = findViewById(R.id.txtPassword)

        /******Redirect to Register Page******/
        btnRegister.setOnClickListener()
        {
            val registerPage : Intent = Intent(this, RegistrationActivity::class.java)
            startActivity(registerPage)

        }

        /******Authenticate User******/
        btnLogin.setOnClickListener()
        {
            val registerPage : Intent = Intent(this, MainActivity::class.java)
            startActivity(registerPage)
        }

    }

    companion object {
        fun setContentView(loginActivity: LoginActivity, activityRegistration: Any): LoginActivity {
            TODO("Not yet implemented")
        }
    }
}