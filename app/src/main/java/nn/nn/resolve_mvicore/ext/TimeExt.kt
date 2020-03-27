package nn.nn.resolve_mvicore.ext

import java.text.SimpleDateFormat
import java.util.*


fun Long.toddMMyyyyHHmmss(): String {
    return SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(Date(this))
}