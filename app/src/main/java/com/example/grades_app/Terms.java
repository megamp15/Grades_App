package com.example.grades_app;

public class Terms {

    private String term_name;

    public Terms(String term_name) {
        this.term_name = term_name;
    }

    public String getTerm_name() {
        return term_name;
    }

    public void setTerm_name(String term_name) {
        this.term_name = term_name;
    }

    @Override
    public String toString() {
        return "Terms{" +
                "term_name='" + term_name + '\'' +
                '}';
    }
}
