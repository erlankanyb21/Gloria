package components

import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import org.tbm.gloria.core_compose.R
import theme.gloriaGradient


@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun ExpandableCard(title: String) {

    var expanded by remember { mutableStateOf(false) }
    val rotateState by animateFloatAsState(targetValue = if (expanded) 180f else 0f)
    val context = LocalContext.current
    Card(
        elevation = 0.dp,
        shape = RoundedCornerShape(28.dp),
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .animateContentSize(animationSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy))
            .padding(16.dp),
        border = BorderStroke(1.dp, color = Color.Black)
    ) {
        Column {
            Row(
                Modifier
                    .clip(RoundedCornerShape(28.dp))
                    .background(gloriaGradient),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = ""),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .weight(2f)
                        .clip(CircleShape)
                        .size(54.dp)
                )

                Text(
                    modifier = Modifier.weight(4f),
                    text = title,
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFFFFFFFF),
                    )
                )

                Icon(
                    modifier = Modifier
                        .rotate(rotateState)
                        .clickable { expanded = !expanded }
                        .weight(1f),
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "",
                    tint = Color.White
                )
            }

            if (expanded) {
                var name by remember { mutableStateOf("") }
                var surname by remember { mutableStateOf("") }
                var dateOfBirth by remember { mutableStateOf("") }
                var phoneNumber by remember { mutableStateOf("") }

                Column(
                    modifier = Modifier
                        .padding(bottom = 15.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        modifier = Modifier
                            .padding(top = 20.dp, bottom = 7.dp),
                        shape = CircleShape,
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                        placeholder = { Text(text = "Имя") },
                    )

                    OutlinedTextField(
                        value = surname,
                        onValueChange = { surname = it },
                        modifier = Modifier.padding(vertical = 7.dp),
                        shape = CircleShape,
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                        placeholder = { Text(text = "Фамилия") }
                    )
                    OutlinedTextField(
                        value = dateOfBirth,
                        onValueChange = { dateOfBirth = it },
                        modifier = Modifier.padding(vertical = 7.dp),
                        shape = CircleShape,
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done,keyboardType = KeyboardType.Number),
                        placeholder = { Text(text = "Дата рождения") },
                    )

                    var showGender by remember { mutableStateOf(false) }
                    var chosenGender by remember { mutableStateOf("") }
                    val rotateIcon by animateFloatAsState(targetValue = if (showGender) 180f else 0f)

                    Card(
                        shape = CircleShape,
                        modifier = Modifier
                            .padding(horizontal = 38.dp, vertical = 7.dp)
                            .height(50.dp),
                        border = if (showGender) BorderStroke(
                            1.dp,
                            gloriaGradient
                        ) else BorderStroke(
                            1.dp,
                            Color.Gray
                        ),
                        onClick = { }
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = if (chosenGender.isEmpty()) "Пол" else chosenGender,
                                color = Color.Black,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(4f)
                                    .padding(horizontal = 22.dp),
                            )

                            Icon(
                                modifier = Modifier
                                    .weight(1f)
                                    .rotate(rotateIcon)
                                    .clickable { showGender = !showGender },
                                imageVector = Icons.Default.KeyboardArrowDown,
                                contentDescription = "",
                                tint = if (showGender) Color(152, 21, 138) else Color.Gray
                            )
                        }
                    }

                    if (showGender) {
                        val selectedValue = remember { mutableStateOf(chosenGender) }

                        val isSelectedItem: (String) -> Boolean = {
                            selectedValue.value == it
                        }
                        val onChangeState: (String) -> Unit = {
                            selectedValue.value = it
                            chosenGender = it
                        }

                        val items = listOf("Мужской", "Женский")
                        Column(Modifier.padding(horizontal = 28.dp)) {
                            items.forEach { item ->
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .selectable(
                                            selected = isSelectedItem(item),
                                            onClick = { onChangeState(item) },
                                            role = Role.RadioButton
                                        )
                                        .padding(8.dp)
                                ) {
                                    RadioButton(
                                        selected = isSelectedItem(item),
                                        onClick = null,
                                        colors = RadioButtonDefaults.colors(
                                            selectedColor = Color(152, 21, 138),
                                            unselectedColor = Color.Gray
                                        )
                                    )
                                    Text(
                                        text = item,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 12.dp)
                                    )
                                }
                            }
                        }
                    }

                    OutlinedTextField(
                        value = phoneNumber,
                        onValueChange = { phoneNumber = it },
                        modifier = Modifier.padding(vertical = 7.dp),
                        shape = CircleShape,
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done,keyboardType = KeyboardType.Phone),
                        placeholder = { Text(text = "Номер телефона") },
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    GradientButton(
                        text = "Сохранить",
                        modifier = Modifier
                            .padding(horizontal = 28.dp)
                            .height(40.dp)
                    ) {

                        when {
                            name.isEmpty() -> {
                                Toast.makeText(context, "Заполните поле Имя", Toast.LENGTH_SHORT)
                                    .show()
                            }

                            surname.isEmpty() -> {
                                Toast.makeText(
                                    context,
                                    "Заполните поле Фамилия",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                            dateOfBirth.isEmpty() -> {
                                Toast.makeText(context, "Заполните поле Дату рождения", Toast.LENGTH_SHORT)
                                    .show()
                            }

                            chosenGender.isEmpty() -> {
                                Toast.makeText(context, "Выберите пол", Toast.LENGTH_SHORT).show()
                            }

                            phoneNumber.isEmpty() -> {
                                Toast.makeText(context, "Заполните поле номер телефона", Toast.LENGTH_SHORT)
                                    .show()
                            }

                            else -> { expanded = false }
                        }
                    }
                }
            }
        }
    }
}