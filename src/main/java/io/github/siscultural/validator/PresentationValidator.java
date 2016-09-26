package io.github.siscultural.validator;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Min;

/**
 * Created by susanneferraz on 16/09/16.
 */
public class PresentationValidator {

    @Size(min = 1, max = 150, message = "Não pode ser nulo. Valor máximo - 150 caracteres")
    @NotNull(message = "Insira o nome da apresentação")
    private String name;

    @Size(min = 1, max = 150, message = "Não pode ser nulo. Valor máximo - 150 caracteres")
    @NotNull(message = "Insira o nome do grupo ou artista responsável pela apresentação")
    private String groupOrArtist;

    @Size(min = 1, max = 2000, message = "Não pode ser nulo. Valor máximo - 2000 caracteres")
    @NotNull(message = "Insira o texto release apresentação")
    private String releaseText;

    @Min(1)
    @NotNull(message = "Informe a duração da apresentação")
    private int duration;


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
}
