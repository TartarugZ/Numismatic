package com.coursework.server_connection;

import java.io.Serializable;

/**
 * Класс для пар номинал-валюта
 */
public  class ValAndCurPair implements Serializable {
    private String first;
    private String second;
    public ValAndCurPair(String first, String second) {
        this.first = first;
        this.second = second;
    }
    public ValAndCurPair(){}

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    @Override
    public int hashCode() {
        return (first+second).hashCode();
    }

    /**Сравнение двух объектов этого класса
     * @param obj ValAndCurPair
     * @return кгу щк афдыу
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (this.getClass() != obj.getClass())
            return false;

        ValAndCurPair comp = (ValAndCurPair)obj;
        return this.first.equals(comp.first)&&this.second.equals(comp.second);
    }

    @Override
    public String toString() {
        return "ValAndCurPair{" +
                "first='" + first + '\'' +
                ", second='" + second + '\'' +
                '}';
    }
}
