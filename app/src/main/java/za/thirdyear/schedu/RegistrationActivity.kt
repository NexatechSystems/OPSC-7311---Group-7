package za.thirdyear.schedu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class RegistrationActivity : AppCompatActivity() {

    // Define view variables
    private lateinit var txtTextName: TextView
    private lateinit var txtSurname: TextView
    private lateinit var txtUserName: TextView
    private lateinit var txtEmailAddress: TextView
    private lateinit var txtPassword: EditText
    private lateinit var btnRegister: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)


            txtTextName = findViewById(R.id.txtTextName)
            txtSurname = findViewById(R.id.txtSurname)
            txtUserName = findViewById(R.id.txtUserName)
            txtEmailAddress = findViewById(R.id.txtEmailAddress)
            txtPassword = findViewById(R.id.txtPassword)
            btnRegister = findViewById(R.id.btnLogin)


        btnRegister.setOnClickListener {
            Toast.makeText(
                this@RegistrationActivity,
                "Thank you, your registration has been successful",
                Toast.LENGTH_SHORT
            ).show()
            val loginActivity: Intent = Intent(this, LoginActivity::class.java)
            startActivity(loginActivity)
        }
    }

    companion object {
        fun setContentView(registrationActivity: RegistrationActivity, activityRegistration: Any): RegistrationActivity {

            return TODO("Provide the return value")
        }
    }
}