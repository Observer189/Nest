package com.example.demo;

import javax.crypto.SecretKey;

class Key {
    private int authorId;
    private int adrId;
    private SecretKey key;

    void setKey(SecretKey key) {
        this.key = key;
    }

    void setAdrId(int adrId) {
        this.adrId = adrId;
    }

    void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    SecretKey getKey() {
        return key;
    }

    int getAdrId() {
        return adrId;
    }

    int getAuthorId() {
        return authorId;
    }
}
