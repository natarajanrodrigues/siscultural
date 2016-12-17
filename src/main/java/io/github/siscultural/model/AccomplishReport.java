package io.github.siscultural.model;

import io.github.siscultural.entities.Program;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * Created by natarajan on 15/12/16.
 */
public class AccomplishReport {

    private String programa;
    private Long publico;
    private Long total;

    public AccomplishReport(String programa, Long total, Long publico) {
        this.programa = programa;
        this.publico = publico;
        this.total = total;
    }

    public AccomplishReport() {
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public Long getPublico() {
        return publico;
    }

    public void setPublico(Long publico) {
        this.publico = publico;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
