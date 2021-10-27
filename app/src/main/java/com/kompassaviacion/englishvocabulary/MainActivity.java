package com.kompassaviacion.englishvocabulary;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kompassaviacion.englishvocabulary.ctrl.Game;

public class MainActivity extends AppCompatActivity {

    public static AppCompatActivity main;

    public static EditText txtIntent;
    public static TextView lblResult;
    public static TextView lblQuest;
    public static TextView lblUnit;
    public static TextView lblSubject;

    public static ImageView imgFlag;
    public static ImageView imgFlagQuestion;
    public static ImageView imgFlagAnswer;

    public static TextView lblCorrectQuestion;
    public static TextView lblCorrectAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        main = this;

        setContentView(R.layout.activity_main);

        lblUnit = findViewById(R.id.lblUnit);
        lblSubject = findViewById(R.id.lblSubject);
        lblQuest = findViewById(R.id.lblQuest);
        txtIntent = findViewById(R.id.txtIntent);
        lblResult = findViewById(R.id.lblResult);
        lblCorrectQuestion = findViewById(R.id.lblCorrectQuestion);
        lblCorrectAnswer = findViewById(R.id.lblCorrectAnswer);

        imgFlag = findViewById(R.id.imgFlag);
        imgFlagQuestion = findViewById(R.id.imgFlagQuestion);
        imgFlagAnswer = findViewById(R.id.imgFlagAnswer);

        String url = "https://kompassaviacion.com/english/vocabulary.txt";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                s -> {
                    Game.generateVocabulary(s);
                    Game.setUnitVisibility(0, true);
                    Game.setLanguageMode(0);
                },
                volleyError -> Toast.makeText(this, volleyError.getMessage(), Toast.LENGTH_LONG).show());
        Volley.newRequestQueue(this).add(stringRequest);

        findViewById(R.id.linearLayoutResult).setVisibility(View.INVISIBLE);

        findViewById(R.id.btnCheck).setOnClickListener(v -> {
            findViewById(R.id.linearLayoutResult).setVisibility(View.VISIBLE);
            Game.check();});

        findViewById(R.id.lblMeetMe).setOnClickListener(v-> {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/miguelangelcifredo/"));
            startActivity(i);
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        menu.findItem(R.id.eng_esp).setChecked(Game.getLanguageMode() == 1);
        menu.findItem(R.id.esp_eng).setChecked(Game.getLanguageMode() == 2);
        menu.findItem(R.id.both).setChecked(Game.getLanguageMode() == 0);

        menu.findItem(R.id.deleteCorrects).setChecked(Game.getDeleteCorrects());

        menu.findItem(R.id.unit00).setChecked(Game.getUnitVisibility(0));
        menu.findItem(R.id.unit01).setChecked(Game.getUnitVisibility(1));
        menu.findItem(R.id.unit02).setChecked(Game.getUnitVisibility(2));
        menu.findItem(R.id.unit03).setChecked(Game.getUnitVisibility(3));
        menu.findItem(R.id.unit04).setChecked(Game.getUnitVisibility(4));
        menu.findItem(R.id.unit05).setChecked(Game.getUnitVisibility(5));
        menu.findItem(R.id.unit06).setChecked(Game.getUnitVisibility(6));
        menu.findItem(R.id.unit07).setChecked(Game.getUnitVisibility(7));
        menu.findItem(R.id.unit08).setChecked(Game.getUnitVisibility(8));

        menu.findItem(R.id.unit01).setEnabled(Game.existsUnit(1));
        menu.findItem(R.id.unit02).setEnabled(Game.existsUnit(2));
        menu.findItem(R.id.unit03).setEnabled(Game.existsUnit(3));
        menu.findItem(R.id.unit04).setEnabled(Game.existsUnit(4));
        menu.findItem(R.id.unit05).setEnabled(Game.existsUnit(5));
        menu.findItem(R.id.unit06).setEnabled(Game.existsUnit(6));
        menu.findItem(R.id.unit07).setEnabled(Game.existsUnit(7));
        menu.findItem(R.id.unit08).setEnabled(Game.existsUnit(8));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.deleteCorrects: Game.setDeleteCorrects(!Game.getDeleteCorrects()); break;

            case R.id.both:    Game.setLanguageMode(0); break;
            case R.id.eng_esp: Game.setLanguageMode(1); break;
            case R.id.esp_eng: Game.setLanguageMode(2); break;

            case R.id.unit00: Game.setUnitVisibility(0, !Game.getUnitVisibility(0)); break;
            case R.id.unit01: Game.setUnitVisibility(1, !Game.getUnitVisibility(1)); break;
            case R.id.unit02: Game.setUnitVisibility(2, !Game.getUnitVisibility(2)); break;
            case R.id.unit03: Game.setUnitVisibility(3, !Game.getUnitVisibility(3)); break;
            case R.id.unit04: Game.setUnitVisibility(4, !Game.getUnitVisibility(4)); break;
            case R.id.unit05: Game.setUnitVisibility(5, !Game.getUnitVisibility(5)); break;
            case R.id.unit06: Game.setUnitVisibility(6, !Game.getUnitVisibility(6)); break;
            case R.id.unit07: Game.setUnitVisibility(7, !Game.getUnitVisibility(7)); break;
            case R.id.unit08: Game.setUnitVisibility(8, !Game.getUnitVisibility(8)); break;

        }
        return true;
    }


}