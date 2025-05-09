package com.concertportal.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.ManyToOne;

@Getter
@Setter
@Entity
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_id_region", referencedColumnName = "id")
    private Region region;

    @ManyToOne
    @JoinColumn(name = "fk_id_city", referencedColumnName = "id")
    private City city;

    private String name;
    private String address;
    private String photo;
}
