package mario.bellido.u3_p7

import androidx.compose.runtime.Composable

typealias MiFuncion = @Composable () -> Unit
sealed class Tabs_item(
    var title: String,
    var screen: MiFuncion
)
{
    object item_info : Tabs_item("INFO", { PInfo() })
    object item_checkin : Tabs_item("CHECK-INS", { PCheckins() })
    object item_photos : Tabs_item("PHOTOS", { PPhotos() })
}