package com.androidtutorialpoint.retrofitandroid;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;


public class MainActivity extends AppCompatActivity {

//    String url = "http://www.androidtutorialpoint.com/";
    String url = "https://reqres.in/";
    TextView text_id_1, text_name_1, text_marks_1 ;
    TextView text_id_2, text_name_2, text_marks_2 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        text_id_1 = (TextView) findViewById(R.id.text_id_1);
        text_name_1 = (TextView) findViewById(R.id.text_name_1);
        text_marks_1 = (TextView) findViewById(R.id.text_marks_1);

        text_id_2 = (TextView) findViewById(R.id.text_id_2);
        text_name_2 = (TextView) findViewById(R.id.text_name_2);
        text_marks_2 = (TextView) findViewById(R.id.text_marks_2);

        Button ButtonArray= (Button) findViewById(R.id.RetrofitArray);
        Button ButtonObject= (Button) findViewById(R.id.RetrofitObject);

        ButtonArray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View VisibleArray = findViewById(R.id.RetrofitArray);
                VisibleArray.setVisibility(View.GONE);
                View VisibleObject = findViewById(R.id.RetrofitObject);
                VisibleObject.setVisibility(View.GONE);
                getRetrofitArray();
            }
        });

        ButtonObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View VisibleArray = findViewById(R.id.RetrofitArray);
                VisibleArray.setVisibility(View.GONE);
                View VisibleObject = findViewById(R.id.RetrofitObject);
                VisibleObject.setVisibility(View.GONE);
                getRetrofitObject();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    void getRetrofitArray() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitArrayAPI service = retrofit.create(RetrofitArrayAPI.class);

        CreateUser createUser = new CreateUser();
        createUser.setName("Vinod Kirte");
        createUser.setJob("Software Engineer");
//        Call<List<Student>> call = service.getStudentDetails();
        Call<UserDataChangedResponse> call = service.updateUserData(createUser);

        call.enqueue(new Callback<UserDataChangedResponse>() {
            @Override
            public void onResponse(Response<UserDataChangedResponse> response, Retrofit retrofit) {

                try {

                    UserDataChangedResponse CreateUserData = response.body();


                    String getNValue = CreateUserData.getName();
                    System.out.println("CreateUserData "+getNValue);

                    text_id_1.setText(CreateUserData.getName()+" "+ CreateUserData.getUpdatedAt() );

//                    for (int i = 0; i < StudentData.size(); i++) {
//
//                        if (i == 0) {
//                            text_id_1.setText("StudentId  :  " + StudentData.get(i).getStudentId());
//                            text_name_1.setText("StudentName  :  " + StudentData.get(i).getStudentName());
//                            text_marks_1.setText("StudentMarks  : " + StudentData.get(i).getStudentMarks());
//                        } else if (i == 1) {
//                            text_id_2.setText("StudentId  :  " + StudentData.get(i).getStudentId());
//                            text_name_2.setText("StudentName  :  " + StudentData.get(i).getStudentName());
//                            text_marks_2.setText("StudentMarks  : " + StudentData.get(i).getStudentMarks());
//                        }
//                    }


                } catch (Exception e) {
                    Log.d("onResponse", "There is an error");
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("onFailure", t.toString());
            }
        });
    }

    void getRetrofitObject() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitObjectAPI service = retrofit.create(RetrofitObjectAPI.class);

        Call<Student> call = service.getStudentDetails();

        call.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Response<Student> response, Retrofit retrofit) {
                try {
                    text_id_1.setText("StudentId  :  " + response.body().getStudentId());
                    text_name_1.setText("StudentName  :  " + response.body().getStudentName());
                    text_marks_1.setText("StudentMarks  : " + response.body().getStudentMarks());

                } catch (Exception e) {
                    Log.d("onResponse", "There is an error");
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("onFailure", t.toString());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
