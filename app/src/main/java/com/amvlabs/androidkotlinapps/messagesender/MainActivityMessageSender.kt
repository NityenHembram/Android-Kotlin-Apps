package com.amvlabs.androidkotlinapps.messagesender

import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.amvlabs.androidkotlinapps.databinding.ActivityMainMessageBinding

private const val TAG = "tag"

class MainActivityMessageSender : AppCompatActivity() {
    private lateinit var sender: Sender
    private lateinit var binding: ActivityMainMessageBinding
    private lateinit var dialog: MyDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMessageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dialog = MyDialog(this)

        binding.sendBtn.setOnClickListener{
            sendMessage()
        }

        binding.sendMediaBtn.setOnClickListener{
            dialog.setDialog("Great","Attach media from Whatsapp app's attach button")
                .setPositiveButton("OK",DialogInterface.OnClickListener { dialogInterface, i ->
                    sendMessage()
                })
            dialog.showDialog()
        }

        sender = Sender(this)
    }

    private fun sendMessage() {
        val cpp = binding.cpp
        cpp.registerCarrierNumberEditText(binding.numberTxt)
        if(cpp.isValidFullNumber){
            val fullname = cpp.fullNumber
            val message = binding.msgTxt.text.toString()
            sender.openWhatsApp(fullname,message)
        }else{
            Toast.makeText(this,"Enter valid number",Toast.LENGTH_SHORT).show()
        }
    }


}