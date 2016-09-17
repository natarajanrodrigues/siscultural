/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    @NotNull
    private Long id;

    @Size(min = 1, max = 150, message = "Não pode ser nulo. Valor máximo - 150 caracteres")
    @NotNull(message = "Insira o nome da apresentação")
    private String name;

    @Size(min = 1, max = 150, message = "Não pode ser nulo. Valor máximo - 150 caracteres")
    @NotNull(message = "Insira o nome do grupo ou artista responsável pela apresentação")
    private String groupOrArtist;

    @Size(min = 1, max = 2000, message = "Não pode ser nulo. Valor máximo - 2000 caracteres")
    @NotNull(message = "Insira o texto release apresentação")
    private String releaseText;

    @Min(value = 1, message = "Informe o valor em minutos")
    @NotNull(message = "Informe a duração da apresentação")
    private int duration; //in minutes - default 60min
    
    
    public Presentation() {
        
        
    }

    public Presentation(String name, String groupOrArtist, String releaseText, int duration) {

        this.name = name;
        this.groupOrArtist = groupOrArtist;
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

    public String getGroupOrArtist() {
        return groupOrArtist;
    }

    public void setGroupOrArtist(String groupOrArtist) {
        this.groupOrArtist = groupOrArtist;
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
        if (!groupOrArtist.equals(that.groupOrArtist)) return false;
        return releaseText.equals(that.releaseText);

    }


}
