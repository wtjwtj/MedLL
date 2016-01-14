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
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LeitlinieMain extends AppCompatActivity {
    private Button mToolsButton;
    public static final String TAG = LeitlinieMain.class.getSimpleName();
    private Leitlinie mLeitlinie;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    private XmlPullParserFactory xmlFactoryObject;
    private XmlPullParser myparser;


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
                    catch(Exception e){
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

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.expandableListView);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        // Listview Group click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();

            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
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
        dialog.show(getFragmentManager(), "error_dialog");
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

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();


        // Adding child data
        JSONData jsonData = new JSONData();
        jsonData.readXML();
        ArrayList jsonDataHeader = new ArrayList<String>();


        listDataHeader.add("Top 250");
        listDataHeader.add("Now Showing");
        //listDataHeader.add(jsonData.getKapitelName().get(0));

        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("The Shawshank Redemption");
        top250.add("The Godfather");
        top250.add("The Godfather: Part II");
        top250.add("Pulp Fiction");
        top250.add("The Good, the Bad and the Ugly");
        top250.add("The Dark Knight");
        top250.add("12 Angry Men");

        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("The Conjuring");
        nowShowing.add("Despicable Me 2");
        nowShowing.add("Turbo");
        nowShowing.add("Grown Ups 2");
        nowShowing.add("Red 2");
        nowShowing.add("The Wolverine");

        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("2 Guns");
        comingSoon.add("The Smurfs 2");
        comingSoon.add("The Spectacular Now");
        comingSoon.add("The Canyons");
        comingSoon.add("Europa Report");

        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);
    }

}
