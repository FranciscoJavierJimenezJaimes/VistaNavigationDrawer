package franciscojavierjimenezjaimes.edu.tesoem.itics.agendasqlitefjjj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import franciscojavierjimenezjaimes.edu.tesoem.itics.agendasqlitefjjj.MainActivity.*;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout DrawerLayout;
    private ActionBarDrawerToggle Toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DrawerLayout = findViewById(R.id.drawer);
        Toggle = new ActionBarDrawerToggle(this, DrawerLayout, R.string.open, R.string.close);
        DrawerLayout.addDrawerListener(Toggle);
        NavigationView nd = findViewById(R.id.nd);
        Toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupDrawerContent(nd);
    }

    public  void selectItemDrawer(MenuItem menuItem){
        Fragment f= null;
        Class fragmentClass;
        switch (menuItem.getItemId()){
            case R.id.Add:
                fragmentClass = Agregar.class;
                break;
            case R.id.Sel:
                fragmentClass = Mostrar.class;
                break;
                default:
                    fragmentClass = Agregar.class;
        }
        try {
            f = (Fragment) fragmentClass.newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fcontenedor,f).commit();
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        DrawerLayout.closeDrawers();
        }
        private void setupDrawerContent(NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectItemDrawer(item);
                return true;
            }
        });
        }
        public boolean onOptionsItemSelected(MenuItem item){
        if (Toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
        }
}
