package org.beginningandroid.gananciaapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;

/**
 * Esta clase representa un fragmento en la aplicación.
 */
public class FragmentOne extends Fragment {

    private EditText numero1;
    private EditText numero2;
    private TextView resultado;
    private RadioGroup radioGroup;
    private DecimalFormat df = new DecimalFormat("#,###");

    /**
     * Esta clase representa un fragmento en la aplicación.
     */
    public static FragmentOne newInstance() {
        return new FragmentOne();
    }

    /**
     * Método llamado cuando se crea el fragmento.
     * @param savedInstanceState Un paquete que contiene el estado guardado de la aplicación.
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Método llamado para crear la vista de este fragmento.
     * @param inflater El LayoutInflater que se utilizará para inflar cualquier vista en el fragmento.
     * @param container Si no es nulo, esta es la vista principal a la que se debe adjuntar el fragmento.
     * @param savedInstanceState Si no es nulo, este fragmento se está reconstruyendo a partir de un estado guardado anterior.
     * @return Devuelve la vista para la interfaz de usuario de este fragmento.
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);

        numero1 = view.findViewById(R.id.valorCompra);
        numero2 = view.findViewById(R.id.ganancia);
        resultado = view.findViewById(R.id.resultado);
        radioGroup = view.findViewById(R.id.radioGroup);

        Button calcular = view.findViewById(R.id.calcular);
        calcular.setOnClickListener(this::OnClick);

        numero1.addTextChangedListener(new NumberTextWatcher(numero1));
        numero2.addTextChangedListener(new NumberTextWatcher(numero2));

        Bundle args = getArguments();
        int selectedTab = 0;
        if (args != null) {
            selectedTab = args.getInt("selected_tab", 0);
        }

        return view;
    }

    /**
     * Clase interna para manejar el formato de los números ingresados en los campos de texto.
     */
    private class NumberTextWatcher implements TextWatcher {
        private EditText editText;

        NumberTextWatcher(EditText editText) {
            this.editText = editText;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            editText.removeTextChangedListener(this);

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
     * Método llamado cuando se hace clic en el botón calcular.
     * Realiza los cálculos y muestra el resultado en el campo de texto correspondiente.
     * @param view La vista que fue clickeada.
     */
    public void OnClick(View view){
        String valor1 = numero1.getText().toString().replace(",", "");
        String valor2 = numero2.getText().toString().replace(",", "");

        if (!valor1.isEmpty() && !valor2.isEmpty()) {
            int valorCompra = Integer.parseInt(valor1);
            int ganancia = Integer.parseInt(valor2);
            double calculo;

            int selectedId = radioGroup.getCheckedRadioButtonId();
            RadioButton selectedRadioButton = radioGroup.findViewById(selectedId);
            if (selectedRadioButton.getText().equals("Sin IVA")) {
                calculo = (valorCompra + ganancia) * 1.19;
            } else {
                calculo = ((valorCompra/1.19) + ganancia) * 1.19;
            }

            NumberFormat nf = NumberFormat.getInstance(new Locale("es", "ES"));
            String total = nf.format(calculo);
            resultado.setText("$" + total);
        }
    }
}
