package softdev1.medll;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Risikoanalyse extends AppCompatActivity {
    private int auswertung;
    private CheckBox diabChk, fehlChk, voraChk, glucChk, hydrChk;
    private Button ausBut;
    private EditText bmiText, alttext, gewText;
    private TextView risikoergText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risikoanalyse);
        diabChk = (CheckBox) findViewById(R.id.diaChk);
        fehlChk = (CheckBox) findViewById(R.id.fehlChk);
        voraChk = (CheckBox) findViewById(R.id.voraChk);
        glucChk = (CheckBox) findViewById(R.id.glucChk);
        hydrChk = (CheckBox) findViewById(R.id.hydrChk);

        ausBut = (Button) findViewById(R.id.auswButton);

        bmiText = (EditText) findViewById(R.id.bmieditText);
        alttext = (EditText) findViewById(R.id.altereditText);
        gewText = (EditText) findViewById(R.id.gebeditText);


        risikoergText = (TextView) findViewById(R.id.ergTextView);
        ausBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                auswerten();
            }

        });


    }

    public void auswerten() {

        int auswertung = 0;
        int z1 = Integer.parseInt(bmiText.getText().toString().trim());//variable für bmi
        int z2 = Integer.parseInt(alttext.getText().toString().trim());//variable für  alter
        int z3 = Integer.parseInt(gewText.getText().toString().trim()); //variable für geburtsgewicht


        if (diabChk.isChecked()) {
            auswertung++;
        }
        if (fehlChk.isChecked()) {
            auswertung++;
        }
        if (voraChk.isChecked()) {
            auswertung++;
        }
        if (glucChk.isChecked()) {
            auswertung++;
        }
        if (hydrChk.isChecked()) {
            auswertung++;
        }
        if (z1 > 27) {
            auswertung++;
        }
        if (z2 > 30) {
            auswertung++;
        }
        if (z3 > 4000) {
            auswertung++;

        }
        if(auswertung<=1){
            risikoergText.setText("geringes Risiko");
            risikoergText.setTextColor(Color.GREEN);
        }
        else if(auswertung==2){
            risikoergText.setText("mittleres Risiko");
            risikoergText.setTextColor(Color.YELLOW);
        }
        else{
            risikoergText.setText("hohes Risiko");
            risikoergText.setTextColor(Color.RED);
        }




    }
}
