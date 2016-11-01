package io.github.siscultural.entities;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Created by natarajan on 01/11/16.
 */
@Entity
public class DailyPublicScore {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

//    @UniqueConstraint(columnNames = {date})
    private LocalDate date;         //data

    private boolean openToVisitors; // informa se é dia em que o centro cultural está aberto

    private int mainPublicScore;    //total de público no contador principal

    private int libraryPublicScore; //total de contagem de público na biblioteca

    private String closingReasonText; //string dizendo o motivo de estar fechado - para impressão na agenda

    public DailyPublicScore() {
    }

    public DailyPublicScore(LocalDate date, boolean openToVisitors, int mainPublicScore, int libraryPublicScore, String closingReasonText) {
        this.date = date;
        this.openToVisitors = openToVisitors;
        this.mainPublicScore = mainPublicScore;
        this.libraryPublicScore = libraryPublicScore;
        this.closingReasonText = closingReasonText;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isOpenToVisitors() {
        return openToVisitors;
    }

    public void setOpenToVisitors(boolean openToVisitors) {
        this.openToVisitors = openToVisitors;
    }

    public int getMainPublicScore() {
        return mainPublicScore;
    }

    public void setMainPublicScore(int mainPublicScore) {
        this.mainPublicScore = mainPublicScore;
    }

    public int getLibraryPublicScore() {
        return libraryPublicScore;
    }

    public void setLibraryPublicScore(int libraryPublicScore) {
        this.libraryPublicScore = libraryPublicScore;
    }

    public String getClosingReasonText() {
        return closingReasonText;
    }

    public void setClosingReasonText(String closingReasonText) {
        this.closingReasonText = closingReasonText;
    }


}
