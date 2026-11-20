package vn.luminawidgets.app

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import vn.luminawidgets.app.widget.LuminaGlanceWidgetReceiver

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    WidgetSetupScreen(
                        onPinWidget = { requestPinWidget() }
                    )
                }
            }
        }
    }

    private fun requestPinWidget() {
        val manager = AppWidgetManager.getInstance(this)
        val provider = ComponentName(this, LuminaGlanceWidgetReceiver::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && manager.isRequestPinAppWidgetSupported) {
            manager.requestPinAppWidget(provider, null, null)
        } else {
            Toast.makeText(
                this,
                "Nhấn giữ màn hình chính > Tiện ích để thêm Thời tiết & Giờ Material",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}

@Composable
private fun WidgetSetupScreen(onPinWidget: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Lumina Widgets",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Hiển thị giờ, nhiệt độ và dự báo nhanh chuẩn Material You trên màn hình chính.",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = onPinWidget,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Ghim Widget lên màn hình chính")
        }
    }
}
