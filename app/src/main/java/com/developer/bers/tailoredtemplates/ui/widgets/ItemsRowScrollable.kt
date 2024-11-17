package com.developer.bers.tailoredtemplates.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

data class MenuSection(
    val title: String,
    val image: String
)

@Composable
fun MenuSections(
    menuSections: List<MenuSection>,
) {
    val selectedIndex = rememberSaveable { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(space = 8.dp)
        ) {
            itemsIndexed(menuSections) { index, item ->
                MenuItemCard(
                    imageItem = item.image,
                    isSelected = index == selectedIndex.intValue,
                    menuTitle = item.title,
                    onClick = { selectedIndex.intValue = index }
                )
            }
        }
    }
}

@Composable
fun MenuItemCard(
    imageItem: String,
    isSelected: Boolean,
    menuTitle: String,
    onClick: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable {
            onClick()
        }
    ) {
        Card(
            colors = CardDefaults.cardColors(
                if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
            ),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = imageItem),
                    contentDescription = menuTitle,
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color.Transparent)
                        .padding(8.dp)
                )
            }
        }
        Text(
            text = menuTitle,
            color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Black,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
    }
}