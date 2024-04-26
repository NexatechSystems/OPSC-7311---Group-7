package za.thirdyear.schedu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegistrationActivity : AppCompatActivity() {

    // Define view variables
    private lateinit var txtTextName: TextView
    private lateinit var txtSurname: TextView
    private lateinit var txtUserName: TextView
    private lateinit var txtEmailAddress: TextView
    private lateinit var txtPassword: EditText
    private lateinit var btnRegister: Button
    private lateinit var registerUser : FirebaseAuth
    private lateinit var firebaseDatabase : FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        FirebaseApp.initializeApp(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        /******Hooks******/
        txtTextName = findViewById(R.id.txtTextName)
        txtSurname = findViewById(R.id.txtSurname)
        txtUserName = findViewById(R.id.txtUserName)
        txtEmailAddress = findViewById(R.id.txtEmailAddress)
        txtPassword = findViewById(R.id.txtPassword)
        btnRegister = findViewById(R.id.btnLogin)

        /******Declare variables*****/
        var name = txtTextName.toString()
        var surname = txtSurname.toString()
        var username = txtUserName.toString()
        var password = txtPassword.text.toString()
        var email = txtEmailAddress.text.toString()


        /******Register User Button******/
        btnRegister.setOnClickListener {
            /******Check if fields are empty******/
            if(txtTextName.text.isBlank() || txtSurname.text.isBlank() ||
                txtEmailAddress.text.isBlank() || txtPassword.text.isBlank())
            {
                Toast.makeText(
                    this@RegistrationActivity,
                    "Please complete all of the fields",
                    Toast.LENGTH_SHORT
                ).show()
            }

            /******If fields are completed******/
            else
            {
                //Create User Object
                var newUser: User = User("User$surname", name, surname, username, email, password)

                //Register User
                var registeredUser: Boolean = newUser.RegisterUser(newUser)
                if (registeredUser.equals(true)) {
                    Toast.makeText(
                        this@RegistrationActivity,
                        "Thank you, your registration has been successful",
                        Toast.LENGTH_SHORT
                    ).show()
                    val loginActivity: Intent = Intent(this, LoginActivity::class.java)
                    startActivity(loginActivity)
                }
                else {
                    Toast.makeText(
                        this@RegistrationActivity,
                        "Registration failed",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }
    }

    /******Companion objects******/
    companion object {
        fun setContentView(registrationActivity: RegistrationActivity, activityRegistration: Any): RegistrationActivity {

            return TODO("Provide the return value")
        }
    }
}