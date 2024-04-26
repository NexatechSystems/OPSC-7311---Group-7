package za.thirdyear.schedu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

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
        FirebaseApp.initializeApp(this) //Initialise Firebase App in application

        /******Hooks******/
        btnRegister = findViewById(R.id.btnRegisterRedirect)
        btnLogin = findViewById(R.id.btnLogin)
        txtEmailAddress = findViewById(R.id.txtEmailAddress)
        var email = txtEmailAddress.text.toString()
        txtPassword = findViewById(R.id.txtPassword)
        var password = txtPassword.text.toString()
        /******Declare variables*****/
        var loginAuth : FirebaseAuth = FirebaseAuth.getInstance()

        /******Redirect to Register Page- Tested******/
        btnRegister.setOnClickListener()
        {
            val registerPage : Intent = Intent(this, RegistrationActivity::class.java)
            startActivity(registerPage)
        }

        /******Authenticate User******/
        btnLogin.setOnClickListener()
        {

        //Check if text boxes are completed
            if(txtEmailAddress.text.isBlank() || txtPassword.text.isBlank())
            {
                Toast.makeText(
                    this,
                    "Please complete all of the fields",
                    Toast.LENGTH_SHORT
                ).show()
            }

            else
            {
                val loginUserMethod = User.LoginUser(email, password)
                if(loginUserMethod.equals(true))
                {
                    //Store user information from login in shared preference
                    

                    //Redirect to new page
                    val registerPage : Intent = Intent(this, CreateCategoryActivity::class.java)
                    startActivity(registerPage)
                }

                else{
                    Toast.makeText(
                        this,
                        "Login Unsuccessful",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }

    }

    companion object {
        fun setContentView(loginActivity: LoginActivity, activityRegistration: Any): LoginActivity {
            TODO("Not yet implemented")
        }
    }
}