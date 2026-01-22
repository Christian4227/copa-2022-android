package me.dio.copa.catar.ui.components.headers

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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

@Composable
fun HeaderPages(
    title: String = ""
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp)
            .background(color = colorResource(R.color.primary_color)),
        verticalAlignment = Alignment.Bottom
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, top = 16.dp, end = 12.dp, bottom = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom,
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(24.dp, Alignment.Start),
                verticalAlignment = Alignment.Bottom,
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                    verticalAlignment = Alignment.Bottom,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.iv_logo_catar_2022),
                        modifier = Modifier
                            .size(40.dp),
                        contentDescription = stringResource(R.string.logo_copa_2022),
                        contentScale = ContentScale.FillBounds
                    )
                    Text(
                        text = title, style = TextStyle(
                            fontSize = 24.sp,
                            fontFamily = FontFamily(Font(R.font.tomorrow_bold)),
                            color = colorResource(R.color.primary_color_2),
                        )
                    )
                }
            }
            Image(
                painter = painterResource(id = R.drawable.iv_configuracoes),
                modifier = Modifier
                    .size(32.dp),
//                    .clickable {
//                        navController.navigate("settings")
//                    },
                contentDescription = stringResource(R.string.text_settings),
                contentScale = ContentScale.FillBounds
            )
        }
    }
}

@Preview
@Composable
fun HeaderPagesPreview() {
    HeaderPages(
        "Página de Teste"
    )
}