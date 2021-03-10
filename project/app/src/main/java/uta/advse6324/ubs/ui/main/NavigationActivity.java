package uta.advse6324.ubs.ui.main;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager.widget.ViewPager;

import uta.advse6324.ubs.R;
import uta.advse6324.ubs.ui.main.profile.ProfileFragment;

public class NavigationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_club, R.id.navigation_market, R.id.navigation_profile)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    @Override
    protected void onResume() {
        //Return from the edit profile page
        //Redirected to Profile Fragment
        int id3 = this.getIntent().getIntExtra("VIEW_PROFILE",0);
        if (id3 == 4){
            BottomNavigationView navView = findViewById(R.id.nav_view);
            navView.setSelectedItemId(navView.getMenu().getItem(id3-1).getItemId());
        }
        int id0 = this.getIntent().getIntExtra("INFORMATION_DETAIL",0);
        if (id0 == 1){
            BottomNavigationView navView = findViewById(R.id.nav_view);
            navView.setSelectedItemId(navView.getMenu().getItem(id0-1).getItemId());
        }
        super.onResume();
    }
}