package com.cym.housedecoration.detail

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import androidx.appcompat.app.AppCompatActivity
import com.cym.housedecoration.bean.DecorativeMaterial

/*
这是自己定义的ActivityResultContract
 */
class GoToDetailResultContract: ActivityResultContract<String, DecorativeMaterial>() {
    override fun createIntent(context: Context, input: String): Intent {
        val intent = Intent(context, DecorativeMaterialDetailActivity::class.java)
        intent.putExtra("input", input)
        return intent
    }

    override fun parseResult(resultCode: Int, intent: Intent?): DecorativeMaterial {
        var result = DecorativeMaterial()
        if (resultCode == AppCompatActivity.RESULT_OK) {
            println("resultCode ok $resultCode")
            result = intent?.getParcelableExtra("DecorativeMaterial")!!
        } else {
            println("resultCode $resultCode")
        }
        return result
    }

}