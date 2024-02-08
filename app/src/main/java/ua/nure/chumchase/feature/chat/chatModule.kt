package ua.nure.chumchase.feature.chat

import android.content.Context
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.logger.ChatLogLevel
import io.getstream.chat.android.client.plugin.factory.PluginFactory
import io.getstream.chat.android.offline.plugin.factory.StreamOfflinePluginFactory
import io.getstream.chat.android.state.plugin.config.StatePluginConfig
import io.getstream.chat.android.state.plugin.factory.StreamStatePluginFactory
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ua.nure.chumchase.BuildConfig
import ua.nure.chumchase.feature.chat.domain.ConnectToChatUseCase
import ua.nure.chumchase.feature.chat.presentation.ChatViewModel

val chatModule = module {
    single { StatePluginConfig() }
    factoryOf(::StreamOfflinePluginFactory)
    factoryOf(::StreamStatePluginFactory)
    single {
        getChatClient(
            androidContext(),
            get<StreamOfflinePluginFactory>(), get<StreamStatePluginFactory>()
        )
    }
    singleOf(::ConnectToChatUseCase)
    singleOf(::ChatViewModel)
}

private fun getChatClient(context: Context, vararg pluginFactories: PluginFactory): ChatClient {
    return ChatClient.Builder(BuildConfig.STREAM_API_KEY, context)
        .withPlugins(*pluginFactories)
        .logLevel(if (BuildConfig.DEBUG) ChatLogLevel.ALL else ChatLogLevel.NOTHING)
        .build()
}