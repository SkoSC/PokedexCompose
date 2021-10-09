package com.skosc.pokedex.uikit.coloristics

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import androidx.palette.graphics.Target
import coil.ImageLoader
import coil.request.ImageRequest
import com.skosc.pokedex.uikit.theme.ColorDef
import com.skosc.pokedex.uikit.theme.LocalColoristic
import com.skosc.pokedex.uikit.theme.PokeColor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.RuntimeException
import java.util.*
import kotlin.collections.HashMap
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

object ColorPicker {

    private val safeAccent = listOf(
        PokeColor.Accent.Green,
        PokeColor.Accent.Magenta,
        PokeColor.Accent.Purple,
        PokeColor.Accent.Red,
        PokeColor.Accent.SoftSwampGreen,
        PokeColor.Accent.Teal,
        PokeColor.Accent.Yellow
    )

    private val norepat: MutableMap<String, Int> = HashMap()

    fun getPokeColorForName(name: String): Color = when (name.lowercase(Locale.getDefault())) {
        "black" -> PokeColor.Pokemon.Black
        "blue" -> PokeColor.Pokemon.Blue
        "brown" -> PokeColor.Pokemon.Brown
        "gray" -> PokeColor.Pokemon.Gray
        "green" -> PokeColor.Pokemon.Green
        "pink" -> PokeColor.Pokemon.Pink
        "purple" -> PokeColor.Pokemon.Purple
        "red" -> PokeColor.Pokemon.Red
        "white" -> PokeColor.Pokemon.White
        "yellow" -> PokeColor.Pokemon.Yellow
        else -> PokeColor.Pokemon.Green
    }

    fun getPokeColorForType(name: String): ColorDef {
        return when (name.lowercase(Locale.getDefault())) {
            "normal" -> ColorDef.TypeNormal
            "fighting" -> ColorDef.TypeFighting
            "flying" -> ColorDef.TypeFlying
            "poison" -> ColorDef.TypePoison
            "ground" -> ColorDef.TypeGround
            "rock" ->  ColorDef.TypeRock
            "bug" -> ColorDef.TypeBug
            "ghost" -> ColorDef.TypeGhost
            "steel" -> ColorDef.TypeSteel
            "fire" ->  ColorDef.TypeFire
            "water" -> ColorDef.TypeWater
            "grass" -> ColorDef.TypeGrass
            "electric" -> ColorDef.TypeElectric
            "psychic" -> ColorDef.TypePsychic
            "ice" -> ColorDef.TypeIce
            "dragon" -> ColorDef.TypeDragon
            "dark" ->  ColorDef.TypeDark
            "fairy" -> ColorDef.TypeFairy
            "shadow" -> ColorDef.TypeShadow
            else -> ColorDef.TypeUnknown
        }
    }

    fun getColorNoRepeat(space: String) : Color {
        val noRepeatValue = norepat.getOrPut(space) { 0 }
        norepat[space] = noRepeatValue + 1
        return safeAccent[noRepeatValue.mod(PokeColor.Pokemon.all.size)]
    }

    suspend fun getPokeColor(context: Context, bitmapUrl: String): ColorDef {
        val bitmap = loadBitmap(context, bitmapUrl) ?: return ColorDef.AccentPrimary
        return ColorDef.Value(getPokeColor(bitmap))
    }

    private suspend fun getPokeColor(bitmap: Bitmap): Color {
        return withContext(Dispatchers.IO) {
            val palette = paletteForImage(bitmap)
            val vibrantSwatch = palette.selectCloseVibrant() ?: return@withContext PokeColor.Accent.Default
            Color(vibrantSwatch.rgb).copy(alpha = 1f)
        }
    }

    private fun Palette.selectCloseVibrant(): Palette.Swatch? =
        vibrantSwatch ?: lightVibrantSwatch ?: dominantSwatch ?: swatches.firstOrNull()

    private suspend fun loadBitmap(context: Context, bitmapUrl: String): Bitmap? {
        val imageLoader = ImageLoader(context)
        return imageLoader.execute(
            ImageRequest.Builder(context)
                .data(bitmapUrl)
                .build()
        ).drawable?.toBitmap()?.copy(Bitmap.Config.RGB_565, false)
    }

    private suspend fun paletteForImage(bitmap: Bitmap): Palette = suspendCoroutine { con ->
        Palette.Builder(bitmap)
            .addTarget(Target.VIBRANT)
            .generate { palette ->
                if (palette == null) {
                    con.resumeWith(Result.failure(RuntimeException("Can't resolve colors")))
                } else {
                    con.resume(palette)
                }
            }
    }
}