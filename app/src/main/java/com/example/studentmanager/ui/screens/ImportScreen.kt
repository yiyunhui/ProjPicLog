package com.example.studentmanager.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.studentmanager.R
import com.example.studentmanager.ui.viewmodel.StudentViewModel
import java.io.FileInputStream

@Composable
fun ImportScreen(
    viewModel: StudentViewModel = viewModel()
) {
    val context = LocalContext.current
    val importStatus by viewModel.importStatus.collectAsState()

    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            try {
                val inputStream = context.contentResolver.openInputStream(it)
                inputStream?.let { stream ->
                    viewModel.importStudentsFromExcel(stream)
                }
            } catch (e: Exception) {
                // 处理错误
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.import_excel),
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.file_format_requirements),
                    style = MaterialTheme.typography.subtitle1
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text("• ${stringResource(R.string.student_id)}")
                Text("• ${stringResource(R.string.student_name)}")
                Text("• ${stringResource(R.string.project_name)}")
                Text("• ${stringResource(R.string.class_name)}")
                Text("• ${stringResource(R.string.department)}")
            }
        }

        Button(
            onClick = { filePickerLauncher.launch("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text(stringResource(R.string.select_file))
        }

        importStatus?.let { status ->
            Text(
                text = status,
                color = if (status.contains("成功")) MaterialTheme.colors.primary else MaterialTheme.colors.error,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
} 