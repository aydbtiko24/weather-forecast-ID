package com.highair.weatherforecastid.data.source.remote

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

/**
 * Return ok response from mock server
 */
class SuccessDispatcher : Dispatcher() {

    private val responseBuilder = JsonResponseBuilder()

    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.path) {
            "/cuaca/0.json" -> MockResponse().setResponseCode(200)
                .setBody(
                    responseBuilder.getJsonContent(
                        fileName = "weather_response.json"
                    )
                )
            else -> MockResponse().setResponseCode(400)
        }
    }
}

private val errorResponse = MockResponse().setResponseCode(400)

/**
 * Return error response from mock server
 */
class ErrorDispatcher : Dispatcher() {

    override fun dispatch(request: RecordedRequest) = errorResponse
}


