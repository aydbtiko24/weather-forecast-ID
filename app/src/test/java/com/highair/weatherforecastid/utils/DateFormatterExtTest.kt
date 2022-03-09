package com.highair.weatherforecastid.utils

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Created by aydbtiko on 6/10/2021.
 *
 */
class DateFormatterExtTest {

    @Test
    fun `get start date time, return expected value`() {
        // given
        val dateTime = 1623439739000 // 2021 06 12 01:88:59

        // when
        val startDateTime = dateTime.toStartDateTime()

        // then
        val expectedStartDateTime = 1623430800000
        assertEquals(expectedStartDateTime, startDateTime)
    }

    @Test
    fun `get end date time, return expected value`() {
        // given
        val dateTime = 1623439739000 // 2021 06 12 01:88:59

        // when
        val endDateTime = dateTime.toEndDateTime()

        // then
        val expectedEndDateTime = 1623517199059 // 2021 06 12 23:59:59:59
        assertEquals(expectedEndDateTime, endDateTime)
    }

    @Test
    fun `get as date string, return expected value`() {
        // given
        val dateTime = 1623344399000 // 2021 06 10 01:88:59

        // when
        val dateString = dateTime.asDateString()

        // then
        val expectedDateString = "Thu, 10 Jun"
        assertEquals(expectedDateString, dateString)
    }

    @Test
    fun `get as time string, return expected value`() {
        // given
        val dateTime = 1623258000000 // 2021 06 10 00:00:00

        // when
        val timeString = dateTime.asTimeString()

        // then
        val expectedTimeString = "00:00 - 06:00"
        assertEquals(expectedTimeString, timeString)
    }

    @Test
    fun `get as local date time, return expected value`() {
        // given
        val dateString = "2021-06-10 01:22:00"

        // when
        val localDateTime = dateString.asLocalDateTime()

        // then
        val expectedDateTime = 1623262920000 // 2021 06 10 01:22:00
        assertEquals(expectedDateTime, localDateTime)
    }

    @Test
    fun `find closest weather on time 01, return 00 value`() {
        // given
        val dateTime = 1623262965000 // 2021 06 10 01:22:45

        // when
        val weatherTime = findClosestWeatherTime(dateTime)

        // then
        val expectedWeatherTime = 1623258000000 // 2021 06 10 00:00:00
        assertEquals(expectedWeatherTime, weatherTime)
    }

    @Test
    fun `find closest weather on time 07, return 06 value`() {
        // given
        val dateTime = 1623283200000 // 2021 06 10 07:00:00

        // when
        val weatherTime = findClosestWeatherTime(dateTime)

        // then
        val expectedWeatherTime = 1623279600000 // 2021 06 10 06:00:00
        assertEquals(expectedWeatherTime, weatherTime)
    }

    @Test
    fun `find closest weather on time 15, return 12 value`() {
        // given
        val dateTime = 1623317379000 // 2021 06 10 15:88:59

        // when
        val weatherTime = findClosestWeatherTime(dateTime)

        // then
        val expectedWeatherTime = 1623301200000 // 2021 06 10 12:00:00
        assertEquals(expectedWeatherTime, weatherTime)
    }

    @Test
    fun `find closest weather on time 20, return 18 value`() {
        // given
        val dateTime = 1623330000000 // 2021 06 10 20:00:00

        // when
        val weatherTime = findClosestWeatherTime(dateTime)

        // then
        val expectedWeatherTime = 1623322800000 // 2021 06 10 18:00:00
        assertEquals(expectedWeatherTime, weatherTime)
    }

    @Test
    fun `format label date to local date`() {
        // given
        val labelDate = "Thu, 10 jun"

        // when
        val currentDate = labelDate.asSelectedLocalDateTime()

        // then
        val expectedWeatherTime = 1654794000000 // 2022 06 10 00:00:00
        assertEquals(expectedWeatherTime, currentDate)
    }

    @Test
    fun `is current weather, return true`() {
        // given
        val currentDateTime = 1623327743000 // 2021 06 10 19:22:23

        val weatherDateTime = 1623322800000 // 2021 06 10 18:00:00

        // when
        val isCurrentWeather = weatherDateTime.isCurrentWeather(
            currentDateTime
        )

        // then
        assertTrue(isCurrentWeather)
    }

    @Test
    fun `is current weather, return false`() {
        // given
        val currentDateTime = 1623327743000 // 2021 06 10 19:22:23

        val weatherDateTime = 1623304800000 // 2021 06 10 13:00:00

        // when
        val isCurrentWeather = weatherDateTime.isCurrentWeather(
            currentDateTime
        )

        // then
        assertFalse(isCurrentWeather)
    }
}