package com.concertportal.model;
import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "concert")
public class Concert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String concertName;
    private String artist;
    private LocalDateTime date;
    // join column private Integer idLocation;
    private String genre;
    private String description;

    @ManyToOne
    @JoinColumn(name = "fk_id_location", referencedColumnName = "id")
    private Location location;
}
