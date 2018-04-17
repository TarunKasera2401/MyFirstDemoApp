package com.androidtutorialpoint.retrofitandroid;

import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;

/**
 * Created by navneet on 4/6/16.
 */
public interface RetrofitArrayAPI {

    /*
     * Retrofit get annotation with our URL
     * And our method that will return us details of student.
    */
    @GET("api/RetrofitAndroidArrayResponse")
    Call<List<Student>> getStudentDetails();

    @POST("api/users")
    Call<CreateUserResponse> createUser(@Body CreateUser user);

    @PUT("api/users/2")
    Call<UserDataChangedResponse> updateUserData(@Body CreateUser user);
}
