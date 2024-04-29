package za.thirdyear.schedu

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.media.Image
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.compose.ui.graphics.ImageBitmap
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.*
import com.google.firebase.ktx.Firebase
import layout.Category
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.lifecycleScope
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.net.Authenticator
import java.text.SimpleDateFormat
import java.util.Date
import android.Manifest
import android.content.pm.PackageManager

class CreateCategoryActivity : AppCompatActivity() {
    private lateinit var btnAddCategoryPhoto : Button
    private lateinit var imgCategoryPhoto : ImageView
    private lateinit var txtCategoryName : EditText
    private lateinit var btnCreateCategory : Button
    //Code for UserID Preference
    private lateinit var authenticator: FirebaseAuth
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toolbar: Toolbar


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_category)
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
                R.id.nav_create_categories -> {
                    val moveIntent = Intent(this, CreateCategoryActivity::class.java)
                    startActivity(moveIntent)
                    true
                }
                R.id.nav_view_categories -> {
                    val moveIntent = Intent(this, ViewCategoriesActivity::class.java)
                    startActivity(moveIntent)
                    true
                }
                R.id.nav_display_details->{
                    val moveIntent = Intent(this, DisplayDetails::class.java)
                    startActivity(moveIntent)
                    true
                }
                R.id.nav_logout->{
                    val moveIntent = Intent(this, LoginActivity::class.java)
                    startActivity(moveIntent)
                    true
                }
                else -> false
            }
        }

        //Check if user is still signed in
        authenticator = Firebase.auth
        val currentUser = FirebaseAuth.getInstance().currentUser
        if(currentUser == null) /**If User is signed out**/
        {
            //Redirect to Login Activity with intent
            val intent : Intent = Intent(this, LoginActivity::class.java)
        }


        /******Hooks******/
        btnAddCategoryPhoto = findViewById(R.id.btnAddCategoryPhoto)
        txtCategoryName = findViewById(R.id.txtCategoryName)
        imgCategoryPhoto = findViewById(R.id.imgCategoryImage)
        btnCreateCategory = findViewById(R.id.btnCreateCategory)


        /******Variables******/
        val bitmap = Bitmap.createBitmap(imgCategoryPhoto.width,
                                        imgCategoryPhoto.height,
                                        Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        imgCategoryPhoto.draw(canvas)



        /******btnAddCategoryPhoto Event Handlers******/
        //Move to activity to take photo and use shared preference to transfer photo to this activity

        //Still need to convert ImageView to Image
        lifecycleScope.launch {

            try
            {

            }
            catch (e : Exception)
            {
                e.printStackTrace()
            }

        }
        //var newCategoryImage : File = (Environment.getExternalStorageDirectory(), "/Android/data/"; getApplicationContext().getPackageName(); "/Files"



        /******btnCreateCategory Event Handlers******/
        //Use Category Class to put category in other class
        btnCreateCategory.setOnClickListener()
        {
            Category.Category = Category("CAT000000", "User000000", txtCategoryName.toString(), imgCategoryPhoto)
            try
                {
                    var enterNewCategory : Boolean


                }
            catch (e : Exception)
            {

            }

        }

    }

    /******Check for permissions to access gallery******/
    private fun AccessGallery()
    {
        val PERMISSION_CODE_READ = 1
        val PERMISSION_CODE_WRITE = 2
        val IMAGE_PICK_CODE = 3 // GIVEN INTEGER VALUE FOR IMAGE_PICK_CODE

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if ((checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
                && (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
            ) {
                val permission = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                val permissionCoarse = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)

                requestPermissions(permission, PERMISSION_CODE_READ) // GIVE AN INTEGER VALUE FOR PERMISSION_CODE_READ LIKE 1001
                requestPermissions(permissionCoarse, PERMISSION_CODE_WRITE) // GIVE AN INTEGER VALUE FOR PERMISSION_CODE_WRITE LIKE 1002
            }
            else
            {
                val intent = Intent(Intent.ACTION_PICK)
                intent.type = "image/*"
                startActivityForResult(intent, IMAGE_PICK_CODE)
            }
        }
    }
}