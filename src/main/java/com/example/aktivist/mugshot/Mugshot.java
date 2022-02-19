package com.example.aktivist.mugshot;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Document(collection = "mugshots")
@AllArgsConstructor
//@JsonIgnoreProperties(ignoreUnknown = true)
public class Mugshot {

    @Id
    private String id;

    @NotNull(message = "Title is required")
    @Pattern(regexp="^[a-zA-Z ]+$", message = "Title must be a string")
    private String nom;


    private String formation;
    private String occupation;
    private String lieu_de_residence;
    private String image;
    private String[] rs;

    public Mugshot() {
    }

    public Mugshot(String nom, String formation, String occupation, String lieu_de_residence, String image, String[] rs) {
        this.nom = nom;
        this.formation = formation;
        this.occupation = occupation;
        this.lieu_de_residence = lieu_de_residence;
        this.image = image;
        this.rs = rs;
    }

    public Mugshot updateWith(Mugshot mugshot){
        return new Mugshot(
                mugshot.nom,
                mugshot.formation,
                mugshot.occupation,
                mugshot.lieu_de_residence,
                mugshot.image,
                mugshot.rs
        );
    }
}
