package ru.gc986.retrofitexample.m.net;

import ru.gc986.retrofitexample.m.models.MessageServer;
import ru.gc986.retrofitexample.m.models.User;
import ru.gc986.retrofitexample.m.net.common.BaseNetConstructor;
import rx.Observable;

/**
 * Created by gc986 on 02.05.17.
 */

public class RequestsImpl {

    public Observable<MessageServer> getRoot(){
        return new BaseNetConstructor().createService(Requests.class).web();
    }

    public Observable<User> getFio(String fName, String lName){
        return new BaseNetConstructor().createService(Requests.class).fio(fName, lName);
    }

}
