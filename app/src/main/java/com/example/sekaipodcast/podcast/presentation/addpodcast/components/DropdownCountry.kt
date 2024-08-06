package com.example.sekaipodcast.podcast.presentation.addpodcast.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.sekaipodcast.common.Constants
import com.example.sekaipodcast.podcast.data.remote.dto.DataCountry

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownCountry(
    modifier: Modifier = Modifier,
    label: String,
    items: List<DataCountry> = emptyList(),
    selectedId: String?,
    onSelect: (String) -> Unit,
    errorMessage: String? = null
) {
    var isExpanded by remember { mutableStateOf(false) }
    var isError by remember { mutableStateOf(false) }

    LaunchedEffect(errorMessage) {
        isError = errorMessage != null
    }

    Text(
        text = label,
        fontSize = 15.sp,
        modifier = Modifier.padding(bottom = 4.dp)
    )

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { isExpanded = it }
    ) {
        TextField(
            value = items.find { it.id == selectedId }?.name ?: "Select a $label",
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            },
            leadingIcon = {
                val logo = items.find { it.id == selectedId }?.image ?: null
                if (logo != null) {
                    AsyncImage(
                        model = "${Constants.BASE_URL_DEV}/v1/country/logo/${logo}",
                        contentDescription = logo,
                        modifier = Modifier
                            .size(40.dp)
                            .padding(start = 8.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                }
            },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                unfocusedContainerColor = Color(0xFFEFEFEF),
                focusedContainerColor = Color(0xFFEFEFEF),
                unfocusedTextColor = Color.Black,
                focusedTextColor = Color.Black,
                errorContainerColor = Color(0xFFFFEBEE),
                errorTextColor = Color.Red,
                errorIndicatorColor = Color.Transparent
            ),
            isError = isError,
            shape = RoundedCornerShape(16.dp),
            modifier = modifier
                .fillMaxWidth()
                .menuAnchor()
        )

        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
            modifier = Modifier.background(Color(0xFFEFEFEF))
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AsyncImage(
                                model = "${Constants.BASE_URL_DEV}/v1/country/logo/${item.image}",
                                contentDescription = item.name,
                                modifier = Modifier
                                    .size(40.dp)
                                    .padding(end = 8.dp)
                                    .clip(RoundedCornerShape(8.dp))
                            )
                            Text(
                                text = item.name,
                                color = Color.Black
                            )
                        }
                    },
                    modifier = Modifier
                        .background(Color(0xFFEFEFEF))
                        .padding(8.dp),
                    onClick = {
                        onSelect(item.id)
                        isError = false
                        isExpanded = false
                    }
                )
            }
        }
    }
    if (isError && errorMessage != null) {
        Text(
            text = errorMessage,
            color = Color.Red,
            fontSize = 14.sp,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}
