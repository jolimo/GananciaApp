package org.beginningandroid.gananciaapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * La clase MainActivity representa la actividad principal de la aplicación.
 * Esta clase se encarga de manejar las interacciones del usuario con la interfaz de la aplicación.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * EditText para el primer número.
     */
    private EditText numero1;

    /**
     * EditText para el segundo número.
     */
    private EditText numero2;

    /**
     * TextView para mostrar el resultado.
     */
    private TextView resultado;

    /**
     * RadioGroup para seleccionar la opción de cálculo.
     */
    private RadioGroup radioGroup;

    /**
     * DecimalFormat para formatear los números.
     */
    private DecimalFormat df = new DecimalFormat("#,###");

    /**
     * Este método se llama cuando se crea la actividad.
     * Inicializa la interfaz de usuario y establece los listeners de los eventos.
     *
     * @param savedInstanceState Si la actividad se reinicia después de una pausa o "stop", este es el último estado guardado.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializa los componentes de la interfaz de usuario.
        numero1 = findViewById(R.id.valorCompra);
        numero2 = findViewById(R.id.ganancia);
        resultado = findViewById(R.id.resultado);
        radioGroup = findViewById(R.id.radioGroup);

        // Establece el listener del botón calcular.
        Button calcular = findViewById(R.id.calcular);
        calcular.setOnClickListener(this::OnClick);

        // Agrega TextWatchers a los EditText para manejar los cambios de texto.
        numero1.addTextChangedListener(new NumberTextWatcher(numero1));
        numero2.addTextChangedListener(new NumberTextWatcher(numero2));

        //Obtiene el TabLayout desde el layout.
        TabLayout tabLayout = findViewById(R.id.tabLayout);

        /**
         * Establece si el indicador de la pestaña debe estirarse para cubrir todo el ancho de la pestaña.
         * Si se establece en true, el color del indicador se establecerá en null.
         */
        tabLayout.setTabIndicatorFullWidth(true);

        // Selecciona el TabItem con el id 'venta' como el seleccionado por defecto.
        TabLayout.Tab ventaTab = tabLayout.getTabAt(0); // Asume que 'venta' es el primer TabItem.
        if (ventaTab != null) {
            ventaTab.select();
        }
    }

    /**
     * Clase interna para manejar los cambios de texto en los EditText.
     */
    private class NumberTextWatcher implements TextWatcher {
        /**
         * EditText asociado a este TextWatcher.
         */
        private EditText editText;

        /**
         * Constructor de la clase.
         *
         * @param editText EditText asociado a este TextWatcher.
         */
        NumberTextWatcher(EditText editText) {
            this.editText = editText;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        /**
         * Este método se llama después de que el texto ha cambiado.
         * Formatea el texto del EditText para mostrarlo con comas como separadores de miles.
         *
         * @param s El texto después del cambio.
         */
        @Override
        public void afterTextChanged(Editable s) {
            editText.removeTextChangedListener(this);

            // Limpia el texto y lo formatea con comas como separadores de miles.
            String cleanString = s.toString().replaceAll("[^\\d]", "");
            if (!cleanString.isEmpty()) {
                double parsed = Double.parseDouble(cleanString);
                String formatted = df.format(parsed);

                editText.setText(formatted);
                editText.setSelection(formatted.length());
            }

            editText.addTextChangedListener(this);
        }
    }

    /**
     * Este método se llama cuando el usuario hace clic en el botón calcular.
     * Realiza los cálculos necesarios y actualiza la interfaz de usuario con los resultados.
     *
     * @param view La vista que fue clickeada.
     */
    public void OnClick(View view){
        // Obtiene los valores de los EditText y los limpia de comas.
        String valor1 = numero1.getText().toString().replace(",", "");
        String valor2 = numero2.getText().toString().replace(",", "");

        // Realiza los cálculos si los valores no están vacíos.
        if (!valor1.isEmpty() && !valor2.isEmpty()) {
            int primerNumero = Integer.parseInt(valor1);
            int segundoNumero = Integer.parseInt(valor2);
            double calculo;

            // Obtiene el RadioButton seleccionado y realiza el cálculo correspondiente.
            int selectedId = radioGroup.getCheckedRadioButtonId();
            RadioButton selectedRadioButton = findViewById(selectedId);
            if (selectedRadioButton.getText().equals("Sin IVA")) {
                calculo = (primerNumero + segundoNumero) * 1.19;
            } else {
                calculo = ((primerNumero/1.19) + segundoNumero) * 1.19;
            }

            // Formatea el resultado con comas como separadores de miles y lo muestra en el TextView.
            NumberFormat nf = NumberFormat.getInstance(new Locale("es", "ES"));
            String total = nf.format(calculo);
            resultado.setText("$" + total);
        }
    }
}
