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
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id")
    private Building building;

    @Column(nullable = false)
    private int numEmp;

}
