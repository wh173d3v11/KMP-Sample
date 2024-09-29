package com.fierydinesh.kmppractice

import com.seiko.imageloader.ImageLoader

expect fun generateImageLoader(): ImageLoader
expect fun getPlatformType() : PlatformType

enum class PlatformType {
    ANDROID,
    IOS,
    MACOS,
    DESKTOP
}