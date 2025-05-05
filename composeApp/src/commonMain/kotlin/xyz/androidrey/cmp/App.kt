package xyz.androidrey.cmp

import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import xyz.androidrey.cmp.coins.presentation.CoinsListScreen
import xyz.androidrey.cmp.theme.CmpTheme

@Composable
@Preview
fun App() {
    CmpTheme {
        CoinsListScreen {  }
    }

}