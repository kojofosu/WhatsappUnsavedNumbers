//import androidx.compose.ui.geometry.Size
//import androidx.compose.ui.graphics.Outline
//import androidx.compose.ui.graphics.Path
//import androidx.compose.ui.graphics.Shape
//import androidx.compose.ui.unit.Density
//import androidx.compose.ui.unit.LayoutDirection
//
//class InwardCaveShape(private val curveRadius: Float) : Shape {
//    override fun createOutline(
//        size: Size,
//        layoutDirection: LayoutDirection,
//        density: Density
//    ): Outline {
//        val width = size.width
//        val height = size.height
//        val path = Path().apply {
//            // Start at the top-left corner
//            moveTo(curveRadius, 0f)
//            // Draw the inward curve
//            quadraticBezierTo(0f, height / 2, curveRadius, height)
//            // Draw the rest of the rectangle
//            lineTo(width, height)
//            lineTo(width, 0f)
//            close()
//        }
//        return Outline.Generic(path)
//    }
//}


import androidx.compose.foundation.shape.GenericShape
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class InwardCaveShape(private val curveRadius: Float) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val width = size.width
        val height = size.height
        val path = Path().apply {
            // Start at the top-left corner
            moveTo(curveRadius, 0f)
            // Draw the inward curve
            cubicTo(0f, 0f, 0f, height, curveRadius, height)
            // Draw the rest of the rectangle
            lineTo(width, height)
            lineTo(width, 0f)
            close()
        }
        return Outline.Generic(path)
    }
}

