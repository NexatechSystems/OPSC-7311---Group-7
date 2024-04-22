package za.thirdyear.schedu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ProgressBar



class SplashScreenActivity : AppCompatActivity()
{

    lateinit var progressBar : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        //Declare Progress Bar to Add animation
        progressBar = findViewById(R.id.pbprogressBar)

        //Timer and untent
        var handler = Handler()
        handler.postDelayed({
            val intent : Intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 5000)






    }
}