package me.dio.copa.catar.ui.screens.main_countries

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.dio.copa.catar.R
import me.dio.copa.catar.data.local.WorldCupData
import me.dio.copa.catar.ui.components.headers.HeaderPages
import me.dio.copa.catar.ui.theme.Copa2022Theme

@Composable
fun CountriesScreen() {
    val countries = WorldCupData.countries

    Copa2022Theme(darkTheme = false) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 32.dp)
            ) {
                // Header e Título
                item {
                    HeaderPages(stringResource(R.string.text_countries))
                    Text(
                        text = stringResource(R.string.text_cup_countries),
                        modifier = Modifier.padding(start = 12.dp, top = 24.dp, end = 12.dp),
                        style = MaterialTheme.typography.h2.copy(
                            fontSize = 32.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_bold)),
                            color = colorResource(R.color.text_body)
                        )
                    )
                }

                // País Sede (Qatar) em Destaque
                item {
                    val qatarRes = R.string.text_qatar
                    val qatar = countries.firstOrNull { it.name == qatarRes }
                    qatar?.let {
                        val countryName = stringResource(it.name)
                        Column(Modifier.padding(12.dp)) {
                            Text(
                                text = stringResource(R.string.text_host_country),
                                style = MaterialTheme.typography.h3.copy(
                                    fontSize = 24.sp,
                                    fontFamily = FontFamily(Font(R.font.roboto_bold)),
                                    color = colorResource(R.color.text_body)
                                )
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = it.flagRes),
                                    contentDescription = countryName,
                                    modifier = Modifier
                                        .weight(1f)
                                        .heightIn(max = 120.dp),
                                    contentScale = ContentScale.Fit
                                )
                                Text(
                                    text = countryName,
                                    modifier = Modifier.weight(1f),
                                    style = TextStyle(
                                        fontSize = 24.sp,
                                        fontFamily = FontFamily(Font(R.font.roboto_bold)),
                                        color = colorResource(R.color.text_body),
                                    )
                                )
                            }
                            Spacer(modifier = Modifier.height(32.dp))
                            Text(
                                text = stringResource(R.string.text_other_teams),
                                style = MaterialTheme.typography.h3.copy(
                                    fontSize = 24.sp,
                                    fontFamily = FontFamily(Font(R.font.roboto_bold)),
                                    color = colorResource(R.color.text_body)
                                )
                            )
                        }
                    }
                }

                // Lista Dinâmica
                val otherCountries = countries.filter { it.name != R.string.text_qatar }
                
                items(otherCountries) { country ->
                    val countryName = stringResource(country.name)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        Image(
                            painter = painterResource(id = country.flagRes),
                            contentDescription = countryName,
                            modifier = Modifier
                                .width(100.dp)
                                .height(65.dp),
                            contentScale = ContentScale.FillBounds
                        )
                        Text(
                            text = countryName,
                            modifier = Modifier.weight(1f),
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                color = colorResource(R.color.text_body),
                            )
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CountriesScreenPreview() {
    CountriesScreen()
}
