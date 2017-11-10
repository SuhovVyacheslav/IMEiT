package ru.vyaacheslav.suhov.imeit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Calendar;

import ru.vyaacheslav.suhov.imeit.Maps.MapsFragment;
import ru.vyaacheslav.suhov.imeit.OtherFragment.Info;
import ru.vyaacheslav.suhov.imeit.OtherFragment.TimeClock;

public class MainActivity extends AppCompatActivity {

    public NavigationView navigationView;
    public DrawerLayout drawerLayout;
    public FragmentTransaction FT;
    public RelativeLayout dexp;
    public FragmentManager FM;
    public Toolbar toolbar;
    public Toast toast;
    public java.util.Calendar calendar;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeWrithe);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        if (prefs.getBoolean("isFirstRun", true)) {
            Intent intent = new Intent(MainActivity.this, SettingsPref.class);
            startActivity(intent);
        } else {}
        prefs.edit().putBoolean("isFirstRun", false).apply();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        navigationView = (NavigationView) findViewById(R.id.shitstuff);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        calendar = java.util.Calendar.getInstance();
        dexp = (RelativeLayout) findViewById(R.id.dexs);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Костомизация меню. Вообщем ненужная хрень. Нужно убрать потом
        Menu menu = navigationView.getMenu();
        MenuItem tools2 = menu.findItem(R.id.tools2);
        SpannableString b = new SpannableString(tools2.getTitle());
        b.setSpan(new TextAppearanceSpan(this, R.style.MenuLine), 0, b.length(), 0); // Меняем цвет полоски разделяющей группы
        tools2.setTitle(b);

        FM = getSupportFragmentManager();
        FT = FM.beginTransaction();
        FT.replace(R.id.containerView, new TabFragment()).commit();

        // Обработка нажатий на пункты меню. Я все это знаю - передаю потомкам

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers();

                switch (item.getItemId()){

                    case R.id.main_tab:
                        loadName();
                        FragmentTransaction fragmentTransaction = FM.beginTransaction();
                        fragmentTransaction.replace(R.id.containerView, new TabFragment()).commit();
                        calendarT();
                        break;
                    case R.id.you_tab:
                        MainActivity.this.getSupportActionBar().setSubtitle("Время звонков");
                        FragmentTransaction fragmentTransaction1 = FM.beginTransaction();
                        fragmentTransaction1.replace(R.id.containerView, new TimeClock()).commit();
                        break;
                   /* case R.id.news:
                      *//*  isNetworkConnected();*//*
                        break;*/
                    case R.id.info:
                        MainActivity.this.getSupportActionBar().setSubtitle("Контакты");
                        FragmentTransaction fragmentTransaction2 = FM.beginTransaction();
                        fragmentTransaction2.replace(R.id.containerView, new Info()).commit();
                        break;
                    case R.id.map:
                        MainActivity.this.getSupportActionBar().setSubtitle("Учебные корпуса");
                        FragmentTransaction fragmentTransaction3 = FM.beginTransaction();
                        fragmentTransaction3.replace(R.id.containerView, new MapsFragment()).commit();
                        break;

                    case R.id.exam:
                        loadName();
                        FragmentTransaction fragmentTransaction4 = FM.beginTransaction();
                        fragmentTransaction4.replace(R.id.containerView, new Exzam()).commit();
                        break;

              /*      case R.id.about:
                        MainActivity.this.getSupportActionBar().setSubtitle("О приложении");
                        FragmentTransaction fragmentTransaction5 = FM.beginTransaction();
                        fragmentTransaction5.replace(R.id.containerView, new About()).commit();
                        break;*/
                    default:
                        break;
                }

                return false;
            }
        });

        calendarT();
        loadName(); // Загрузка имени группы согласно настройкам
        LoadPreferences(); //  Загрузка темы основной темы приложения
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu, menu);
        calendarT();
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                Intent intent = new Intent(MainActivity.this, SettingsPref.class);
                startActivity(intent);
                finish();
                return true;
            case R.id.ssas:
                calendarT();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    // Подзаголовок subTitle
    public void loadName() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this); // Название группы берется из String-array
        String regular = prefs.getString(getString(R.string.pref_style), "");
        MainActivity.this.getSupportActionBar().setSubtitle(regular);
    }

    public void calendarT() {

        Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
        int weekYear = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);

        menu = navigationView.getMenu();


        if ((weekYear == 45)) {
            Toast.makeText(getApplicationContext(), "Текущая неделя: Числитель", Toast.LENGTH_SHORT).show();
            menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.look4));
        }
        if ((weekYear == 46)) {
            Toast.makeText(getApplicationContext(), "Текущая неделя: Знаменатель", Toast.LENGTH_SHORT).show();
            menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.look3));
        }

        if ((weekYear == 47)) {
            Toast.makeText(getApplicationContext(), "Текущая неделя: Числитель", Toast.LENGTH_SHORT).show();
            menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.look4));
        }
        if ((weekYear == 48)) {
            Toast.makeText(getApplicationContext(), "Текущая неделя: Знаменатель", Toast.LENGTH_SHORT).show();
            menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.look3));
        }
        if ((weekYear == 49)) {
            Toast.makeText(getApplicationContext(), "Текущая неделя: Числитель", Toast.LENGTH_SHORT).show();
            menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.look4));
        }
        if ((weekYear == 50)) {
            Toast.makeText(getApplicationContext(), "Текущая неделя: Знаменатель", Toast.LENGTH_SHORT).show();
            menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.look3));
        }
        if ((weekYear == 51)) {
            Toast.makeText(getApplicationContext(), "Текущая неделя: Числитель", Toast.LENGTH_SHORT).show();
            menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.look4));
        }
        if ((weekYear == 52)) {
            Toast.makeText(getApplicationContext(), "Текущая неделя: Знаменатель", Toast.LENGTH_SHORT).show();
            menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.look3));
        }
        if ((weekYear == 53)) {
            Toast.makeText(getApplicationContext(), "Текущая неделя: Числитель", Toast.LENGTH_SHORT).show();
            menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.look4));
        }
        if ((weekYear == 1)) {
            Toast.makeText(getApplicationContext(), "Текущая неделя: Знаменатель", Toast.LENGTH_SHORT).show();
            menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.look3));
        }
        if ((weekYear == 2)) {
            Toast.makeText(getApplicationContext(), "Текущая неделя: Числитель", Toast.LENGTH_SHORT).show();
            menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.look4));
        }
        if ((weekYear == 3)) {
            Toast.makeText(getApplicationContext(), "Текущая неделя: Знаменатель", Toast.LENGTH_SHORT).show();
            menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.look3));
        }

    }


   /* private void isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni == null) {
            toast = Toast.makeText(getApplicationContext(), "Проверте интернет соединение", Toast.LENGTH_SHORT);
            toast.show();
            return;
        } else
            MainActivity.this.getSupportActionBar().setSubtitle("Новости");
        FragmentTransaction fragmentTransaction1 = FM.beginTransaction();
        fragmentTransaction1.replace(R.id.containerView, new FragmentN()).commit();
    }*/

    // Загрузка выбора темы приложения
    private void LoadPreferences() {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String regular = prefs.getString(getString(R.string.pref_theme), "");

        switch (regular) {
            case "Светлая":
                ThemeWrithe();
                break;
            case "Темная":
                ThemeDark();
                break;
        }
    }


    // Кастомизация тем  Светлая и Темная
    public void ThemeWrithe() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        navigationView.setBackgroundResource(R.color.colorWhitee);
        navigationView.setItemIconTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorTextBlack)));
        navigationView.setItemTextColor(ColorStateList.valueOf(getResources().getColor(R.color.colorTextBlack)));
        toolbar.setBackgroundResource(R.color.colorPrimary);
    }
    public void ThemeDark() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDarkD));
        }
        navigationView.setBackgroundResource(R.color.colorPrimarySS);
        navigationView.setItemIconTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorWhitee)));
        navigationView.setItemTextColor(ColorStateList.valueOf(getResources().getColor(R.color.colorWhitee)));
        toolbar.setBackgroundResource(R.color.colorPrimaryD);
    }
}