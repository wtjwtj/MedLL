package softdev1.medll;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;


public class ToolsActivity extends AppCompatActivity {
GridView gridView;
    private double bmiErg;
    private Button mToolsButton1;

     static final String[]tools = new String[]{
        "BMI-Rechner","Einheiten-Rechner","Risikoanalyse", "Stopuhr",

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        gridView = (GridView) findViewById(R.id.toolsGrid);
        ArrayAdapter<String> toolsAdapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,tools);
        gridView.setAdapter(toolsAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                switch (position) {
                    case 0:
                        bmiRechner();
                        break;


                    case 1:
                        Toast.makeText(ToolsActivity.this, "Wrong Input", Toast.LENGTH_LONG).show();
                        break;
                    default:
                        Toast.makeText(ToolsActivity.this, "right Input", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });

    }

    void bmiRechner(){



        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.dialog_bmi, null));

        builder.setTitle("");
        builder.setMessage("");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {

AlertDialog aDialog = (AlertDialog)dialog;
                EditText item_gew = (EditText) aDialog.findViewById(R.id.bmi_gewicht);
                EditText item_gro = (EditText) aDialog.findViewById(R.id.bmi_groesse);
                //edittext to string
                String bmiGew = item_gew.getText().toString();
                String bmiGro = item_gro.getText().toString();
                //parse string to int and double
                int bmiGewi = Integer.parseInt(bmiGew);
                double bmiGroe = Double.parseDouble(bmiGro);

                // Do something with value!
                bmiErg = bmiGewi / (bmiGroe*bmiGroe);

                Toast.makeText(ToolsActivity.this, "Der BMI beträgt " + bmiErg, Toast.LENGTH_LONG).show();


            }
        });
        builder.show();
    }




}




