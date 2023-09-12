import ktor.KtorCartDataSource
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider
import org.kodein.di.singleton
import repositories.CartRepository
import repositories.CartRepositoryImpl

val mainModule = DI.Module("mainModule") {
    bind<CartRepository>() with singleton {
        CartRepositoryImpl(instance())
    }
    bind<KtorCartDataSource>() with provider {
        KtorCartDataSource(instance())
    }
}
