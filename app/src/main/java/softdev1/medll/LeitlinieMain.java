package softdev1.medll;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class LeitlinieMain extends AppCompatActivity {
    private Button mToolsButton;
    public static final String TAG = LeitlinieMain.class.getSimpleName();
    private Leitlinie mLeitlinie;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leitlinie_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String leitlinieUrl = "http://10.0.3.2/test.json";
        if (isNetworkAvailable()){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(leitlinieUrl).build();
        Call call = client.newCall(request);
        //execute by putting into queue
        //asynchronous task
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.v(TAG, "FAILURE");

            }

            @Override
            public void onResponse(Response response) throws IOException {

                try {
                    String jsonData = response.body().string();
                    Log.v(TAG,jsonData );
                    if (response.isSuccessful())  {
                        mLeitlinie = parseLeitlinieData(jsonData);

                    } else {
                        alertUserAboutError();
                    }
                } catch (IOException e) {
                    Log.e(TAG, "Exception caught", e);
                }
                catch(JSONException e){
                    Log.e(TAG, "Exception caught", e);
                }

            }
        });}
        else {
            Toast.makeText(this,"Network is unavailable",Toast.LENGTH_LONG).show();
        }




        mToolsButton = (Button) findViewById(R.id.ToolsButton);

        mToolsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LeitlinieMain.this, ToolsActivity.class);
                startActivity(intent);
            }
        });

    }




    private Leitlinie parseLeitlinieData(String jsonData) throws Exception{

        Leitlinie leitlinie = new Leitlinie();


        leitlinie.setMeta(getMetaData(jsonData));
        leitlinie.setAutorj(getAutorjData(jsonData));



                return leitlinie;

    }

    private Meta[] getMetaData(String jsonData) throws JSONException {
        JSONObject leitlinie = new JSONObject(jsonData);

        JSONObject meta =  leitlinie.getJSONObject("meta");
        JSONArray herausgeber = meta.getJSONArray("herausgeber");

        Meta[] metas = new Meta[herausgeber.length()];

        for(int i =0; i< herausgeber.length(); i++){
            JSONObject jsonMeta = herausgeber.getJSONObject(i);
            Meta met = new Meta();

            met.setName(jsonMeta.getString("name"));
            met.setInstitut(jsonMeta.getString("institut"));

            metas[i] = met;
        }
        return metas;
    }


    private Autorj[] getAutorjData(String jsonData) throws Exception {

        JSONObject leitlinie = new JSONObject(jsonData);

        JSONObject autorj = leitlinie.getJSONObject("autorj");
        JSONArray autor = autorj.getJSONArray("autor");

        Autorj[] autorjs = new Autorj[autor.length()];

        for (int i=0 ;i<autor.length();i++){
            JSONObject jsonAutorj = autor.getJSONObject(i);
            Autorj aut  = new Autorj();

            aut.setName(jsonAutorj.getString("name"));
            aut.setInstitut(jsonAutorj.getString("institut"));

            autorjs[i] = aut;
        }
    return autorjs;
    }


/*
    private Meta getTitelData(String jsonData) throws JSONException {
        JSONObject jData = new JSONObject(jsonData);
        String timezone = jData.getString("timezone");
                Log.i(TAG, "from json" + timezone);

        JSONObject autor = jData.getJSONObject("autor");

        Autor leitData = new Autor();
        leitData.setName(autor.getString("name"));
        leitData.setInstitut(autor.getString("insitut"));

        return leitData;
    }*/

    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if(networkInfo!=null&&networkInfo.isConnected())

        {
            isAvailable=true;
        }
        return isAvailable;
    }

    private void alertUserAboutError() {
    AlertDialogFragment dialog = new AlertDialogFragment();
        dialog.show(getFragmentManager(),"error_dialog");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_leitlinie_main, menu);
        setTitle("Leitlinie");
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
