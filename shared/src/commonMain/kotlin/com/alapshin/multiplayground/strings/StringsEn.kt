package com.alapshin.multiplayground.strings

import cafe.adriel.lyricist.LyricistStrings

@LyricistStrings(languageTag = Locales.EN, default = true)
val EnStrings = object : Strings {
    override val login = "Login"
    override val register = "Register"
}
