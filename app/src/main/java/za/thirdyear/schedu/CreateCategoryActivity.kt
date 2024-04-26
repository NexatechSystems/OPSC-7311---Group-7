package za.thirdyear.schedu

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.media.Image
import android.nfc.Tag
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.compose.ui.graphics.ImageBitmap
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.*
import com.google.firebase.ktx.Firebase
import layout.Category
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.net.Authenticator
import java.text.SimpleDateFormat
import java.util.Date

class CreateCategoryActivity : AppCompatActivity() {
    private lateinit var btnAddCategoryPhoto: Button
    private lateinit var imgCategoryPhoto: ImageView
    private lateinit var txtCategoryName: EditText
    private lateinit var btnCreateCategory: Button

    //Code for UserID Preference
    private lateinit var authenticator: FirebaseAuth

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_category)

        //Check if user is still signed in
        authenticator = Firebase.auth
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser == null)
        /**If User is signed out -- ChatGPT**/
        {
            //Redirect to Login Activity with intent
            val intent: Intent = Intent(this, LoginActivity::class.java)
        }



        /******Hooks******/
        btnAddCategoryPhoto = findViewById(R.id.btnAddCategoryPhoto)
        txtCategoryName = findViewById(R.id.txtCategoryName)
        imgCategoryPhoto = findViewById(R.id.imgCategoryImage)
        btnCreateCategory = findViewById(R.id.btnCreateCategory)


        /******btnAddCategoryPhoto Event Handlers******/
        //Move to activity to take photo and use shared preference to transfer photo to this activity
        btnAddCategoryPhoto.setOnClickListener()
        {
            /******Popup option asking if user wished to add image from gallery or take photo******/
            val options = arrayOf<String>("Select image from gallery", "Take photo")
            val popUp = AlertDialog.Builder(this)
            popUp.setTitle("Select an option")
            popUp.setItems(options)
            { dialog, selection ->
                // Respond to option selection
                when (selection) {
                    0 -> {
                        //Boolean function to check if app can access Gallery
                        val PICK_IMAGE_REQUEST = 1
                        val REQUEST_PERMISSION_CODE = 2
                        /******Check if user has permission to access Gallery- rename variables******/
                        fun CheckPermissions() : Boolean
                        {
                            return ContextCompat.checkSelfPermission(
                                this,
                                Manifest.permission.DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION
                            ) == PackageManager.PERMISSION_GRANTED
                        }

                        /******Request if user has permission to access Gallery- rename variables- Check this code later******/
                        //fun requestPermission() {
                        //    ActivityCompat.requestPermissions(
                        //        this,
                        //        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                        //        REQUEST_PERMISSION_CODE
                        //    )
                        //}

                        //Open Gallery
                        @Override
                        fun openGallery() {
                            val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                            startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST)
                        }

                        fun onRequestPermissionsResult(
                            requestCode: Int,
                            permissions: Array<String>,
                            grantResults: IntArray
                        ) {
                            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
                            if (requestCode == REQUEST_PERMISSION_CODE) {
                                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                                    openGallery()
                                } else {
                                    // Permission denied, handle it
                                }
                            }
                        }

                        //Select Image from Gallery
                        @Override
                        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
                            super.onActivityResult(requestCode, resultCode, data)

                            if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
                                // Image is selected from the gallery, handle it
                                val selectedImageUri = data.data
                                // Now you can do whatever you want with the selected image URI
                                // For example, you can display it in an ImageView or upload it to Firebase
                            }
                        }

                    }

                    1 -> {
                        //Redirect to AddPhotoActivity
                        val  addPhotoActivity : Intent = Intent(this, AddPhotoActivity::class.java)
                        startActivity(addPhotoActivity)
                    }
                }
            }
            val imageDialog = popUp.create()
            imageDialog.show()

            /******Bit map Variables******/
            val bitmap = Bitmap.createBitmap(
                imgCategoryPhoto.width,
                imgCategoryPhoto.height,
                Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(bitmap)
            imgCategoryPhoto.draw(canvas)


            //Still need to convert ImageView to Image
            lifecycleScope.launch {

                try {
                    var date = Date().time.toString()
                    var path: String = applicationContext.filesDir.path
                    var fout: OutputStream

                    //Category name is the name of the category name associated with the image
                    var newFile: File = File(path, "CategoryName")
                    if (!newFile.exists()) {
                        newFile.mkdirs()
                    }

                    var newFile2: File = File(newFile, "CategoryName,$date.png")
                    fout = FileOutputStream(newFile2)

                    //Add bitmap
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fout)
                    fout.flush()
                    fout.close()


                } catch (e: Exception) {
                    //Logcat message- unable to use image
                    e.printStackTrace()
                    Log.d("btnAddCategoryPhoto", "Unable to convert ImageView to image")
                }

            }
        }




        /******btnCreateCategory Event Handlers******/
        //Use Category Class to put category in other class
        btnCreateCategory.setOnClickListener()
        {
            Category("CAT000000", "User000000", txtCategoryName.toString(), imgCategoryPhoto)
            try {
                var enterNewCategory: Boolean
                //Display Popup to ask if user wants to take a photo or use image from gallery

            } catch (e: Exception) {
                //Logcat message- unable to create category

                //Popup message indicating that category was unable to be created


            }

        }

    }











    }
