package com.gorkemersizer.mealye.ui.screens.profilescreen

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.gorkemersizer.mealye.R
import com.gorkemersizer.mealye.databinding.FragmentProfileScreenBinding
import com.gorkemersizer.mealye.ui.screens.loginscreen.LoginScreenActivity
import com.gorkemersizer.mealye.util.Constants.Companion.USEREMAIL
import com.gorkemersizer.mealye.util.Constants.Companion.USERNAME
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.collections.ArrayList

class ProfileScreen : Fragment() {
    private lateinit var binding: FragmentProfileScreenBinding
    private lateinit var permissionLauncher: ActivityResultLauncher<String>
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    private lateinit var db: FirebaseFirestore
    private lateinit var storage: FirebaseStorage
    private var selectedPicture: Uri?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile_screen, container, false)
        binding.profileScreenFragment = this
        binding.textViewEmail.text = USEREMAIL
        binding.textViewProfileName.text = USERNAME
        registerLauncher()
        auth= Firebase.auth
        firestore=Firebase.firestore
        storage=Firebase.storage
        db=Firebase.firestore
        getData()
        return binding.root
    }

    fun upload(view: View){
        val uuid = UUID.randomUUID()
        val imageName ="$uuid.jpg"
        val reference = storage.reference
        val imageReference = reference.child("images/$imageName")

        if (selectedPicture!=null) {

            imageReference.putFile (selectedPicture!!).addOnSuccessListener {
                val uploadPictureReference = storage.reference.child("images/$imageName")
                uploadPictureReference.downloadUrl.addOnSuccessListener {
                    val downloadUrl = it.toString()

                    if (auth.currentUser!=null) {

                        val postMap= hashMapOf<String, Any>()
                        postMap.put("imageUrl",downloadUrl)
                        postMap.put("userEmail",auth.currentUser!!.email!!)
                        //postMap.put("date",Timestamp.now())

                        firestore.collection("images").add(postMap).addOnSuccessListener {
                            //finish()
                        }.addOnFailureListener {
                            Toast.makeText(this.requireContext(), it.localizedMessage,Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }.addOnFailureListener{
                Toast.makeText(this.requireContext(), it.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }
    }

    fun signOutClick(v: View) {
        auth.signOut()
        val intent= Intent(this.context, LoginScreenActivity::class.java)
        startActivity(intent)
    }

    fun selectImage(view: View){

        if(ContextCompat.checkSelfPermission(this.requireContext(),Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this.requireActivity(),Manifest.permission.READ_EXTERNAL_STORAGE)){
                Snackbar.make(view,"Galeri İçin İzin Gerekli !",Snackbar.LENGTH_INDEFINITE).setAction("İzin Ver"){
                    permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                }.show()
            }else{
                permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }else{
            val intentToGallery= Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            activityResultLauncher.launch(intentToGallery)
        }
    }

    private fun registerLauncher(){
        activityResultLauncher=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            if(result.resultCode== AppCompatActivity.RESULT_OK){
                val intentFromResult=result.data
                if(intentFromResult!=null){
                    selectedPicture=intentFromResult.data
                    selectedPicture?.let {
                        binding.imageView.setImageURI(it)
                    }
                }
            }
        }
        permissionLauncher=registerForActivityResult(ActivityResultContracts.RequestPermission()){ result ->
            if(result){
                val intentToGallery= Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intentToGallery)
            }else{
                Toast.makeText(this.requireContext(),"Permission needed!", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun getData(){
        db.collection("images").addSnapshotListener { value, error ->
            if(error!=null){
                Toast.makeText(this.requireContext(),error.localizedMessage,Toast.LENGTH_LONG).show()
            }else{
                if(value!=null){
                    if(!value.isEmpty){
                        val storageRef = storage.reference
                        val imageRef = storageRef.child("images/")
                        imageRef.delete()

                        val documents = value.documents
                        val uriList = ArrayList<String>()
                        uriList.clear()
                        for(document in documents){
                            val downloadUrl=document.get("imageUrl") as String
                            uriList.add(downloadUrl)
                            Log.e("profilepicture","profilepicture uri: ${downloadUrl.toUri()}")
                        }
                        if (uriList.isNotEmpty()){
                            Picasso.get().load("${uriList.last().toUri()}").into(binding.imageView)
                        }
                    }
                }
            }
        }
    }

}