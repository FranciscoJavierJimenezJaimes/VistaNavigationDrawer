package franciscojavierjimenezjaimes.edu.tesoem.itics.agendasqlitefjjj.BaseDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import franciscojavierjimenezjaimes.edu.tesoem.itics.agendasqlitefjjj.DatosGS.DatosGS;

public class DatosHelper extends SQLiteOpenHelper {

    private static DatosHelper datosHelper = null;

    public static String NOMBRE_BD="Agenda7T11FJJJ.db";
    public static int VERSION_BD=1;

    //datos que llevara la base de datos
    public static class TablaDatos{
        public static String TABLA="TablaAgenda";
        public static String COLUMNA_ID="id";
        public static String COLUMNA_NOMBRE="nombre";
        public static String COLUMNA_EDAD="edad";
        public static String COLUMNA_CORREO="correo";
        public static String COLUMNA_CURP="curp";
    }
    public static final String CONSULTA_CREAR_TABLA = "CREATE TABLE " +
            TablaDatos.TABLA + " (" + TablaDatos.COLUMNA_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "+
            TablaDatos.COLUMNA_NOMBRE + " VARCHAR(200), "+ TablaDatos.COLUMNA_EDAD + " INTEGER, " +
            TablaDatos.COLUMNA_CORREO + "  VARCHAR(200)," + TablaDatos.COLUMNA_CURP + " VARCHAR(200));";

    public static final  String CONSULTA_ELIMINAR_TABLA="DROP TABLE IF EXISTS "+ TablaDatos.TABLA;

    public static DatosHelper getInstance(Context context){
        if (datosHelper == null){
            datosHelper = new DatosHelper(context.getApplicationContext());
        }
        return datosHelper;
    }

    public DatosHelper(@Nullable Context context){
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL(CONSULTA_CREAR_TABLA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL(CONSULTA_ELIMINAR_TABLA);
    onCreate(db);
    }
   public List llenarGridView() {
        String datos[] = {"id", "nombre", "edad", "correo", "curp"};
        Cursor cursor = this.getReadableDatabase().query("TablaAgenda",null,null,null,null,null,null);
        List muestradatos = new ArrayList();

        int Iid, Inombre, Iedad, Icorreo, Icurp;
        Iid = cursor.getColumnIndex(TablaDatos.COLUMNA_ID);
        Inombre = cursor.getColumnIndex(TablaDatos.COLUMNA_NOMBRE);
        Iedad = cursor.getColumnIndex(TablaDatos.COLUMNA_EDAD);
        Icorreo = cursor.getColumnIndex(TablaDatos.COLUMNA_CORREO);
        Icurp = cursor.getColumnIndex(TablaDatos.COLUMNA_CURP);


        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            DatosGS datosGS = new DatosGS();
            datosGS.setId(cursor.getInt(Iid));
            datosGS.setNombre(cursor.getString(Inombre));
            datosGS.setEdad(cursor.getInt(Iedad));
            datosGS.setCorreo(cursor.getString(Icorreo));
            datosGS.setCurp(cursor.getString(Icurp));

            muestradatos.add(datosGS);

        }
        return muestradatos;
    }
    public boolean Update(DatosGS datosGS){
        String[] valores = {String.valueOf(datosGS.getId())};

        ContentValues datos = new ContentValues();
        int res;

        datos.put("nombre",datosGS.getNombre());
        datos.put("edad",datosGS.getEdad());
        datos.put("correo",datosGS.getCorreo());

        res = this.getReadableDatabase().update("TablaAgenda",datos,"id=?",valores);

        if (res>0) return  true;
        else {
            return false;
        }
    }
    public boolean Delete(DatosGS datosGS){
        String[] valores = {String.valueOf(datosGS.getId())};

        ContentValues datos = new ContentValues();
        int res;

        datos.put("nombre",datosGS.getNombre());
        datos.put("edad",datosGS.getEdad());
        datos.put("correo",datosGS.getCorreo());

        res = (int) getWritableDatabase().delete(TablaDatos.TABLA, TablaDatos.COLUMNA_ID+"=?",valores);
        if (res>0) return  true;
        else {
            return false;
        }
    }
}

   //FUNCIONA PERO NO MUESTRA LOS REGISTROS CHECAR
   /*public String[] llenarGridView() {
       String datos[] = {"id", "nombre", "edad", "correo", "curp"};
       List muestradatos = new ArrayList();
       int columna=5;

        Cursor cursor = this.getReadableDatabase().query(TablaDatos.TABLA,null,null,null,null,null,null);

      int Iid, Inombre, Iedad, Icorreo, Icurp;
       Iid = cursor.getColumnIndex(TablaDatos.COLUMNA_ID);
       Inombre = cursor.getColumnIndex(TablaDatos.COLUMNA_NOMBRE);
       Iedad = cursor.getColumnIndex(TablaDatos.COLUMNA_EDAD);
       Icorreo = cursor.getColumnIndex(TablaDatos.COLUMNA_CORREO);
       Icurp = cursor.getColumnIndex(TablaDatos.COLUMNA_CURP);


       if (cursor.moveToFirst()){
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

           while (cursor.moveToNext()){
               datos[columna] = String.valueOf(cursor.getInt(0));
               datos[columna+1] = cursor.getString(1);
               datos[columna+2] = String.valueOf(cursor.getInt(2));
               datos[columna+3] = cursor.getString(3);
               datos[columna+4] = cursor.getString(4);
               columna+=5;
               DatosGS datosGS = new DatosGS();
               datosGS.setId(cursor.getInt(Iid));
               datosGS.setNombre(cursor.getString(Inombre));
               datosGS.setEdad(cursor.getInt(Iedad));
               datosGS.setCorreo(cursor.getString(Icorreo));
               datosGS.setCurp(cursor.getString(Icurp));
               muestradatos.add(datosGS);

           }

       }
       return datos;
    }*/
