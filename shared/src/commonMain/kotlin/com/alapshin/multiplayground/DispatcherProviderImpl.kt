package com.alapshin.multiplayground

import me.tatarka.inject.annotations.Inject

@Inject
expect class DispatcherProviderImpl constructor() : DispatcherProvider
