package com.cym.sample.download

import android.content.Context
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.security.KeyStore
import java.security.cert.CertificateFactory
import javax.net.ssl.KeyManagerFactory
import javax.net.ssl.SSLSession
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

/**
 * Created by caoj on 2023/12/19.
 */
object OkHttpDownload {
    fun downloadFile(path: String, url: String, fileName: String,
                     listener: (progress: Float, total: Long) -> Unit = { _, _ -> })
    : File {

        val client = OkHttpClient.Builder()
//            .hostnameVerifier { _: String?, _: String? -> true } // Skip strict hostname verification for HTTP
//            .addInterceptor(CacheInterceptor()) // Use cache for efficient download
            .cache(Cache(File(path, "download_cache"), 100 * 1024 * 1024)) // Set cache size to 100MB

            .build()

        // Load CA certificate from assets
//        val certInputStream = context.assets.open(caAssetPath)
        val certFactory = CertificateFactory.getInstance("X.509")
//        val cert = certFactory.generateCertificate(certInputStream)
        //val cert = certFactory.generateCertificate(CertUtil.certInputStream())
//        certInputStream.close()

        val certificateFactory = CertificateFactory.getInstance("X.509")
        val keyStore = KeyStore.getInstance(KeyStore.getDefaultType())
        keyStore.load(null, null)

//        val certificate = CertUtil.certInputStream()
//        keyStore.setCertificateEntry("trust", certificateFactory.generateCertificate(certificate))
//
//        certificate?.close()

        // Create trust manager with custom CA certificate
//        val trustManagerFactory = X509TrustManagerFactory.getInstance("X.509")

        /*
        val trustManagerFactory = TrustManagerFactory.getInstance("X.509")
        //X509TrustManagerFactory

//        val trustManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm())
//        trustManagerFactory.init(KeyStore.getInstance(KeyStore.defaultType).apply {
//            load(null, null)
//            setCertificateEntry("ca", cert)
//        })
        trustManagerFactory.init(keyStore)
        val trustManagers = trustManagerFactory.trustManagers as Array<X509TrustManager>

        // Configure client builder with custom trust manager
        val builder = client.newBuilder()

        builder.sslSocketFactory(
            Tls12SocketFactory(
                OkHttpClient().sslSocketFactory(),

            ),
            trustManagers[0]
        )


        val newClient = builder.build()
*/
        val newClient = client
        val request = Request.Builder().url(url).build()
        val response = newClient.newCall(request).execute()

        val body = response.body() ?: throw RuntimeException("Empty response")

        val file = File(path, fileName)
        val bufferedSink = file.outputStream().buffered()
        val source = body.source()

        var downloadedBytes = 0L
        val totalBytes = body.contentLength()
        source.use {
            while (!it.exhausted()) {
                val buffer = it.readByteArray(8192)
                downloadedBytes += buffer.size
                bufferedSink.write(buffer)
                listener(downloadedBytes.toFloat() / totalBytes, totalBytes)
            }
        }
        bufferedSink.close()

        return file
    }

}