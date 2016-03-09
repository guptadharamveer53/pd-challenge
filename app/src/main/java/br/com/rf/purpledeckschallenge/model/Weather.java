package br.com.rf.purpledeckschallenge.model;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import br.com.rf.purpledeckschallenge.R;
import br.com.rf.purpledeckschallenge.util.Constants;
import br.com.rf.purpledeckschallenge.util.PreferencesUtil;

/**
 * Created by rodrigoferreira on 3/5/16.
 */
public class Weather {

    public static final int TYPE_SUN = 0;
    public static final int TYPE_RAIN = 1;
    public static final int TYPE_CLOUDY = 2;

    public String city;
    public String time;
    public String tempture;
    public int weatherType;
    public String photoUrl;

    public Weather() {

    }

    public Weather(String city, String time, String tempture, int weatherType) {
        this.city = city;
        this.time = time;
        this.tempture = tempture;
        this.weatherType = weatherType;
    }

    public Weather(WeatherApiWrapper apiWrapper) {
        this.city = apiWrapper.getCity();
        this.time = apiWrapper.getDt();
        this.tempture = apiWrapper.getTempture();
        this.weatherType = apiWrapper.getWeatherType();
    }

    public static List<Weather> getMockList() {
        List<Weather> weatherList = new ArrayList<>();

        weatherList.add(new Weather("london", "16:00", "21°", Weather.TYPE_SUN));
        weatherList.add(new Weather("reijavik", "17:00", "2°", Weather.TYPE_RAIN));
        weatherList.add(new Weather("dublin", "20:00", "18°", Weather.TYPE_CLOUDY));
        weatherList.add(new Weather("beijing", "03:00", "20°", Weather.TYPE_CLOUDY));
        weatherList.add(new Weather("sidney", "12:00", "21°", Weather.TYPE_SUN));
        weatherList.add(new Weather("warsaw", "19:00", "2°", Weather.TYPE_CLOUDY));
        weatherList.add(new Weather("copenhagem", "12:00", "21°", Weather.TYPE_SUN));

        return weatherList;
    }

    public static List<String> getDefaultCities() {
        return Arrays.asList(new String[]{"Dublin", "London", "Beijing", "Sydney"});
    }

    public static void saveMyCitiesByWeather(Context context, List<Weather> weatherList) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < weatherList.size(); i++) {
            sb.append(weatherList.get(i).city);
            if (i < weatherList.size() - 1) {
                sb.append(";");
            }
        }
        Log.d("saveCities", sb.toString());
        PreferencesUtil.savePreference(context, Constants.PREF_KEY_MY_CITIES, sb.toString());
    }

    public static void saveMyCitiesByString(Context context, List<String> citiesList) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < citiesList.size(); i++) {
            sb.append(citiesList.get(i));
            if (i < citiesList.size() - 1) {
                sb.append(";");
            }
        }
        Log.d("saveCities", sb.toString());
        PreferencesUtil.savePreference(context, Constants.PREF_KEY_MY_CITIES, sb.toString());
    }

    public static List<String> getMySavedCities(Context context) {
        String mySavedCities = PreferencesUtil.getStringPreference(context, Constants.PREF_KEY_MY_CITIES, null);
        if (mySavedCities == null || mySavedCities.length() == 0) {
            return new ArrayList<String>();
        }
        return Arrays.asList(mySavedCities.split(";"));

    }

    public static Weather getRandomWeather(String city) {
        Random random = new Random();
        String time = String.valueOf(random.nextInt(24) + 1);
        String tempture = String.valueOf(random.nextInt(30));
        int weatherType = random.nextInt(3);
        return new Weather(city, time.length() == 1 ? "0" + time + ":00" : time + ":00", tempture + "˚", weatherType);
    }

    public static List<Weather> getWeatherListByCities(List<String> citiesList) {
        List<Weather> weatherList = new ArrayList<Weather>();
        for (String city : citiesList) {
            weatherList.add(Weather.getRandomWeather(city));
        }
        return weatherList;
    }

    public static int getTypeIconRes(int type) {
        if (type == TYPE_SUN) {
            return R.drawable.ic_sun;
        } else if (type == TYPE_RAIN) {
            return R.drawable.ic_rain;
        } else {
            return R.drawable.ic_cloudy;
        }
    }

    public static int getTypeOverlayRes(int type) {
        if (type == TYPE_SUN) {
            return R.drawable.overlay_3;
        } else if (type == TYPE_RAIN) {
            return R.drawable.overlay_1;
        } else {
            return R.drawable.overlay_2;
        }
    }

    public static int getSupportedWeatherType(String weatherType) {
        if ("Thunderstorm".equals(weatherType) || "Drizzle".equals(weatherType)
                || "Rain".equals(weatherType) || "Snow".equals(weatherType)) {
            return TYPE_RAIN;
        } else if ("Atmosphere".equals(weatherType) || "Clouds".equals(weatherType)
                || "Extreme".equals(weatherType) || "Additional".equals(weatherType)) {
            return TYPE_CLOUDY;
        } else {
            return TYPE_SUN;
        }
    }
}