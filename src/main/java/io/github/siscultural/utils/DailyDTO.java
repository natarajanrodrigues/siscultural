package io.github.siscultural.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.siscultural.entities.DailyPublicScore;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by natarajan on 02/11/16.
 */
public class DailyDTO {

    private String date;
    private boolean badge;
    private String classname;
    private int score;
    private String title;
    private String body;
    private String footer;
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    public DailyDTO(DailyPublicScore dailyPublicScore) {
        this.date = dailyPublicScore.getDate().toString();

        if (dailyPublicScore.getMainPublicScore() != 0) {
            this.score = dailyPublicScore.getMainPublicScore();
            this.badge = true;
        } else
            this.badge = false;

        if (dailyPublicScore.isOpenToVisitors())
            this.classname = "azul-evento";
        else
            this.classname = "color-yellow";

        this.title = "<p>Público em " + LocalDate.parse(this.date).format(dateTimeFormatter) + "</p>";
        if (this.score != 0) {
            this.body = Integer.toString(this.score);
        } else {
            this.body = "Ainda não registrado.";
        }

        this.footer = "";


    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isBadge() {
        return badge;
    }

    public void setBadge(boolean badge) {
        this.badge = badge;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public String toJson(){
        Gson gson = new GsonBuilder()
//                .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
                .create();
        return gson.toJson(this);
    }
}
