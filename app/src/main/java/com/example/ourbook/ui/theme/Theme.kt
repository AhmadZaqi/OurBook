package com.example.ourbook.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Peach,
    secondary = Peach,
    background = Black,
    onBackground = White,
    onPrimary = White,
    onSecondary = White,
    onSurface = DarkPeach,
    onSurfaceVariant = DarkPeach,
    primaryContainer = Dark,
    onPrimaryContainer = Peach,
    secondaryContainer = DarkPeach,
    onSecondaryContainer = LightPeach
)

private val LightColorScheme = lightColorScheme(
    primary = DarkPeach,
    secondary = DarkPeach,
    background = White,
    onBackground = Black,
    onPrimary = White,
    onSecondary = White,
    onSurface = DarkPeach,
    onSurfaceVariant = DarkPeach,
    primaryContainer = LightPeach,
    onPrimaryContainer = DarkPeach,
    secondaryContainer = Peach,
    onSecondaryContainer = LightPeach,
)

@Composable
fun OurBookTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}