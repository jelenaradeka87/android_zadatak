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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import com.androidzadatak.ui.theme.AppColors

@Composable
fun CompetitionListItem(
    leftIcon: ImageVector,
    leftText: String,
    centerIcon: ImageVector,
    centerSmallText1: String,
    centerSmallText2: String,
    centerBigText: String,
    rightIcon: ImageVector,
    rightText: String,
    modifier: Modifier = Modifier
) {
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
            Icon(
                imageVector = leftIcon,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(leftText, color = Color.White, fontSize = 14.sp)
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.weight(2f)
        ) {
            Icon(
                imageVector = centerIcon,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(36.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(centerSmallText1, color = Color.White, fontSize = 12.sp)
            Spacer(modifier = Modifier.height(2.dp))
            Text(centerSmallText2, color = Color.White, fontSize = 12.sp)
            Spacer(modifier = Modifier.height(2.dp))
            Text(centerBigText, color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                imageVector = rightIcon,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(rightText, color = Color.White, fontSize = 14.sp)
        }
    }
}
