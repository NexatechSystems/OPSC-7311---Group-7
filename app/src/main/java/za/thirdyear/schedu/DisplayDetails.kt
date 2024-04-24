package za.thirdyear.schedu

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class DisplayDetails : AppCompatActivity() {

    /******Declare Control Variables- lateint var ******/
    lateinit var txtschedu : TextView
    lateinit var imgLogo : ImageView
    lateinit var txtCategory : TextView
    lateinit var imgInfoButton: ImageButton
    lateinit var btnFilter : Button
    lateinit var btnCalculate : Button
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    @SuppressLint("StaticFieldLeak")
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_details)

        /******Hooks******/
        imgLogo = findViewById(R.id.imageViewLogo)
        txtCategory = findViewById(R.id.textViewCategory)
        imgInfoButton = findViewById(R.id.imageViewInfoIcon)
        btnCalculate = findViewById(R.id.buttonCalculateHours)
        btnFilter = findViewById(R.id.buttonFilter)





    }
}