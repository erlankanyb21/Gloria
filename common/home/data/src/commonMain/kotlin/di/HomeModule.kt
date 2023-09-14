package di

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider
import org.kodein.di.singleton
import remote.KtorHomeDateSource
import repository.HomeRepository
import repository.HomeRepositoryImpl

val homeModule = DI.Module("homeModule") {

    bind<HomeRepository>() with singleton {
        HomeRepositoryImpl(instance())
    }

    bind<KtorHomeDateSource>() with provider {
        KtorHomeDateSource(instance())
    }
}