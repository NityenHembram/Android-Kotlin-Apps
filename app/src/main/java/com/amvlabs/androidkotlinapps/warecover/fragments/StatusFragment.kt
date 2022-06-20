package com.amvlabs.androidkotlinapps.warecover.fragments

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Context.STORAGE_SERVICE
import android.content.Intent
import android.content.SharedPreferences
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.os.storage.StorageManager
import android.provider.DocumentsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.documentfile.provider.DocumentFile
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amvlabs.androidkotlinapps.databinding.FragmentStatusBinding
import com.amvlabs.androidkotlinapps.warecover.adapter.OnImageItemClickListener
import com.amvlabs.androidkotlinapps.warecover.adapter.StatusAdapter
import com.amvlabs.androidkotlinapps.warecover.utils.Common
import com.amvlabs.androidkotlinapps.warecover.utils.Constants.STORAGE_PERMISSION
import com.amvlabs.androidkotlinapps.warecover.utils.Constants.WA_DIR_PATH
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.util.*
import kotlin.collections.ArrayList


private const val TAG = "status"

class StatusFragment : Fragment(), OnImageItemClickListener {
    lateinit var mdSc: MediaScannerConnection

    private lateinit var pref: SharedPreferences
    private lateinit var edit: SharedPreferences.Editor
    private var fileList = ArrayList<Uri>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var common: Common

    companion object {
        const val WHATSAPP_STATUS_FOLDER_PATH = "/Android/media/com.whatsapp/WhatsApp/Media/.Statuses/"
    }

    private lateinit var binding:FragmentStatusBinding
    private var file = File("/storage/emulated/0/$WHATSAPP_STATUS_FOLDER_PATH")

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentStatusBinding.inflate(layoutInflater)
        recyclerView = binding.statusRecyclerView
        pref = requireActivity().getSharedPreferences("myPref", MODE_PRIVATE)
        edit = pref.edit()
        common = Common(requireContext(),recyclerView)
        checkAccessToStorageVolumes()
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    @RequiresApi(Build.VERSION_CODES.R)
    fun setImage(uri: Uri) {
        val listFile = getFromSdcard(uri)
        lifecycleScope.launch(){
            if (listFile != null) {
                for (imgFile in listFile) {
                    Log.d(TAG, "getImagePath: ${imgFile.type}")
                    if (imgFile.type.equals("image/jpeg")) {
                        fileList.add(imgFile.uri)
                    }
                }
                withContext(Dispatchers.Main){
                    recyclerView.layoutManager = GridLayoutManager(requireActivity(),2)
                    recyclerView.adapter = StatusAdapter(fileList,this@StatusFragment,requireActivity())
                }
            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.Q)
    fun requestFolderPermission() {
        val intent: Intent
        val sm = requireActivity().getSystemService(STORAGE_SERVICE) as StorageManager
        val f = File("/storage/emulated/0/$WHATSAPP_STATUS_FOLDER_PATH")
        val statusDir: String = f.absolutePath
        val str = "android.provider.extra.INITIAL_URI"
        if (Build.VERSION.SDK_INT >= 29) {
            intent = sm.primaryStorageVolume.createOpenDocumentTreeIntent()
            val scheme = (intent.getParcelableExtra<Parcelable>(str) as Uri?).toString()
                .replace("/root/", "/document/")
            val stringBuilder = scheme +
                    "%3A" +
                    statusDir
            intent.putExtra(str, Uri.parse(stringBuilder))
        } else {
            intent = Intent("android.intent.action.OPEN_DOCUMENT_TREE")
            intent.putExtra(str, Uri.parse(statusDir))
        }

        startActivityForResult(intent, 12)
        return
    }
    @RequiresApi(Build.VERSION_CODES.R)
    private fun checkAccessToStorageVolumes() {
        val permissions: String = pref.getString(STORAGE_PERMISSION, "").toString()
        val storageVolumePathsWeHaveAccessTo = HashSet<String>()
        val persistedUriPermissions = requireActivity().contentResolver.persistedUriPermissions
        persistedUriPermissions.forEach {
            storageVolumePathsWeHaveAccessTo.add(it.uri.toString())
        }

        Log.d(TAG, "checkAccessToStorageVolumes: $permissions")
        if (permissions.isNotEmpty() && permissions == WA_DIR_PATH) {
            setImage(Uri.parse(permissions))
        } else {
            requestFolderPermission()
        }
    }

    private fun buildVolumeUriFromUuid(uuid: String): String {
        return DocumentsContract.buildTreeDocumentUri(
            "com.android.externalstorage.documents",
            "$uuid:"
        ).toString()
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val uri = data?.data
        val takeFlags =
            Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
        requireActivity().contentResolver.takePersistableUriPermission(uri!!, takeFlags)

        edit.putString(STORAGE_PERMISSION, uri.toString())
        edit.commit()
        setImage(uri)
    }

    private fun getFromSdcard(uri: Uri): Array<DocumentFile>? {
        val fromTreeUri =
            DocumentFile.fromTreeUri(requireContext(), uri)
        return if (fromTreeUri != null && fromTreeUri.exists() && fromTreeUri.isDirectory && fromTreeUri.canRead() && fromTreeUri.canWrite()) {
            fromTreeUri.listFiles()
        } else null
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onDownButtonClicked(list: ArrayList<Uri>, adapterPosition: Int, ctx: Context) {
        common.saveFile(list,adapterPosition,ctx)
    }

    override fun onShareButtonClicked(list: ArrayList<Uri>, adapterPosition: Int) {
        common.shareImage(list,adapterPosition)
    }
}