package com.watasolutions.file.services

import android.annotation.SuppressLint
import android.content.Context
import java.io.*
import java.text.SimpleDateFormat
import java.util.*

class FileUtils {

    fun createFile(context: Context, fileName: String? = null, extensionType: String): File {
        return fileName?.let {
            File(context.filesDir, "${fileName}.${extensionType}")
        } ?: File(context.filesDir, "${randomNameBaseOnTime()}.${extensionType}")

    }

    fun createCachedFile(context: Context, fileName: String? = null, extensionType: String): File {
        return fileName?.let {
            File(context.cacheDir, "${fileName}.${extensionType}")
        } ?: File(context.cacheDir, "${randomNameBaseOnTime()}.${extensionType}")

    }

    fun createFileInExternalStorage(
        context: Context,
        fileName: String? = null,
        extensionType: String
    ): File {
        return fileName?.let {
            File(context.getExternalFilesDir(null), "${fileName}.${extensionType}")
        } ?: File(context.getExternalFilesDir(null), "${randomNameBaseOnTime()}.${extensionType}")
    }

    fun createCachedFileInExternalStorage(
        context: Context,
        fileName: String? = null,
        extensionType: String
    ): File {
        return fileName?.let {
            File(context.externalCacheDir, "${fileName}.${extensionType}")
        } ?: File(context.externalCacheDir, "${randomNameBaseOnTime()}.${extensionType}")
    }

    //generate file name
    fun createImageFile(parentDir: File, extensionType: String): File {
        // Create an image file name
        val timeStamp: String = randomNameBaseOnTime()

        // Create the storage directory if it does not exist
        if (!parentDir.exists() && !parentDir.mkdirs()) {
            throw FileNotFoundException("parent directory is not found")
        }
        val fileName = "${timeStamp}.${extensionType}"

        // Return the file target for the photo based on filename
        return File(parentDir.path + File.separator + fileName)
    }

    //write data to file
    fun writeDataToFile(file: File, content: String) {
        val fos: FileOutputStream
        try {
            fos = FileOutputStream(file)
            fos.write(content.toByteArray())
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    //read data to file
    fun readDataFromFile(file: File): String {
        val fis: FileInputStream
        try {
            fis = FileInputStream(file)
            val inputStreamReader = InputStreamReader(fis)
            val bufferReader = BufferedReader(inputStreamReader)
            val stringBuilder = StringBuilder()
            var text: String? = null
            while (run {
                    text = bufferReader.readLine()
                    text
                } != null) {
                stringBuilder.append(text)
            }
            return stringBuilder.toString()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            return ""
        } catch (e: IOException) {
            e.printStackTrace()
            return ""
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun randomNameBaseOnTime(): String {
        return SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    }
}

class FileUtilsException(msg: String) : Exception(msg)