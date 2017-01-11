package io.github.siscultural.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by natarajan on 11/01/17.
 */
@Entity
public class SystemPayment implements Serializable {

    @Id
    private BigInteger numPg;

    private String cpfCnpj;
    private BigDecimal valor;

    public SystemPayment() {
    }

    public SystemPayment(BigInteger numPg, String cpfCnpj, BigDecimal valor) {
        this.numPg = numPg;
        this.cpfCnpj = cpfCnpj;
        this.valor = valor;
    }

    public BigInteger getNumPg() {
        return numPg;
    }

    public void setNumPg(BigInteger numPg) {
        this.numPg = numPg;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "numPg=" + numPg +
                ", cpfCnpj='" + cpfCnpj + '\'' +
                ", valor=" + valor +
                '}';
    }

}
