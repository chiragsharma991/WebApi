package com.example.csuthar.webapi;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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


public class MainActivity extends ImagePicker implements GetRequest.responser {

    @BindViews({R.id.edit_name, R.id.edit_id})
    List<EditText> editText;
    @BindViews({R.id.button,R.id.img_pick})
    List<Button> button;
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

    @OnClick({R.id.button,R.id.img_pick})

    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.button:
                ApiCallBack(null, 0);
                break;
            case R.id.img_pick:
                imagePicker();
                break;
            default:
                break;

        }

    }

    private void imagePicker() {

        final CharSequence[] items = { "Take Photo", "Choose from Library",
                "Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result=checkPermission(MainActivity.this);
                Log.e(TAG, "onClick: result"+result );
                if (items[item].equals("Take Photo")) {
                    userChoosenTask="Take Photo";
                    if(result)
                        cameraIntent();
                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask="Choose from Library";
                    if(result)
                        galleryIntent();
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();

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
