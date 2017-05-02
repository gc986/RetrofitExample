package ru.gc986.retrofitexample.v.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.gc986.retrofitexample.R;
import ru.gc986.retrofitexample.m.models.MessageServer;
import ru.gc986.retrofitexample.m.models.User;
import ru.gc986.retrofitexample.m.net.RequestsImpl;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tvData) TextView tvData;

    RequestsImpl requests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        requests = new RequestsImpl();
    }

    @OnClick(R.id.btMessage)
    public void clickBtGetData(){

        requests.getRoot()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MessageServer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(MessageServer user) {
                        tvData.setText(user.getMessage());
                    }
                });
    }

    @OnClick(R.id.btUser)
    public void clickBtGetUser(){
        requests.getFio("NNN_","FFF_")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<User>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(User user) {
                        tvData.setText(user.getFirstName() + " " + user.getLastName());
                    }
                });
    }

}
