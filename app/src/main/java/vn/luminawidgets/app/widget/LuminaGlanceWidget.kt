package vn.luminawidgets.app.widget

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.action.ActionParameters
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.width
import androidx.glance.material3.ColorProviders
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme

private val WidgetLightColors = lightColorScheme(
    primary = Color(0xFF1E6B58),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFB7EAD9),
    onPrimaryContainer = Color(0xFF09382C),
    surface = Color(0xFFF2F7F4),
    surfaceVariant = Color(0xFFE1ECE6),
    onSurface = Color(0xFF13201B),
    onSurfaceVariant = Color(0xFF445850),
    outline = Color(0xFFC5D6CD)
)

private val WidgetDarkColors = darkColorScheme(
    primary = Color(0xFF7ED4BC),
    onPrimary = Color(0xFF06372B),
    primaryContainer = Color(0xFF115141),
    onPrimaryContainer = Color(0xFFC3F2E3),
    surface = Color(0xFF16201C),
    surfaceVariant = Color(0xFF22312B),
    onSurface = Color(0xFFE5EFEA),
    onSurfaceVariant = Color(0xFFA4B8AF),
    outline = Color(0xFF354840)
)

class LuminaGlanceWidget : GlanceAppWidget() {

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            GlanceTheme(
                colors = ColorProviders(
                    light = WidgetLightColors,
                    dark = WidgetDarkColors
                )
            ) {
                WidgetContent()
            }
        }
    }

    @Composable
    private fun WidgetContent() {
        Box(
            modifier = GlanceModifier
                .fillMaxSize()
                .cornerRadius(24.dp)
                .background(GlanceTheme.colors.surface)
                .padding(16.dp)
        ) {
            Column(
                modifier = GlanceModifier.fillMaxSize()
            ) {
                Row(
                    modifier = GlanceModifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "TP. Hồ Chí Minh",
                        style = TextStyle(
                            color = GlanceTheme.colors.onSurfaceVariant,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium
                        )
                    )
                    Spacer(modifier = GlanceModifier.defaultWeight())
                    Text(
                        text = "Cập nhật",
                        modifier = GlanceModifier.clickable(actionRunCallback<RefreshWidgetAction>()),
                        style = TextStyle(
                            color = GlanceTheme.colors.primary,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                    )
                }
                Spacer(modifier = GlanceModifier.defaultWeight())
                Text(
                    text = "31°C",
                    style = TextStyle(
                        color = GlanceTheme.colors.onSurface,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = GlanceModifier.height(4.dp))
                Text(
                    text = "Nắng nhẹ · Độ ẩm 64%",
                    style = TextStyle(
                        color = GlanceTheme.colors.onSurfaceVariant,
                        fontSize = 13.sp
                    )
                )
            }
        }
    }
}

class RefreshWidgetAction : ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        // Cập nhật lại dữ liệu Widget khi người dùng chạm trên màn hình chính
        LuminaGlanceWidget().update(context, glanceId)
    }
}
