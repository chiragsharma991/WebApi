package com.example.csuthar.webapi;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.csuthar.webapi.Https.GetRequest;
import com.example.csuthar.webapi.Https.customModel;
import com.example.csuthar.webapi.Utils.AppUrl;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity implements GetRequest.responser {

    @BindViews({R.id.edit_name, R.id.edit_id})
    List<EditText> editText;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.toolbar)
    android.support.v7.widget.Toolbar toolbar;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    @BindViews({R.id.male, R.id.female})
    List<RadioButton> radioButton;

    private Context context;
    private String TAG = this.getClass().getSimpleName();
    private GetRequest requestApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        context = this;
        setSupportActionBar(toolbar);


    }

    @OnClick({R.id.button})

    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.button:
                ApiCallBack(null, 0);
                break;
            default:
                break;

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Log.e(TAG, "onOptionsItemSelected: " );

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbarset,menu);
        return true;
    }

    private void ApiCallBack(customModel model, int id) {
        String url = null;


        switch (id) {

            case 0:
                url = AppUrl.AUTHLOGIN.toString();
                requestApi = new GetRequest(context, url, url, id); // api tag is asper url
                break;

            default:
                break;
        }


    }


    @Override
    public void onSucess(int id) {
        Log.e(TAG, "onSucess: " + id);
    }

    @Override
    public void onFailed(int id) {
        Log.e(TAG, "onFailed: " + id);

    }





}
