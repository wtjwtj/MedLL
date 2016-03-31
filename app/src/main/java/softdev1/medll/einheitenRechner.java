package softdev1.medll;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

import softdev1.medll.R;

public class einheitenRechner extends AppCompatActivity {
    private Spinner spinner1;
    private TextView erg;
    private EditText rech;
    private Button buttonH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_einheiten_rechner);


        rech = (EditText) findViewById(R.id.rechn);
        erg = (TextView) findViewById(R.id.erg);
        
        buttonH = (Button)findViewById(R.id.Button01);
        buttonH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentH = new Intent(einheitenRechner.this, LeitlinieMain.class);
                startActivity(intentH);
            }
        });

        Button button = (Button) findViewById(R.id.button);
//berechnen button enablen
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double str = Double.parseDouble(rech.getText().toString().trim());
                Spinner mySpinner = (Spinner) findViewById(R.id.spinner);
                String text = mySpinner.getSelectedItem().toString();
                switch (text) {
                    case "g to kg":
                        str = str / 1000;
                        erg.setText("" + str);
                        break;
                    case "g to mg":
                        str = str * 1000;
                        erg.setText("" + str);
                        break;
                    case "kg to g":
                        str = str * 1000;
                        erg.setText("" + str);
                        break;
                    case "kg to mg":
                        str = str * 1000000;
                        erg.setText("" + str);
                        break;
                    case "mg to g":
                        str = str / 1000;
                        erg.setText("" + str);
                        break;
                    case "mg to kg":
                        str = str / 1000000;
                        erg.setText("" + str);
                        break;
                }


            }
        });


    }
}

