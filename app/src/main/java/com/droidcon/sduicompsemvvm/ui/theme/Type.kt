package com.droidcon.sduicompsemvvm.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.droidcon.sduicompsemvvm.R

// Font family definitions
private val ralewayLight = FontFamily(Font(R.font.raleway_light, FontWeight.W300))
val ralewayRegular = FontFamily(Font(R.font.raleway_regular, FontWeight.W400))
private val ralewayMedium = FontFamily(Font(R.font.raleway_medium, FontWeight.W500))
private val ralewaySemiBold = FontFamily(Font(R.font.raleway_semibold, FontWeight.W600))

// Text styles
val titleBarStyle = TextStyle(
    fontFamily = ralewaySemiBold,
    fontWeight = FontWeight.W600,
    fontSize = 30.sp
)

val subTitleBarStyle = TextStyle(
    fontFamily = ralewaySemiBold,
    fontWeight = FontWeight.W600,
    fontSize = 20.sp
)

val primaryButtonStyle = TextStyle(
    fontFamily = ralewayMedium,
    fontWeight = FontWeight.W500,
    fontSize = 15.sp
)

val primaryInputStyle = TextStyle(
    fontFamily = ralewayRegular,
    fontWeight = FontWeight.W400,
    fontSize = 15.sp
)

val primaryLabelStyle = TextStyle(
    fontFamily = ralewayRegular,
    fontWeight = FontWeight.W400,
    fontSize = 15.sp
)

val secondaryLabelStyle = TextStyle(
    fontFamily = ralewayRegular,
    fontWeight = FontWeight.W300,
    fontSize = 15.sp
)

val captionTextStyle = TextStyle(
    fontFamily = ralewayRegular,
    fontWeight = FontWeight.W400,
    fontSize = 16.sp
)

// Main typography instance
val ralewayTypography = Typography(
    titleLarge = TextStyle(
        fontFamily = ralewayRegular,
        fontWeight = FontWeight.W300,
        fontSize = 30.sp
    ),
    titleMedium = TextStyle(
        fontFamily = ralewayRegular,
        fontWeight = FontWeight.W400,
        fontSize = 28.sp
    ),
    titleSmall = TextStyle(
        fontFamily = ralewaySemiBold,
        fontWeight = FontWeight.W600,
        fontSize = 26.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = ralewaySemiBold,
        fontWeight = FontWeight.W600,
        fontSize = 24.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = ralewaySemiBold,
        fontWeight = FontWeight.W600,
        fontSize = 22.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = ralewayRegular,
        fontWeight = FontWeight.W400,
        fontSize = 20.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = ralewayRegular,
        fontWeight = FontWeight.W500,
        fontSize = 16.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = ralewayRegular,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp
    ),
    bodySmall = TextStyle(
        fontFamily = ralewayRegular,
        fontWeight = FontWeight.W300,
        fontSize = 12.sp
    ),
    displayLarge = TextStyle(
        fontFamily = ralewayRegular,
        fontWeight = FontWeight.W400,
        fontSize = 12.sp
    ),
    displayMedium = TextStyle(
        fontFamily = ralewaySemiBold,
        fontWeight = FontWeight.W600,
        fontSize = 10.sp
    ),
    displaySmall = TextStyle(
        fontFamily = ralewayMedium,
        fontWeight = FontWeight.W500,
        fontSize = 8.sp
    ),
    labelLarge = TextStyle(
        fontFamily = ralewayRegular,
        fontWeight = FontWeight.W600,
        fontSize = 16.sp
    ),
    labelMedium = TextStyle(
        fontFamily = ralewayRegular,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    labelSmall = TextStyle(
        fontFamily = ralewayRegular,
        fontWeight = FontWeight.W400,
        fontSize = 12.sp
    )
)