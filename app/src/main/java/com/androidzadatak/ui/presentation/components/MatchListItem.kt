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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.androidzadatak.ui.theme.AppColors

@Composable
fun MatchListItem(
    topLeftIcon1: ImageVector,
    topLeftText1: String,
    topLeftIcon2: ImageVector,
    topLeftText2: String,
    row1Icon: ImageVector,
    row1Text: String,
    row1Number: String,
    row2Icon: ImageVector,
    row2Text: String,
    row2Number: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(color = Color.Black, shape = RoundedCornerShape(12.dp))
            .border(
                width = 1.dp,
                color = AppColors.grey,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                topLeftIcon1,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(topLeftText1, color = Color.White, fontSize = 14.sp)

            Spacer(modifier = Modifier.width(16.dp))

            Icon(
                topLeftIcon2,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(topLeftText2, color = Color.White, fontSize = 14.sp)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    row1Icon,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(row1Text, color = Color.White, fontSize = 14.sp)
            }

            Spacer(modifier = Modifier.weight(1f))

            Text(row1Number, color = Color.White, fontSize = 14.sp)
        }

        Spacer(modifier = Modifier.height(4.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    row2Icon,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(row2Text, color = Color.White, fontSize = 14.sp)
            }

            Spacer(modifier = Modifier.weight(1f))

            Text(row2Number, color = Color.White, fontSize = 14.sp)
        }
    }
}
