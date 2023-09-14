import ktor.cart.KtorCartDataSource
import ktor.more.MoreDataSource
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider
import org.kodein.di.singleton
import repositories.cart.CartRepository
import repositories.cart.CartRepositoryImpl
import repositories.more.MoreRepository
import repositories.more.MoreRepositoryImpl

val mainModule = DI.Module("mainModule") {
    cartScreen()
    moreScreen()
}

private fun DI.Builder.cartScreen() {
    bind<CartRepository>() with singleton {
        CartRepositoryImpl(instance())
    }
    bind<KtorCartDataSource>() with provider {
        KtorCartDataSource(instance())
    }
}

private fun DI.Builder.moreScreen() {
    bind<MoreRepository>() with singleton {
        MoreRepositoryImpl(instance())
    }
    bind<MoreDataSource>() with provider {
        MoreDataSource(instance())
    }
}
