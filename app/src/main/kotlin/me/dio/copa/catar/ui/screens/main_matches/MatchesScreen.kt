package me.dio.copa.catar.ui.screens.main_matches

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.dio.copa.catar.R
import me.dio.copa.catar.ui.components.headers.HeaderPages
import me.dio.copa.catar.ui.theme.Copa2022Theme

@Composable
fun MatchesScreen() {
    Copa2022Theme(darkTheme = false) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())) {
                HeaderPages(stringResource(R.string.text_matches))
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(start = 12.dp, top = 24.dp, end = 12.dp, bottom = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.Top),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(
                        text = stringResource(R.string.text_cup_matches),
                        style = MaterialTheme.typography.h2.copy(
                            fontSize = 32.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_bold)),
                            color = colorResource(R.color.text_body)
                        )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun MatchesScreenPreview() {
    MatchesScreen()
}