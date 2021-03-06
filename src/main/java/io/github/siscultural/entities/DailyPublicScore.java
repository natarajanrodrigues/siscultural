package io.github.siscultural.entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.siscultural.utils.LocalDateSerializer;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.text.DateFormat;
import java.time.LocalDate;

/**
 * Created by natarajan on 01/11/16.
 */
@Entity
public class DailyPublicScore {
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

//    @Column(unique = true, columnDefinition = "DATE")
    @Id
    @Column(columnDefinition = "DATE")
    private LocalDate date;         //data

    private boolean openToVisitors; // informa se é dia em que o centro cultural está aberto

    private int mainPublicScore;    //total de público no contador principal

    private int libraryPublicScore; //total de contagem de público na biblioteca

    private String closingReasonText; //string dizendo o motivo de estar fechado - para impressão na agenda

    private String openingReasonText; //string dizendo o motivo de estar fechado - para impressão na agenda

    public DailyPublicScore() {
    }

    public DailyPublicScore(LocalDate date, boolean openToVisitors, int mainPublicScore, int libraryPublicScore, String closingReasonText) {
        this.date = date;
        this.openToVisitors = openToVisitors;
        this.mainPublicScore = mainPublicScore;
        this.libraryPublicScore = libraryPublicScore;
        this.closingReasonText = closingReasonText;
    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

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
        if (mainPublicScore >= 0)
            this.mainPublicScore = mainPublicScore;
        else
            throw new IllegalArgumentException("Número de público do contador principal não pode ser negativo.");
    }

    public int getLibraryPublicScore() {
        return libraryPublicScore;
    }

    public void setLibraryPublicScore(int libraryPublicScore) {
        if (libraryPublicScore >= 0)
            this.libraryPublicScore = libraryPublicScore;
        else
            throw new IllegalArgumentException("Público da biblioteca não pode ser negativo.");
    }

    public String getClosingReasonText() {
        return closingReasonText;
    }

    public void setClosingReasonText(String closingReasonText) {
        this.closingReasonText = closingReasonText;
    }

    public String getOpeningReasonText() {
        return openingReasonText;
    }

    public void setOpeningReasonText(String openingReasonText) {
        this.openingReasonText = openingReasonText;
    }

    public String toZabutoCalendarJson(){
//        Gson gson = new GsonBuilder()
//                .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
//                .create();
//        return gson.toJson(this);

//        StringBuilder sb = new StringBuilder();
//        sb.append("{");
//        sb.append("\"").append("date").append("\":").append("\"").append(getDate().toString()).append("\",");
//        sb.append("\"").append("badge").append("\":");
//        if (getMainPublicScore() != 0)
//            sb.append("true");
//        else
//            sb.append("false");
//        sb.append("}");
//        return sb.toString();

        return null;
    }

}
