package me.dio.copa.catar.ui.components.switches

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.dio.copa.catar.R

@Composable
fun CustomSwitch(
    checked: Boolean = false,
    onCheckedChange: (Boolean) -> Unit
) {
    val width = 56.dp
    val height = 28.dp
    val padding = 3.dp

    val thumbSize = height - padding * 2

    val offsetX by animateDpAsState(
        targetValue = if (checked) width - thumbSize - padding * 2 else 0.dp,
        label = "switch"
    )

    Box(
        modifier = Modifier
            .width(width)
            .height(height)
            .clip(RoundedCornerShape(50))
            .background(
                if (checked) colorResource(R.color.primary_color) else colorResource(R.color.switch_disabled)
            )
            .clickable { onCheckedChange(!checked) }
            .padding(padding),
        contentAlignment = Alignment.CenterStart
    ) {
        Box(
            modifier = Modifier
                .offset(x = offsetX)
                .size(thumbSize)
                .clip(CircleShape)
                .background(Color.White)
        )
    }
}

@Preview
@Composable
fun CustomSwitchPreview() {
    CustomSwitch(checked = false, onCheckedChange = {})
}
