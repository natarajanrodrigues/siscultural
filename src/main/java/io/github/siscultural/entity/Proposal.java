/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.entity;

/**
 *
 * @author susanneferraz
 */
class Proposal {
    
    private int id;
    private String name;
    private String groupOrArtist; 
    private String releaseText;
    private int duration; //in minutes - default 60min

    public Proposal() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Proposal other = (Proposal) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
}
