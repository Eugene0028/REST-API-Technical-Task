package com.eugene.spring.springboot.shiftlab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "LETTERINTERVALS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LetterInterval
{
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "_start")
    private String start;

    @Column(name = "_end")
    private String end;
}