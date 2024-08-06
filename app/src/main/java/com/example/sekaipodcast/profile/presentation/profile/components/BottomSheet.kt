package com.example.sekaipodcast.profile.presentation.profile.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sekaipodcast.profile.presentation.user.BottomSheetsItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserBottomSheet(
    sheetState: SheetState,
    isSheetOpen: Boolean,
    onDismiss: () -> Unit,
    items: List<BottomSheetsItem>,
) {
    if (isSheetOpen) {
        ModalBottomSheet(
            containerColor = Color.White.copy(alpha = 1f),
            sheetState = sheetState,
            onDismissRequest = onDismiss
        ) {
            Column(
                modifier = Modifier
                    .requiredHeightIn(min = 300.dp, max = 400.dp)
                    .fillMaxSize()
                    .padding(16.dp) // Adjust padding as needed
            ) {
                items.forEach { item ->
                    Text(
                        text = item.title,
                        fontSize = item.fontSize.sp,
                        color = item.color,
                        fontWeight = item.fontWeight,
                        modifier = Modifier.padding(bottom = 8.dp).clickable {
                            item.action()
                        }
                    )
                }
            }
        }
    }
}