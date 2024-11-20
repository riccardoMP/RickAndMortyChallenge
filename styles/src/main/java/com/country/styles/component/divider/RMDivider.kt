package com.country.styles.component.divider

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.country.styles.theme.DividerDark
import com.country.styles.theme.RickAndMortyTheme

@Composable
fun RMDivider(modifier: Modifier = Modifier) {
    HorizontalDivider(
        modifier = modifier
            .fillMaxWidth()
            .height(1.dp),
        color = DividerDark,
    )
}

@Preview(showBackground = true)
@Composable
private fun DividerPreview() {
    RickAndMortyTheme {
        Box(Modifier.size(height = 10.dp, width = 100.dp)) {
            RMDivider(Modifier.align(Alignment.Center))
        }
    }
}
