package com.example.wa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val apiKey = "500c361e84ac4f2faf355609241501" // Replace with your actual API key
    private val location = "Tbilisi" // Replace with your actual API key

    private lateinit var locationTextView: TextView
    private lateinit var temperatureTextView: TextView
    private lateinit var humidityTextView: TextView
    private lateinit var airTextView: TextView
    private lateinit var cloudTextView: TextView
    private lateinit var windTextView: TextView
    private lateinit var pressureTextView: TextView
    private lateinit var conditionTextView: TextView
    private lateinit var dateTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize TextViews
        locationTextView = findViewById(R.id.textViewLocation)
        temperatureTextView = findViewById(R.id.textViewTemperature)
        humidityTextView = findViewById(R.id.textviewHumidity)
        airTextView = findViewById(R.id.textviewAirQuality)
        cloudTextView = findViewById(R.id.textviewCloudCover)
        windTextView = findViewById(R.id.textviewWind)
        pressureTextView = findViewById(R.id.textviewPressure)
        conditionTextView = findViewById(R.id.textviewStatus)
        dateTextView = findViewById(R.id.textViewDate)

        // Make API call
        val call = RetrofitClient.weatherApiService.getCurrentWeather(apiKey, location)

        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if (response.isSuccessful) {
                    val weatherResponse = response.body()

                    if (weatherResponse != null) {
                        val current = weatherResponse.current
                        dateTextView.text = " ${current.last_updated}"
                        locationTextView.text = "${weatherResponse.location.name}, ${weatherResponse.location.region}, ${weatherResponse.location.country}"
                        temperatureTextView.text = "${current.temp_c} °C"
                        humidityTextView.text = "${current.humidity}%"
                        cloudTextView.text = "${current.cloud}%"
                        airTextView.text = " ${current.vis_km} CO"
                        windTextView.text = "${current.wind_kph} km/h"
                        pressureTextView.text = "${current.pressure_mb} mb"
                        conditionTextView.text = "${current.condition.text}"
                    }
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                // Handle network error
            }
        })
    }
}