package com.example.studentmanager.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentmanager.data.AppDatabase
import com.example.studentmanager.data.model.Student
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.apache.poi.ss.usermodel.WorkbookFactory
import java.io.InputStream

class StudentViewModel(application: Application) : AndroidViewModel(application) {
    private val database = AppDatabase.getDatabase(application)
    private val studentDao = database.studentDao()

    private val _searchResult = MutableStateFlow<Student?>(null)
    val searchResult: StateFlow<Student?> = _searchResult

    private val _searchError = MutableStateFlow<String?>(null)
    val searchError: StateFlow<String?> = _searchError

    private val _importStatus = MutableStateFlow<String?>(null)
    val importStatus: StateFlow<String?> = _importStatus

    fun searchStudent(studentId: String, studentName: String) {
        viewModelScope.launch {
            try {
                val student = studentDao.getStudentById(studentId)
                if (student != null && student.name == studentName) {
                    _searchResult.value = student
                    _searchError.value = null
                } else {
                    _searchResult.value = null
                    _searchError.value = "未找到匹配的学生信息"
                }
            } catch (e: Exception) {
                _searchResult.value = null
                _searchError.value = "查询出错：${e.message}"
            }
        }
    }

    fun importStudentsFromExcel(inputStream: InputStream) {
        viewModelScope.launch {
            try {
                val workbook = WorkbookFactory.create(inputStream)
                val sheet = workbook.getSheetAt(0)
                val students = mutableListOf<Student>()

                for (row in sheet.drop(1)) { // 跳过表头
                    val student = Student(
                        studentId = row.getCell(0)?.stringCellValue ?: continue,
                        name = row.getCell(1)?.stringCellValue ?: continue,
                        projectName = row.getCell(2)?.stringCellValue ?: continue,
                        className = row.getCell(3)?.stringCellValue ?: continue,
                        department = row.getCell(4)?.stringCellValue ?: continue
                    )
                    students.add(student)
                }

                studentDao.insertStudents(students)
                _importStatus.value = "成功导入 ${students.size} 条学生信息"
            } catch (e: Exception) {
                _importStatus.value = "导入失败：${e.message}"
            }
        }
    }
} 