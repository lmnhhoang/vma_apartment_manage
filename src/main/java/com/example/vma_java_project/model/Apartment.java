package com.example.vma_java_project.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "Apartment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Apartment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "apartment_id")
    private long apartment_id;

    @Column(name = "roomNo")
    private String roomNo;
    @Column(name = "numOfRoom")
    private int numOfRoom;
    @Column(name = "acreage")
    private double acreage;
    @Column(name = "status")
    private String status;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "in_building")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Building building;
//    @OneToMany(mappedBy = "dweller_aprtm", cascade = CascadeType.ALL)
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    private Collection<Dwellers> dwellers;
//    @OneToMany(mappedBy = "bill_aprtm", cascade = CascadeType.ALL)
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    private Collection<Bill> bills;

    public Apartment(String roomNo, int numOfRoom, double acreage, String status, String description) {
        this.roomNo = roomNo;
        this.numOfRoom = numOfRoom;
        this.acreage = acreage;
        this.status = status;
        this.description = description;
    }
}
