package com.eugene.spring.springboot.shiftlab.model;


import jakarta.persistence.*;

@Entity
@Table(name = "LETTERINTERVALS")
public class LetterInterval
{
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "_start")
    private String start;

    @Column(name = "_end")
    private String end;

    public LetterInterval() {
    }

    public LetterInterval(String start, String end) {
        this.start = start;
        this.end = end;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "LetterInterval{" +
                "id=" + id +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                '}';
    }
}
