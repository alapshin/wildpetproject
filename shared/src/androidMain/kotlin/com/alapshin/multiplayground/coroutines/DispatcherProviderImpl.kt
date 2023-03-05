package com.alapshin.multiplayground.coroutines

import kotlinx.coroutines.Dispatchers
import me.tatarka.inject.annotations.Inject

@Inject
@Suppress("InjectDispatcher")
actual class DispatcherProviderImpl : DispatcherProvider {
    override val io = Dispatchers.IO
    override val main = Dispatchers.Main
    override val unconfined = Dispatchers.Unconfined
}
