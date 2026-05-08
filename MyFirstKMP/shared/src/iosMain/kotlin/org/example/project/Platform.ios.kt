package org.example.project

import platform.UIKit.UIDevice
import platform.UIKit.UIScreen

actual class Platform {
    actual val osName: String
        get() = UIDevice.currentDevice.systemName  // "iOS"

    actual val osVersion: String
        get() = UIDevice.currentDevice.systemVersion  // "17.0"

    actual val deviceModel: String
        get() = UIDevice.currentDevice.model  // "iPhone", "iPad"

    actual val density: Int
        get() = UIScreen.mainScreen.scale.toInt()  // 2.0, 3.0

    actual fun logSystemInfo() {
        println("OS: $osName $osVersion | Device: $deviceModel | Density: $density")
    }
}