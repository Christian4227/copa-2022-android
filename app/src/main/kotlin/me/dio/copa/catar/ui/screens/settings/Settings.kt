package me.dio.copa.catar.ui.screens.settings

import android.Manifest
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import me.dio.copa.catar.data.worker.NotificationWorker
import me.dio.copa.catar.extensions.hasNotificationPermission
import me.dio.copa.catar.ui.components.headers.HeaderPages
import me.dio.copa.catar.ui.components.switches.CustomSwitch
import me.dio.copa.catar.ui.theme.Copa2022Theme

@Composable
fun Settings(onBackClick: () -> Unit) {
    val context = LocalContext.current

    // Estado que controla se o switch visualmente está ligado
    var notificationsEnabled by remember {
        mutableStateOf(context.hasNotificationPermission())
    }

    // 1. Definimos o Launcher para pedir permissão no Compose
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        notificationsEnabled = isGranted
        if (isGranted) {
            NotificationWorker.start(context)
        } else {
            Toast.makeText(
                context,
                R.string.text_conf_notification_permission_denied,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    Copa2022Theme(darkTheme = false) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                HeaderPages(stringResource(R.string.text_settings))

                // Botão de Voltar
                Row(
                    modifier = Modifier
                        .padding(start = 12.dp, top = 24.dp, end = 12.dp, bottom = 24.dp)
                        .clickable { onBackClick() },
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_arrow_left),
                        modifier = Modifier.size(40.dp),
                        contentDescription = stringResource(R.string.back_button),
                        contentScale = ContentScale.FillBounds
                    )
                    Text(
                        text = stringResource(R.string.back_button),
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_semibold)),
                            color = colorResource(R.color.text_body),
                        )
                    )
                }

                Column(
                    modifier = Modifier.padding(horizontal = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    Text(
                        text = stringResource(R.string.text_notifications_copa),
                        style = MaterialTheme.typography.h2.copy(
                            fontSize = 32.sp,
                            fontFamily = FontFamily(Font(R.font.roboto_bold)),
                            color = colorResource(R.color.text_body)
                        )
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        CustomSwitch(
                            checked = notificationsEnabled,
                            onCheckedChange = { isEnabled ->
                                if (isEnabled) {
                                    // 2. Se for Android 13+ e não tiver permissão, solicita
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && !context.hasNotificationPermission()) {
                                        permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                                    } else {
                                        notificationsEnabled = true
                                        NotificationWorker.start(context)
                                    }
                                } else {
                                    notificationsEnabled = false
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

@Preview
@Composable
fun SettingsPreview() {
    Settings(onBackClick = {})
}
