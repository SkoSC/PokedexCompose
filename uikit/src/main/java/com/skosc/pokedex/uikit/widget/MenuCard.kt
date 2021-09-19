package com.skosc.pokedex.uikit.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skosc.pokedex.uikit.R
import com.skosc.pokedex.uikit.theme.PokeCardShape
import com.skosc.pokedex.uikit.theme.PokeColor

@Composable
fun SettingsCard(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    icon: @Composable () -> Unit,
    backgroundColor: Color,
    onClick: () -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .background(backgroundColor, PokeCardShape)
            .clip(PokeCardShape)
            .clickable { onClick() }
            .padding(start = 16.dp, end = 32.dp, top = 16.dp, bottom = 16.dp)
    ) {
        icon()
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            PokeCardHeader(text = title)
            if (subtitle != null) {
                PokeCardSubHeader(text = subtitle)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Preview_MenuCard() {
    SettingsCard(
        title = "Language",
        subtitle = "English",
        icon = {
            Image(
                painter = painterResource(id = R.drawable.ic_pokeball_filed),
                colorFilter = ColorFilter.tint(Color.White),
                contentDescription = "Language",
                modifier = Modifier.size(32.dp) // TODO make dynamic
            )
        },
        backgroundColor = PokeColor.Teal,
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp)
            .wrapContentHeight()
    )
}