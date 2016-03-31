package softdev1.medll;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class LeitlinieMain extends AppCompatActivity {
    private Button mToolsButton;
    private Button mHomebutton;
    public static final String TAG = LeitlinieMain.class.getSimpleName();
    private Leitlinie mLeitlinie;
    // ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    private XmlPullParserFactory xmlFactoryObject;
    private XmlPullParser myparser;
    private String url = "http://medll.bplaced.net/leitlinie/leitlinie1/leitlinie2.json";
    ProgressDialog PD;
    List<NLevelItem> list;
    ListView listView;
    JSONObject leitlinienJsonObject;
String jsonData = "{ \"leitlinie\": { \"meta\":[{ \"titel\": \"Gestationsdiabetes mellitus (GDM)\" }, { \"awmf\": \"057/008\" }, { \"version\": \"0.1\" }, { \"version-ll-json\": \"0.1\"\t}, { \"stand\": \"2011-08\" }, { \"erstellungsdatum\": \"2001\" }, { \"naechsteUeberpruefung\": \"2016-06\" }, { \"klasse\": \"S3\" }, { \"logo\":\t\"platzhalter\" }, { \"hinweis\": \"Die Leitlinien der Wissenschaftlichen Medizinischen Fachgesellschaften sind systematisch entwickelte Hilfen für Ärzte zur Entscheidungsfindung in spezifischen Situationen. Sie beruhen auf aktuellen wissenschaftlichen Erkenntnissen und in der Praxis bewährten Verfahren und sorgen für mehr Sicherheit in der Medizin, sollen aber auch ökonomische Aspekte berücksichtigen. Die Leitlinien sind für Ärzte rechtlich nicht bindend und haben daher weder haftungsbegründende noch haftungsbefreiende Wirkung. Die AWMF erfasst und publiziert die Leitlinien der Fachgesellschaften mit größtmöglicher Sorgfalt - dennoch kann die AWMF für die Richtigkeit des Inhalts keine Verantwortung übernehmen. Insbesondere für Dosierungsangaben sind stets die Angaben der Hersteller zu beachten!\" }, { \"copyright\": \"(c)Deutsche DiabetesGesellschaft\" }, { \"untertitel\": \"Evidenzbasierte Leitlinie zu Diagnostik, Therapie u. Nachsorge der Deutschen Diabetes-Gesellschaft (DDG) und der Deutschen Gesellschaft für Gynäkologie und Geburtshilfe (DGGG)\" }, {\t\"autor\": [ { \"name\": \"H.Kleinwechter\", \"institut\": \"diabetologikum kiel, Diabetes-Schwerpunktpraxis und Schulungszentrum, Kiel\" }, { \"name\": \"U.Schäfer-Graf\", \"institut\": \"Berliner Diabeteszentrum für Schwangere, Klinik für Gynäkologie und Geburtshilfe, St.Joseph Krankenhaus, Berlin\" }, { \"name\": \"C.Bührer\", \"institut\": \"Klinik für Neonatologie, Charité Universitätsmedizin, Berlin\" }, { \"name\": \"I.Hoesli\", \"institut\": \"Frauenklinik, Geburtshilfe und Schwangerschaftsmedizin, Universitätsspital Basel, Basel/CH\" }, { \"name\": \"F.Kainer\", \"institut\": \"Klinik und Poliklinik für Frauenheilkunde und Geburtshilfe, Perinatalzentrum Klinikum Innenstadt LMU, München\" }, { \"name\": \"A.Kautzky-Willer\", \"institut\": \"Medizinische Universitätsklinik Wien, Klinik für Innere Medizin III, Abteilung für Endokrinologie u. Stoffwechsel, Gender Medicine Unit, Wien/A\" }, { \"name\": \"B.Pawlowski\", \"institut\": \"Klinik für Stoffwechselkrankheiten, Universitätsklinikum Düsseldorf und Deutsches Diabetes-Zentrum, Düsseldorf\" }, { \"name\": \"K.Schnuck\", \"institut\": \"Klinik für Kinder- und Jugendmedizin, Krankenhaus im Friedrichshain, Berlin\" }, { \"name\": \"T.Somville\", \"institut\": \"Klinik für Geburtshilfe und Pränatalmedizin, Universitätsklinikum Hamburg-Eppendorf, Hamburg\" }, { \"name\": \"M.Sorger\", \"institut\": \"Medizinische Poliklinik, Universitätsklinikum Bonn, Bonn\" } ]}, { \"weitereInfos\": [ { \"text\":\"Erstveröffentlichung 08/2011\" }, { \"text\":\"Geplante Überarbeitung 08/2016\" }, { \"text\":\"Korrespondenzadressen für die Deutsche Diabetes-Gesellschaft\n" +
        " Dr.med. Helmut Kleinwechter, Sprecher der Expertengruppe &quot;Diabetes und Schwangerschaft&quot; der DDG diabetologikum kiel, Diabetes-Schwerpunktpraxis und Schulungszentrum, Alter Markt 11 u. 14, 24103 Kiel\n" +
        " Tel.: 0431/95807, Fax: 0431/95805, E-mail: arzt@diabetologikum-kiel.de\" }, { \"text\":\"Prof. Dr.med. Monika Kellerer, Leitlinienbeauftragte des Vorstands der DDG\n" +
        " Zentrum für Innere Medizin I, Marienhospital Stuttgart, Boheimstr.37, 70199 Stuttgart\n" +
        "Tel.: 0711/64892102, Fax: 0711/64892119, E-Mail: monikakellerer@vinzenz.de\" }, { \"text\":\"Korrespondenzadresse für die Deutsche Gesellschaft für Gynäkologie u. Geburtshilfe\n" +
        "PD Dr.med. Ute Schäfer-Graf, Sprecherin der Expertengruppe Diabetes und Schwangerschaft der DGGG\n" +
        "Berliner Diabeteszentrum für Schwangere, Klinik für Gynäkologie und Geburtshilfe, St.Joseph Krankenhaus, Wüsthoffstraße 15, 12101 Berlin\n" +
        "Tel.: 030/7882-4214/2236, Fax: 030/7882-2766, E-mail: ute.schaefer-graf@sjk.de\" } ]}], \"inhalt\":[ { \"vorbemerkung\":[ { \"text\":\"Diese interdisziplinäre, evidenzbasierte S3-Leitlinie (AWMF-Leitlinie 057/008) ist fachgesellschaftsübergreifend und bearbeitet nur den Gestationsdiabetes mellitus (GDM, ICD-10: O24.4G), aber nicht den präkonzeptionell bekannten Diabetes mellitus (Typ-1, Typ-2, spezifische Formen). Die Leitlinie ersetzt die Empfehlungen zur Diagnostik und Therapie des Gestationsdiabetes aus dem Jahr 2001 (AG Diabetes und Schwangerschaft der DDG 2001 EK IV). Diese Leitlinie wird durch eine Kurzfassung als Praxisleitlinie der DDG/DGGG und eine Laienversion für Schwangere und Interessierte ergänzt.\" }, { \"text\":\"Für zusätzliche Informationen zum Thema Diabetes und Schwangerschaft wird auf folgende Leitlinien und Empfehlungen hingewiesen\" }, { \"text\":\"\tEvidenzbasierte Leitlinie Diabetes und Schwangerschaft\n" +
        "AWMF-Leitlinie 057/023\n" +
        "http://www.deutsche-diabetesgesellschaft.de/fileadmin/Redakteur/Leitlinien/Evidenzbasierte_Leitlinien/EBL_Schwangerschaft_2008.pdf\" }, { \"text\":\"\tPraxisleitlinie Diabetes und Schwangerschaft\n" +
        "ttp://www.deutsche-diabetesgesellschaft.de/fileadmin/Redakteur/Leitlinien/Praxisleitlinien/PL_DDG2011_Diab_und_Schwangerschaft.pdf\" }]}, {\"kapitel 1\":[ { \"ueberschrift\":\"1 Gesundheitsziele\" }, { \"text\":\"Die International Diabetes Federation (IDF) und die WHO-Europa haben 1989 und 1999 Gesundheitsziele für Schwangerschaften bei Frauen mit Diabetes formuliert (WHO u. IDF Europe 1990 EK IV, WHO Europa 1999 EK IV). Danach sollen Verlauf und Ergebnisse von Schwangerschaften bei Frauen mit Diabetes von 1999 bis zum Jahr 2020 um ein Drittel verbessert werden. Die IDF legte 2009 erstmals eine um den Gestationsdiabetes erweiterte globale Leitlinie zu den Problemen von Diabetes und Schwangerschaft vor (IDF 2009 EK IV). Die IDF stellt fest, dass der GDM mit Komplikationen in der Schwangerschaft assoziiert ist, die durch rechtzeitige Diagnostik und intensive Behandlung abgemildert werden können. Dies bestätigt auch eine systematische Übersicht und Metaanalyse: Therapeutisch effektiv sind sowohl eine Blutglukosesenkende als auch eine spezialisierte geburtsmedizinische Betreuung (Horvath 2010 EK Ia).\" }, { \"text\":\"(härtegrad B) Die Vorgaben der Gesundheitsziele sollten anhand von Qualitätsindikatoren überprüft werden (siehe Seite 64-65, 9 Qualitätssicherung)\" }]}, {\"kapitel 2\":[ {\"ueberschrift\":\"2 Definition\" }, { \"text\": \"Gestationsdiabetes mellitus (GDM, ICD-10: O24.4G) ist definiert\" }, { \"text\": \"als eine Glukosetoleranzstörung, die erstmals in der Schwangerschaft mit einem 75-g oralen Glukosetoleranztest (oGTT) unter standardisierten Bedingungen und qualitätsgesicherter Glukosemessung aus venösem Plasma diagnostiziert wird. Die Diagnose ist bereits mit einem erhöhten Glukosewert möglich.\" }, { \"text\": \"Die diagnostischen Grenzwerte beruhen auf internationaler Konsensbildung durch Experten (IADPSG Consensus Panel 2010 EK IV). Der Experten-Konsensbildung wurden die Ergebnisse einer epidemiologischen Untersuchung mit mütterlichen und neonatalen, klinisch relevanten Endpunkten zugrunde gelegt (HAPO Cooperative Research Group 2008 EK IIb, 2009 EK IIb, 2010 IIb). Alle in der Leitlinie beschriebenen Blutglukosewerte beziehen sich auf Blutplasma.\" }, { \"text\":  \"Die Übergänge zwischen sog. normaler Glukosetoleranz in der Schwangerschaft und Gestationsdiabetes sind fließend, ein Schwellenwert existiert nicht. Nach internationalem Konsens werden ein bisher unbekannter, manifester Diabetes und eine Hyperglykämie unterschieden, die unterhalb dieser Grenzen liegt und als Gestationsdiabetes, wie oben beschrieben, klassifiziert wird (IADPSG Consensus Panel 2010 EK IV, American Diabetes Association 2011 EK IV). Die Definition des manifesten Diabetes entspricht der außerhalb einer Schwangerschaft. Damit fallen ein manifester Typ-1- oder Typ-2-Diabetes mellitus oder spezifische Diabetesformen, die erstmals während der Schwangerschaft diagnostiziert werden, nicht mehr unter die Diagnoseklasse des Gestationsdiabetes.\" }, { \"text\": \"(härtegrad A) Das in dieser Leitlinie empfohlene Vorgehen nach IADPSG Consensus Panel soll einheitlich angewendet werden\" }, {\"kapitel 2.1\"\t:[ { \"ueberschrift\":\"2.1 Pathopsychologie\" }, { \"text\": \"Die Pathophysiologie des Gestationsdiabetes entspricht zu einem großen Teil der des Typ-2- Diabetes. Auf der Basis einer genetischen Prädisposition spielen vor allem Übergewicht und der Lebensstil (Ernährung, Bewegung) der Frauen eine große Rolle. Die in der zweiten Schwangerschaftshälfte physiologisch einsetzende Insulinresistenz führt im Falle eines Gestationsdiabetes bei gleichzeitig vorliegendem (zumindest relativem) Insulinsekretionsdefekt zur Hyperglykämie in der Gravidität. Neben den hormonellen Veränderungen in der Gravidität dürften auch eine veränderte Freisetzung von Adipokinen und Zytokinen aus dem Fettgewebe und der Plazenta eine Rolle spielen.\"}, { \"text\": \"Die zugrundeliegenden pathophysiologischen Mechanismen sind in Analogie zum heterogenen Erscheinungsbild des GDM unterschiedlich und bislang â€“ wie bei Diabetes im Allgemeinen - nicht vollständig geklärt (Metzger 2007 EK IV). Kausal dürfte beim klassischen Bild des GDM eine chronische, d.h. bereits präkonzeptionell bestehende, Herabsetzung der Insulinsensitivität bestehen, die zusätzlich durch die ab der 20. Schwangerschaftswoche zunehmende physiologische Insulinresistenz verstärkt wird und durch die endogene Insulinsekretion nur unzureichend kompensiert werden kann (=relativer Insulinmangel [Kautzky 1997 EK IIa]). Für die Insulinresistenz ebenso wie für die Insulinsekretionsstörung liegt teilweise eine genetische Prädisposition vor, wobei aber die Ausprägung durch Umweltfaktoren, den Lebensstil (hochkalorische Ernährung fast food und Bewegungsmangel), insbesondere Übergewicht, wesentlich beeinflusst wird. Frauen, die einen Gestationsdiabetes entwickeln, weisen meist die gleichen Risikofaktoren wie Frauen mit einem Typ-2-Diabetes auf. Ebenso wurden eine ungünstige Veränderung im Sekretionsmuster von Adipokinen, insbesondere eine Verminderung von Adiponektin und eine Zunahme von Leptin, sowie ein Anstieg von TNF , beim GDM beschrieben (Kautzky 2004 EK III, Catalano 2008 EK III). Genomweite Assoziationsstudien weisen beim GDM auf die gleichen Kandidatgene wie für den Typ-2-Diabetes hin (Watanabe 2007 EK III).\" }, { \"text\": \"Aufgrund dieser typischen metabolischen Veränderungen und dem Vorliegen von Übergewicht bei der Großzahl der betroffenen Schwangeren wird der klassische GDM als eine Form des Prä-Typ-2- Diabetes angesehen, welcher durch eine erhebliche Insulinresistenz und eine gestörte Beta- Zellfunktion (Frühphasen-Sekretionsdefekt) charakterisiert ist (Kim 2002 EK III, Kautzky 2005 EK IIb). GDM kann daher meistens als eine chronische Funktionsstörung beschrieben werden, gekennzeichnet durch eine Insulinresistenz mit abfallender ß-Zell-Kompensation, die nur durch ein Glukosescreening als Routine-Maßnahme in der Schwangerschaft entdeckt wird (Xiang 2010 EK IIa).\" } ]}]}, {\"kapitel 3\"\t:[ { \"ueberschrift\": \"3 Epidemologie\" }, { \"text\": \"Die Häufigkeit des GDM ist von folgenden Einflussfaktoren abhängig:\n" +
        " \tder epidemiologischen Untersuchung verschiedener Bevölkerungsgruppen (z.B. Kaukasierinnen, Asiatinnen, Lateinamerikanerinnen [sog. Hispanierinnen], Pima- Indianerinnen),\n" +
        "der Untersuchung von Volksgruppen unterschiedlicher genetischer Belastung mit Typ-2- Diabetes und damit unterschiedlichem Prä-Test-Risiko (z.B. Europäer vs. Inder),\n" +
        "der Untersuchung nur von Risikogruppen (z.B. Adipöse, ältere Schwangere) oder allen Schwangeren einer Population,\n" +
        "dem Zeitpunkt der Untersuchung (Frühschwangerschaft, 24-28 SSW, nach 32 SSW),\n" +
        "der Untersuchung mittels vorgeschalteter Screeningverfahren (Uringlukose, Gelegenheitsglukose, Nüchternglukose, 50-g GCT mit unterschiedlichen Screening- Schwellen) oder ohne vorgeschaltetes Screening,\n" +
        "dem diagnostischen Testverfahren (Menge der Glukose: 50 g [ehemalige DDR, Dänemark], 75 g [Europa], 100 g oGTT [Nordamerika]), der Anzahl der erhobenen Messwerte (1, 2, 3, 4 oder mehr), der Anzahl der erreichten oder überschrittenen Grenzwerte (1 oder 2), der Qualität der Glukosemessung (unzureichender vs. adäquater Laborstandard), der Verwendung des Blutmediums (venöses Plasma vs. kapilläres Vollblut), der präanalytischen Verarbeitung abgenommener Blutproben (Glykolyse unzentrifugierter venöser Vollblut- Blutproben) und der enzymatischen Methode der Blutglukosemessung,\n" +
        "den von Arbeitsgruppen und Fachgesellschaften durch Expertenmeinung festgelegten diagnostischen Grenzwerten.\" }, { \"text\": \"Nach Literaturangaben der letzten 20 Jahre variierten die Prävalenzen des GDM zwischen 0,6% und 22 % (King 1998 EK IV, Murgia 2006 EK IV), vereinzelt noch niedriger oder auch höher. Je häufiger nach einem GDM bei Schwangeren gesucht wird, je höher die Diabetesrisiken allgemein sind und je niedriger die diagnostischen Grenzen liegen, umso häufiger wird ein GDM diagnostiziert.\" }, { \"text\":\"Im Jahr 2010 wurden in Deutschland rund 650.000 Neugeborene von der Perinatalstatistik beim Institut für angewandte Qualitätsförderung und Forschung im Gesundheitswesen GmbH (AQUA) erfasst. Das AQUA-Institut hat die Bundesgeschäftsstelle für Qualitätssicherung (BQS) ab 1.1.2010 bei der Bundesauswertung Geburtshilfe abgelöst. Die ausgewerteten Daten repräsentieren 99,2% der erwarteten Geburten aus 821 Kliniken. Bei den Müttern lag in 23.872 Fällen (3,7%) ein Gestationsdiabetes vor (AQUA 2011 IIb). Der seit 2002 dokumentierte, ständige Anstieg der Fälle von GDM (2007 bis 2008: +25%) setzt sich nach vorübergehender Stagnation 2009 aktuell fort (Abb.1+2).\" }, { \"grafik\":\"http://medll.bplaced.net/leitlinien/grafik/GDM20022010.png file:///storage/emulated/0/GDM20022010.png\t Abbildung Relative Häufigkeiten des Gestationsdiabetes in Deutschland 2002-2010\" }, { \"grafik\":\"http://medll.bplaced.net/leitlinien/grafik/GDMAbs20022010.png file:///storage/emulated/0/GDMAbs20022010.png\t Abbildung Absolute Häufigkeiten des Gestationsdiabetes in Deutschland 2002-2010\" }, { \"text\":\"Zum Zeitpunkt der Geburt ist die GDM-Prävalenz in Deutschland von 2002 bis 2010 relativ um das 2,52-fache angestiegen. Nach den Festlegungen des IADPSG Consensus Panel beträgt die um manifeste Diabetesfälle bereinigte Netto-GDM-Prävalenz im HAPO-Studienkollektiv nach epidemiologischen Maßstäben und methodisch optimierter Blutglukosemessung 16,1% (IADPSG Consensus Panel 2010 EK IV, Nesbitt 2006 EK IIb, Bruns 2009 EK IV).\" }, { \"text\": \"(härtegrad B) Bei der regionalen Versorgung sollte die erhobene GDM-Prävalenz beachtet und der Versorgungsgrad angepasst werden.\" }]}, {\"glossar\":[ {\"begriff\": [ { \"abk\":\"ACE\" }, { \"Vollbegriff\":\"Angiotensin Converting Enzyme\" } ]}, { \"begriff\":[ { \"abk\": \"ACOG\" }, { \"Vollbegriff\":\"American College of Obstetricians and Gynecologists\" } ]} ], \"literatur\":[{ \"quelle\": \"ACOG Practice Bulletin. Clinical management guidelines for obstetrician-gynecologists. Number 30, September 2001 (replaces Technical Bulletin Number 200, December 1994). Gestational diabetes. Obstet Gynecol 2001;98: 525-538.\" }, { \"quelle\":\"Agarwal M, Dhatt G, Punnose J. Gestational diabetes: utility of fasting plasma glucose as a screening test depends on the diagnostic criteria. Diabet Med 2006;23:1319-1326.\" }\t],\" kapitel\":[ { \"ueberschrift\":\"12 flussdiagramm: Diagnostik der Hyperglykämie in der Schwangerschaft\" }, { \"grafik\": \"platzhalter\" } ],\" kapitel\":[ { \"ueberschrift\":\"13 Diabetes-Risikotabelle\" }, { \"text\":\"(irgendwie eingerahmt) Alter > 45 Jahre\n" +
        "BMI > 30 kg/mÂ² präkonzeptionell\n" +
        " Körperliche Inaktivität\n" +
        " Eltern oder Geschwister mit Diabetes\n" +
        " Angehörige einer ethnischen Risikopopulation (z.B. Asiatinnen, Lateinamerikanerinnen)\n" +
        " Geburt eines Kindes >4500 g\n" +
        " GDM in der Vorgeschichte\n" +
        " Arterielle Hypertonie (Blutdruck >140/90 mmHg) oder Einnahme von Medikamenten zur Therapie der arteriellen Hypertonie\n" +
        " Dyslipidämie präkonzeptionell (HDL <35 mg/ld [0,9 mmol/l] und/oder Triglyceride >250 mg/dl [2,82 mmol/l])\n" +
        " Polyzystisches Ovarsyndrom\n" +
        " Prädiabetes (IGT/IFG/HbA1c >5,7%) bei früherem Test (unabhängig von früherem GDM)\n" +
        " Andere klinische Zustände, die mit Insulinresistenz assoziiert sind (z.B. Acanthosis nigricans)\n" +
        " Vorgeschichte mit KHK, pAVK, zerebral-arterieller Durchblutungsstörung\n" +
        " Einnahme kontrainsulinärer Medikation (z.B. Glukokortikoide)\" } ] }] }}";    /* * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leitlinie_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String leitlinieUrl = "http://medll.bplaced.net/leitlinien/leitlinie1/leitlinie2.json";
        if (isNetworkAvailable()) {
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

                        jsonData = response.body().string();
                        if (response.isSuccessful()) {
                            mLeitlinie = parseLeitlinieData(jsonData);
                        } else {
                            alertUserAboutError();
                        }

                    } catch (IOException e) {
                        Log.e(TAG, "Exception caught", e);
                    } catch (JSONException e) {
                        Log.e(TAG, "Exception caught", e);
                    } catch (Exception e) {
                        Log.e(TAG, "Exception caught", e);
                    }

                }
            });
        } else {
            Toast.makeText(this, "Network is unavailable", Toast.LENGTH_LONG).show();
        }

        expListView = (ExpandableListView) findViewById(R.id.expandableListView);

        PD = new ProgressDialog(this);
        PD.setMessage("Loading.....");
        PD.setCancelable(false);

        // makejsonobjreq();

        final LayoutInflater inflater = LayoutInflater.from(this);

        mToolsButton = (Button) findViewById(R.id.ToolsButton);

        mToolsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LeitlinieMain.this, ToolsActivity.class);
                startActivity(intent);
            }
        });
        mHomebutton = (Button) findViewById(R.id.Button01);
        listView = (ListView) findViewById(R.id.listView1);
        list = new ArrayList<NLevelItem>();
        Random rng = new Random();
        //final LayoutInflater inflater = LayoutInflater.from(this);
        NLevelItem kapitelName = null;
        NLevelItem ersteEbene = null;
        NLevelItem zweiteEbene = null;
        NLevelItem dritteEbene = null;
        String data="";
        try {
            Log.i("hallo", "jetztfehler");
            JSONObject jsonRootObject = new JSONObject(jsonData);
            JSONObject jsonLeitlinieObject = jsonRootObject.getJSONObject("leitlinie");
            JSONArray jsonArrayInhalt = jsonLeitlinieObject.getJSONArray("inhalt");

            //JSONObject meta = jsonLeitlinieObject.getJSONObject("meta");
            // JSONObject jsonObjectInhalt = jsonLeitlinieObject.getJSONObject("titel");
            String titel = jsonArrayInhalt.getString(1);
            // leitlinienJsonObject = jsonObject.optJSONObject("leitlinie");
            for (int i = 0; i < jsonArrayInhalt.length()-1; i++) {
                JSONObject jsonObjectKapitelName = null;

                JSONObject jsonObject = jsonArrayInhalt.getJSONObject(i);
                JSONArray key = jsonObject.names();
                Log.i("Kapitel 1", key.toString());;
                if(jsonArrayInhalt.getJSONObject(i).keys().next()=="ueberschrift") {
                    kapitelName = new NLevelItem(new SomeObject(jsonArrayInhalt.getJSONObject(i).getString(jsonArrayInhalt.getJSONObject(i).getString("ueberschrift"))), null, new NLevelView() {

                        @Override
                        public View getView(NLevelItem item) {
                            View view = inflater.inflate(R.layout.expandable_list_textitem, null);
                            TextView tv = (TextView) view.findViewById(R.id.textView);
                            tv.setBackgroundColor(Color.GREEN);
                            String name = (String) ((SomeObject) item.getWrappedObject()).getName();
                            tv.setText(name);
                            return view;
                        }
                    });
                }
                else if(jsonArrayInhalt.getJSONObject(i).keys().next()=="glossar"){}else{
                    kapitelName = new NLevelItem(new SomeObject(jsonArrayInhalt.getJSONObject(i).keys().next()), null, new NLevelView() {

                        @Override
                        public View getView(NLevelItem item) {
                            View view = inflater.inflate(R.layout.expandable_list_textitem, null);
                            TextView tv = (TextView) view.findViewById(R.id.textView);
                            tv.setBackgroundColor(Color.GREEN);
                            String name = (String) ((SomeObject) item.getWrappedObject()).getName();
                            tv.setText(name);
                            return view;
                        }
                    });
                }


                list.add(kapitelName);

                int laenge = 0;

                for (int j = 0; j < jsonArrayInhalt.getJSONObject(i).names().length(); j++) {
                    Iterator<String> keys = jsonArrayInhalt.getJSONObject(i).keys();
                    Log.wtf("laenge", keys.next());
                    while (keys.hasNext()) {
                        laenge++;
                        keys.next();
                    }
                    JSONObject jsonObject1 = jsonArrayInhalt.getJSONObject(i);
                 /*   if(jsonObject1.getString(keys.next()).contains("{\"kapitel")){
                        Log.i("hallo3", jsonObject1.toString);
                    }*/

                    Log.d("jsonObject", jsonObject1.toString());
                    Log.wtf("laenge", Integer.toString(laenge));

                    JSONArray jsonObject3 = jsonObject1.names();
                    Log.i("hallo2", jsonObject3.get(0).toString());
                    int kapitel = j + 1;
                    ;
                    JSONArray jsonArray1 = jsonObject.getJSONArray(jsonObject1.names().getString(j));

                    for (int m = 0; m < jsonArray1.length(); m++) {
                        int n = 0;
                        Log.d("names", jsonObject3.getString(j));

                       //Log.e("msg", jsonArray1.getJSONObject(m).names().getString(j));
                        //if(jsonObject1.getJSONArray(jsonObject3.getString(j)).getJSONObject(m).keys().next().contains("kapitel")){

                        Log.e("g", jsonObject1.getJSONArray(jsonObject3.getString(j)).toString());
                        if (jsonObject1.getJSONArray(jsonObject3.getString(j)).getJSONObject(m).keys().next().equals("text")) {
                            Log.e("m", jsonObject1.getJSONArray(jsonObject3.getString(j)).getJSONObject(m).getString("text"));
                            if(jsonObject1.getJSONArray(jsonObject3.getString(j)).getJSONObject(m).getString("text").contains("(härtegrad")) {
                                ersteEbene = new NLevelItem(new SomeObject(jsonObject1.getJSONArray(jsonObject3.getString(j)).getJSONObject(m).getString("text").substring(1, 12)), kapitelName, new NLevelView() {

                                    @Override
                                    public View getView(NLevelItem item) {
                                        View view = inflater.inflate(R.layout.expandable_list_textitem, null);
                                        TextView tv = (TextView) view.findViewById(R.id.textView);
                                        tv.setBackgroundColor(Color.RED);
                                        tv.setTextColor(Color.rgb(23, 38, 0));
                                        String name = (String) ((SomeObject) item.getWrappedObject()).getName();
                                        tv.setText(name);
                                        return view;
                                    }
                                });
                            }
                            else{
                            ersteEbene = new NLevelItem(new SomeObject(jsonObject1.getJSONArray(jsonObject3.getString(j)).getJSONObject(m).getString("text").substring(0,20)), kapitelName, new NLevelView() {

                                @Override
                                public View getView(NLevelItem item) {
                                    View view = inflater.inflate(R.layout.expandable_list_textitem, null);
                                    TextView tv = (TextView) view.findViewById(R.id.textView);
                                    tv.setBackgroundColor(Color.CYAN);
                                    tv.setTextColor(Color.rgb(23,38,0));
                                    String name = (String) ((SomeObject) item.getWrappedObject()).getName();
                                    tv.setText(name);
                                    return view;
                                }
                            });
                        } }else if(jsonArray1.getJSONObject(m).keys().next()!="text"&&jsonArray1.getJSONObject(m).keys().next()!="grafik") {
                                if (jsonArray1.getJSONObject(m).get(jsonArray1.getJSONObject(m).keys().next()) instanceof JSONArray) {
                                    JSONArray jsonArraySub1 = jsonArray1.getJSONObject(m).getJSONArray(jsonArray1.getJSONObject(m).keys().next());
                                    for(int sub1 = 0; sub1<jsonArraySub1.length();sub1++){
                                        Log.i("laengneenne", jsonArraySub1.toString());
                                        Log.d("überschrift", jsonArraySub1.getJSONObject(sub1).keys().next());
                                        if(jsonArraySub1.getJSONObject(sub1).keys().next().equals("ueberschrift")) {
                                            Log.e("fits", "läuft");
                                            ersteEbene = new NLevelItem(new SomeObject(jsonArraySub1.getJSONObject(sub1).getString(jsonArraySub1.getJSONObject(sub1).keys().next())), kapitelName, new NLevelView() {

                                                @Override
                                                public View getView(NLevelItem item) {
                                                    View view = inflater.inflate(R.layout.expandable_list_textitem, null);
                                                    TextView tv = (TextView) view.findViewById(R.id.textView);
                                                    tv.setBackgroundColor(Color.GREEN);
                                                    String name = (String) ((SomeObject) item.getWrappedObject()).getName();
                                                    tv.setText(name);
                                                    return view;
                                                }
                                            });
                                            if(list.contains(ersteEbene)){
                                                if(list.contains(zweiteEbene)){

                                                }else{
                                                    Log.i("test", "testitiest");
                                                    list.add(zweiteEbene);}
                                            }else {
                                                list.add(ersteEbene);
                                            }}
                                        else{
                                            Log.d("subchap",jsonArraySub1.getJSONObject(sub1).getString
                                                    (jsonArraySub1.getJSONObject(sub1).keys().next()));
                                            zweiteEbene = new NLevelItem(new SomeObject(jsonArraySub1.getJSONObject(sub1).getString
                                                    (jsonArraySub1.getJSONObject(sub1).keys().next())), ersteEbene, new NLevelView() {

                                                @Override
                                                public View getView(NLevelItem item) {
                                                    View view = inflater.inflate(R.layout.expandable_list_textitem, null);
                                                    TextView tv = (TextView) view.findViewById(R.id.textView);
                                                    tv.setBackgroundColor(Color.WHITE);
                                                    tv.setTextColor(Color.BLACK);
                                                    String name = (String) ((SomeObject) item.getWrappedObject()).getName();
                                                    tv.setText(name);
                                                    return view;
                                                }
                                            });
                                            if(list.contains(zweiteEbene)){

                                            }else {
                                                list.add(zweiteEbene);
                                            }
                                        }
                                    }
                                }
                            }else if(jsonArray1.getJSONObject(m).keys().next()=="grafik") {
                        }

                        if(list.contains(ersteEbene)){
                            if(list.contains(zweiteEbene)){

                            }else{
                                Log.i("test", "testitiest");
                            list.add(zweiteEbene);}
                        }else {
                            list.add(ersteEbene);
                        }

                        for (int k = 0; k < jsonObject1.getJSONArray(jsonObject3.getString(j)).getJSONObject(k).names().length(); k++) {
                            if (jsonObject1.getJSONArray(jsonObject3.getString(j)).getJSONObject(m).has("text")) {
                                zweiteEbene = new NLevelItem(new SomeObject(jsonObject1.getJSONArray(jsonObject3.getString(j)).getJSONObject(m).getString("text")), ersteEbene, new NLevelView() {

                                    @Override
                                    public View getView(NLevelItem item) {
                                        View view = inflater.inflate(R.layout.expandable_list_textitem, null);
                                        TextView tv = (TextView) view.findViewById(R.id.textView);
                                        tv.setBackgroundColor(Color.WHITE);
                                        tv.setTextColor(Color.BLACK);
                                        String name = (String) ((SomeObject) item.getWrappedObject()).getName();
                                        tv.setText(name);
                                        return view;
                                    }
                                });
                            }
                            else if(jsonObject1.getJSONArray(jsonObject3.getString(j)).getJSONObject(m).keys().next().equals("grafik")){
                                ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                                        .threadPriority(Thread.NORM_PRIORITY - 2)
                                        .denyCacheImageMultipleSizesInMemory()
                                        .discCacheFileNameGenerator(new Md5FileNameGenerator())
                                        .tasksProcessingOrder(QueueProcessingType.LIFO)
                                        .build();
                               final ImageLoader imageLoader= ImageLoader.getInstance();

                                zweiteEbene = new NLevelItem(new SomeObject(jsonObject1.getJSONArray(jsonObject3.getString(j)).getJSONObject(m).getString("grafik")), ersteEbene, new NLevelView() {

                                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                                    @Override
                                    public View getView(NLevelItem item) {
                                        View view = inflater.inflate(R.layout.expandable_list_textitem, null);
                                        TextView tv = (TextView) view.findViewById(R.id.textView);
                                        ImageView iv = (ImageView) view.findViewById(R.id.imageView);

                                        tv.setBackgroundColor(Color.YELLOW);
                                        tv.setTextColor(Color.BLACK);
                                        String uri = (String) ((SomeObject) item.getWrappedObject()).getName();
                                       tv.setText(uri.substring(uri.indexOf("Abbildung")));
                                        int urlendplus3 = uri.indexOf("file://");
                                        URL url = null;
                                        /*try {
                                            //url = new URL(uri.substring(0,(urlendplus3-2)));
                                        } catch (MalformedURLException e) {
                                            e.printStackTrace();
                                        }*/


                                        //Log.i("bild", uri.substring(0, urlendplus3-1));
                                        /*try {
                                            if(uri.subSequence(0,3)=="http" && isNetworkAvailable()) {
                                                Log.i("bild", "bild");
                                            }
                                            else{
                                                uri=uri.substring(uri.indexOf("file://"), uri.indexOf("Abbildung")-2);
                                                Log.i("URI",uri);
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        Bitmap bmp = null;
                                        try {
                                            if(isNetworkAvailable()){
                                            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());}
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }*/
                                        //iv.setImageBitmap(bmp);
                                        int filenameStart = uri.lastIndexOf("/");
                                        int filenameEnd = uri.lastIndexOf(".png");
                                        Log.i("filename", uri.substring(filenameStart+1, filenameEnd+4));

                                        imageLoader.displayImage("file:///" + R.drawable.image, iv);
                                        imageLoader.displayImage(uri.substring(0, (uri.indexOf(" file"))), iv);
                                        iv.setImageDrawable(iv.getDrawable());
                                        view.setBackground(iv.getDrawable());
                                        DownloadFromUrl(uri.substring(0, (uri.indexOf(" file"))), uri.substring(filenameStart + 1, filenameEnd + 4));

                                        return view;
                                }

                            });


                        }
                            if(list.contains(zweiteEbene)){

                            }else {
                                list.add(zweiteEbene);
                            }}
                    }
                }
            }

            NLevelAdapter adapter = new NLevelAdapter(list);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                        long arg3) {
                    ((NLevelAdapter)listView.getAdapter()).toggle(arg2);
                    ((NLevelAdapter)listView.getAdapter()).getFilter().filter();

                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client2 = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private Leitlinie parseLeitlinieData(String jsonData) throws Exception {

        Leitlinie leitlinie = new Leitlinie();
        JSONObject jsonObject = new JSONObject(jsonData);
        leitlinienJsonObject = jsonObject.optJSONObject("Employee");
        //leitlinie.setMeta(getMetaData(jsonData));
        //leitlinie.setAutorj(getAutorjData(jsonData));


        return leitlinie;

    }

    private Meta[] getMetaData(String jsonData) throws JSONException {
        JSONObject leitlinie = new JSONObject(jsonData);
        leitlinienJsonObject = leitlinie.optJSONObject("leitlinie");
        Log.i("leitlinie", jsonData);
        JSONArray metaArray = leitlinienJsonObject.getJSONArray("meta");
        JSONObject metaObject = metaArray.getJSONObject(1);
        JSONArray herausgeber = metaObject.getJSONArray("herausgeber");

        Meta[] metas = new Meta[herausgeber.length()];

        for (int i = 0; i < herausgeber.length(); i++) {
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

        for (int i = 0; i < autor.length(); i++) {
            JSONObject jsonAutorj = autor.getJSONObject(i);
            Autorj aut = new Autorj();

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
                Log.i(TAG, from json" + timezone);

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
        if (networkInfo != null && networkInfo.isConnected())

        {
            isAvailable = true;
        }
        return isAvailable;
    }
    public void DownloadFromUrl(String DownloadUrl, String fileName) {
        String filepath = Environment.getExternalStorageDirectory().getAbsolutePath();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        try
        {
            URL url = new URL(DownloadUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(true);
            urlConnection.connect();
            File SDCardRoot = Environment.getExternalStorageDirectory().getAbsoluteFile();
            String filename=fileName;
            Log.i("Local filename:",""+filename);
            File file = new File(SDCardRoot,filename);
            if(file.createNewFile())
            {
                file.createNewFile();
            }
            FileOutputStream fileOutput = new FileOutputStream(file);
            InputStream inputStream = urlConnection.getInputStream();
            int totalSize = urlConnection.getContentLength();
            int downloadedSize = 0;
            byte[] buffer = new byte[1024];
            int bufferLength = 0;
            while ( (bufferLength = inputStream.read(buffer)) > 0 )
            {
                fileOutput.write(buffer, 0, bufferLength);
                downloadedSize += bufferLength;
                Log.i("Progress:","downloadedSize:"+downloadedSize+"totalSize:"+ totalSize) ;
            }
            fileOutput.close();
            if(downloadedSize==totalSize) filepath=file.getPath();
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            filepath=null;
            e.printStackTrace();
        }
        Log.i("filepath:"," "+filepath) ;
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


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client2.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "LeitlinieMain Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://softdev1.medll/http/host/path")
        );
        AppIndex.AppIndexApi.start(client2, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "LeitlinieMain Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://softdev1.medll/http/host/path")
        );
        AppIndex.AppIndexApi.end(client2, viewAction);
        client2.disconnect();
    }
    public boolean hasAnotherArray(JSONObject jsonObject) throws JSONException {
        if(jsonObject.has("ueberschrift")){
        if (jsonObject.get("ueberschrift") instanceof JSONObject) {
                return false;
            } else {
                JSONArray kapitelArray = jsonObject.getJSONArray(jsonObject.getString("ueberschrift"));
                return true;
            }}
        else{
            return false;
        }



    }
    public NLevelItem nextLevelItem(JSONArray kapitelArray, int i) throws JSONException {
        final LayoutInflater inflater = LayoutInflater.from(this);

        NLevelItem kapitelName = new NLevelItem(new SomeObject(kapitelArray.getJSONObject(i).getString(kapitelArray.getJSONObject(i).names().toString())), null, new NLevelView() {

                @Override
                public View getView(NLevelItem item) {
                    View view = inflater.inflate(R.layout.expandable_list_textitem, null);
                    TextView tv = (TextView) view.findViewById(R.id.textView);
                    tv.setBackgroundColor(Color.GREEN);
                    String name = (String) ((SomeObject) item.getWrappedObject()).getName();
                    tv.setText(name);
                    return view;
                }
            });
        Log.e("works", kapitelName.toString());
        if(hasAnotherArray(kapitelArray.getJSONObject(i))){
            list.add(nextLevelItem(kapitelArray.getJSONObject(i).getJSONArray(kapitelName.toString()), i));
        }
        return kapitelName;
    }


    class SomeObject {
        public String name;

        public SomeObject(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }
    }
}
