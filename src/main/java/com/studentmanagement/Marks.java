package com.studentmanagement;

public class Marks {

    private int marksId;
    private int studentId;
    private String subject;
    private double marks;

    public Marks() {
    }

    public Marks(int marksId, int studentId, String subject, double marks) {
        this.marksId = marksId;
        this.studentId = studentId;
        this.subject = subject;
        this.marks = marks;
    }

    public int getMarksId() {
        return marksId;
    }

    public void setMarksId(int marksId) {
        this.marksId = marksId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }
}