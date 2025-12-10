package com.androidzadatak.ui.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.androidzadatak.ui.theme.AppColors
import java.io.File

@Composable
fun PreMatchListItem(
    homeTeamAvatar: String,
    homeTeamAvatarLocalPath: String?,
    homeTeamName: String,
    leagueIcon: String,
    leagueIconLocalPath: String?,
    leagueName: String,
    dateText: String,
    timeText: String,
    awayTeamAvatar: String,
    awayTeamAvatarLocalPath: String?,
    awayTeamName: String,
    modifier: Modifier = Modifier
) {

    val iconToLoadHomeTeam = homeTeamAvatarLocalPath?.let { File(it) } ?: homeTeamAvatar
    val iconToLoadAwayTeam = awayTeamAvatarLocalPath?.let { File(it) } ?: awayTeamAvatar
    val iconToLoadLeagueIcon = leagueIconLocalPath?.let { File(it) } ?: leagueIcon

    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            add(SvgDecoder.Factory())
        }
        .build()
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .border(
                width = 1.dp,
                color = AppColors.grey,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.weight(1f)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(iconToLoadHomeTeam)
                    .crossfade(true)
                    .build(),
                contentDescription = "",
                imageLoader = imageLoader,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(homeTeamName, color = Color.White, fontSize = 12.sp, textAlign = TextAlign.Center, lineHeight = 15.sp)
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.weight(2f)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(iconToLoadLeagueIcon)
                    .crossfade(true)
                    .build(),
                contentDescription = "",
                imageLoader = imageLoader,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(leagueName, color = Color.Gray, fontSize = 10.sp, textAlign = TextAlign.Center, lineHeight = 15.sp)
            Spacer(modifier = Modifier.height(2.dp))
            Text(dateText, color = Color.White, fontSize = 12.sp)
            Spacer(modifier = Modifier.height(2.dp))
            Text(timeText, color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.weight(1f)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(iconToLoadAwayTeam)
                    .crossfade(true)
                    .build(),
                contentDescription = "",
                imageLoader = imageLoader,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(awayTeamName, color = Color.White, fontSize = 12.sp, textAlign = TextAlign.Center, lineHeight = 15.sp)
        }
    }
}
