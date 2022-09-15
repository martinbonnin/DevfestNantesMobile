package com.gdgnantes.devfest.android.ui.screens.about.partners

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.gdgnantes.devfest.android.ui.theme.DevFest_NantesTheme
import com.gdgnantes.devfest.model.Partner
import com.gdgnantes.devfest.model.stubs.buildPartnerStub
import timber.log.Timber

@Composable
fun PartnerCard(
    modifier: Modifier = Modifier,
    partner: Partner,
    onWeblinkClick: (String) -> Unit
) {
    partner.logoUrl?.let {
        AsyncImage(
            modifier = Modifier
                .height(50.dp)
                .clickable { partner.url?.let { onWeblinkClick(it) } },
            model = ImageRequest.Builder(LocalContext.current)
                .data(partner.logoUrl)
                .crossfade(true)
                .build(),
            onError = { state ->
                Timber.w(
                    state.result.throwable,
                    "${partner.name}'s logo loading failed (url = ${partner.logoUrl})"
                )
            },
            contentDescription = "${partner.name} logo",
            contentScale = ContentScale.Fit
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PartnerCardPreview() {
    DevFest_NantesTheme {
        Scaffold {
            PartnerCard(
                modifier = Modifier.padding(it),
                partner = buildPartnerStub(),
                onWeblinkClick = {})
        }
    }
}