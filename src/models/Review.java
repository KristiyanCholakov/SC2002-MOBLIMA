package models;

public class Review {
    private int grade;
    private String comment;

    public Review(int grade, String comment) {
        this.grade = grade;
        this.comment = comment;
    }

    public int getGrade() {
        return grade;
    }

    public String getComment() {
        return comment;
    }
}
