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
    lateinit var txtschedu: TextView
    lateinit var imgLogo: ImageView
    lateinit var txtCategory: TextView
    lateinit var imgInfoButton: ImageButton
    lateinit var btnFilter: Button
    lateinit var btnCalculate: Button
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

        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.navigationView)
        toolbar = findViewById(R.id.toolbar)
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_logout -> {
                    val moveIntent = Intent(this, LoginActivity::class.java)
                    startActivity(moveIntent)
                    true
                }

                R.id.nav_view_categories -> {
                    val moveIntent = Intent(this, ViewCategoriesActivity::class.java)
                    startActivity(moveIntent)
                    true
                }

                R.id.nav_display_details -> {
                    val moveIntent = Intent(this, DisplayDetails::class.java)
                    startActivity(moveIntent)
                    true
                }

                R.id.nav_logout -> {
                    val moveIntent = Intent(this, LoginActivity::class.java)
                    startActivity(moveIntent)
                    true
                }

                R.id.nav_create_categories -> {
                    val moveIntent = Intent(this, CreateCategoryActivity::class.java)
                    startActivity(moveIntent)
                    true
                }


                else -> false
            }
        }


    }
}