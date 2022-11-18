package io.volgadev.sampleapp.feature.prices_list.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.ktor.feature.prices_list.R
import io.volgadev.sampleapp.feature.prices_list.domain.model.CryptoAsset
import io.volgadev.sampleapp.feature.prices_list.presentation.model.AssetsListScreenState
import org.koin.androidx.compose.getViewModel


@Composable
internal fun AssetsListScreen(
    modifier: Modifier,
    navController: NavHostController,
    viewModel: AssetsListViewModel = getViewModel()
) {
    val state = viewModel.state.collectAsState(initial = AssetsListScreenState.Loading)

    when (val stateValue = state.value) {
        is AssetsListScreenState.Content -> {
            AssetsListScreen(
                modifier = modifier,
                items = stateValue.items,
                onClickItem = {}
            )
        }
        AssetsListScreenState.Error -> TODO()
        AssetsListScreenState.Loading -> TODO()
    }
}

@Composable
private fun AssetsListScreen(
    modifier: Modifier = Modifier,
    items: List<CryptoAsset>,
    onClickItem: (CryptoAsset) -> Unit
) {
    Column(modifier) {
        Spacer(modifier = Modifier.height(8.dp))
        PriceListHeader()
        LazyColumn {
            itemsIndexed(
                items,
                { _, CryptoAsset -> CryptoAsset.id }) { index, asset ->
                Row(
                    Modifier
                        .background(if (index % 2 == 0) Color.LightGray else MaterialTheme.colors.background)
                        .fillParentMaxWidth()
                        .clickable { onClickItem(asset) }
                        .padding(vertical = 16.dp)

                ) {
                    Row(Modifier.width(150.dp)) {
                        Spacer(Modifier.width(8.dp))
                        Text(text = index.inc().toString(), modifier = Modifier.width(32.dp))
                        Text(text = asset.name)
                    }
                    Text(text = asset.price.toString())
                }
            }
        }
    }
}

@Composable
private fun PriceListHeader() {
    Row(
        Modifier
            .fillMaxWidth()
            .border(width = 2.dp, color = Color.Gray)
    ) {
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = stringResource(R.string.asset_name_title),
            modifier = Modifier.width(150.dp)
        )
        Text(
            text = stringResource(R.string.asset_price_title),
            modifier = Modifier.width(120.dp)
        )
    }
}


@Preview
@Composable
internal fun PricesListUiPreview() {
    Surface {
        AssetsListScreen(
            items = listOf(
                CryptoAsset(id = "asdasd", name = "BTC", price = 50000.0),
                CryptoAsset(id = "asssdaa", name = "ETF", price = 2132.0)
            ),
            onClickItem = {}
        )
    }
}
