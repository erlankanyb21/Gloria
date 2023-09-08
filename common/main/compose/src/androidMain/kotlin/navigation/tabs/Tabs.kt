package navigation.tabs

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.tbm.gloria.core_compose.R
import ru.alexgladkov.odyssey.compose.navigation.bottom_bar_navigation.TabConfiguration
import ru.alexgladkov.odyssey.compose.navigation.bottom_bar_navigation.TabItem
import theme.color

class MainTab : TabItem() {

    override val configuration: TabConfiguration
        @Composable
        get() {
            return TabConfiguration(
                title = "Главная",
                selectedIcon = painterResource(id = R.drawable.ic_home),
                unselectedIcon = painterResource(id = R.drawable.ic_home),
                selectedColor = color.deeepPink,
                unselectedColor = Color.Gray,
                titleStyle = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            )
        }
}
class CatalogTab : TabItem() {

    override val configuration: TabConfiguration
        @Composable
        get() {
            return TabConfiguration(
                title = "Каталог",
                selectedIcon = painterResource(id = R.drawable.ic_basket),
                unselectedIcon = painterResource(id = R.drawable.ic_basket),
                selectedColor = color.deeepPink,
                unselectedColor = Color.Gray,
                titleStyle = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            )
        }
}

class CartTab : TabItem() {

    override val configuration: TabConfiguration
        @Composable
        get() {
            return TabConfiguration(
                title = "Корзина",
                selectedIcon = painterResource(id = R.drawable.ic_cart),
                unselectedIcon = painterResource(id = R.drawable.ic_cart),
                selectedColor = color.deeepPink,
                unselectedColor = Color.Gray,
                titleStyle = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            )
        }
}

class MoreTab : TabItem() {

    override val configuration: TabConfiguration
        @Composable
        get() {
            return TabConfiguration(
                title = "Ещё",
                selectedIcon = painterResource(id = R.drawable.ic_menu),
                unselectedIcon = painterResource(id = R.drawable.ic_menu),
                selectedColor = color.deeepPink,
                unselectedColor = Color.Gray,
                titleStyle = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            )
        }
}