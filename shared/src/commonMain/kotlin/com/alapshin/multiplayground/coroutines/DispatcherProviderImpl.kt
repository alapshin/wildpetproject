package com.alapshin.multiplayground.coroutines

import me.tatarka.inject.annotations.Inject

@Inject
expect class DispatcherProviderImpl constructor() : DispatcherProvider
