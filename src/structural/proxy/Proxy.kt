package structural.proxy

import com.sun.org.apache.xpath.internal.operations.Bool

interface ThirdPartyLib {
    fun listVideos(): String
    fun getVideoInfo(id: Int): String
    fun downloadVideo(id: Int): String
}

class ThirdPartyLibImpl : ThirdPartyLib {
    override fun listVideos() = "list"
    override fun getVideoInfo(id: Int) = "info"
    override fun downloadVideo(id: Int) = "download..."
}

class CachedLib(
    private val service: ThirdPartyLibImpl
) : ThirdPartyLib {

    private var listCache: String? = null
    private var videoCache: String? = null
    private var resetNeed: Boolean = false

    override fun listVideos(): String {
        if (listCache.isNullOrEmpty() or resetNeed) listCache = service.listVideos()
        return listCache ?: ""
    }

    override fun getVideoInfo(id: Int): String {
        if (videoCache.isNullOrEmpty() or resetNeed) videoCache = service.getVideoInfo(id)
        return videoCache ?: ""
    }

    override fun downloadVideo(id: Int): String {
        if (videoCache.isNullOrEmpty() or resetNeed) videoCache = service.downloadVideo(id)
        return videoCache ?: ""
    }
}

class Manager(private val lib: ThirdPartyLib) {
    fun renderVideo() = println(lib.getVideoInfo(1))
    fun renderList() = println(lib.downloadVideo(1))
}

fun main() {
    val service = ThirdPartyLibImpl()
    val proxy = CachedLib(service)

    val manager = Manager(proxy)
    manager.renderList()
    manager.renderVideo()
}