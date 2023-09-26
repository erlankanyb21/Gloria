package di

import ktor.cart.KtorCartDataSource
import ktor.catalog.KtorCatalogDataSource
import ktor.home.KtorHomeDataSource
import ktor.more.MoreDataSource
import ktor.product.KtorProductDataSource
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider
import org.kodein.di.singleton
import repositories.CatalogRepository
import repositories.CatalogRepositoryImpl
import repositories.HomeRepository
import repositories.HomeRepositoryImpl
import repositories.ProductRepository
import repositories.ProductRepositoryImpl
import repositories.cart.CartRepository
import repositories.cart.CartRepositoryImpl
import repositories.more.MoreRepository
import repositories.more.MoreRepositoryImpl

val mainModule = DI.Module("mainModule") {
    bind<CartRepository>() with singleton {
        CartRepositoryImpl(instance())
    }
    bind<KtorCartDataSource>() with provider {
        KtorCartDataSource(instance(),instance())
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
    bind<MoreRepository>() with singleton {
        MoreRepositoryImpl(instance())
    }
    bind<MoreDataSource>() with provider {
        MoreDataSource(instance(), instance())
    }
    bind<KtorProductDataSource>() with provider {
        KtorProductDataSource(instance())
    }
    bind<ProductRepository>() with singleton {
        ProductRepositoryImpl(instance())
    }
}
