package me.dio.copa.catar.ui.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import me.dio.copa.catar.ui.components.headers.Header
import me.dio.copa.catar.ui.theme.Copa2022Theme
import me.dio.copa.catar.ui.theme.PrimaryColor

@Composable
fun MainScreen(onSettingsClick: () -> Unit) {
    Copa2022Theme(
        darkTheme = false
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Header(onSettingsClick = onSettingsClick)
                Image(
                    painter = painterResource(id = R.drawable.iv_banner_copa_2022),
                    modifier = Modifier.fillMaxWidth(),
                    contentDescription = stringResource(R.string.banner_world_cup_2022),
                    contentScale = ContentScale.FillWidth
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 12.dp, top = 32.dp, end = 12.dp, bottom = 32.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.Top),
                ) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .background(
                                color = PrimaryColor,
                                shape = RoundedCornerShape(size = 5.dp)
                            )
                            .padding(start = 12.dp, top = 16.dp, end = 12.dp, bottom = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
                        verticalAlignment = Alignment.Top,
                    ) {
                        Text(
                            text = stringResource(R.string.text_cup_matches),
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontFamily = FontFamily(Font(R.font.tomorrow_bold)),
                                color = colorResource(R.color.primary_color_2),
                            )
                        )
                    }
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .background(
                                color = PrimaryColor,
                                shape = RoundedCornerShape(size = 5.dp)
                            )
                            .padding(start = 12.dp, top = 16.dp, end = 12.dp, bottom = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
                        verticalAlignment = Alignment.Top,
                    ) {
                        Text(
                            text = stringResource(R.string.text_cup_countries),
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontFamily = FontFamily(Font(R.font.tomorrow_bold)),
                                color = colorResource(R.color.primary_color_2),
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
fun MainScreenPreview() {
    MainScreen(onSettingsClick = {})
}
