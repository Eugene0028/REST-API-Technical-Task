package com.eugene.spring.springboot.shiftlab.model;

import jakarta.persistence.*;

@Entity
@Table(name = "DIGITINTERVALS")
public class DigitInterval
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "_start")
    private Long start;

    @Column(name = "_end")
    private Long end;

    public DigitInterval() {
    }

    public DigitInterval(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getStart() {
        return start;
    }


    public Long getEnd() {
        return end;
    }

    public void setEnd(Long end) {
        this.end = end;
    }
}