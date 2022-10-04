package io.zipcoder;

import java.util.ArrayList;
import java.util.Arrays;

public class Student {
    String firstName;
    String lastName;
    ArrayList<Double> examScores;

    public Student(String firstName, String lastName, Double[] examScores) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.examScores = new ArrayList<Double>(Arrays.asList(examScores));
    }

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getExamScores() {
        String examScoresStr = "Exam Scores:";
        int numScores = getNumberOfExamsTaken();
        for (int i = 0; i < numScores; i++) {
            examScoresStr += String.format("\n\tExam %d -> %.0f",
                    i+1,  examScores.get(i));
        }
        return examScoresStr;
    }
    public void setExamScores(int examNumber, double newScore) {
        examScores.set(examNumber-1, newScore);
    }
    public void addExamScore(double examScore) {
        examScores.add(examScore);
    }
    public Double getAverageExamScore() {
        // we could check here for non-zero examScores size
        Double sum = examScores.stream().reduce(Double::sum).get();
        return sum/getNumberOfExamsTaken();
    }
    @Override
    public String toString() {
        return "Student Name: " + firstName + " " + lastName +
                (String.format("\n> Average Score: %.0f", getAverageExamScore()) + "\n> ") +
                getExamScores();
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ArrayList<Double> getExamScoresAsList() {
        return examScores;
    }

    public Integer getNumberOfExamsTaken() {
        return examScores.size();
    }
}
