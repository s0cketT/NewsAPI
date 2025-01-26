package com.newsapi.presentation.news_screen.news_detail

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.newsapi.domain.model.ArticleResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import java.net.URLDecoder
import java.nio.charset.StandardCharsets
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone
import javax.inject.Inject

@HiltViewModel
class NewsDetailViewModel @Inject constructor(): ViewModel() {

    //переходим по ссылке
    fun openLink(url: String, context: Context) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }

    //декодируем объект, который получили при навигации
    fun getNewsFromJson(newsJson: String?): ArticleResponse? {
        // Декодируем строку JSON, если она есть
        val decodedJson = newsJson?.let {
            try {
                URLDecoder.decode(it, StandardCharsets.UTF_8.toString())
            } catch (e: Exception) {
                // Логирование ошибки при декодировании
                e.printStackTrace()
                null
            }
        }

        // Десериализуем JSON обратно в объект ArticleResponse
        return decodedJson?.let {
            try {
                Gson().fromJson(it, ArticleResponse::class.java)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }


    //конвертируем дату в нормальный вид
    fun formatIsoDate(isoDate: String, outputFormat: String = "dd MMM yyyy, HH:mm"): String {
        return try {
            // Формат входящей даты
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
            inputFormat.timeZone = TimeZone.getTimeZone("UTC") // Устанавливаем часовой пояс UTC

            // Формат для вывода даты
            val outputFormatter = SimpleDateFormat(outputFormat, Locale.ENGLISH)

            // Преобразуем строку в дату и форматируем в выходной формат
            val date = inputFormat.parse(isoDate)
            outputFormatter.format(date ?: return "Invalid date")
        } catch (e: Exception) {
            "Invalid date"
        }
    }

}