package me.dio.copa.catar.ui.screens.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
import me.dio.copa.catar.ui.components.switches.CustomSwitch
import me.dio.copa.catar.ui.components.headers.HeaderPages
import me.dio.copa.catar.ui.theme.Copa2022Theme
import me.dio.copa.catar.data.worker.NotificationWorker
import me.dio.copa.catar.extensions.hasNotificationPermission

@Composable
fun Settings(onBackClick: () -> Unit) {
    val context = LocalContext.current
    var notificationsEnabled by remember {
        mutableStateOf(context.hasNotificationPermission())
    }

    Copa2022Theme(
        darkTheme = false
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp),
            color = MaterialTheme.colors.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(0.dp),
                verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
                horizontalAlignment = Alignment.Start,
            ) {
                HeaderPages(stringResource(R.string.text_settings))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(0.dp),
                    verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
                    horizontalAlignment = Alignment.Start,
                ) {
                    // Cabeçalho
                    Row(
                        modifier = Modifier
                            .padding(start = 12.dp, top = 24.dp, end = 12.dp, bottom = 24.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_arrow_left),
                            modifier = Modifier
                                .size(40.dp)
                                .clickable { onBackClick() },
                            contentDescription = stringResource(R.string.back_button),
                            contentScale = ContentScale.FillBounds
                        )
                        Text(
                            text = stringResource(R.string.back_button), style = TextStyle(
                                fontSize = 20.sp,
                                fontFamily = FontFamily(Font(R.font.roboto_semibold)),
                                color = colorResource(R.color.text_body),
                            )
                        )
                    }
                    // Corpo da tela
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 12.dp, top = 0.dp, end = 12.dp, bottom = 0.dp),
                        verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.Top),
                        horizontalAlignment = Alignment.Start,
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(0.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
                            horizontalAlignment = Alignment.Start,
                        ) {
                            Text(
                                text = stringResource(R.string.text_notifications_copa),
                                style = MaterialTheme.typography.h2.copy(
                                    fontSize = 32.sp,
                                    fontFamily = FontFamily(Font(R.font.roboto_bold)),
                                    color = colorResource(R.color.text_body)
                                )
                            )
                            // Switch de notificações
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(
                                    12.dp,
                                    Alignment.Start
                                ),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                CustomSwitch(
                                    checked = notificationsEnabled,
                                    onCheckedChange = { isEnabled ->
                                        notificationsEnabled = isEnabled
                                        if (isEnabled) {
                                            NotificationWorker.start(context)
                                        } else {
                                            NotificationWorker.stop(context)
                                        }
                                    }
                                )
                                Text(
                                    text = stringResource(R.string.text_match_own_country),
                                    style = TextStyle(
                                        fontSize = 16.sp,
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
    }
}

@Preview
@Composable
fun SettingsPreview() {
    Settings(onBackClick = {})
}
