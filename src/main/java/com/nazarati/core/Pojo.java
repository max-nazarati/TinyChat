package com.nazarati.core;

import java.io.Serializable;
import java.util.Objects;

public class Pojo implements Serializable {
    int num;
    String word;

    public Pojo(int num, String word) {
        this.num = num;
        this.word = word;
    }

    public Pojo() {
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public String toString() {
        return "Pojo{" +
                "num=" + num +
                ", word='" + word + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pojo pojo = (Pojo) o;
        return num == pojo.num && Objects.equals(word, pojo.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(num, word);
    }

}
