package mos.dro.in.pal.palindromos;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button limpiar;
    Button verificar;
    TextView resultado;
    TextView resultado2;
    EditText palabra;
    ScrollView svApp;
    ScrollView svInfo;
    ToggleButton toggle;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "ToDo poner alguna acción", Snackbar.LENGTH_LONG)
                        .setAction("Act0ion", null).show();
            }
        });*/
        verificar = (Button) findViewById(R.id.btVerificar);
        limpiar = (Button) findViewById(R.id.btLimpiar);
        resultado = (TextView) findViewById(R.id.tvResultado);
        resultado2 = (TextView) findViewById(R.id.tvResultado2);
        palabra = (EditText) findViewById(R.id.et01);
        svApp = (ScrollView) findViewById(R.id.svApp);
        svInfo = (ScrollView) findViewById(R.id.svInfo);
        toggle = (ToggleButton) findViewById(R.id.toggle);

        verificar.setOnClickListener(this);
        limpiar.setOnClickListener(this);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    svApp.setVisibility(View.VISIBLE);
                    svInfo.setVisibility(View.INVISIBLE);
                } else {
                    svApp.setVisibility(View.INVISIBLE);
                    svInfo.setVisibility(View.VISIBLE);
                }
            }
        });

        //Toast.makeText(getApplicationContext(), R.string.instruccionInicial, Toast.LENGTH_LONG).show();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btVerificar:
                if (palabra.getText().toString().length() == 0) {
                    limpiarControles();
                    Toast.makeText(getApplicationContext(), "No ha escrito nada, por favor intentalo de nuevo", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean palindromo = verificarPalindromo(palabra.getText().toString());
                    if (palindromo) {
                        resultado.setText(R.string.siPalindromo);
                        resultado.setBackgroundResource(R.color.colorAccent);
                    } else {
                        resultado.setText(R.string.noPalindromo);
                        resultado.setBackgroundResource(R.color.noPalindromo);
                    }
                    resultado2.setText(invertirCadena(palabra.getText().toString()));
                }
                break;
            case R.id.btLimpiar:
                limpiarControles();
                break;
        }
    }

    private void limpiarControles() {
        resultado.setText("");
        palabra.setText("");
        resultado2.setText("");
    }

    private String invertirCadena(String text) {
        return new StringBuffer(text).reverse().toString();
    }

    private boolean verificarPalindromo(String text) {
        text = text.replaceAll(" ", "").toLowerCase();
        text = text.replace("á", "a").replace("é", "e").replace("í", "i").replace("ó", "o").replace("ú", "u");
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) != text.charAt(text.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }
}
