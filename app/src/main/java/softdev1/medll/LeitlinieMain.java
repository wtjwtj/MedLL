package softdev1.medll;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
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
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class LeitlinieMain extends AppCompatActivity {
    private Button mToolsButton;
    public static final String TAG = LeitlinieMain.class.getSimpleName();
    private Leitlinie mLeitlinie;
   // ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    private XmlPullParserFactory xmlFactoryObject;
    private XmlPullParser myparser;
    private String url = "http://medll.bplaced.net/leitlinien/main.json";
    ProgressDialog PD;
    List<NLevelItem> list;
    ListView listView;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leitlinie_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String leitlinieUrl = "http://medll.bplaced.net/leitlinien/main.json";
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
                        String jsonData = " {\n" +
                                "  \"leitlinie\": {\n" +
                                "  \"meta\":[{\n" +
                                "  \n" +
                                "  \"titel\": \"Gestationsdiabetes mellitus (GDM)\"\n" +
                                "},\n" +
                                "{\n" +
                                "\"awmf\": \"057/008\"\n" +
                                "},\n" +
                                "{\n" +
                                "\"version\": \"0.1\"\n" +
                                "},\n" +
                                "{\n" +
                                "\"version-ll-json\": \"0.1\"\n" +
                                "},\n" +
                                "{\n" +
                                "\"stand\": \"2011-08\"\n" +
                                "},\n" +
                                "{\n" +
                                "\"erstellungsdatum\": \"2001\"\n" +
                                "},\n" +
                                "{\n" +
                                "\"naechsteUeberpruefung\": \"2016-06\"\n" +
                                "},\n" +
                                "{\n" +
                                "\"klasse\": \"S3\"\n" +
                                "},\n" +
                                "{\n" +
                                "\"logo\":\"platzhalter\"\n" +
                                "},\n" +
                                "{\n" +
                                "\"hinweis\": \"Die \\\"Leitlinien\\\" der Wissenschaftlichen Medizinischen Fachgesellschaften sind systematisch entwickelte Hilfen fÃ¼r Ã„rzte zur Entscheidungsfindung in spezifischen Situationen. Sie beruhen auf aktuellen wissenschaftlichen Erkenntnissen und in der Praxis bewÃ¤hrten Verfahren und sorgen fÃ¼r mehr Sicherheit in der Medizin, sollen aber auch Ã¶konomische Aspekte berÃ¼cksichtigen. Die \\\"Leitlinien\\\"; sind fÃ¼r Ã„rzte rechtlich nicht bindend und haben daher weder haftungsbegrÃ¼ndende noch haftungsbefreiende Wirkung. Die AWMF erfasst und publiziert die Leitlinien der Fachgesellschaften mit grÃ¶ÃŸtmÃ¶glicher Sorgfalt - dennoch kann die AWMF fÃ¼r die Richtigkeit des Inhalts keine Verantwortung Ã¼bernehmen. Insbesondere fÃ¼r Dosierungsangaben sind stets die Angaben der Hersteller zu beachten!\"\n" +
                                "},\n" +
                                "{\n" +
                                "\"copyright\": \"(c)Deutsche DiabetesGesellschaft\"\n" +
                                "},\n" +
                                "{\n" +
                                "\"untertitel\": \"Evidenzbasierte Leitlinie zu Diagnostik, Therapie u. Nachsorge der Deutschen Diabetes-Gesellschaft (DDG) und der Deutschen Gesellschaft fÃ¼r GynÃ¤kologie und Geburtshilfe (DGGG)\"\n" +
                                "\n" +
                                "},\n" +
                                "{\n" +
                                "\"autor\": [\n" +
                                "      {\n" +
                                "        \"name\": \"H.Kleinwechter\",\n" +
                                "        \"institut\": \"diabetologikum kiel, Diabetes-Schwerpunktpraxis und Schulungszentrum, Kiel\"\n" +
                                "      },\n" +
                                "      {\n" +
                                "        \"name\": \"U.SchÃ¤fer-Graf\",\n" +
                                "        \"institut\": \"Berliner Diabeteszentrum fÃ¼r Schwangere, Klinik fÃ¼r GynÃ¤kologie und Geburtshilfe, St.Joseph Krankenhaus, Berlin\"\n" +
                                "      },\n" +
                                "      {\n" +
                                "        \"name\": \"C.BÃ¼hrer\",\n" +
                                "        \"institut\": \"Klinik fÃ¼r Neonatologie, CharitÃ© UniversitÃ¤tsmedizin, Berlin\"\n" +
                                "      },\n" +
                                "      {\n" +
                                "        \"name\": \"I.Hoesli\",\n" +
                                "        \"institut\": \"Frauenklinik, Geburtshilfe und Schwangerschaftsmedizin, UniversitÃ¤tsspital Basel, Basel/CH\"\n" +
                                "      },\n" +
                                "      {\n" +
                                "        \"name\": \"F.Kainer\",\n" +
                                "        \"institut\": \"Klinik und Poliklinik fÃ¼r Frauenheilkunde und Geburtshilfe, Perinatalzentrum Klinikum Innenstadt LMU, MÃ¼nchen\"\n" +
                                "      },\n" +
                                "      {\n" +
                                "        \"name\": \"A.Kautzky-Willer\",\n" +
                                "        \"institut\": \"Medizinische UniversitÃ¤tsklinik Wien, Klinik fÃ¼r Innere Medizin III, Abteilung fÃ¼r Endokrinologie u. Stoffwechsel, Gender Medicine Unit, Wien/A\"\n" +
                                "      },\n" +
                                "      {\n" +
                                "        \"name\": \"B.Pawlowski\",\n" +
                                "        \"institut\": \"Klinik fÃ¼r Stoffwechselkrankheiten, UniversitÃ¤tsklinikum DÃ¼sseldorf und Deutsches Diabetes-Zentrum, DÃ¼sseldorf\"\n" +
                                "      },\n" +
                                "      {\n" +
                                "        \"name\": \"K.Schnuck\",\n" +
                                "        \"institut\": \"Klinik fÃ¼r Kinder- und Jugendmedizin, Krankenhaus im Friedrichshain, Berlin\"\n" +
                                "      },\n" +
                                "      {\n" +
                                "        \"name\": \"T.Somville\",\n" +
                                "        \"institut\": \"Klinik fÃ¼r Geburtshilfe und PrÃ¤natalmedizin, UniversitÃ¤tsklinikum Hamburg-Eppendorf, Hamburg\"\n" +
                                "      },\n" +
                                "      {\n" +
                                "        \"name\": \"M.Sorger\",\n" +
                                "        \"institut\": \"Medizinische Poliklinik, UniversitÃ¤tsklinikum Bonn, Bonn\"\n" +
                                "      }\n" +
                                "    ]},\n" +
                                "{\n" +
                                "\"weitereInfos\": [\n" +
                                "{\n" +
                                "\"text\":\"ErstverÃ¶ffentlichung 08/2011\"\n" +
                                "},\n" +
                                "{\n" +
                                "\"text\":\"Geplante Ãœberarbeitung 08/2016\"\n" +
                                "},\n" +
                                "{\n" +
                                "\"text\":\"Korrespondenzadressen fÃ¼r die Deutsche Diabetes-Gesellschaft\\n Dr.med. Helmut Kleinwechter, Sprecher der Expertengruppe &quot;Diabetes und Schwangerschaft&quot; der DDG diabetologikum kiel, Diabetes-Schwerpunktpraxis und Schulungszentrum, Alter Markt 11 u. 14, 24103 Kiel\\n Tel.: 0431/95807, Fax: 0431/95805, E-mail: arzt@diabetologikum-kiel.de\"\n" +
                                "},\n" +
                                "{\n" +
                                "\"text\":\"Prof. Dr.med. Monika Kellerer, Leitlinienbeauftragte des Vorstands der DDG\\n Zentrum fÃ¼r Innere Medizin I, Marienhospital Stuttgart, Boheimstr.37, 70199 Stuttgart\\nTel.: 0711/64892102, Fax: 0711/64892119, E-Mail: monikakellerer@vinzenz.de\"\n" +
                                "},\n" +
                                "{\n" +
                                "\"text\":\"Korrespondenzadresse fÃ¼r die Deutsche Gesellschaft fÃ¼r GynÃ¤kologie u. Geburtshilfe\\nPD Dr.med. Ute SchÃ¤fer-Graf, Sprecherin der Expertengruppe \\\"Diabetes und Schwangerschaft\\\" der DGGG\\nBerliner Diabeteszentrum fÃ¼r Schwangere, Klinik fÃ¼r GynÃ¤kologie und Geburtshilfe, St.Joseph Krankenhaus, WÃ¼sthoffstraÃŸe 15, 12101 Berlin\\nTel.: 030/7882-4214/2236, Fax: 030/7882-2766, E-mail: ute.schaefer-graf@sjk.de\"\n" +
                                "}\n" +
                                "]}],\n" +
                                "\"inhalt\":[\n" +
                                "{\n" +
                                "\"vorbemerkung\":[\n" +
                                "{\n" +
                                "\"text\":\"Diese interdisziplinÃ¤re, evidenzbasierte S3-Leitlinie (AWMF-Leitlinie 057/008) ist fachgesellschaftsÃ¼bergreifend und bearbeitet nur den Gestationsdiabetes mellitus (GDM, ICD-10: O24.4G), aber nicht den prÃ¤konzeptionell bekannten Diabetes mellitus (Typ-1, Typ-2, spezifische Formen). Die Leitlinie ersetzt die Empfehlungen zur Diagnostik und Therapie des Gestationsdiabetes aus dem Jahr 2001 (AG Diabetes und Schwangerschaft der DDG 2001 EK IV). Diese Leitlinie wird durch eine Kurzfassung als Praxisleitlinie der DDG/DGGG und eine Laienversion fÃ¼r Schwangere und Interessierte ergÃ¤nzt.\"\n" +
                                "},\n" +
                                "{\n" +
                                "\"text\":\"FÃ¼r zusÃ¤tzliche Informationen zum Thema \\\"Diabetes und Schwangerschaft\\\" wird auf folgende Leitlinien und Empfehlungen hingewiesen\"\n" +
                                "},\n" +
                                "{\n" +
                                "\"text\":\"\\Evidenzbasierte Leitlinie Diabetes und Schwangerschaft\\nAWMF-Leitlinie 057/023\\nhttp://www.deutsche-diabetesgesellschaft.de/fileadmin/Redakteur/Leitlinien/Evidenzbasierte_Leitlinien/EBL_Schwangerschaft_2008.pdf\"\n" +
                                "},\n" +
                                "{\n" +
                                "\"text\":\"\\Praxisleitlinie Diabetes und Schwangerschaft\\nttp://www.deutsche-diabetesgesellschaft.de/fileadmin/Redakteur/Leitlinien/Praxisleitlinien/PL_DDG2011_Diab_und_Schwangerschaft.pdf\"\n" +
                                "}]},\n" +
                                "{\"kapitel 1\":[\n" +
                                "{\n" +
                                "\"ueberschrift\":\"1 Gesundheitsziele\"\n" +
                                "},\n" +
                                "{\n" +
                                "\"text\":\"Die International Diabetes Federation (IDF) und die WHO-Europa haben 1989 und 1999 Gesundheitsziele fÃ¼r Schwangerschaften bei Frauen mit Diabetes formuliert (WHO u. IDF Europe 1990 EK IV, WHO Europa 1999 EK IV). Danach sollen Verlauf und Ergebnisse von Schwangerschaften bei Frauen mit Diabetes von 1999 bis zum Jahr 2020 um ein Drittel verbessert werden. Die IDF legte 2009 erstmals eine um den Gestationsdiabetes erweiterte globale Leitlinie zu den Problemen von Diabetes und Schwangerschaft vor (IDF 2009 EK IV). Die IDF stellt fest, dass der GDM mit Komplikationen in der Schwangerschaft assoziiert ist, die durch rechtzeitige Diagnostik und intensive Behandlung abgemildert werden kÃ¶nnen. Dies bestÃ¤tigt auch eine systematische Ãœbersicht und Metaanalyse: Therapeutisch effektiv sind sowohl eine Blutglukosesenkende als auch eine spezialisierte geburtsmedizinische Betreuung (Horvath 2010 EK Ia).\"\n" +
                                "},\n" +
                                "{\n" +
                                "\"text\":\"(haertegrad B) Die Vorgaben der Gesundheitsziele sollten anhand von QualitÃ¤tsindikatoren Ã¼berprÃ¼ft werden (siehe Seite 64-65, 9 QualitÃ¤tssicherung)\"\n" +
                                "}]},\n" +
                                "{\"kapitel 2\":[\n" +
                                "{\n" +
                                "\"ueberschrift\":\"2 Definition\"\n" +
                                "},\n" +
                                "{\n" +
                                "\"text\": \"Gestationsdiabetes mellitus (GDM, ICD-10: O24.4G) ist definiert\"\n" +
                                "},\n" +
                                "{\n" +
                                "\"text\": \"als eine GlukosetoleranzstÃ¶rung, die erstmals in der Schwangerschaft mit einem 75-g oralen Glukosetoleranztest (oGTT) unter standardisierten Bedingungen und qualitÃ¤tsgesicherter Glukosemessung aus venÃ¶sem Plasma diagnostiziert wird. Die Diagnose ist bereits mit einem erhÃ¶hten Glukosewert mÃ¶glich.\"\n" +
                                "},\n" +
                                "{\n" +
                                "\"text\": \"Die diagnostischen Grenzwerte beruhen auf internationaler Konsensbildung durch Experten (IADPSG Consensus Panel 2010 EK IV). Der Experten-Konsensbildung wurden die Ergebnisse einer epidemiologischen Untersuchung mit mÃ¼tterlichen und neonatalen, klinisch relevanten Endpunkten zugrunde gelegt (HAPO Cooperative Research Group 2008 EK IIb, 2009 EK IIb, 2010 IIb). Alle in der Leitlinie beschriebenen Blutglukosewerte beziehen sich auf Blutplasma.\"\n" +
                                "},\n" +
                                "{\n" +
                                "\"text\": \" Die ÃœbergÃ¤nge zwischen sog. normaler Glukosetoleranz in der Schwangerschaft und Gestationsdiabetes sind flieÃŸend, ein Schwellenwert existiert nicht. Nach internationalem Konsens werden ein bisher unbekannter, manifester Diabetes und eine HyperglykÃ¤mie unterschieden, die unterhalb dieser Grenzen liegt und als Gestationsdiabetes, wie oben beschrieben, klassifiziert wird (IADPSG Consensus Panel 2010 EK IV, American Diabetes Association 2011 EK IV). Die Definition des manifesten Diabetes entspricht der auÃŸerhalb einer Schwangerschaft. Damit fallen ein manifester Typ-1- oder Typ-2-Diabetes mellitus oder spezifische Diabetesformen, die erstmals wÃ¤hrend der Schwangerschaft diagnostiziert werden, nicht mehr unter die Diagnoseklasse des Gestationsdiabetes.\"\n" +
                                "},\n" +
                                "{\n" +
                                "\"text\": \"(haertegrad A) Das in dieser Leitlinie empfohlene Vorgehen nach IADPSG Consensus Panel soll einheitlich angewendet werden\"\n" +
                                "},\n" +
                                "{\"kapitel 2.1\":[\n" +
                                "{\n" +
                                "\"ueberschrift\":\"2.1 Pathopsychologie\"\n" +
                                "},\n" +
                                "{\n" +
                                "\"text\": \"Die Pathophysiologie des Gestationsdiabetes entspricht zu einem groÃŸen Teil der des Typ-2- Diabetes. Auf der Basis einer genetischen PrÃ¤disposition spielen vor allem Ãœbergewicht und der Lebensstil (ErnÃ¤hrung, Bewegung) der Frauen eine groÃŸe Rolle. Die in der zweiten SchwangerschaftshÃ¤lfte physiologisch einsetzende Insulinresistenz fÃ¼hrt im Falle eines Gestationsdiabetes bei gleichzeitig vorliegendem (zumindest relativem) Insulinsekretionsdefekt zur HyperglykÃ¤mie in der GraviditÃ¤t. Neben den hormonellen VerÃ¤nderungen in der GraviditÃ¤t dÃ¼rften auch eine verÃ¤nderte Freisetzung von Adipokinen und Zytokinen aus dem Fettgewebe und der Plazenta eine Rolle spielen.\"\n" +
                                "},\n" +
                                "{\n" +
                                "\"text\": \"Die zugrundeliegenden pathophysiologischen Mechanismen sind in Analogie zum heterogenen Erscheinungsbild des GDM unterschiedlich und bislang â€“ wie bei Diabetes im Allgemeinen - nicht vollstÃ¤ndig geklÃ¤rt (Metzger 2007 EK IV). Kausal dÃ¼rfte beim klassischen Bild des GDM eine chronische, d.h. bereits prÃ¤konzeptionell bestehende, Herabsetzung der InsulinsensitivitÃ¤t bestehen, die zusÃ¤tzlich durch die ab der 20. Schwangerschaftswoche zunehmende physiologische Insulinresistenz verstÃ¤rkt wird und durch die endogene Insulinsekretion nur unzureichend kompensiert werden kann (=relativer Insulinmangel [Kautzky 1997 EK IIa]). FÃ¼r die Insulinresistenz ebenso wie fÃ¼r die InsulinsekretionsstÃ¶rung liegt teilweise eine genetische PrÃ¤disposition vor, wobei aber die AusprÃ¤gung durch Umweltfaktoren, den Lebensstil (hochkalorische ErnÃ¤hrung \\\"fast food\\\" und Bewegungsmangel), insbesondere Ãœbergewicht, wesentlich beeinflusst wird. Frauen, die einen Gestationsdiabetes entwickeln, weisen meist die gleichen Risikofaktoren wie Frauen mit einem Typ-2-Diabetes auf. Ebenso wurden eine ungÃ¼nstige VerÃ¤nderung im Sekretionsmuster von Adipokinen, insbesondere eine Verminderung von Adiponektin und eine Zunahme von Leptin, sowie ein Anstieg von TNF , beim GDM beschrieben (Kautzky 2004 EK III, Catalano 2008 EK III). Genomweite Assoziationsstudien weisen beim GDM auf die gleichen Kandidatgene wie fÃ¼r den Typ-2-Diabetes hin (Watanabe 2007 EK III).\"\n" +
                                "},\n" +
                                "{\n" +
                                "\"text\": \"Aufgrund dieser typischen metabolischen VerÃ¤nderungen und dem Vorliegen von Ãœbergewicht bei der GroÃŸzahl der betroffenen Schwangeren wird der \\\"klassische\\\" GDM als eine Form des PrÃ¤-Typ-2- Diabetes angesehen, welcher durch eine erhebliche Insulinresistenz und eine gestÃ¶rte Beta- Zellfunktion (FrÃ¼hphasen-Sekretionsdefekt) charakterisiert ist (Kim 2002 EK III, Kautzky 2005 EK IIb). GDM kann daher meistens als eine chronische FunktionsstÃ¶rung beschrieben werden, gekennzeichnet durch eine Insulinresistenz mit abfallender ÃŸ-Zell-Kompensation, die nur durch ein Glukosescreening als Routine-MaÃŸnahme in der Schwangerschaft entdeckt wird (Xiang 2010 EK IIa).\"\n" +
                                "}\n" +
                                "]}]},\n" +
                                "{\"kapitel 3\":[\n" +
                                "{\n" +
                                "\"ueberschrift\": \"3 Epidemologie\"\n" +
                                "},\n" +
                                "{\n" +
                                "\"text\": \"Die HÃ¤ufigkeit des GDM ist von folgenden Einflussfaktoren abhÃ¤ngig:\\n \\der epidemiologischen Untersuchung verschiedener BevÃ¶lkerungsgruppen (z.B. Kaukasierinnen, Asiatinnen, Lateinamerikanerinnen [sog. Hispanierinnen], Pima- Indianerinnen),\\nder Untersuchung von Volksgruppen unterschiedlicher genetischer Belastung mit Typ-2- Diabetes und damit unterschiedlichem PrÃ¤-Test-Risiko (z.B. EuropÃ¤er vs. Inder),\\nder Untersuchung nur von Risikogruppen (z.B. AdipÃ¶se, Ã¤ltere Schwangere) oder allen Schwangeren einer Population,\\ndem Zeitpunkt der Untersuchung (FrÃ¼hschwangerschaft, 24-28 SSW, nach 32 SSW),\\nder Untersuchung mittels vorgeschalteter Screeningverfahren (Uringlukose, Gelegenheitsglukose, NÃ¼chternglukose, 50-g GCT mit unterschiedlichen Screening- Schwellen) oder ohne vorgeschaltetes Screening,\\ndem diagnostischen Testverfahren (Menge der Glukose: 50 g [ehemalige DDR, DÃ¤nemark], 75 g [Europa], 100 g oGTT [Nordamerika]), der Anzahl der erhobenen Messwerte (1, 2, 3, 4 oder mehr), der Anzahl der erreichten oder Ã¼berschrittenen Grenzwerte (1 oder 2), der QualitÃ¤t der Glukosemessung (unzureichender vs. adÃ¤quater Laborstandard), der Verwendung des Blutmediums (venÃ¶ses Plasma vs. kapillÃ¤res Vollblut), der prÃ¤analytischen Verarbeitung abgenommener Blutproben (Glykolyse unzentrifugierter venÃ¶ser Vollblut- Blutproben) und der enzymatischen Methode der Blutglukosemessung,\\nden von Arbeitsgruppen und Fachgesellschaften durch Expertenmeinung festgelegten diagnostischen Grenzwerten.\"\n" +
                                "},\n" +
                                "{\n" +
                                "\"text\": \"Nach Literaturangaben der letzten 20 Jahre variierten die PrÃ¤valenzen des GDM zwischen 0,6% und 22 % (King 1998 EK IV, Murgia 2006 EK IV), vereinzelt noch niedriger oder auch hÃ¶her. Je hÃ¤ufiger nach einem GDM bei Schwangeren gesucht wird, je hÃ¶her die Diabetesrisiken allgemein sind und je niedriger die diagnostischen Grenzen liegen, umso \\\"hÃ¤ufiger\\\" wird ein GDM diagnostiziert.\"\n" +
                                "}\n" +
                                "{\n" +
                                "\"text\":\"Im Jahr 2010 wurden in Deutschland rund 650.000 Neugeborene von der Perinatalstatistik beim Institut fÃ¼r angewandte QualitÃ¤tsfÃ¶rderung und Forschung im Gesundheitswesen GmbH (AQUA) erfasst. Das AQUA-Institut hat die BundesgeschÃ¤ftsstelle fÃ¼r QualitÃ¤tssicherung (BQS) ab 1.1.2010 bei der Bundesauswertung \\\"Geburtshilfe\\\" abgelÃ¶st. Die ausgewerteten Daten reprÃ¤sentieren 99,2% der erwarteten Geburten aus 821 Kliniken. Bei den MÃ¼ttern lag in 23.872 FÃ¤llen (3,7%) ein Gestationsdiabetes vor (AQUA 2011 IIb). Der seit 2002 dokumentierte, stÃ¤ndige Anstieg der FÃ¤lle von GDM (2007 bis 2008: +25%) setzt sich nach vorÃ¼bergehender Stagnation 2009 aktuell fort (Abb.1+2).\"\n" +
                                "}\n" +
                                "{\n" +
                                "\"grafik\":\"Platzhalter bild\\ Relative HÃ¤ufigkeiten des Gestationsdiabetes in Deutschland 2002-2010\"\n" +
                                "}\n" +
                                "{\n" +
                                "\"grafik\":\"Platzhalter bild \\ Absolute HÃ¤ufigkeiten des Gestationsdiabetes in Deutschland 2002-2010\"\n" +
                                "},\n" +
                                "{\n" +
                                "\"text\":\"Zum Zeitpunkt der Geburt ist die GDM-PrÃ¤valenz in Deutschland von 2002 bis 2010 relativ um das 2,52-fache angestiegen. Nach den Festlegungen des IADPSG Consensus Panel betrÃ¤gt die um manifeste DiabetesfÃ¤lle bereinigte Netto-GDM-PrÃ¤valenz im HAPO-Studienkollektiv nach epidemiologischen MaÃŸstÃ¤ben und methodisch optimierter Blutglukosemessung 16,1% (IADPSG Consensus Panel 2010 EK IV, Nesbitt 2006 EK IIb, Bruns 2009 EK IV).\"\n" +
                                "},\n" +
                                "{\n" +
                                "\"text\": \"(hÃ¤rtegrad B) Bei der regionalen Versorgung sollte die erhobene GDM-PrÃ¤valenz beachtet und der Versorgungsgrad angepasst werden.\"\n" +
                                "}]},\n" +
                                "\n" +
                                "{\"glossar\":[\n" +
                                "{\"begriff\": [\n" +
                                "{\n" +
                                "\"abk\":\"ACE\"\n" +
                                "\n" +
                                "},\n" +
                                "{\n" +
                                "\"Vollbegriff\":\"Angiotensin Converting Enzyme\"\n" +
                                "}\n" +
                                "]},\n" +
                                "{\n" +
                                "\"begriff\":[\n" +
                                "{\n" +
                                "\"abk\": \"ACOG\"\n" +
                                "\n" +
                                "},\n" +
                                "{\n" +
                                "\"Vollbegriff\":\"American College of Obstetricians and Gynecologists\"\n" +
                                "}\n" +
                                "]}\n" +
                                "\n" +
                                "],\n" +
                                "\"literatur\":[{\n" +
                                "\"quelle\": \"ACOG Practice Bulletin. Clinical management guidelines for obstetrician-gynecologists. Number 30, September 2001 (replaces Technical Bulletin Number 200, December 1994). Gestational diabetes. Obstet Gynecol 2001;98: 525-538.\"\n" +
                                "},\n" +
                                "{\n" +
                                "\"quelle\":\"Agarwal M, Dhatt G, Punnose J. Gestational diabetes: utility of fasting plasma glucose as a screening test depends on the diagnostic criteria. Diabet Med 2006;23:1319-1326.\"\n" +
                                "}\n" +
                                "],\n" +
                                "\"kapitel\":[\n" +
                                "{\n" +
                                "\"ueberschrift\":\"12 flussdiagramm: Diagnostik der HyperglykÃ¤mie in der Schwangerschaft\"\n" +
                                "},\n" +
                                "{\n" +
                                "\"grafik\": \"platzhalter\"\n" +
                                "}\n" +
                                "],\n" +
                                "\"kapitel\":[\n" +
                                "{\n" +
                                "\"ueberschrift\":\"13 Diabetes-Risikotabelle\"\n" +
                                "},\n" +
                                "{\n" +
                                "\"text\":\"(irgendwie eingerahmt) Alter > 45 Jahre\\nBMI > 30 kg/mÂ² prÃ¤konzeptionell\\n KÃ¶rperliche InaktivitÃ¤t\\n Eltern oder Geschwister mit Diabetes\\n AngehÃ¶rige einer ethnischen Risikopopulation (z.B. Asiatinnen, Lateinamerikanerinnen)\\n Geburt eines Kindes >4500 g\\n GDM in der Vorgeschichte\\n Arterielle Hypertonie (Blutdruck >140/90 mmHg) oder Einnahme von Medikamenten zur Therapie der arteriellen Hypertonie\\n DyslipidÃ¤mie prÃ¤konzeptionell (HDL <35 mg/ld [0,9 mmol/l] und/oder Triglyceride >250 mg/dl [2,82 mmol/l])\\n Polyzystisches Ovarsyndrom\\n PrÃ¤diabetes (IGT/IFG/HbA1c >5,7%) bei frÃ¼herem Test (unabhÃ¤ngig von frÃ¼herem GDM)\\n Andere klinische ZustÃ¤nde, die mit Insulinrsistenz assoziiert sind (z.B. Acanthosis nigricans)\\n Vorgeschichte mit KHK, pAVK, zerebral-arterieller DurchblutungsstÃ¶rung\\n Einnahme kontrainsulinÃ¤rer Medikation (z.B. Glukokortikoide)\"\n" +
                                "}\n" +
                                "]\n" +
                                "\n" +
                                "\n" +
                                "\n" +
                                "\n" +
                                "\n" +
                                "\n" +
                                "  }]\n" +
                                "  }}\n" +
                                " ";
                        Log.v(TAG, jsonData);
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


        mToolsButton = (Button) findViewById(R.id.ToolsButton);

        mToolsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LeitlinieMain.this, ToolsActivity.class);
                startActivity(intent);
            }
        });
        listView = (ListView) findViewById(R.id.listView1);
        list = new ArrayList<NLevelItem>();
        Random rng = new Random();
        final LayoutInflater inflater = LayoutInflater.from(this);
        for (int i = 0; i < 5; i++) {

            final NLevelItem grandParent = new NLevelItem(new SomeObject("text"),null, new NLevelView() {

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
            list.add(grandParent);
            int numChildren = rng.nextInt(4) + 1;
            for (int j = 0; j < numChildren; j++) {
                NLevelItem parent = new NLevelItem(new SomeObject("Parent "+j),grandParent, new NLevelView() {

                    @Override
                    public View getView(NLevelItem item) {
                        View view = inflater.inflate(R.layout.expandable_list_textitem, null);
                        TextView tv = (TextView) view.findViewById(R.id.textView);
                        tv.setBackgroundColor(Color.YELLOW);
                        String name = (String) ((SomeObject) item.getWrappedObject()).getName();
                        tv.setText(name);
                        return view;
                    }
                });

                list.add(parent);
                int grandChildren = rng.nextInt(5)+1;
                for( int k = 0; k < grandChildren; k++) {
                    NLevelItem child = new NLevelItem(new SomeObject("child "+k),parent, new NLevelView() {

                        @Override
                        public View getView(NLevelItem item) {
                            View view = inflater.inflate(R.layout.expandable_list_textitem, null);
                            TextView tv = (TextView) view.findViewById(R.id.textView);
                            tv.setBackgroundColor(Color.GRAY);
                            String name = (String) ((SomeObject) item.getWrappedObject()).getName();
                            tv.setText(name);
                            return view;
                        }
                    });

                    list.add(child);
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
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client2 = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    private Leitlinie parseLeitlinieData(String jsonData) throws Exception {

        Leitlinie leitlinie = new Leitlinie();


        leitlinie.setMeta(getMetaData(jsonData));
        leitlinie.setAutorj(getAutorjData(jsonData));


        return leitlinie;

    }

    private Meta[] getMetaData(String jsonData) throws JSONException {
        JSONObject leitlinie = new JSONObject(jsonData);

        JSONObject meta = leitlinie.getJSONObject("meta");
        JSONArray herausgeber = meta.getJSONArray("herausgeber");

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
        if (networkInfo != null && networkInfo.isConnected())

        {
            isAvailable = true;
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
  /*  private void makejsonobjreq() {
        PD.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(com.android.volley.Request.Method.GET, "http://medll.bplaced.net/leitlinien/main.json",
                null, new com.android.volley.Response.Listener<JSONObject>() {
            public void onResponse(JSONObject response) {
                ArrayList<Group> list = new ArrayList<>();
                ArrayList<Child> ch_list;

                try {
                    Iterator<String> key = response.keys();
                    while (key.hasNext()) {
                        String k = key.next();

                        Group gru = new Group();
                        gru.setName(k);
                        ch_list = new ArrayList<>();

                        JSONArray ja = response.getJSONArray(k);

                        for (int i = 0; i < ja.length(); i++) {

                            JSONObject jo = ja.getJSONObject(i);

                            Child ch = new Child();
                            ch.setName(jo.getString("name"));
                            ch.setName(jo.getString("leitlinien"));
                            ch_list.add(ch);
                        } // for loop end
                        gru.setItems(ch_list);
                        list.add(gru);
                    } // while loop end

                    listAdapter = new ExpandableListAdapter(
                            LeitlinieMain.this, list);
                    expListView.setAdapter(listAdapter);
                    Log.i("ll", mLeitlinie.toString());
                    PD.dismiss();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                PD.dismiss();
            }
        });
        Log.i("jsonObjReq",jsonObjReq.toString());
        ExpandableListApplication.getInstance().addToReqQueue(jsonObjReq, "jreq");
    }*/

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
