package franciscojavierjimenezjaimes.edu.tesoem.itics.agendasqlitefjjj.BaseDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import franciscojavierjimenezjaimes.edu.tesoem.itics.agendasqlitefjjj.BaseDatos.DatosConexion.*;
import franciscojavierjimenezjaimes.edu.tesoem.itics.agendasqlitefjjj.BaseDatos.DatosHelper.*;

public class DatosConexion {

    public SQLiteDatabase basedatos;
    public DatosHelper datosHelper;

    public DatosConexion(Context context) {
        datosHelper = DatosHelper.getInstance(context);
    }

    public void open() {
        basedatos = datosHelper.getWritableDatabase();

    }

    public void close() {
        basedatos.close();
    }

   /* public String[] llenarGridView() {
        String datos[] = {"id", "nombre", "edad", "correo", "curp"};
        List muestradatos = new ArrayList();
        int columna=5;

        Cursor cursor = basedatos.rawQuery("SELECT * FROM "+ TablaDatos.TABLA,null);

        if (cursor.getCount() <= 0){
            datos = new String[5];

            datos[0] = TablaDatos.COLUMNA_ID;
            datos[1] = TablaDatos.COLUMNA_NOMBRE;
            datos[2] = TablaDatos.COLUMNA_EDAD;
            datos[3] = TablaDatos.COLUMNA_CORREO;
            datos[4] = TablaDatos.COLUMNA_CURP;
        }else{
            datos = new String[(cursor.getCount()*5)+5];
            datos[0] = TablaDatos.COLUMNA_ID;
            datos[1] = TablaDatos.COLUMNA_NOMBRE;
            datos[2] = TablaDatos.COLUMNA_EDAD;
            datos[3] = TablaDatos.COLUMNA_CORREO;
            datos[4] = TablaDatos.COLUMNA_CURP;
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                datos[columna] = String.valueOf(cursor.getInt(0));
                datos[columna+1] = cursor.getString(1);
                datos[columna+2] = String.valueOf(cursor.getInt(2));
                datos[columna+3] = cursor.getString(3);
                datos[columna+4] = cursor.getString(4);
                columna+=5;
                cursor.moveToNext();
            }

        }
        return datos;
    }
*/

    public boolean Insertar(ContentValues contentValues) {
        boolean estado = true;
        basedatos.isOpen();
        int resultadoconsulta = (int) basedatos.insert(TablaDatos.TABLA, null, contentValues);
        if (!(resultadoconsulta > 0)) estado = false;
        basedatos.close();
        return estado;
    }



}

