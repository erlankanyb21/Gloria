import json.serializationModule
import ktor.ktorModule
import org.kodein.di.DI
import setting.settingsModule

val coreModule = DI.Module("coreModule"){
    importAll(
        serializationModule,
        ktorModule,
        settingsModule
    )
}