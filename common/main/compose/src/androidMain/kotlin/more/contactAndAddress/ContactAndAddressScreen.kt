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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import org.tbm.gloria.core_compose.R.drawable.location
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import components.GradientButton
import components.ToolBar
import org.tbm.gloria.main.compose.R
import org.tbm.gloria.core_compose.R.drawable.ic_back_arrow
import ru.alexgladkov.odyssey.compose.local.LocalRootController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ContactsAndAddressScreen() {
    val rootController = LocalRootController.current
    val context = LocalContext.current
    var permissionState by remember { mutableStateOf(false) }
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            permissionState = true
        } else {
            Toast.makeText(context, "Permission denied", Toast.LENGTH_SHORT).show()
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
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier
                    .padding(18.dp)
                    .clickable {
                        open2GISWithAddress(context)
                    }
                    .clip(RoundedCornerShape(8.dp)),
                painter = painterResource(id = location),
                contentDescription = ""
            )

            Text(text = "Глория", modifier = Modifier.padding(vertical = 10.dp))

            GradientButton(
                text = "Позвонить",
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .height(40.dp)
            ) {
                val intent = Intent(Intent.ACTION_CALL)
                intent.data = Uri.parse("tel:+996700103333")

                if (context.checkSelfPermission(android.Manifest.permission.CALL_PHONE)
                    == android.content.pm.PackageManager.PERMISSION_GRANTED
                ) {
                    if (permissionState) context.startActivity(intent)
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