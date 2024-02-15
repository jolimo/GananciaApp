package org.beginningandroid.gananciaapp;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase es un adaptador para manejar una lista de fragmentos y sus títulos.
 * Extiende de FragmentPagerAdapter que es una clase base que proporciona el adaptador para llenar páginas dentro de un ViewPager.
 */
public class TabFragmentAdapter extends FragmentPagerAdapter {
    // Lista para contener los fragmentos
    private final List<Fragment> fragmentList = new ArrayList<>();
    // Lista para contener los títulos de los fragmentos
    private final List<String> fragmentTitleList = new ArrayList<>();

    /**
     * Constructor para la clase TabFragmentAdapter.
     * @param fm FragmentManager para manejar fragmentos.
     */
    public TabFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * Devuelve el Fragment asociado con una posición especificada.
     * @param i La posición del elemento en el adaptador.
     * @return El Fragment asociado con la posición especificada.
     */
    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    /**
     * Devuelve el número total de elementos en la lista.
     * @return El número total de elementos en la lista.
     */
    @Override
    public int getCount() {
        return fragmentList.size();
    }

    /**
     * Devuelve el título de la página para el indicador superior.
     * @param position La posición del título en la lista.
     * @return El título de la página en la posición especificada.
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitleList.get(position);
    }

    /**
     * Agrega un fragmento y su título a la lista.
     * @param fragment El fragmento a agregar.
     * @param title El título del fragmento a agregar.
     */
    public void addFragment(Fragment fragment, String title) {
        fragmentList.add(fragment);
        fragmentTitleList.add(title);
    }
}
