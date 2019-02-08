package teko.biz.bluetoothapp.domain.entity

data class BleEntity(
    val id: String,
    val rssi: Int,
    val uuid: String? = null,
    val major: Int = 0,
    val manor: Int = 0,
    val txPower: Int = 0,
    val distance: Double = 0.0
)