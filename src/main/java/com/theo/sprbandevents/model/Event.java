package com.theo.sprbandevents.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Entity
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable=false)
    private Calendar eventDate;

    @JsonIgnoreProperties("events")
    @ManyToMany(mappedBy = "events", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Band> bands = new HashSet<>();

    @Transient
    private List<Integer> bandsIds;

    @Override
    public String toString() {
        String toSendString = "\nname: " + name + "\n";

        for(Band band: bands) {
            toSendString += "band: " + band.getName() + ", " + band.getId() + "\n";
        }

        return toSendString;
    }
}
