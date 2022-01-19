package com.cym.jetpack.workmanager

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.zltech.compose_view.CommonItemComposeView

@Composable
fun WorkManagerComposeView(navController: NavHostController = rememberNavController()) {

    Scaffold(modifier = Modifier.background(Color.LightGray)) {
        Column {
            Text(text = "work manager compose view")
            CommonItemComposeView(title = "CommonItemComposeView", onClick = { /*TODO*/ })

            WorkManagerViewModelTestComposeView()

            val viewModel: WorkManagerViewModel = viewModel()
            viewModel.start(navController.context)
            println("caoj $viewModel")

            if (viewModel.workResultState) {
                Text(text = "workResultState is ${viewModel.workResultState}")
            } else {
                Text(text = " false workResultState is ${viewModel.workResultState}")
            }
        }
    }
}

@Composable
fun WorkManagerViewModelTestComposeView() {
    val viewModel: WorkManagerViewModel = viewModel()
    println("caoj 2 $viewModel")
    if (viewModel.workResultState) {
        Text(text = "workResultState in WorkManagerViewModelTestComposeView is ${viewModel.workResultState}")
    } else {
        Text(text = " false workResultState in WorkManagerViewModelTestComposeView is ${viewModel.workResultState}")
    }

}