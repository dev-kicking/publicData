package dev.kick.data.util

import okhttp3.Interceptor
import okhttp3.Response
import kotlin.math.pow

class RetryInterceptor(
    private val maxRetry: Int = 3,
    private val backoffMultiplier: Long = 2L
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        var response: Response
        var retryCount = 0

        do {
            response = chain.proceed(request)
            if (!response.isSuccessful) {
                if (retryCount < maxRetry) {
                    val waitTime = (1000 * backoffMultiplier.toDouble().pow(retryCount.toDouble())).toLong()
                    Thread.sleep(waitTime) // 대기 시간
                    retryCount++
                } else {
                    break // 최대 재시도 횟수 초과 시 종료
                }
            }
        } while (!response.isSuccessful)

        return response
    }
} 