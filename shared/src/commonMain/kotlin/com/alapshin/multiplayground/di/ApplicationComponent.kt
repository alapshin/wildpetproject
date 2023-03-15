package com.alapshin.multiplayground.di

import com.alapshin.multiplayground.network.di.NetworkComponent
import me.tatarka.inject.annotations.Component

@Component
abstract class ApplicationComponent(
    @Component val platformComponent: PlatformComponent,
) : DispatcherComponent, NetworkComponent
