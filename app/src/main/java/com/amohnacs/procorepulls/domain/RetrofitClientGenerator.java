package com.amohnacs.procorepulls.domain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by adrianmohnacs on 4/17/18.
 */

/**
 * If we would like to expand on our application and include a number of other APIs or endpoints then
 * we need a long term way to do that.  By passing in the class of the Client we reduce boilerplate code
 * and can easily build variations of our Retrofit Clients.
 */
public class RetrofitClientGenerator {
    private static final String TAG = RetrofitClientGenerator.class.getSimpleName();

    // https://api.github.com/repos/HabitRPG/habitica/pulls
    private static final String GITHUB_REPOS_ENDPOINT = "https://api.github.com/";

    static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl(GITHUB_REPOS_ENDPOINT)
            .addConverterFactory(
                    buildGsonConverter()
            );

    public static <C> C generateClient(Class<C> clientsClass) {
        Retrofit retrofitClient = retrofitBuilder.client(
                buildLogginHttpBuilder().build()
        ).build();

        return retrofitClient.create(clientsClass);
    }

    private static OkHttpClient.Builder buildLogginHttpBuilder() {
        //logger
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().addInterceptor(interceptor);
    }

    private static GsonConverterFactory buildGsonConverter() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeNulls();

        Gson myGson = gsonBuilder
                .setDateFormat("yyyy-MM-dd")
                .create();


        return GsonConverterFactory.create(myGson);
    }
}
