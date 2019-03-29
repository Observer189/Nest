package com.example.demo;

public class Message {
    private String authorName;
    private int authorId;
    int adrId;
    private String text;
    private long time;

    public Message() {

    }

    Message(int authorId, int adrId, String text) {
        this.authorId = authorId;
        this.adrId = adrId;
        this.text = text;
    }

    String getAuthorName() {
        return authorName;
    }

    String getText() {
        return text;
    }

    void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    void setText(String text) {
        this.text = text;
    }

    long getTime() {
        return time;
    }

    void setTime(long time) {
        this.time = time;
    }

    int getAuthorId() {
        return authorId;
    }

    void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    int getAdrId() {
        return adrId;
    }

    void setAdrId(int adrId) {
        this.adrId = adrId;
    }
}

