package com.kompassaviacion.englishvocabulary.model;

public class Term {

    String unit;
    String subject;
    String englishTerm;
    String spanishTerm;

    public Term() {
        this.unit = "";
        this.subject = "";
        this.englishTerm = "";
        this.spanishTerm = "";
    }

    public Term(String unit, String subject, String englishTerm, String spanishTerm) {
        this.unit = unit;
        this.subject = subject;
        this.englishTerm = englishTerm;
        this.spanishTerm = spanishTerm;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getsSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEnglishTerm() {
        return englishTerm;
    }

    public void setEnglishTerm(String englishTerm) {
        this.englishTerm = englishTerm;
    }

    public String getSpanishTerm() {
        return spanishTerm;
    }

    public void setSpanishTerm(String spanishTerm) {
        this.spanishTerm = spanishTerm;
    }

    @Override
    public String toString() {
        return "Term{" +
                "unit='" + unit + '\'' +
                ", subject='" + subject + '\'' +
                ", englishTerm='" + englishTerm + '\'' +
                ", spanishTerm='" + spanishTerm + '\'' +
                '}';
    }

}