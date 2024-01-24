package com.eugene.spring.springboot.shiftlab.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "DIGITINTERVALS")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DigitInterval
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "_start")
    private Long start;

    @Column(name = "_end")
    private Long end;
    public DigitInterval(Long start, Long end) {
        this.start = start;
        this.end = end;
    }
}