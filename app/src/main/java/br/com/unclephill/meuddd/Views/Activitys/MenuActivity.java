package br.com.unclephill.meuddd.Views.Activitys;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.unclephill.meuddd.App.FunctionsApp;
import br.com.unclephill.meuddd.App.RecyclerViewTouchListenerApp;
import br.com.unclephill.meuddd.R;
import br.com.unclephill.meuddd.Views.Fragments.CityFragment;
import br.com.unclephill.meuddd.Views.Fragments.DDDFragment;

public class MenuActivity extends AppCompatActivity{

    private TabLayout idTabLayout;
    private ViewPager idViewPager;
    private TextView idTxwNomeUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.idViewPager = (ViewPager) findViewById(R.id.idViewPager);
        this.setSetupViewPager(this.idViewPager);
        this.idTabLayout = (TabLayout) findViewById(R.id.idTabLayout);
        this.idTabLayout.setupWithViewPager(this.idViewPager);

        this.idTxwNomeUsuario = (TextView) findViewById(R.id.idTxwNomeUsuario);
        this.idTxwNomeUsuario.setText("Usuário: " + FunctionsApp.User.getNome());
    }

    private void setSetupViewPager(ViewPager viewPager){
        PagerRegras adapter = new PagerRegras(getSupportFragmentManager());
        adapter.addFragment(new DDDFragment(),"POR DDD");
        adapter.addFragment(new CityFragment(),"POR CIDADE");
        this.idViewPager.setAdapter(adapter);
    }

    public static class PlaceholderFragment extends Fragment {
        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int position) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_ddd, container, false);
            return rootView;
        }
    }

    public class PagerRegras extends FragmentPagerAdapter {
        private final List<Fragment> lFragmentList = new ArrayList<>();
        private final List<String> lFragmentTitleList = new ArrayList<>();

        public PagerRegras(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return lFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return lFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position){
            return lFragmentTitleList.get(position);
        }

        public void addFragment(Fragment fragment, String title){
            lFragmentList.add(fragment);
            lFragmentTitleList.add(title);
        }
    }

    public void onClickLogOut(View view){
        FunctionsApp.fecharActivity(MenuActivity.this);
    }

    public void onClickConsultarCidade(View view){
        Toast.makeText(MenuActivity.this,"Cidade", Toast.LENGTH_SHORT).show();
    }
}
