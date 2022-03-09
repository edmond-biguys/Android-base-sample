package com.cym.common.widget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.xmcc.androidbasesample.databinding.DialogCommonEditBinding

class EditDialog: DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DialogCommonEditBinding.inflate(inflater, container, false)
        binding.textViewTitle.text = "窗户"
        binding.editTextContent.setText("val binding = DialogCommonEditBinding.inflate(inflater, container, false)")
        return binding.root
    }

    companion object {
        fun newInstance(): EditDialog {
            val args = Bundle()

            val fragment = EditDialog()
            fragment.arguments = args
            return fragment
        }
    }


}