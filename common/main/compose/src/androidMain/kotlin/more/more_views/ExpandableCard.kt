package more.more_views

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import more.more_views.NumberDefaults.INPUT_LENGTH
import more.more_views.NumberDefaults.MASK
import more.profile.ProfileEvent
import more.profile.ProfileViewModel
import org.tbm.gloria.core_compose.R.drawable.empty_ava
import org.tbm.gloria.main.compose.R
import theme.gloriaGradient
import utils.MaskVisualTransformation
import utils.compressBitmap

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpandableCard(title: String) {
    StoredViewModel(factory = { ProfileViewModel() }) { viewModel ->
        val viewState by viewModel.viewStates().observeAsState()

        var fullName by remember { mutableStateOf(viewState.getProfileResponse?.fullname ?: "") }
        var lastName by remember { mutableStateOf(viewState.getProfileResponse?.lastName ?: "") }
        var dateOfBirth by remember {
            mutableStateOf(
                viewState.getProfileResponse?.dateOfBirthday ?: ""
            )
        }
        var chosenGender by remember { mutableStateOf("") }
        var openedFirst by remember { mutableStateOf(false) }
        var phoneNumber by remember {
            mutableStateOf(
                viewState.getProfileResponse?.phoneNumber ?: ""
            )
        }
        val calendarState = rememberUseCaseState()

        val context = LocalContext.current
        var selectedImageUri by remember {
            mutableStateOf<Uri?>(null)
        }

        val imagePickLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia(),
            onResult = {
                if (it != null) {
                    viewState.image = compressBitmap(context, it, Bitmap.CompressFormat.JPEG, 80)
                    selectedImageUri = it
                }
            }
        )

        var permissionState by remember { mutableStateOf(false) }
        val requestPermissionLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            permissionState = isGranted
        }

        var expanded by remember { mutableStateOf(false) }
        val rotateCardIcon by animateFloatAsState(targetValue = if (expanded) 180f else 0f)

        var showGender by remember { mutableStateOf(false) }
        val rotateGenderIcon by animateFloatAsState(targetValue = if (showGender) 180f else 0f)

        var selectedValue by remember { mutableStateOf(chosenGender) }

        val isSelectedItem: (String) -> Boolean = {
            if (selectedValue.isEmpty())
                selectedValue = viewState.getProfileResponse?.gender ?: ""
            selectedValue == it
        }
        val onChangeState: (String) -> Unit = {
            selectedValue = it
            chosenGender = it
            viewState.gender = it
        }

        LaunchedEffect(key1 = viewState.getProfileResponse, block = {
            Log.e("SIDE", "ExpandableCard: ${viewModel.viewStates().value}")
            fullName = viewState.getProfileResponse?.fullname ?: ""
            phoneNumber = viewState.getProfileResponse?.phoneNumber ?: ""
            lastName = viewState.getProfileResponse?.lastName ?: ""
            dateOfBirth = viewState.getProfileResponse?.dateOfBirthday ?: ""
            chosenGender = viewState.getProfileResponse?.gender ?: ""
        })

        Card(
            elevation = CardDefaults.cardElevation(0.dp),
            shape = RoundedCornerShape(28.dp),
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
                .animateContentSize(animationSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy))
                .padding(horizontal = 20.dp, 10.dp),
            border = BorderStroke(1.dp, color = Color.Black)
        ) {
            Column(modifier = Modifier.background(Color.White)) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(28.dp))
                        .fillMaxWidth()
                        .size(54.dp)
                        .clickable {
                            expanded = !expanded
                        }
                        .background(gloriaGradient)
                        .padding(horizontal = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    val userAvatar = viewState.getProfileResponse?.avatar?.replace(
                        "http",
                        "https"
                    )
                    SubcomposeAsyncImage(
                        model = if (selectedImageUri == null) userAvatar ?: empty_ava
                        else selectedImageUri,
                        contentDescription = "",
                        loading = { CircularProgressIndicator(color = Color.White) },
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .size(50.dp)
                            .padding(8.dp)
                            .clickable {
                                if (expanded)
                                    handlePermission(
                                        context,
                                        imagePickLauncher,
                                        requestPermissionLauncher
                                    )
                            }
                            .clip(CircleShape)
                    )

                    Text(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .fillMaxWidth()
                            .padding(horizontal = 50.dp),
                        text = title,
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                            fontWeight = FontWeight(600),
                            color = Color.White,
                        )
                    )

                    Icon(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .rotate(rotateCardIcon)
                            .clickable {
                                expanded = !expanded
                            },
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "",
                        tint = Color.White
                    )
                }

                if (expanded) {

                    Column(
                        modifier = Modifier
                            .padding(bottom = 15.dp)
                            .background(Color.White)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        OutlinedTextField(
                            value = fullName,
                            onValueChange = {
                                fullName = it
                                viewState.name = it
                            },
                            modifier = Modifier
                                .padding(top = 20.dp, bottom = 7.dp),
                            shape = CircleShape,
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                            placeholder = {
                                Text(
                                    text = stringResource(id = R.string.name),
                                    style = TextStyle(
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight(400),
                                        color = Color.Gray,
                                    )
                                )
                            },
                        )

                        OutlinedTextField(
                            value = lastName,
                            onValueChange = {
                                lastName = it
                                viewState.surname = it
                            },
                            modifier = Modifier.padding(vertical = 7.dp),
                            shape = CircleShape,
                            maxLines = 1,
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                            placeholder = {
                                Text(
                                    text = stringResource(id = R.string.surname),
                                    style = TextStyle(
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight(400),
                                        color = Color.Gray,
                                    )
                                )
                            }
                        )

                        CalendarDialog(
                            state = calendarState,
                            config = CalendarConfig(
                                monthSelection = true,
                                yearSelection = true
                            ),
                            selection = CalendarSelection.Date {
                                viewState.date = it.toString()
                                dateOfBirth = it.toString()
                                calendarState.setManualActions(
                                    positiveAction = {
                                        true
                                    },
                                    negativeAction = {
                                        calendarState.finish()
                                    }
                                )
                            })

                        OutlinedButton(
                            modifier = Modifier
                                .padding(vertical = 7.dp)
                                .width(280.dp)
                                .size(50.dp),
                            onClick = {
                                calendarState.show()
                            }) {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Start,
                                fontWeight = FontWeight.Normal,
                                text = if (dateOfBirth.isEmpty()) stringResource(id = R.string.data_of_birth)
                                else dateOfBirth,
                                color = if (dateOfBirth.isEmpty()) Color.Gray
                                else Color.Black
                            )
                        }

                        Card(
                            shape = CircleShape,
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            ),
                            modifier = Modifier
                                .padding(vertical = 7.dp)
                                .width(280.dp)
                                .size(50.dp),
                            border = if (showGender) BorderStroke(1.dp, gloriaGradient)
                            else BorderStroke(1.dp, Color.Gray),
                            onClick = { }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .clickable { showGender = !showGender },
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = when {
                                        chosenGender.isEmpty() -> stringResource(id = R.string.gender)
                                        viewState.getProfileResponse?.gender == null -> chosenGender
                                        else -> chosenGender
                                    },
                                    style = TextStyle(
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight(400),
                                        color = Color.Black,
                                    ),
                                    color = if (chosenGender.isEmpty()) Color.Gray
                                    else Color.Black,
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(4f)
                                        .padding(horizontal = 22.dp),
                                )

                                Icon(
                                    modifier = Modifier
                                        .weight(1f)
                                        .rotate(rotateGenderIcon)
                                        .clickable { showGender = !showGender },
                                    imageVector = Icons.Default.KeyboardArrowDown,
                                    contentDescription = "",
                                    tint = if (showGender) Color(152, 21, 138) else Color.Gray
                                )
                            }
                        }

                        if (showGender) {

                            val items = stringArrayResource(id = R.array.genders)
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
                            onValueChange = { it ->
                                if (it.length <= INPUT_LENGTH) {
                                    phoneNumber = it.filter { it.isDigit() }
                                    viewState.phone = it
                                }
                            },
                            modifier = Modifier.padding(vertical = 7.dp),
                            shape = CircleShape,
                            maxLines = 1,
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Done,
                                keyboardType = KeyboardType.Number
                            ),
                            visualTransformation = MaskVisualTransformation(MASK),
                            placeholder = {
                                Text(
                                    text = stringResource(id = R.string.phone_number),
                                    style = TextStyle(
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight(400),
                                        color = Color.Gray,
                                    )
                                )
                            },
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Button(
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Transparent
                            ),
                            modifier = Modifier
                                .width(280.dp)
                                .size(50.dp)
                                .clip(RoundedCornerShape(40.dp))
                                .background(gloriaGradient),
                            onClick = {
                                when {
                                    fullName.isEmpty() -> {
                                        Toast.makeText(
                                            context,
                                            "Поле имя не может быть пустым",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }

                                    phoneNumber.isEmpty() -> {
                                        Toast.makeText(
                                            context,
                                            "Поле номер телефона не может быть пустым",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }

                                    phoneNumber.length < 12 -> {
                                        Toast.makeText(
                                            context,
                                            "Заполните номер",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }

                                    !phoneNumber.contains("996") -> {
                                        Toast.makeText(
                                            context,
                                            "формат должен начинаться с 996",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }

                                    else -> {
                                        viewModel.obtainEvent(ProfileEvent.UpdateData)
                                        expanded = false
                                        openedFirst = false
                                        Toast.makeText(
                                            context,
                                            "Данные сохранены",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        if (selectedImageUri != null) {
                                            viewModel.obtainEvent(ProfileEvent.UploadAvatar)
                                        }
                                    }
                                }
                            }
                        ) {
                            Text(
                                text = stringResource(id = R.string.save),
                                fontSize = 18.sp,
                            )
                        }
                    }
                }
            }
        }
    }
}

private fun handlePermission(
    context: Context,
    imagePickLauncher: ManagedActivityResultLauncher<PickVisualMediaRequest, Uri?>,
    requestPermissionLauncher: ManagedActivityResultLauncher<String, Boolean>
) {
    val permissionToCheck = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> Manifest.permission.READ_MEDIA_IMAGES
        else -> Manifest.permission.READ_EXTERNAL_STORAGE
    }

    if (context.checkSelfPermission(permissionToCheck) == PackageManager.PERMISSION_GRANTED) {
        imagePickLauncher.launch(
            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
        )

    } else {
        requestPermissionLauncher.launch(permissionToCheck)
    }
}

object NumberDefaults {
    const val MASK = "###-###-###-###"
    const val INPUT_LENGTH = 12
}

@Composable
fun SimpleExpandableCard(
    modifier: Modifier = Modifier,
    title: String,
    content: (@Composable () -> Unit)
) {
    var expanded by remember { mutableStateOf(false) }
    val rotateCardIcon by animateFloatAsState(targetValue = if (expanded) 180f else 0f)

    Card(
        elevation = CardDefaults.cardElevation(0.dp),
        shape = RoundedCornerShape(28.dp),
        modifier = modifier
            .background(Color.White)
            .fillMaxWidth()
            .animateContentSize(animationSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy))
            .padding(horizontal = 28.dp, vertical = 10.dp),
        border = BorderStroke(1.dp, gloriaGradient)
    ) {
        Column(
            modifier = Modifier.background(Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(28.dp))
                    .fillMaxWidth()
                    .wrapContentSize()
                    .clickable { expanded = !expanded }
                    .background(gloriaGradient)
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.CenterStart),
                    text = title,
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFFFFFFFF),
                    ),
                )

                Icon(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .rotate(rotateCardIcon)
                        .clickable { expanded = !expanded },
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "",
                    tint = Color.White
                )
            }

            if (expanded) {
                Column(
                    modifier = Modifier
                        .padding(bottom = 15.dp)
                        .background(Color.White)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    content()
                }
            }
        }
    }
}