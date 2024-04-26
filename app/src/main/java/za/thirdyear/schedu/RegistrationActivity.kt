package za.thirdyear.schedu

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class RegistrationActivity : AppCompatActivity() {

    // Define view variables
    private lateinit var txtTextName: EditText
    private lateinit var txtSurname: EditText
    private lateinit var txtUserName: EditText
    private lateinit var txtEmailAddress: EditText
    private lateinit var txtPassword: EditText
    private lateinit var btnRegister: Button
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE)

        // Initialize views
        txtTextName = findViewById(R.id.txtTextName)
        txtSurname = findViewById(R.id.txtSurname)
        txtUserName = findViewById(R.id.txtUserName)
        txtEmailAddress = findViewById(R.id.txtEmailAddress)
        txtPassword = findViewById(R.id.txtPassword)
        btnRegister = findViewById(R.id.btnRegister)

        btnRegister.setOnClickListener {
            val name = txtTextName.text.toString().trim()
            val surname = txtSurname.text.toString().trim()
            val username = txtUserName.text.toString().trim()
            val emailAddress = txtEmailAddress.text.toString().trim()
            val password = txtPassword.text.toString().trim()

            // Check if any field is empty
            if (name.isEmpty() || surname.isEmpty() || username.isEmpty() || emailAddress.isEmpty() || password.isEmpty()) {
                // Display error message
                Toast.makeText(this@RegistrationActivity, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                // All fields are filled, proceed with registration
                // Save the data (you can implement your saving logic here)
                saveUserData(name, surname, username, emailAddress, password)
// Retrieve saved user data from SharedPreferences
                val savedEmail = sharedPreferences.getString("emailAddress", "")
                val savedPassword = sharedPreferences.getString("password", "")

                Toast.makeText(
                    this@RegistrationActivity,
                    "email: $savedEmail , password: $savedPassword",
                    Toast.LENGTH_SHORT
                ).show()
                // Show success message
                Toast.makeText(
                    this@RegistrationActivity,
                    "Thank you, your registration has been successful",
                    Toast.LENGTH_SHORT
                ).show()

                // Navigate to login activity
                val loginIntent = Intent(this@RegistrationActivity, LoginActivity::class.java)
                startActivity(loginIntent)
            }
        }
    }

    private fun saveUserData(name: String, surname: String, username: String, emailAddress: String, password: String) {
        // Save user data to SharedPreferences
        val editor = sharedPreferences.edit()
        editor.putString("name", name)
        editor.putString("surname", surname)
        editor.putString("username", username)
        editor.putString("emailAddress", emailAddress)
        editor.putString("password", password)
        editor.apply()
    }
}


