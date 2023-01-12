package com.niit.UserTask.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class DbSequence {


    @Id
    private String  userId;
    private int seq;

    public DbSequence() {
    }

    public DbSequence(String userId, int seq) {
        this.userId = userId;
        this.seq = seq;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }
}
