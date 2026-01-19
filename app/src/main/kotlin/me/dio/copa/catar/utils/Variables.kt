package me.dio.copa.catar.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import me.dio.copa.catar.R

class Variables(
) {
    // Cores
    val primaryColor: Color
        @Composable
        get() = colorResource(id = R.color.primary_color)

    val primaryColor2: Color
        @Composable
        get() = colorResource(id = R.color.primary_color_2)

    // Fontes
    val fontBoldApp: Font
        @Composable
        get() = Font(R.font.tomorrow_bold)

    val fontRegularApp: Font
        @Composable
        get() = Font(R.font.tomorrow_regular)
}