package ru.gc986.retrofitexample.m.net.common;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.gc986.retrofitexample.R;

/** Базовый запрос к серверу с аутентификацией на сервере
 * @author Язовцев Игорь Алексеевич
 * @version 1
 * @since 2016
 * Created by gc986 on 25.08.16.
 */

public class BaseNetConstructor {

    private static String server = "http://192.168.168.53:4200";

    private OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
//    private OkHttpClient.Builder httpClient = new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor(message ->  {
//        Log.i("MyApp","Body request : " + message);}).setLevel(HttpLoggingInterceptor.Level.BODY));

    public <S> S createService(Class<S> serviceClass) {
        return new Retrofit.Builder()
                .baseUrl(server)
                .addConverterFactory(GsonConverterFactory.create())
                .client(
                        httpClient.addInterceptor(chain ->  {
                            return  chain.proceed(chain.request().newBuilder()
                                    .header("Accept", "application/json")
                                    .method(chain.request().method(), chain.request().body())
                                    .build());
                        }
                        ).build()
                )
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(serviceClass);
    }

}
