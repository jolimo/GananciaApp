package org.beginningandroid.gananciaapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.view.View;
import android.widget.FrameLayout;
import com.google.android.material.tabs.TabLayout;

/**
 * Esta es la actividad principal de la aplicación.
 * Contiene un ViewPager para cambiar entre fragmentos y un TabLayout para mostrar las pestañas correspondientes a cada fragmento.
 */
public class MainActivity extends AppCompatActivity {

    TabLayout mTabs;
    View mIndicator;
    ViewPager mViewPager;

    private int indicatorWidth;

    /**
     * Método llamado cuando se crea la actividad.
     * @param savedInstanceState Un paquete que contiene el estado guardado de la aplicación.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Asigna las referencias de las vistas
        mTabs = findViewById(R.id.tab);
        mIndicator = findViewById(R.id.indicator);
        mViewPager = findViewById(R.id.viewPager);

        // Configura el ViewPager y los fragmentos
        TabFragmentAdapter adapter = new TabFragmentAdapter(getSupportFragmentManager());
        adapter.addFragment(FragmentOne.newInstance(), "Ganancias");
        adapter.addFragment(FragmentTwo.newInstance(), "Valor Clientes");
        mViewPager.setAdapter(adapter);
        mTabs.setupWithViewPager(mViewPager);

        // Determina el ancho del indicador en tiempo de ejecución
        mTabs.post(new Runnable() {
            @Override
            public void run() {
                indicatorWidth = mTabs.getWidth() / mTabs.getTabCount();

                // Asigna el nuevo ancho
                FrameLayout.LayoutParams indicatorParams = (FrameLayout.LayoutParams) mIndicator.getLayoutParams();
                indicatorParams.width = indicatorWidth;
                mIndicator.setLayoutParams(indicatorParams);
            }
        });

        // Agrega un listener al ViewPager para mover el indicador cuando el usuario se desplaza entre páginas
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            //Para mover el indicador a medida que el usuario se desplaza, necesitaremos los valores de desplazamiento
            // positionOffset es un valor de [0..1] que representa cuánto se ha desplazado la página
            @Override
            public void onPageScrolled(int i, float positionOffset, int positionOffsetPx) {
                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams)mIndicator.getLayoutParams();

                // Multiplica positionOffset con indicatorWidth para obtener la traducción
                float translationOffset =  (positionOffset+i) * indicatorWidth ;
                params.leftMargin = (int) translationOffset;
                mIndicator.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
}