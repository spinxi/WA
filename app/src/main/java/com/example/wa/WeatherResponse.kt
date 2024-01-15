package com.example.wa

data class WeatherResponse(
    val location: Location,
    val current: Current
)

data class Location(
    val name: String,
    val region: String,
    val country: String,
    val lat: Double,
    val lon: Double,
    val tz_id: String,
    val localtime_epoch: Long,
    val localtime: String
)

data class Current(
    val last_updated_epoch: Long,
    val last_updated: String,
    val temp_c: Double,
    val temp_f: Double,
    val is_day: Int,

    val vis_km: Int,
    val condition: Condition,
    val wind_kph: Double,
    val pressure_mb: Double,
    val humidity: Int,
    val air_quality: AirQuality,  // Added air quality property
    val cloud: Int  // Added cloud cover property
)

data class Condition(
    val text: String,
    val icon: String,
    val code: Int
)

data class AirQuality(
    val co: Double,
    val no2: Double,
    val o3: Double,
    val so2: Double,
    val pm2_5: Double,
    val pm10: Double
)

