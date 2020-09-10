package com.example.guireglogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.ToolbarWidgetWrapper;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.guireglogin.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private NavigationView nav_view;
    //need i explain ^

    private static final String TAG = "HomeActivity";
    private static final int ERROR_DIALOG_REQUEST = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = findViewById(R.id.toolbar);
        nav_view = findViewById(R.id.navigation_view);
        drawer = findViewById(R.id.drawer_layout);
        nav_view.setNavigationItemSelectedListener(this);
        //instansiearar allt som behövs instansiearas
        setSupportActionBar(toolbar);
        //om vi gör setSupportActionBar har vi en helt ny action bar som vi kommer ha mer kontroll över
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.nav_bar_open, R.string.nav_bar_close); // detta gör så att vi får typ en enum för att kunna öppna och stänga nav_baren
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)) //gravity compat gör så att den pushar ut eller in hela sliden utan att ändra storlek
            drawer.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();

        super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) { //on click listener för alla knappar i navbaren är ett interface(implements) med ett switchcase för att se vilken knapp som blir tryckt
        switch (item.getItemId()){
            case R.id.change_language:
                Toast.makeText(this, "you clicked language", Toast.LENGTH_LONG).show();
                break;
            case R.id.placeholder:
                Toast.makeText(this, "placeholder item nr 1", Toast.LENGTH_LONG).show();
                break;
            case R.id.placeholder1:
                Toast.makeText(this, "placeholder item nr 2", Toast.LENGTH_LONG).show();
                break;
            case R.id.maps_location:
                if(isServiceOK()){
                    init();
                }
                break;
        }
        return true;
    }

    //CHECKS IF THE CORRECT GOOGLE PLAY SERVICES IS INSTALLED
    public boolean isServiceOK(){
        Log.d(TAG,"is service OK: Checking Google servieces Version");
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(HomeActivity.this);
        //if there are no errors
        if(available == ConnectionResult.SUCCESS){
            //make map request
            Log.d(TAG, "Is Service OK: Google Play Services is working!");
            return true;
        }else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){ //if there is a resolvable error
            Log.d(TAG, "Is ServiceOK: A resolvable error has occurred!");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(HomeActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }else { //Unresolvable Error occurred
            Toast.makeText(this, "ERROR: Unresolvable error occurred!", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    public void init(){
                Intent intent = new Intent(HomeActivity.this, MapsActivity.class);
                startActivity(intent);
    }
}