/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Natarajan Rodrigues && Victor Hugo
 */
@Entity
public class Presentation implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Size(min = 1, max = 150, message = "Não pode ser nulo. Valor máximo - 150 caracteres")
    @NotNull(message = "Insira o nome da apresentação")
    private String name;

    @Size(min = 1, max = 150, message = "Não pode ser nulo. Valor máximo - 150 caracteres")
    @NotNull(message = "Insira o nome do grupo ou artista responsável pela apresentação")
    private String artist;

    @Size(min = 1, max = 5000, message = "Não pode ser nulo. Valor máximo - 5000 caracteres")
    @NotNull(message = "Insira o texto release apresentação")
    private String releaseText;

    @Min(value = 1, message = "Informe o valor em minutos")
    @NotNull(message = "Informe a duração da apresentação")
    private int duration; //in minutes - default 60min
    
    
    public Presentation() {
        
        
    }

    public Presentation(String name, String artist, String releaseText, int duration) {

        this.name = name;
        this.artist = artist;
        this.releaseText = releaseText;
        this.duration = duration;
        
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getReleaseText() {
        return releaseText;
    }

    public void setReleaseText(String releaseText) {
        this.releaseText = releaseText;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Presentation)) return false;

        Presentation that = (Presentation) o;

        if (duration != that.duration) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (!name.equals(that.name)) return false;
        if (!artist.equals(that.artist)) return false;
        return releaseText.equals(that.releaseText);

    }

    @Override
    public String toString() {
        return "Presentation{" + "name=" + name + ", artist=" + artist + ", releaseText=" + releaseText + ", duration=" + duration + '}';
    }

}
