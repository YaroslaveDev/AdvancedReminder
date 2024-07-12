package com.pfv.advancedreminder.ui.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pfv.advancedreminder.ext.date.getDayOfMonth
import com.pfv.advancedreminder.ext.date.getDayOfWeekShort
import java.util.Date

@Composable
fun DateItem(
    isActiveItem: Boolean,
    date: Date
) {

    val primaryColor = MaterialTheme.colorScheme.primary
    val secondaryColor = MaterialTheme.colorScheme.secondary

    Box(
        modifier = Modifier
            .size(74.dp)
            .background(
                color = if (isActiveItem) secondaryColor else primaryColor,
                shape = RoundedCornerShape(8.dp)
            ),
        contentAlignment = Alignment.Center
    ){

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = date.getDayOfWeekShort(),
                color = MaterialTheme.colorScheme.surface,
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = date.getDayOfMonth().toString(),
                color = MaterialTheme.colorScheme.surface,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold, fontSize = 22.sp
                )
            )
        }
    }
}