package com.androidzadatak.ui.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.androidzadatak.ui.theme.AppColors
import java.io.File

@Composable
fun MatchListItem(
    leagueIconUrl: String?,
    leagueIconLocalPath: String?,
    leagueName: String,
    currentTime: String,
    homeTeamAvatarUrl: String?,
    homeTeamAvatarLocalPath: String?,
    homeTeamName: String,
    homeTeamPoints: String,
    awayTeamAvatarUrl: String?,
    awayTeamAvatarLocalPath: String?,
    awayTeamName: String,
    awayTeamPoints: String,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val iconToLoadHomeTeam = homeTeamAvatarLocalPath?.let { File(it) } ?: homeTeamAvatarUrl
    val iconToLoadAwayTeam = awayTeamAvatarLocalPath?.let { File(it) } ?: awayTeamAvatarUrl
    val iconToLoadLeagueIcon = leagueIconLocalPath?.let { File(it) } ?: leagueIconUrl

    val imageLoader = ImageLoader.Builder(context)
        .components {
            add(SvgDecoder.Factory())
        }
        .build()

    Column(
        modifier = modifier
            .background(color = Color.Black, shape = RoundedCornerShape(12.dp))
            .border(
                width = 1.dp,
                color = AppColors.grey,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(iconToLoadLeagueIcon)
                    .crossfade(true)
                    .build(),
                contentDescription = leagueName,
                imageLoader = imageLoader,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                leagueName,
                color = Color.Gray,
                fontSize = 10.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
                lineHeight = 10.sp,
                modifier = Modifier.width(130.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            androidx.compose.material3.Icon(
                Icons.Default.PlayArrow,
                contentDescription = null,
                tint = AppColors.green,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                currentTime, color = AppColors.green, fontSize = 10.sp)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(iconToLoadHomeTeam)
                        .crossfade(true)
                        .build(),
                    contentDescription = homeTeamName,
                    imageLoader = imageLoader,
                    modifier = Modifier.size(24.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))
                Text(homeTeamName, color = Color.White, fontSize = 14.sp)
            }

            Spacer(modifier = Modifier.weight(1f))

            Text(homeTeamPoints, color = Color.White, fontSize = 14.sp, modifier = Modifier.padding(start = 10.dp))
        }

        Spacer(modifier = Modifier.height(4.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(iconToLoadAwayTeam)
                        .crossfade(true)
                        .build(),
                    contentDescription = awayTeamName,
                    imageLoader = imageLoader,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(awayTeamName, color = Color.White, fontSize = 14.sp)
            }

            Spacer(modifier = Modifier.weight(1f))

            Text(awayTeamPoints, color = Color.White, fontSize = 14.sp,  modifier = Modifier.padding(start = 5.dp))
        }
    }
}