package di

import ktor.cart.KtorCartDataSource
import ktor.catalog.KtorCatalogDataSource
import ktor.home.KtorHomeDataSource
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider
import org.kodein.di.singleton
import repositories.CartRepository
import repositories.CartRepositoryImpl
import repositories.CatalogRepository
import repositories.CatalogRepositoryImpl
import repositories.HomeRepository
import repositories.HomeRepositoryImpl

val mainModule = DI.Module("mainModule") {
    bind<CartRepository>() with singleton {
        CartRepositoryImpl(instance())
    }
    bind<KtorCartDataSource>() with provider {
        KtorCartDataSource(instance())
    }
    bind<CatalogRepository>() with singleton {
        CatalogRepositoryImpl(instance())
    }
    bind<KtorCatalogDataSource>() with provider {
        KtorCatalogDataSource(instance())
    }
    bind<HomeRepository>() with singleton {
        HomeRepositoryImpl(instance())
    }
    bind<KtorHomeDataSource>() with provider {
        KtorHomeDataSource(instance())
    }
}
