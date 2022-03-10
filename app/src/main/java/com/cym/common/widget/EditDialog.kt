package com.cym.common.widget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.MutableLiveData
import com.xmcc.androidbasesample.databinding.DialogCommonEditBinding

class EditDialog: DialogFragment() {
    val liveData = MutableLiveData<String>()

    private var editContent = ""
    private var editTitle = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        editContent = arguments?.getString("content").toString()
        editTitle = arguments?.getString("title").toString()

        val binding = DialogCommonEditBinding.inflate(inflater, container, false)
        with(binding) {
            textViewTitle.text = editTitle
            editTextContent.setText(editContent)
            editTextContent.addTextChangedListener {
                editContent = it.toString()
            }
            buttonConfirm.setOnClickListener {
                liveData.value = editContent
                dismiss()
            }
            buttonCancel.setOnClickListener { dismiss() }
        }
        return binding.root
    }

    companion object {
        fun newInstance(title: String = "", content: String = ""): EditDialog {
            val args = Bundle()
            args.putString("content", content)
            args.putString("title", title)
            val fragment = EditDialog()
            fragment.arguments = args
            return fragment
        }
    }
}