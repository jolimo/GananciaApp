package org.beginningandroid.gananciaapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

/**
 * La clase SplashActivity representa la pantalla de inicio de la aplicación.
 * Esta clase se encarga de mostrar la versión de la aplicación y de iniciar la MainActivity después de un retraso.
 */
public class SplashActivity extends AppCompatActivity {

    /**
     * Este método se llama cuando se crea la actividad.
     * Muestra la versión de la aplicación y inicia la MainActivity después de un retraso.
     *
     * @param savedInstanceState Si la actividad se reinicia después de una pausa o "stop", este es el último estado guardado.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // TextView para mostrar la versión de la aplicación.
        TextView versionTextView = findViewById(R.id.textView2); // Asegúrate de usar el id correcto de tu TextView
        try {
            // Obtiene el nombre de la versión de la aplicación.
            String versionName = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
            // Muestra el nombre de la versión en el TextView.
            versionTextView.setText("Versión " + versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        // Inicia la MainActivity después de un retraso.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 900); // 900 = 0.9 segundos
    }
}