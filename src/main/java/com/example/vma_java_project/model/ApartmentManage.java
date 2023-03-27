package com.example.vma_java_project.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Table(name = "ApartmentManage")
@Data
public class ApartmentManage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aprtm_id")
    private long aprtm_id;


//    @OneToMany(mappedBy = "apartment_no", cascade = CascadeType.ALL)
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    private Collection<Apartment> apartmentManages;

    @Column(name = "e_value")
    private long e_value;
    @Column(name = "w_value")
    private long w_value;
    @Column(name = "last_record_date",columnDefinition = "DATE")
    private LocalDate record_date;

    @ManyToOne
    @JoinColumn(name = "aprtm_room")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Apartment aprtm_room;

}
