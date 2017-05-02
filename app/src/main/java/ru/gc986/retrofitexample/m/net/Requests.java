package ru.gc986.retrofitexample.m.net;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import ru.gc986.retrofitexample.m.models.MessageServer;
import ru.gc986.retrofitexample.m.models.User;
import rx.Observable;

/**
 * Created by gc986 on 02.05.17.
 */

public interface Requests {

    @GET("/")
    Observable<MessageServer> web();

    @GET("/fio")
    Observable<User> fio(@Query("firstName") String fName, @Query("lastName") String lName);

}
