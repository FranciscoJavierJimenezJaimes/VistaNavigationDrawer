package franciscojavierjimenezjaimes.edu.tesoem.itics.agendasqlitefjjj;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import franciscojavierjimenezjaimes.edu.tesoem.itics.agendasqlitefjjj.BaseDatos.DatosConexion;
import franciscojavierjimenezjaimes.edu.tesoem.itics.agendasqlitefjjj.BaseDatos.DatosHelper.*;


    public class AgregarActivity extends AppCompatActivity {
        EditText txtnombre, txtedad, txtcorreo, txtcurp;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.fragment_agregar);

            txtnombre = findViewById(R.id.txtnombre);
            txtedad = findViewById(R.id.txtedad);
            txtcorreo = findViewById(R.id.txtcorreo);
            txtcurp = findViewById(R.id.txtcurp);

        }

        public void Registrar(View view) {
            ContentValues contentValues = new ContentValues();
            DatosConexion datosConexion = new DatosConexion(this);
            datosConexion.open();

            //contentValues.put(DatosHelper.TablaDatos.COLUMNA_NOMBRE,txtnombre.getText().toString());
            //contentValues.put(DatosHelper.TablaDatos.COLUMNA_EDAD,txtedad.getText().toString());
            //contentValues.put(DatosHelper.TablaDatos.COLUMNA_CORREO,txtcorreo.getText().toString());
            //contentValues.put(DatosHelper.TablaDatos.COLUMNA_CURP,txtcurp.getText().toString());

            contentValues.put("Nombre", txtnombre.getText().toString());
            contentValues.put("Edad", txtedad.getText().toString());
            contentValues.put("Correo", txtcorreo.getText().toString());
            contentValues.put("Curp", txtcurp.getText().toString());


            if (datosConexion.Insertar(contentValues)) {
                Toast.makeText(this, "Se Agergo el dato :D", Toast.LENGTH_SHORT).show();
                txtnombre.setText("");
                txtedad.setText("");
                txtcorreo.setText("");
                txtcurp.setText("");


            } else {
                Toast.makeText(this, "Error al Agregar", Toast.LENGTH_SHORT).show();

            }
        }
    }
