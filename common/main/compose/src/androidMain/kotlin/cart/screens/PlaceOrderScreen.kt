package cart.screens

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cart.cart_views.CartTextField
import cart.cart_views.DeliveryInfoContainer
import cart.cart_views.SelectFilialView
import cart.place_order.PlaceOrderViewModel
import cart.place_order.models.PlaceOrderAction
import cart.place_order.models.PlaceOrderEvent
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import components.BackIcon
import components.GradientButton
import components.ToolBar
import org.tbm.gloria.core_compose.R
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import theme.color

@Composable
fun PlaceOrderScreen() {
    val rootController = LocalRootController.current
    val context = LocalContext.current

    StoredViewModel(factory = { PlaceOrderViewModel() }) { viewModel ->
        val viewState = viewModel.viewStates().observeAsState()
        val viewAction = viewModel.viewActions().observeAsState()

        LaunchedEffect(Unit) {
            viewModel.getFilialList()
            viewModel.fetchUserCart()
        }

        BackHandler(true) {
            rootController.popBackStack()
        }

        Scaffold(
            topBar = {
                ToolBar(
                    title = stringResource(id = R.string.place_order_title),
                    backIcon = { BackIcon { viewModel.obtainEvent(PlaceOrderEvent.OnBackClick) } }
                )
            }
        ) { paddingValues ->

            when (viewAction.value) {
                PlaceOrderAction.OnBackClick -> {
                    rootController.popBackStack()
                }
                else -> {}
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color.white)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                        .padding(top = paddingValues.calculateTopPadding())
                        .padding(horizontal = 20.dp)
                ) {

                    CartTextField(
                        value = viewState.value.fieldName,
                        placeHolder = "Имя"
                    ) { text ->
                        viewModel.obtainEvent(PlaceOrderEvent.FieldNameChanged(text))
                    }

                    CartTextField(
                        value = viewState.value.fieldPhone,
                        placeHolder = "Номер телефона",
                        keyboardType = KeyboardType.Decimal
                    ) { text ->
                        viewModel.obtainEvent(PlaceOrderEvent.FieldPhoneNumberChanged(text))
                    }

                    viewState.value.radioButtonsState.forEach { info ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 12.dp)
                                .clickable(
                                    indication = null,
                                    interactionSource = remember { MutableInteractionSource() }
                                ) {
                                    viewModel.obtainEvent(PlaceOrderEvent.SelectRadioButton(info.text))
                                }
                        ) {
                            RadioButton(
                                modifier = Modifier.size(20.dp, 20.dp),
                                selected = info.isChecked,
                                colors = RadioButtonDefaults.colors(color.purple200),
                                onClick = {
                                    viewModel.obtainEvent(PlaceOrderEvent.SelectRadioButton(info.text))
                                })
                            Text(
                                modifier = Modifier.padding(start = 12.dp),
                                text = info.text,
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    color = color.black,
                                    fontWeight = FontWeight.Medium
                                )
                            )
                        }
                    }

                    AnimatedVisibility(viewState.value.radioButtonsState.last().isChecked) {
                        DeliveryInfoContainer(viewState = viewState) { event ->
                            viewModel.obtainEvent(event)
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Как можно скорее",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = color.black
                            )
                        )
                        Switch(
                            checked = viewState.value.switchState,
                            onCheckedChange = { isChecked ->
                                viewModel.obtainEvent(PlaceOrderEvent.SetSwitchState(isChecked))
                            },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = color.purple,
                                checkedTrackAlpha = 0.1f,
                                uncheckedTrackColor = Color.LightGray
                            )
                        )
                    }

                    Text(
                        text = "Внимание!",
                        style = TextStyle(
                            fontSize = 12.sp,
                            color = color.purple200
                        )
                    )
                    Text(
                        text = "Подготовка заказа занимат от 30 минут.",
                        style = TextStyle(
                            fontSize = 12.sp,
                            color = color.black
                        )
                    )

                    CartTextField(
                        value = viewState.value.fieldDateOfOrder,
                        placeHolder = "Дата и время заказа"
                    ) { text ->
                        viewModel.obtainEvent(PlaceOrderEvent.FieldDateOfOrderChanged(text))
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    SelectFilialView(Modifier.background(Color.White), viewState) { event ->
                        viewModel.obtainEvent(event)
                    }

                    Row(
                        modifier = Modifier.padding(top = 12.dp)
                    ) {
                        Image(
                            modifier = Modifier.padding(top = 4.dp),
                            painter = painterResource(id = R.drawable.ic_warning),
                            contentDescription = null
                        )

                        Column(
                            modifier = Modifier.padding(start = 8.dp)
                        ) {
                            Text(
                                text = "Внимание!",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    color = color.purple200
                                )
                            )
                            Text(
                                text = "Отмена заказа с возвратом средств возможна не позже, чем за 8 часов до выдачи заказа.",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    color = color.black
                                )
                            )
                        }
                    }
                    Text(
                        modifier = Modifier.padding(top = 12.dp),
                        text = "Итого",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = color.black
                        )
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Общая стоимость",
                            style = TextStyle(
                                fontSize = 12.sp,
                                color = color.black
                            )
                        )
                        Text(
                            text = viewState.value.totalValue.toString(),
                            style = TextStyle(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp,
                                color = color.black
                            )
                        )
                    }

                    GradientButton(
                        modifier = Modifier.padding(top = 60.dp, bottom = 32.dp),
                        text = "Оформить заказ", fontSize = 18.sp
                    ) {
//                        rootController.present(NavigationTree.Main.SuccessfulOrder.name)
                        val deliveryMethod = viewState.value.radioButtonsState.find { it.isChecked }?.text
                        val userName = viewState.value.fieldName.ifEmpty {
                            Toast.makeText(context, "Заполните имя", Toast.LENGTH_SHORT).show()
                            return@GradientButton
                        }
                        val userNumber = viewState.value.fieldPhone.ifEmpty {
                            Toast.makeText(context, "Заполните номер телефона", Toast.LENGTH_SHORT).show()
                            return@GradientButton
                        }
                        val order = arrayListOf(
                            "Заказ: ($deliveryMethod)\n",
                            "Имя: $userName\n",
                            "Номер телефона: $userNumber\n\n"
                        )
                        if (deliveryMethod == "Доставка") {
                            val address = viewState.value.deliveryInfo.fieldAddress.ifEmpty {
                                Toast.makeText(context, "Заполните поле \"Адресс\"", Toast.LENGTH_SHORT).show()
                                return@GradientButton
                            }
                            val apartmentNumber = viewState.value.deliveryInfo.fieldApartmentNumber.ifEmpty {
                                Toast.makeText(context, "Заполните номер \"Квартира / Офис\"", Toast.LENGTH_SHORT).show()
                                return@GradientButton
                            }
                            val floorAndDoorCode = viewState.value.deliveryInfo.fieldFloorAndDoorCode.ifEmpty {
                                Toast.makeText(context, "Заполните номер \"Этаж, код дври / домофона\"", Toast.LENGTH_SHORT).show()
                                return@GradientButton
                            }
                            val moreInfo = viewState.value.deliveryInfo.fieldMoreInfo
                            order.add("Адресс: $address\n")
                            order.add("Квартира / Офис: $apartmentNumber\n")
                            order.add("Этаж, код дври / домофона: $floorAndDoorCode\n")
                            order.add("Дополнительно к заказу: $moreInfo\n")
                        }
                        viewState.value.cartItems.forEach { item ->
                            order.add("${item.name}: ${item.quantity}шт \n")
                        }
                        order.add("\nОбщая стоимость: ${viewState.value.totalValue}c")
                        context.startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse(
                                    String.format(
                                        "https://api.whatsapp.com/send?phone=%s&text=%s",
                                        "+996708081003",
                                        order.joinToString().replace(",", "")
                                    )
                                )
                            )
                        )
                    }
                }
            }
        }
    }
}