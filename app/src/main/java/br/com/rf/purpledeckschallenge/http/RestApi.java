package br.com.rf.purpledeckschallenge.http;

import br.com.rf.purpledeckschallenge.model.FlickrPhoto;
import br.com.rf.purpledeckschallenge.model.WeatherApiWrapper;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by rodrigoferreira on 3/7/16.
 */
public interface RestApi {

    String WEATHER_API_END_POINT = "http://api.openweathermap.org/data";
    String PHOTO_SEARCH_API_END_POINT = "https://api.flickr.com/services";

    @GET("/rest/?method=flickr.photos.search&api_key=34fc09eb49b0653e3bf5c3f7d94f44fa&format=json&nojsoncallback=1")
    void searchPhotoByTag(@Query("tags") String tag, Callback<FlickrPhoto> callback);

    @GET("/2.5/weather?units=metric&appid=44db6a862fba0b067b1930da0d769e98&")
    WeatherApiWrapper getWeatherByCity(@Query("q") String city);
}