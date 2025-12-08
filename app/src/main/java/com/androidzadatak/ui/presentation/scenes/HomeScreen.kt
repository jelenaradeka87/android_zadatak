package com.androidzadatak.ui.presentation.scenes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import com.androidzadatak.ui.presentation.components.SportsListItem
import com.androidzadatak.ui.presentation.components.TitleView
import com.androidzadatak.R
import com.androidzadatak.ui.presentation.components.CompetitionListItem
import com.androidzadatak.ui.presentation.components.HorizontalListItem
import com.androidzadatak.ui.presentation.components.MatchListItem


@Composable
fun HomeScreen() {

    var selectedIndex by remember { mutableStateOf(0) }
    var selectedIndexDay by remember { mutableStateOf(0) }
    val sportsList = remember {
        List(10) { index -> Icons.Default.Favorite to "Sport $index" }
    }
    val daysList = remember {
        List(5) { index -> "Danas" }
    }


    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        LazyRow(
            modifier = Modifier.fillMaxWidth().padding(start = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(sportsList) { index, item ->
                SportsListItem(
                    icon = item.first,
                    sport = item.second,
                    isSelected = index == selectedIndex,
                    onClick = { selectedIndex = index }
                )
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        TitleView(
            text = stringResource(id = R.string.live_matches).toUpperCase(Locale.current)
        )

        Spacer(modifier = Modifier.height(8.dp))

        MatchListItem(
            topLeftIcon1 = Icons.Default.Favorite,
            topLeftText1 = "Team A",
            topLeftIcon2 = Icons.Default.Favorite,
            topLeftText2 = "Team B",
            row1Icon = Icons.Default.Favorite,
            row1Text = "Goals",
            row1Number = "2",
            row2Icon = Icons.Default.Favorite,
            row2Text = "Shots",
            row2Number = "5",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        TitleView(
            text = stringResource(id = R.string.prematch_offer).toUpperCase(Locale.current)
        )

        Spacer(modifier = Modifier.height(24.dp))

        LazyRow(
            modifier = Modifier.fillMaxWidth().padding(start = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(sportsList) { index, item ->
                HorizontalListItem (
                    text = item.second,
                    isSelected = index == selectedIndexDay,
                    onClick = { selectedIndexDay = index }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        CompetitionListItem(
            leftIcon = Icons.Default.Star,
            leftText = "Team A",
            centerIcon = Icons.Default.Favorite,
            centerSmallText1 = "Small info 1",
            centerSmallText2 = "Small info 2",
            centerBigText = "Score 3",
            rightIcon = Icons.Default.Star,
            rightText = "Team B",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

    }
}