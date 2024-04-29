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
import android.app.Activity
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.compose.ui.graphics.painter.BitmapPainter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

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
    private lateinit var newCategoryImage : Image
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseDatabase: FirebaseDatabase
    var imageUri: Uri? = null
    var imageView: ImageView? = null
    val IMAGE_CHOOSE = 1
    val IMAGE_CAPTURE = 2
    val IMAGE_PICK_CODE = 3 // GIVEN INTEGER VALUE FOR IMAGE_PICK_CODE


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_category)

        /******Hooks******/
        btnAddCategoryPhoto = findViewById(R.id.btnAddCategoryPhoto)
        txtCategoryName = findViewById(R.id.txtCategoryName)
        imgCategoryPhoto = findViewById(R.id.imgCategoryImage)
        btnCreateCategory = findViewById(R.id.btnCreateCategory)
        /******Variables******/

        //Check if user has ID
        val currentUserID = FirebaseAuth.getInstance().currentUser?.uid
        val currentUser = FirebaseAuth.getInstance().currentUser
        val database : FirebaseDatabase = FirebaseDatabase.getInstance()
        val id : String = ""

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

                R.id.nav_view_categories ->{

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

        if(currentUser == null) /**If User is signed out**/
        {
            //Redirect to Login Activity with intent
            val intent : Intent = Intent(this, LoginActivity::class.java)
        }
        else
        {
            currentUserID?.let {
                id -> val userReference: DatabaseReference = database.getReference("users").child(id)
            }
        }

        /******btnAddCategoryPhoto Event Handlers******/
        btnAddCategoryPhoto.setOnClickListener()
        {
            var accessPermission : Boolean = true
            var message : String
            //Still need to convert ImageView to Image
            lifecycleScope.launch {

                try
                {
                    accessPermission = AccessGallery()
                    if (accessPermission == true)
                    {
                        //Select Gallery Image
                        val intent = Intent(Intent.ACTION_PICK)
                        intent.type = "image/*"
                        startActivityForResult(intent, IMAGE_PICK_CODE)
                        //Store Image from Gallery into imageview and image variable

                    }

                    else //Show alert that permission was denied
                    {
                        message = "Permission denied"
                        showAlert(message)
                    }
                }
                catch (e : Exception)
                {
                    e.printStackTrace()
                }
            }
        }

        /******btnCreateCategory Event Handlers******/
        //Use Category Class to put category in other class
        btnCreateCategory.setOnClickListener()
        {
            var categoryID = "CAT ${txtCategoryName.text.toString()}"

            //Create Category Object
            var Category : Category
            Category = Category(categoryID, id,  txtCategoryName.text.toString(), imgCategoryPhoto)
            try
                {
                    var enterNewCategory : Boolean
                    //Add Category to Firebase


                }
            catch (e : Exception)
            {

            }

        }

    }

    //Need to set for image imported from gallery
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        var bitmap :Bitmap
        var canvas : Canvas
        //imgCategoryPhoto.draw(canvas)
        //Check if there is an image selected
        if (resultCode == IMAGE_PICK_CODE && requestCode == Activity.RESULT_OK && data != null) {
            // Set image captured to image view
            imageUri = data.data
            if(imageUri != null)
            {
                bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, imageUri)
                if(bitmap != null)
                {
                    //Stores image into bitmap
                    val imageVariable = bitmap

                }
                else
                {
                    //if bitmap is null (Show Alert)
                }
            }

        }
        else
        {
            //if Image URI is null (Show Alert)
            
        }
    }

    /******Check for permissions to access gallery******/
    private fun AccessGallery() : Boolean
    {
        val PERMISSION_CODE_READ = 1 // GIVE AN INTEGER VALUE FOR PERMISSION_CODE_READ LIKE 1
        val PERMISSION_CODE_WRITE = 2 // GIVE AN INTEGER VALUE FOR PERMISSION_CODE_WRITE LIKE 2
        var accessPermission = false

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if ((checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
                && (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
            ) //If permissions are denied
            {
                val permission = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                val permissionCoarse = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)

                //Display Permission dialogue to user
                requestPermissions(permission, PERMISSION_CODE_READ)
                requestPermissions(permissionCoarse, PERMISSION_CODE_WRITE)
                //handle deny response from user
            }
            else //Get Image from user
            {
                accessPermission = true
            }
        }

        return accessPermission
    }

    /******Show Alert Message with String return Type******/
    private fun showAlert(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(message)
        builder.setPositiveButton(R.string.ok_button_title, null)

        val dialog = builder.create()
        dialog.show()
    }




}