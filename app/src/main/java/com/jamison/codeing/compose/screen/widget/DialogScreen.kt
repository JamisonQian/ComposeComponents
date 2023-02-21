package com.jamison.codeing.compose.screen.widget

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.jamison.codeing.compose.screen.viewmodel.DialogViewModel
import com.jamison.codeing.compose.ui.theme.Gray500
import com.jamison.codeing.compose.ui.theme.StatusBar
import com.jamison.codeing.compose.ui.theme.largeButton
import com.jamison.codeing.compose.widget.AppTopBar
import com.jamison.codeing.compose.widget.SuperButton

/**
 * @FileName ScrollBehaviorTopBar
 * @Description
 * @Author arjun
 * @Date 2022/6/22 13:48
 * @Version
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogScreen(navController: NavController) {
    Scaffold(
        topBar = {
            AppTopBar(
                title = {
                    Text(text = "Dialog", color = Color.White, fontSize = 18.sp)
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                },
                backgroundColor = StatusBar
            )

        }
    ) {


        val viewModel:DialogViewModel = viewModel()


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            SuperButton(
                onClick = {
                    viewModel.openDialog()
                },
                modifier = Modifier
                    .padding(12.dp)
                    .largeButton(),
            ) {
                Text(text = "AlertDialog", color = Color.White)
            }
        }

        AlertDialogNormal(viewModel)
        Log.d("TAG","绘制")
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AlertDialogNormal(viewModel: DialogViewModel) {
    val showDialogState:Boolean by viewModel.showDialog.collectAsState()
    if (showDialogState) {
        AlertDialog(
            onDismissRequest = {
                viewModel.dismissDialog()
            },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.dismissDialog()
                    },
                    shape = RoundedCornerShape(bottomStart = 8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                ) {
                    Text(text = "取消", color = Color.Black)
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        viewModel.dismissDialog()
                    },
                    shape = RoundedCornerShape(bottomEnd = 8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                ) {
                    Text(text = "确定", color = Color.Black)
                }
            },
            title = {
                Text(text = "对话框标题")
            },
            text = {
                Text(text = "对话框内容对话框内容")
            },
            shape = RoundedCornerShape(8.dp),
            containerColor = Color.White,
            properties = DialogProperties(
                usePlatformDefaultWidth = false
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
    
    Dialog(onDismissRequest = { /*TODO*/ }) {
        
    }
}
