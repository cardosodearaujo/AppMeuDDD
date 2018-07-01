package br.com.unclephill.meuddd.Views.Fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import br.com.unclephill.meuddd.API.API;
import br.com.unclephill.meuddd.Adapter.DDDCidadesAdapter;
import br.com.unclephill.meuddd.App.RecyclerViewTouchListenerApp;
import br.com.unclephill.meuddd.Object.DDDCityObject;
import br.com.unclephill.meuddd.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static br.com.unclephill.meuddd.App.FunctionsApp.closePgDialog;
import static br.com.unclephill.meuddd.App.FunctionsApp.modal;
import static br.com.unclephill.meuddd.App.FunctionsApp.showPgDialog;

public class CityFragment extends Fragment implements RecyclerViewTouchListenerApp.RecyclerViewOnClickListenerHack,
        NavigationView.OnNavigationItemSelectedListener,
        LoaderManager.LoaderCallbacks<Cursor> ,
        SwipeRefreshLayout.OnRefreshListener{

    private RecyclerView idRwDDDCidades;
    private Button idBtnCadastrar;
    private EditText idEdtCidade;

    public CityFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_city, container, false);
        this.inflar(view);
        return view;
    }

    private void inflar(View view){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        this.idEdtCidade = (EditText) view.findViewById(R.id.idEdtCidade);

        this.idRwDDDCidades = (RecyclerView) view.findViewById(R.id.idRwDDDCidades);
        this.idRwDDDCidades.setHasFixedSize(true);
        this.idRwDDDCidades.addOnItemTouchListener(new RecyclerViewTouchListenerApp(getContext(),this.idRwDDDCidades,this));
        this.idRwDDDCidades.setLayoutManager(linearLayoutManager);

        this.idBtnCadastrar = (Button) view.findViewById(R.id.idBtnConsultar);
        this.idBtnCadastrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (idEdtCidade.getText().toString().equals("")){
                    modal(getContext(),"Atenção!","Informe o Email do usuário!","OK");
                    idEdtCidade.requestFocus();
                    return;
                }

                showPgDialog(getContext());

                DDDCityObject dddCityObject = new DDDCityObject();
                dddCityObject.setCidade(idEdtCidade.getText().toString());
                geDDDFromAPI(dddCityObject);
            }
        });
    }

    private void geDDDFromAPI(DDDCityObject dddCityObject){
        API.getDDD(dddCityObject)
                .enqueue(new Callback<List<DDDCityObject>>() {
                    @Override
                    public void onResponse(Call<List<DDDCityObject>> call, Response<List<DDDCityObject>> response) {
                        closePgDialog();
                        if (response.isSuccessful()){
                            if (response.body().size() > 0){
                                idRwDDDCidades.setAdapter(new DDDCidadesAdapter(getContext(),response.body()));
                            }else{
                                modal(getContext(),"Atenção!",
                                        "Não foi possivel carregar as cidades. Tente novamente!",
                                        "OK");
                            }
                        }else{
                            modal(getContext(),
                                    "Atenção!",response.message(),
                                    "OK");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<DDDCityObject>> call, Throwable t) {
                        closePgDialog();
                        modal(getContext(),"Atenção!",
                                "Ocorreu um erro: " + t.getMessage() + ". Causa: " + t.getCause(),
                                "OK");
                    }
                });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onClickListener(View view, int position) {

    }

    @Override
    public void onLongPressClickListener(View view, int position) {

    }
}
