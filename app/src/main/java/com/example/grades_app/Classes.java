package com.example.grades_app;

public class Classes {

    private String name;
    private Float grade;

    public Classes(String name, Float grade) {
        this.name = name;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getGrade() {
        return grade;
    }

    public void setGrade(Float grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Classes{" +
                "name='" + name + '\'' +
                ", grade=" + grade +
                '}';
    }
}
