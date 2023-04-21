package com.bms.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private boolean outOfOrder = false;

}
