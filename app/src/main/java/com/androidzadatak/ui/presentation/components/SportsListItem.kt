package com.androidzadatak.ui.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.androidzadatak.ui.theme.AppColors
import com.androidzadatak.util.TranslationUtil
import java.io.File

@Composable
fun SportsListItem(
    iconUrl: String?,
    localIconPath: String?,
    sport: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {

    val context = LocalContext.current
    val iconToLoad = localIconPath?.let { File(it) } ?: iconUrl


    val imageLoader = ImageLoader.Builder(context)
        .components {
            add(SvgDecoder.Factory())
        }
        .build()

    val backgroundColor = if (isSelected) AppColors.yellow else AppColors.unselectedBackground
    val contentColor = if (isSelected) AppColors.selectedContent else AppColors.unselectedContent

    Row(
        modifier = Modifier
            .clickable { onClick() }
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = if (isSelected) 12.dp else 8.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(iconToLoad)
                .crossfade(true)
                .build(),
            contentDescription = sport,
            imageLoader = imageLoader,
            modifier = Modifier.size(24.dp)
        )

        if (isSelected) {
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = TranslationUtil.translate(context, sport),
                color = contentColor,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
