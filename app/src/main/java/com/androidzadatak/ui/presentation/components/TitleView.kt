package com.androidzadatak.ui.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.androidzadatak.ui.theme.AppColors

@Composable
fun TitleView(
    text: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .width(1.5.dp)
                .height(15.dp)
                .background(color = AppColors.yellow, shape = RoundedCornerShape(1.dp))
        )
        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = text,
            color = AppColors.lightGrey,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )

    }
}