package com.example.vma_java_project.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Dwellers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dwellers implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dweller_id")
    private long dweller_id;
    @Column(name = "cid")
    private double cid;
    @Column(name = "fullname")
    private String fullname;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "birthday",columnDefinition = "DATE")
    private LocalDate birthday;
    @Column(name = "gender")
    private String gender;
    @ManyToOne
    @JoinColumn(name = "dweller_aprtm")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Apartment dweller_aprtm;

    public Dwellers(double cid, String fullname, String email,
                    String phone, LocalDate birthday, String gender, Apartment dweller_aprtm) {
        this.cid = cid;
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.birthday = birthday;
        this.gender = gender;
        this.dweller_aprtm = dweller_aprtm;
    }
}
