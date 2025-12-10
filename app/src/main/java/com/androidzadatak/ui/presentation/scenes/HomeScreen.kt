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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.androidzadatak.R
import com.androidzadatak.ui.presentation.MainViewModel
import com.androidzadatak.ui.presentation.components.CenteredLoader
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
    val context = LocalContext.current

    val sports by viewModel.sports.collectAsState()
    val sportsLoading by viewModel.sportsLoading.collectAsState()

    val matches by viewModel.matches.collectAsState()
    val matchesLoading by viewModel.matchesLoading.collectAsState()

    val competitions by viewModel.competitions.collectAsState()

    var selectedSportId by remember { mutableIntStateOf(1) }
    var selectedIndexDay by remember { mutableIntStateOf(0) }

    val filteredMatches = remember(matches, selectedSportId) {
        matches.filter { it.sportId == selectedSportId && it.status == Constants.LIVE }
    }

    val dates = remember(matches, selectedSportId) {
        DateUtil.getUniqueDates(context, matches.filter { it.sportId == selectedSportId })
    }

    val preMatchFiltered = remember(matches, selectedSportId, selectedIndexDay) {
        matches
            .filter { match ->
                match.sportId == selectedSportId &&
                        match.status == Constants.PRE_MATCH &&
                        DateUtil.formatDateLabel(context, match.date) ==
                        dates.getOrNull(selectedIndexDay)?.second
            }
            .sortedBy { it.date }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(bottom = 32.dp)
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        if (sportsLoading) {
            CenteredLoader(modifier = Modifier.height(50.dp))
        } else {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(sports) { sport ->
                    SportsListItem(
                        iconUrl = sport.sportIconUrl,
                        localIconPath = sport.localIconPath,
                        sport = sport.name,
                        isSelected = sport.id == selectedSportId,
                        onClick = { selectedSportId = sport.id }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        TitleView(text = stringResource(id = R.string.live_matches).uppercase())

        Spacer(modifier = Modifier.height(8.dp))

        if (matchesLoading) {
            CenteredLoader(modifier = Modifier.height(50.dp))
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
                    .padding(horizontal = 16.dp)
            ) {
                items(filteredMatches) { match ->

                    val competition = competitions.find { it.id == match.competitionId }

                    MatchListItem(
                        leagueIconUrl = competition?.competitionIconUrl ?: "",
                        leagueIconLocalPath = competition?.competitionIconLocalPath,
                        leagueName = competition?.name ?: "Competition ${match.competitionId}",
                        currentTime = match.currentTime ?: "-",
                        homeTeamAvatarUrl = match.homeTeamAvatar,
                        homeTeamAvatarLocalPath = match.homeTeamLocalPath,
                        homeTeamName = match.homeTeam,
                        homeTeamPoints = match.result?.home?.toString() ?: "-",
                        awayTeamAvatarUrl = match.awayTeamAvatar,
                        awayTeamAvatarLocalPath = match.awayTeamLocalPath,
                        awayTeamName = match.awayTeam,
                        awayTeamPoints = match.result?.away?.toString() ?: "-",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        TitleView(text = stringResource(id = R.string.prematch_offer).uppercase())

        Spacer(modifier = Modifier.height(16.dp))

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

        if (matchesLoading) {
            CenteredLoader(modifier = Modifier.height(50.dp))
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(horizontal = 16.dp)
            ) {
                items(preMatchFiltered) { match ->

                    val competition = competitions.find { it.id == match.competitionId }

                    PreMatchListItem(
                        homeTeamAvatar = match.homeTeamAvatar,
                        homeTeamAvatarLocalPath = match.homeTeamLocalPath,
                        homeTeamName = match.homeTeam,
                        leagueIcon = competition?.competitionIconUrl ?: "",
                        leagueIconLocalPath = competition?.competitionIconLocalPath,
                        leagueName = competition?.name ?: "Competition ${match.competitionId}",
                        dateText = DateUtil.formatDateLabel(context, match.date),
                        timeText = DateUtil.getTimeText(match.date),
                        awayTeamAvatar = match.awayTeamAvatar,
                        awayTeamAvatarLocalPath = match.awayTeamLocalPath,
                        awayTeamName = match.awayTeam,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    )
                }
            }
        }
    }
}
