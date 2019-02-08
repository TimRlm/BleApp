package teko.biz.bluetoothapp.presentation.main

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.callbacks.onDismiss
import com.clj.fastble.data.BleDevice
import kotlinx.android.synthetic.main.activity_main.*
import pub.devrel.easypermissions.EasyPermissions
import teko.biz.bluetoothapp.R
import teko.biz.bluetoothapp.app.App
import teko.biz.bluetoothapp.domain.entity.BleEntity


class MainActivity : AppCompatActivity(), EasyPermissions.PermissionCallbacks {
    companion object {
        val coarseLocationPermission = arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION)
        val COARSE_LOCATION = 1
    }

    private lateinit var adapter: BleDevicesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val app = application as App
        app.devicesLive.observe(this, devicesObserver)
        rv.layoutManager = LinearLayoutManager(this)
        adapter = BleDevicesAdapter()
        rv.adapter = adapter

        ActivityCompat.requestPermissions(this, coarseLocationPermission, COARSE_LOCATION)
    }

    private val devicesObserver = Observer<HashMap<String, BleEntity>>{ map ->
        adapter.updateData(map.values.sortedBy { -it.rssi })
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        if (requestCode == COARSE_LOCATION) {

        }
    }

    override fun onPermissionsDenied(requestCode: Int, permList: List<String>) {
        if (requestCode == COARSE_LOCATION) {
            MaterialDialog(this)
                .title(text = "Ошибка")
                .message(text = "Необходимо дать разрешение")
                .positiveButton(text = "Хорошо")
                .onDismiss{
                    ActivityCompat.requestPermissions(this, coarseLocationPermission, COARSE_LOCATION)
                }.show()
        }
    }
}