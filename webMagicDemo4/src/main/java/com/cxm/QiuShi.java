package com.cxm;

public class QiuShi {

    private int id;
    private String u_name;
    private String content;
    private int ilike;

    @Override
    public String toString() {
        return "QiuShi{" +
                "id=" + id +
                ", u_name='" + u_name + '\'' +
                ", content='" + content + '\'' +
                ", ilike=" + ilike +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIlike() {
        return ilike;
    }

    public void setIlike(int ilike) {
        this.ilike = ilike;
    }
}
