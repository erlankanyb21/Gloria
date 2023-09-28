package more.contactAndAddress

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import components.GradientButton
import components.ToolBar
import extensions.OnBackPress
import org.tbm.gloria.core_compose.R.drawable.ic_back_arrow
import org.tbm.gloria.core_compose.R.drawable.location
import org.tbm.gloria.main.compose.R
import ru.alexgladkov.odyssey.compose.local.LocalRootController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ContactsAndAddressScreen() {
    val rootController = LocalRootController.current
    OnBackPress { rootController.popBackStack() }
    val context = LocalContext.current
    var permissionState by remember { mutableStateOf(false) }
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            permissionState = true
        } else {
            Toast.makeText(
                context,
                "Чтобы позвонить, приложению нужно разрешение на доступ к звонкам. " +
                        "Пожалуйста, предоставьте разрешение в настройках приложения.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
    Scaffold(
        topBar = {
            ToolBar(
                title = stringResource(id = R.string.contacts_and_address),
                backIcon = {
                    IconButton(
                        modifier = Modifier.size(50.dp),
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = Color.White
                        ),
                        onClick = { rootController.popBackStack() },
                    ) {
                        Icon(
                            painter = painterResource(id = ic_back_arrow),
                            contentDescription = null,
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .padding(18.dp)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        open2GISWithAddress(context)
                    }
                    .clip(RoundedCornerShape(8.dp)),
                painter = painterResource(id = location),
                contentDescription = ""
            )

            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Глория\n")
                        append("Cтудия аэродизайна")
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp),
            )

            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
                        append("5-й микрорайон, 66/1")
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp, vertical = 10.dp),
            )

            Spacer(modifier = Modifier.height(20.dp))

            GradientButton(
                text = stringResource(R.string.make_call),
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .height(40.dp)
            ) {
                if (context.checkSelfPermission(android.Manifest.permission.CALL_PHONE)
                    == android.content.pm.PackageManager.PERMISSION_GRANTED
                ) {
                    Intent(Intent.ACTION_CALL).also { intent ->
                        intent.data = Uri.parse("tel:+996700103333")
                        context.startActivity(intent)
                    }
                } else {
                    requestPermissionLauncher.launch(android.Manifest.permission.CALL_PHONE)
                }
            }
        }
    }
}

private fun open2GISWithAddress(context: Context) {
    val address = "https://2gis.kg/bishkek/geo/70000001027484401"

    Intent(Intent.ACTION_VIEW).also { intent ->
        intent.data =
            Uri.parse(address)
        context.startActivity(intent)
    }
}