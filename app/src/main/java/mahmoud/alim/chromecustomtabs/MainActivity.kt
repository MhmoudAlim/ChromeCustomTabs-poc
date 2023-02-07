package mahmoud.alim.chromecustomtabs

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Browser
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.browser.customtabs.CustomTabsIntent
import androidx.browser.customtabs.CustomTabsSession
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import mahmoud.alim.chromecustomtabs.ui.theme.ChromeCustomTabsTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChromeCustomTabsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        OpenInAppBrowser("Open 4Sale web")
                    }
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val code = intent?.data?.getQueryParameter("code")
    }


    @Composable
    fun OpenInAppBrowser(name: String) {
        Button({
            openForSaleWeb()
        }) {
            Text(text = name)
        }
    }

    private fun openForSaleWeb() {
        val authorize = ""
        val url = "https://www.q84sale.com/auth/$authorize"

        val customTab = CustomTabsIntent.Builder()
            .setShareState(CustomTabsIntent.SHARE_STATE_ON)
            .build()

        customTab.apply {
            intent.`package` = "com.android.chrome"
            launchUrl(this@MainActivity, Uri.parse(url))
        }

    }

    fun constructExtraHeadersIntent(session: CustomTabsSession): CustomTabsIntent {
        val intent = CustomTabsIntent.Builder(session).build();
        val headers = Bundle()
        headers.putString("bearer-token", "Some token");
        headers.putString("redirect-url", "Some redirect url");
        intent.intent.putExtra(Browser.EXTRA_HEADERS, headers);
        return intent
    }
}

