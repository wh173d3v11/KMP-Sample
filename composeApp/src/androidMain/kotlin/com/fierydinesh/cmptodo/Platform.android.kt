package com.fierydinesh.kmppractice

import com.seiko.imageloader.ImageLoader
import com.seiko.imageloader.cache.memory.maxSizePercent
import com.seiko.imageloader.component.setupDefaultComponents
import com.seiko.imageloader.intercept.bitmapMemoryCacheConfig
import com.seiko.imageloader.intercept.imageMemoryCacheConfig
import com.seiko.imageloader.intercept.painterMemoryCacheConfig
import com.seiko.imageloader.option.androidContext
import okio.Path.Companion.toOkioPath

actual fun generateImageLoader(): ImageLoader {
    val context = MainApp.applicationContext
    return ImageLoader {
        options {
            androidContext(context)
        }
        components {
            setupDefaultComponents()
        }
        interceptor {
            // cache 25% memory bitmap
            bitmapMemoryCacheConfig {
                maxSizePercent(context, 0.25)
            }
            // cache 50 image
            imageMemoryCacheConfig {
                maxSize(50)
            }
            // cache 50 painter
            painterMemoryCacheConfig {
                maxSize(50)
            }
            diskCacheConfig {
                directory(context.cacheDir.resolve("image_cache").toOkioPath())
                maxSizeBytes(512L * 1024 * 1024) // 512MB
            }
        }
    }
}

actual fun getPlatformType(): PlatformType = PlatformType.ANDROID