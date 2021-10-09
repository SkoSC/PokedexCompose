package com.skosc.pokedex.uikit.widget

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.skosc.pokedex.uikit.theme.LocalColoristic

@Composable
fun PokeText(text: String, onCard: Boolean, modifier: Modifier = Modifier) {
    val color = if (onCard) LocalColoristic.current.textSecondaryAccent else LocalColoristic.current.textPrimaryAccent
    Text(text = text, color = color, modifier = modifier)
}

@Composable
fun PokeLabel(
    text: String,
    onColor: Boolean,
    modifier: Modifier = Modifier,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
) {
    val color = if (onColor) LocalColoristic.current.textSecondaryAccent else LocalColoristic.current.textPrimaryAccent
    Text(text = text, color = color, overflow = overflow, maxLines = maxLines, fontWeight = FontWeight.Bold, modifier = modifier)
}

@Composable
fun PokeSubLabel(text: String, onCard: Boolean, modifier: Modifier = Modifier) {
    val color = if (onCard) LocalColoristic.current.textSecondaryAccent else LocalColoristic.current.textPrimaryAccent
    Text(text = text, color = color, fontSize = 12.sp, fontWeight = FontWeight.Bold, modifier = modifier)
}

@Composable
fun PokeHeader(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        color = LocalColoristic.current.textPrimaryAccent,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        modifier = modifier)
}

@Composable
fun SubPokeHeader(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        color = LocalColoristic.current.textPrimaryAccent,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        modifier = modifier
    )
}

@Composable
fun PokeLink(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        color = LocalColoristic.current.textLink,
        modifier = modifier
    )
}

@Composable
fun PokeCardHeader(
    text: String,
    maxLines: Int = 1,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontWeight = FontWeight.Bold,
        color = LocalColoristic.current.textSecondaryAccent,
        maxLines = maxLines,
        overflow = overflow,
        modifier = modifier
    )
}

@Composable
fun PokeCardSubHeader(
    text: String,
    maxLines: Int = 1,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        color = LocalColoristic.current.textSecondaryAccent,
        maxLines = maxLines,
        overflow = overflow,
        modifier = modifier
    )
}

@Composable
fun ListItemText(
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = 1,
    overflow: TextOverflow = TextOverflow.Ellipsis,
) {
    Text(
        text = text,
        color = LocalColoristic.current.textPrimaryAccent,
        maxLines = maxLines,
        overflow = overflow,
        modifier = modifier,
        fontSize = 16.sp
    )
}

@Composable
@Preview
private fun PreviewPokeHeader() {
    PokeHeader(text = "Hello world")
}