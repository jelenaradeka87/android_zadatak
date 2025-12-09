package com.androidzadatak.ui.presentation.scenes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.androidzadatak.R
import com.androidzadatak.ui.presentation.MainViewModel
import com.androidzadatak.ui.presentation.components.HorizontalListItem
import com.androidzadatak.ui.presentation.components.MatchListItem
import com.androidzadatak.ui.presentation.components.PreMatchListItem
import com.androidzadatak.ui.presentation.components.SportsListItem
import com.androidzadatak.ui.presentation.components.TitleView
import com.androidzadatak.util.Constants
import com.androidzadatak.util.DateUtil


@Composable
fun HomeScreen(
    viewModel: MainViewModel = viewModel()
) {

    val sports by viewModel.sports
    val matches by viewModel.matches
    val competitions by viewModel.competitions
    var selectedSportId by remember { mutableIntStateOf(1) }
    val filteredMatches = matches.filter {
        it.sportId == selectedSportId || it.status == Constants.LIVE
    }
    val context = LocalContext.current
    val dates = remember(matches) { DateUtil.getUniqueDates(context, matches) }
    var selectedIndexDay by remember { mutableIntStateOf(0) }
    val preMatchFiltered = matches
        .filter { match ->
            match.status == Constants.PRE_MATCH &&
                    DateUtil.formatDateLabel(context, match.date) ==
                    dates[selectedIndexDay].second
        }
        .sortedBy { it.date }

    LaunchedEffect(Unit) {
        viewModel.loadSports()
        viewModel.loadMatches()
        viewModel.loadCompetitions()
    }
    LaunchedEffect(sports) {
        if (sports.isNotEmpty()) {
            selectedSportId = sports.first().id
        }
    }


    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(sports) { sport ->
                SportsListItem(
                    iconUrl = sport.sportIconUrl,
                    sport = sport.name,
                    isSelected = sport.id == selectedSportId,
                    onClick = { selectedSportId = sport.id }
                )
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        TitleView(
            text = stringResource(id = R.string.live_matches).toUpperCase(Locale.current)
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
                .height(250.dp)
        ) {
            items(filteredMatches) { match ->

                val competition = competitions.find { it.id == match.competitionId }

                MatchListItem(
                    leagueIconUrl = competition?.competitionIconUrl ?: "",
                    leagueName = competition?.name ?: "Competition ${match.competitionId}",
                    currentTime = match.currentTime ?: "-",
                    homeTeamAvatarUrl = match.homeTeamAvatar,
                    homeTeamName = match.homeTeam,
                    homeTeamPoints = match.result?.home?.toString() ?: "-",
                    awayTeamAvatarUrl = match.awayTeamAvatar,
                    awayTeamName = match.awayTeam,
                    awayTeamPoints = match.result?.away?.toString() ?: "-",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )
            }
        }


        Spacer(modifier = Modifier.height(20.dp))

        TitleView(
            text = stringResource(id = R.string.prematch_offer).toUpperCase(Locale.current)
        )

        Spacer(modifier = Modifier.height(24.dp))

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(dates) { index, item ->
                HorizontalListItem(
                    text = item.second,
                    isSelected = index == selectedIndexDay,
                    onClick = { selectedIndexDay = index }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(250.dp)
        ) {
            items(preMatchFiltered) { match ->
                val competition = competitions.find { it.id == match.competitionId }

                PreMatchListItem(
                    homeTeamAvatar = match.homeTeamAvatar,
                    homeTeamName = match.homeTeam,
                    leagueIcon = competition?.competitionIconUrl ?: "",
                    leagueName = competition?.name ?: "Competition ${match.competitionId}",
                    dateText = DateUtil.formatDateLabel(LocalContext.current, match.date!!),
                    timeText = DateUtil.getTimeText(match.date!!),
                    awayTeamAvatar = match.awayTeamAvatar,
                    awayTeamName = match.awayTeam,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )
            }
        }

    }
}