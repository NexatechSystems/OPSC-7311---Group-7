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
import androidx.compose.ui.graphics.ImageBitmap
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.*
import com.google.firebase.ktx.Firebase
import layout.Category
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
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
    private lateinit var btnAddCategoryPhoto : Button
    private lateinit var imgCategoryPhoto : ImageView
    private lateinit var txtCategoryName : EditText
    private lateinit var btnCreateCategory : Button
    //Code for UserID Preference
    private lateinit var authenticator: FirebaseAuth


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_category)

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


        //Still need to convert ImageView to Image
        lifecycleScope.launch {

            try
            {
                var date = Date().time.toString()
                var path: String =  applicationContext.filesDir.path
                var fout: OutputStream

                //Category name is the name of the category name associated with the image
                var newFile: File = File(path,"CategoryName")
                if (!newFile.exists()) {
                    newFile.mkdirs();
                }

                var newFile2: File = File(newFile, "$date.png");
                fout = FileOutputStream(newFile2);

                //add your bitmap
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fout)
                fout.flush()
                fout.close()

            }
            catch (e : Exception)
            {
                e.printStackTrace()
            }

        }

        //var newCategoryImage : File = (Environment.getExternalStorageDirectory(),
        //                                    "/Android/data/",
        //                                    getApplicationContext().getPackageName(),
        //                                    "/Files")

        /******btnAddCategoryPhoto Event Handlers******/
        //Move to activity to take photo and use shared preference to transfer photo to this activity


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
}