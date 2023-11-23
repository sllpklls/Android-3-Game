package com.example.btl.retrofit;

import com.example.btl.model.AdminModel;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface callApi {
    @POST("dangnhap.php")
    @FormUrlEncoded
     Observable<AdminModel> dangNhap(
            @Field("email") String email,
            @Field("pass") String pass
    );

    @POST("dangki.php")
    @FormUrlEncoded
    Observable<AdminModel> dangKi(
            @Field("email") String email,
            @Field("pass") String pass,
            @Field("username") String username,
            @Field("mobile") String mobile
    );
}
