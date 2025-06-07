/**
 * Created by shreyasrivastava on 03.06.2025.
 *
 * Description: [Brief description of this class].
 */
package com.example.framework

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import coil.compose.AsyncImage

@Composable
fun Avatar(
    modifier: Modifier,
    imageUrl: String,
    size: Dp,
    contentDescription: String? = "User Avatar"
){
    AsyncImage(
        model = imageUrl,
        contentDescription = contentDescription,
        modifier = modifier
            .size(size)
            .clip(CircleShape),
        contentScale = ContentScale.Crop
    )
}