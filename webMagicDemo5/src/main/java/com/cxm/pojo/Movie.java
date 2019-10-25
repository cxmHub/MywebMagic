package com.cxm.pojo;

public class Movie {

    private String name;
    private String comment;
    private double score;

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                ", score=" + score +
                '}';
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
