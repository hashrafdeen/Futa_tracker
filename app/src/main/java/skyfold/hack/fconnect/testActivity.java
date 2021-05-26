package skyfold.hack.fconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;

public class testActivity extends FragmentActivity implements OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
     Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    LocationManager locationManager;
    Location currentLocation;
    TextView textView;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_test);

        drawerLayout = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationView);
        View header = navigationView.getHeaderView(0);
        navigationView.setNavigationItemSelectedListener(this);
        textView = header.findViewById(R.id.textEmail);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawerOpen,R.string.drawerClose);
        toolbar.setTitle("FUTA Connect");



        //Objects.requireNonNull(getActionBar()).setDisplayShowHomeEnabled(true);
        //toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawerOpen, R.string.drawerClose);
        drawerLayout.addDrawerListener(toggle);
       drawerLayout.setDrawerListener(toggle);
        toggle.syncState();


        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fetchLastLocation();


    }



    private void fetchLastLocation() {

        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener((new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location !=null){
                    currentLocation = location;
                    Toast.makeText(getApplicationContext(),currentLocation.getLatitude()+"  "+currentLocation.getLongitude(),Toast.LENGTH_SHORT).show();
                    SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);
                    supportMapFragment.getMapAsync(testActivity.this);

                }
            }
        }));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng latLng = new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("Am here");
        googleMap.addMarker(markerOptions);
        googleMap.animateCamera((CameraUpdateFactory.newLatLng(latLng)));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng ,18));




    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_CODE:
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    fetchLastLocation();

                }
                break;
        }
    }


    @Override
    public boolean onNavigationItemSelected( MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Profile:
                Intent intent_profile = new Intent(testActivity.this , profile_activity.class);
                startActivity(intent_profile);
                Toast.makeText(this," Profile Selected",Toast.LENGTH_SHORT).show();
                break;
            case R.id.chat:
                Intent intent = new Intent(testActivity.this , chat_activity.class);
                startActivity(intent);
                Toast.makeText(this," Contact Us Selected",Toast.LENGTH_SHORT).show();
                break;
            case R.id.track:
                Intent intent_track = new Intent(testActivity.this , track_activity.class);
                startActivity(intent_track);
                Toast.makeText(this,"Track Us Selected ",Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout:
                Toast.makeText(this," Logout Selected",Toast.LENGTH_SHORT).show();
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}


  /*  int itemId;
        itemId = item.getItemId();
                if (itemId == R.id.Profile) {
                Toast.makeText(this, " Profile Selected", Toast.LENGTH_SHORT).show();
                } else if (itemId == R.id.chat) {
                Toast.makeText(this, " Contact Us Selected", Toast.LENGTH_SHORT).show();
                } else if (itemId == R.id.about) {
                Toast.makeText(this, "About Us Selected ", Toast.LENGTH_SHORT).show();
                } else if (itemId == R.id.logout) {
                Toast.makeText(this, " Logout Selected", Toast.LENGTH_SHORT).show();
                }
                return true;
                }
*/

/*
 switch (item.getItemId()) {
           case R.id.Profile:
               Toast.makeText(this," Profile Selected",Toast.LENGTH_SHORT).show();
               break;
           case R.id.chat:
           Toast.makeText(this," Contact Us Selected",Toast.LENGTH_SHORT).show();
           break;
            case R.id.about:
           Toast.makeText(this,"About Us Selected ",Toast.LENGTH_SHORT).show();
           break;
            case R.id.logout:
           Toast.makeText(this," Logout Selected",Toast.LENGTH_SHORT).show();
           break;

        }
        return true;
    }
 */
