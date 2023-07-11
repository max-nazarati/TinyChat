package com.nazarati.core;

public class Pojo {
    int num;
    String word;

    public Pojo(int num, String word) {
        this.num = num;
        this.word = word;
    }

    @Override
    public String toString() {
        return "Pojo{" +
                "num=" + num +
                ", word='" + word + '\'' +
                '}';
    }

}
