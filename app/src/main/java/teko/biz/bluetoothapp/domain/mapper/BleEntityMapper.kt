package teko.biz.bluetoothapp.domain.mapper

import android.bluetooth.BluetoothClass
import com.clj.fastble.data.BleDevice
import org.altbeacon.beacon.Beacon
import teko.biz.bluetoothapp.domain.entity.BleEntity

class BleEntityMapper{
    companion object {
        fun map(beacon: Beacon): BleEntity{
            return BleEntity(beacon.bluetoothAddress,
                beacon.rssi,
                beacon.id1.toHexString(),
                beacon.id2.toInt(),
                beacon.id3.toInt(),
                beacon.txPower,
                beacon.distance)
        }

        fun map(device: BleDevice): BleEntity{
            return BleEntity(device.mac, device.rssi)
        }
    }
}