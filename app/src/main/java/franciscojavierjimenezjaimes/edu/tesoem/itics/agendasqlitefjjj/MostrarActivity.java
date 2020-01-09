package franciscojavierjimenezjaimes.edu.tesoem.itics.agendasqlitefjjj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import franciscojavierjimenezjaimes.edu.tesoem.itics.agendasqlitefjjj.BaseDatos.DatosConexion;

import franciscojavierjimenezjaimes.edu.tesoem.itics.agendasqlitefjjj.BaseDatos.DatosHelper;
import franciscojavierjimenezjaimes.edu.tesoem.itics.agendasqlitefjjj.BaseDatos.DatosHelper.*;
import franciscojavierjimenezjaimes.edu.tesoem.itics.agendasqlitefjjj.DatosGS.DatosGS;

public class MostrarActivity extends AppCompatActivity {
    //TextView cntxtid, cntxtnombre, cntxtedad, cntxtcorreo;
    String[] datosgrid;
    GridView GridView;
    ArrayAdapter<String> adapter;
    String[] lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_mostrar);


        GridView = findViewById(R.id.GridView);
        llenado();
    }

    public void llenado() {
        DatosHelper con = new DatosHelper(this);
        List lista = con.llenarGridView();
        datosgrid = new String[lista.size() * 5];
        int counter = 0;
        for (Object datos : lista) {
            DatosGS element = (DatosGS) datos;
            datosgrid[counter] = String.valueOf(element.getId());
            datosgrid[counter + 1] = element.getNombre();
            datosgrid[counter + 2] = String.valueOf(element.getEdad());
            datosgrid[counter + 3] = element.getCorreo();
            datosgrid[counter + 4] = element.getCurp();

            counter += 5;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datosgrid);
        GridView.setAdapter(adapter);
    }



    /*public boolean number(String value){
        try {
            int P = Integer.valueOf(value);

        }catch (NumberFormatException e){
            return false;
        }
        return true;

    }

     */
}
