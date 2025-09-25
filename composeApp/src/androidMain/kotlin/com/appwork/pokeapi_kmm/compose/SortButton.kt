package com.appwork.pokeapi_kmm.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.appwork.pokeapi_kmm.R
import com.appwork.pokeapi_kmm.utils.AppColor
import com.appwork.pokeapi_kmm.utils.toComposeColor
import androidx.compose.ui.layout.boundsInWindow

@Composable
fun SortButton(
    isSorted: Boolean,
    onSortedChange: (Boolean) -> Unit,
    onPositionChange: (Rect) -> Unit = {},
    action: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    val density = LocalDensity.current

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .onGloballyPositioned { coordinates ->
                val bounds = coordinates.boundsInWindow()
                onPositionChange(bounds)
            }
            .background(Color.White, shape = RoundedCornerShape(20.dp))
            .then(
                if (action != null) {
                    Modifier.clickable {
                        action()
                        onSortedChange(!isSorted)
                    }
                } else {
                    Modifier
                }
            )
            .padding(4.dp)
    ) {
        // Sort icon
        Icon(
            painter = painterResource(id = R.drawable.sort),
            contentDescription = "Sort",
            tint = AppColor.Primary.toComposeColor(),
            modifier = Modifier.size(24.dp)
        )

        // Overlay border + shadow simulation
//        if (isSorted) {
//            Box(
//                modifier = Modifier
//                    .matchParentSize()
//                    .border(
//                        width = 0.5.dp,
//                        color = AppColor.GrayscaleLight.toComposeColor(),
//                        shape = CircleShape
//                    )
//                    .shadow(
//                        elevation = 1.dp,
//                        shape = CircleShape,
//                        clip = false
//                    )
//            )
//        } else {
//            Box(
//                modifier = Modifier
//                    .matchParentSize()
//                    .border(
//                        width = 1.dp,
//                        color = AppColor.GrayscaleLight.toComposeColor(),
//                        shape = CircleShape
//                    )
//                    .shadow(
//                        elevation = 1.dp,
//                        shape = CircleShape,
//                        clip = true
//                    )
//            )
//        }
    }
}
