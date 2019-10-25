package com.cxm.pojo;

public class Movie {

    private String m_name;
    private String comment;
    private double score;

    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
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

    @Override
    public String toString() {
        return "Movie{" +
                "m_name='" + m_name + '\'' +
                ", comment='" + comment + '\'' +
                ", score=" + score +
                '}';
    }
}
