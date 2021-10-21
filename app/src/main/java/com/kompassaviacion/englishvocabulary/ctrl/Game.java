package com.kompassaviacion.englishvocabulary.ctrl;

import android.widget.Toast;

import com.kompassaviacion.englishvocabulary.MainActivity;
import com.kompassaviacion.englishvocabulary.model.Term;

import java.util.ArrayList;
import java.util.Random;

public class Game {

    private static int numQuestion;
    private static int languageMode = 0;
    private static boolean deleteCorrects = false;
    private static String answerCorrect;

    private static final boolean[] unit = new boolean[10];

    private static final ArrayList<Term> vocabulary = new ArrayList<>();

    public static void generateVocabulary(String str) {
        String[] s = str.split("\n");
        for (String value : s) {
            String[] field = value.split("#");
            vocabulary.add(new Term(field[0], field[1], field[2], field[3]));
        }
    }

    public static void setLanguageMode(int value) {
        languageMode = value;
        playNewTerm();
    }

    public static int getLanguageMode() {
        return languageMode;
    }

    public static String getQuestionTerm() {
        String q;
        int lq = languageMode;
        if (lq == 0) {
            lq = new Random().nextInt(2) + 1;
        }
        if (lq == 1) {
            q = vocabulary.get(numQuestion).getEnglishTerm();
            answerCorrect = vocabulary.get(numQuestion).getSpanishTerm();
        } else {
            q = vocabulary.get(numQuestion).getSpanishTerm();
            answerCorrect = vocabulary.get(numQuestion).getEnglishTerm();
        }
        return q;
    }

    public static String getAnswerCorrect() {
        return answerCorrect;
    }

    public static boolean getDeleteCorrects() {
        return deleteCorrects;
    }

    public static void setDeleteCorrects(boolean mode) {
        deleteCorrects = mode;
    }

    public static void setUnitVisibility(int u, boolean state) {
        unit[u] = state;
        if (u == 0 & state) {
            for (int i = 1; i < unit.length; i++)
                setUnitVisibility(i, false);
        }
        if (u != 0 & state) {
            setUnitVisibility(0, false);
        }
        int cont = 0;
        for (int i = 0; i < unit.length; i++) {
            if (getUnitVisibility(i))
                cont++;
        }
        if (cont == 0)
            setUnitVisibility(0, true);

        playNewTerm();

    }

    public static boolean getUnitVisibility(int u) {
        return unit[u];
    }

    public static void generateNumQuestion() {

        if (vocabulary.size() == 0 || numQuestion==vocabulary.size()) {
            System.exit(0);
        }
        boolean esValido;
        do {
            numQuestion = new Random().nextInt(vocabulary.size() - 1) + 1;
            esValido = false;
            if (getUnitVisibility(0))
                esValido = true;
            else
                esValido = getUnitVisibility(Integer.parseInt(vocabulary.get(numQuestion).getUnit()));
        } while (!esValido);
    }

    public static void check() {
        String correct = getAnswerCorrect().trim().toLowerCase();
        String question = MainActivity.lblQuest.getText().toString().trim().toLowerCase();
        String intent = MainActivity.txtIntent.getText().toString().trim().toLowerCase();
        String texto = "";
        if (intent.equals(correct)) {
            texto = "CORRECT !";
            if (getDeleteCorrects()) {
                vocabulary.remove(numQuestion);
            }
        } else {
            if (intent.length() > 0) {
                texto = "ERROR\n";
            }
            texto += question + " : " + correct;
        }
        MainActivity.txtIntent.setText("");
        MainActivity.lblResult.setText(texto);

        playNewTerm();

    }

    public static void playNewTerm() {
        generateNumQuestion();
        MainActivity.lblUnit.setText("Unit " + vocabulary.get(numQuestion).getUnit());
        MainActivity.lblSubject.setText("Subject: " + vocabulary.get(numQuestion).getsSubject());
        MainActivity.lblQuest.setText(getQuestionTerm());
    }

    public static boolean existsUnit(int u) {
        boolean existe = false;
        try {
            int cont = 0;
            do {
                String unidad = "" + u;
                String uni = vocabulary.get(cont).getUnit();
                existe = uni.equals(unidad);
                cont++;
            } while (cont < vocabulary.size() && !existe);
        }catch (Exception e) {}
        return existe;
    }
}