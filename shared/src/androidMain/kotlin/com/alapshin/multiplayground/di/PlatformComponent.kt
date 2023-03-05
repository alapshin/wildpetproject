package com.alapshin.multiplayground.di

import android.content.Context
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@Component
actual abstract class PlatformComponent(@get:Provides internal val context: Context)
