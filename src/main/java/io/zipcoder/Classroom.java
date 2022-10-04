package io.zipcoder;

import java.util.*;

public class Classroom {
    public Student[] students;
    public Classroom() {
        this.students = new Student[30];
    }
    public Classroom(Student[] students) {
        this.students = students;
    }
    public Classroom(int maxNumberOfStudents) {
        this.students = new Student[maxNumberOfStudents];
    }
    public Double getAverageExamScore() {
        Double sum = 0.0;
        for (Student student: students) {
            sum += student.getAverageExamScore();
        }
        return sum / students.length;
    }
    public void addStudent(Student student) {
        ArrayList<Student> newStudentList = new ArrayList<>(Arrays.asList(students));
        newStudentList.add(student);
        students = newStudentList.toArray(new Student[0]);
    }
    public void removeStudent(String firstName, String lastName) {
        // removal without leaving null values
        ArrayList<Student> newStudentList = new ArrayList<>(Arrays.asList(students));
        for (int i = 0; i < newStudentList.size(); i++) {
            if (newStudentList.get(i).getFirstName().equals(firstName) &&
                    newStudentList.get(i).getLastName().equals(lastName)) {
                newStudentList.remove(newStudentList.get(i));
                i--;
            }
        }
        students = newStudentList.toArray(new Student[0]);
    }

    public Student[] getStudentsByScore() {
        ArrayList<Student> newStudentList = new ArrayList<>(Arrays.asList(students));
        // descending avg exam, then sorted by last name
        Comparator<Student> byExamScore = Comparator.comparing(Student::getAverageExamScore);
        Comparator<Student> byLastName = Comparator.comparing(Student::getLastName);
        newStudentList.sort(byExamScore.reversed().thenComparing(byLastName));

        return newStudentList.toArray(new Student[0]);
    }

    public HashMap<Student, String> getGradeBook() {
        HashMap<Student, String> gradeBook = new HashMap<>();
        Student[] sortedStudents = getStudentsByScore();
        int numStudents = sortedStudents.length;

        for (int i = 0; i < numStudents-1; i++) {
            for (int j = i+1; j < numStudents; j++) {
                if (sortedStudents[i].getAverageExamScore() > sortedStudents[j].getAverageExamScore()) {
                    Double percentile = 100.0*(numStudents-j)/(double)numStudents;
                    String grade = getGradeByPercentile(percentile);
                    gradeBook.put(sortedStudents[i], grade);
                    break;
                }
            }
        }
        // lowest score always gets an F
        gradeBook.put(sortedStudents[numStudents-1], "F");

        return gradeBook;
    }
    public String getGradeByPercentile(Double percentile) {
        if (percentile.isNaN() || percentile.isInfinite()) return "Nan";
        if (percentile >= 100.0 || percentile < 0.0) return "Out of Bounds";
        if (percentile >= 90.0) return "A";
        if (percentile >= 71.0) return "B";
        if (percentile >= 50.0) return "C";
        if (percentile >= 11.0) return "D";
        if (percentile >= 0.0) return "F";
        return "N/A";
    }
    public Student[] getStudents() {
        return students;
    }

}