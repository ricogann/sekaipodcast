package com.example.sekaipodcast.podcast.presentation.addpodcast

import android.widget.Toast
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.sekaipodcast.SetStatusBarColor
import com.example.sekaipodcast.podcast.presentation.addpodcast.components.AddButton
import com.example.sekaipodcast.podcast.presentation.addpodcast.components.DropdownCountry
import com.example.sekaipodcast.podcast.presentation.addpodcast.components.DropdownLevel
import com.example.sekaipodcast.podcast.presentation.addpodcast.components.ImagePicker
import com.example.sekaipodcast.podcast.presentation.addpodcast.components.InputText
import com.example.sekaipodcast.podcast.presentation.addpodcast.components.PodcastPicker
import com.example.sekaipodcast.podcast.presentation.addpodcast.components.Toast
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.io.InputStream

@Composable
fun AddEditPodcastScreen(
    modifier: Modifier = Modifier,
    viewModel: AddPodcastViewModel = hiltViewModel()
) {
    SetStatusBarColor(color = Color.White)
    var titleState by remember { mutableStateOf(TextFieldValue()) }
    var descriptionState by remember { mutableStateOf(TextFieldValue()) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var podcastState by remember { mutableStateOf<Uri?>(null) }
    var countryState by remember { mutableStateOf<String?>(null) }
    var levelState by remember { mutableStateOf<String?>(null) }
    val accountState = "91ceaca6-c228-4b69-99bf-2eeb7d33f224"

    var titleError by remember { mutableStateOf<String?>(null) }
    var descriptionError by remember { mutableStateOf<String?>(null) }
    var imageError by remember { mutableStateOf<String?>(null) }
    var podcastError by remember { mutableStateOf<String?>(null) }
    var countryError by remember { mutableStateOf<String?>(null) }
    var levelError by remember { mutableStateOf<String?>(null) }

    fun validateFields(): Boolean {
        var isValid = true
        titleError = if (titleState.text.isBlank()) {
            isValid = false
            "Title is required"
        } else null

        descriptionError = if (descriptionState.text.isBlank()) {
            isValid = false
            "Description is required"
        } else null

        imageError = if (imageUri == null) {
            isValid = false
            "Thumbnail image is required"
        } else null

        podcastError = if (podcastState == null) {
            isValid = false
            "Podcast file is required"
        } else null

        countryError = if (countryState == null) {
            isValid = false
            "Country is required"
        } else null

        levelError = if (levelState == null) {
            isValid = false
            "Level is required"
        } else null

        return isValid
    }

    val context = LocalContext.current
    val contentResolver = context.contentResolver

    var countries = viewModel.countries ?: emptyList()
    var levels = viewModel.level ?: emptyList()
    var statusUpload = viewModel.statusUpload
//    var statusUpload = true

    fun getFileFromUri(uri: Uri): File? {
        return try {
            val inputStream: InputStream? = contentResolver.openInputStream(uri)
            val file = File(context.cacheDir, uri.lastPathSegment ?: "temp_file")
            inputStream?.use { input ->
                file.outputStream().use { output ->
                    input.copyTo(output)
                }
            }
            file
        } catch (e: Exception) {
            Log.e("FileError", "Error getting file from URI: ${e.message}")
            null
        }
    }

    fun UploadPodcast() {
        if (!validateFields()) return

        val titleBody = titleState.text.toRequestBody("text/plain".toMediaTypeOrNull())
        val descriptionBody = descriptionState.text.toRequestBody("text/plain".toMediaTypeOrNull())
        val countryBody = (countryState ?: "").toRequestBody("text/plain".toMediaTypeOrNull())
        val levelBody = (levelState ?: "").toRequestBody("text/plain".toMediaTypeOrNull())
        val accountBody = accountState.toRequestBody("text/plain".toMediaTypeOrNull())

        val imagePart = imageUri?.let { uri ->
            getFileFromUri(uri)?.let { file ->
                val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
                MultipartBody.Part.createFormData("thumbnail", file.name, requestFile)
            }
        }

        val podcastPart = podcastState?.let { uri ->
            getFileFromUri(uri)?.let { file ->
                val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
                MultipartBody.Part.createFormData("podcast", file.name, requestFile)
            }
        }

        viewModel.postUploadPodcast(
            titleBody,
            descriptionBody,
            countryBody,
            levelBody,
            accountBody,
            imagePart,
            podcastPart
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (statusUpload) {
            Toast ( { } )
        }
        LazyColumn (
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            item {
                if (imageUri != null) {
                    Image(
                        modifier = Modifier
                            .size(360.dp)
                            .clip(RoundedCornerShape(25.dp)),
                        painter = rememberAsyncImagePainter(imageUri),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .size(360.dp)
                            .clip(RoundedCornerShape(25.dp))
                            .background(Color(0xFFEFEFEF)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Thumbnail",
                            color = Color.Gray,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }

            item {
                ImagePicker(imageUri, imageError) { uri -> imageUri = uri }
                Spacer(modifier = Modifier.height(6.dp))
                InputText(
                    value = titleState,
                    onValueChange = { titleState = it },
                    label = "Title",
                    errorMessage = titleError
                )
                Spacer(modifier = Modifier.height(6.dp))
                InputText(
                    value = descriptionState,
                    onValueChange = { descriptionState = it },
                    label = "Description",
                    errorMessage = descriptionError
                )
                Spacer(modifier = Modifier.height(6.dp))
                DropdownCountry(
                    label = "Country",
                    items = countries,
                    selectedId = countryState,
                    onSelect = { id -> countryState = id },
                    errorMessage = countryError
                )
                Spacer(modifier = Modifier.height(6.dp))
                DropdownLevel(
                    label = "Level",
                    items = levels,
                    selectedId = levelState,
                    onSelect = { id -> levelState = id },
                    errorMessage = levelError
                )
                Spacer(modifier = Modifier.height(6.dp))
                PodcastPicker(podcastState, podcastError) { uri -> podcastState = uri }
            }

            item {
                Spacer(modifier = Modifier.height(10.dp))
                AddButton(onClick = { UploadPodcast() }, buttonText = "UPLOAD CONTENT")
            }
        }
    }
}
