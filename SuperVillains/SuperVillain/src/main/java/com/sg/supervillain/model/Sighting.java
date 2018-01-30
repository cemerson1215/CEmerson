/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supervillain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
//final
/**
 *
 * @author n0149245
 */
public class Sighting {
   private int sightingId;
   private Location location;
   private LocalDateTime timeOfSight;
   private List<SuperVillain> villainsList;

    public int getSightingId() {
        return sightingId;
    }

    public void setSightingId(int sightingId) {
        this.sightingId = sightingId;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public LocalDateTime getTimeOfSight() {
        return timeOfSight;
    }

    public void setTimeOfSight(LocalDateTime timeOfSight) {
        this.timeOfSight = timeOfSight;
    }

    public List<SuperVillain> getVillainsList() {
        return villainsList;
    }

    public void setVillainsList(List<SuperVillain> villainsList) {
        this.villainsList = villainsList;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.sightingId;
        hash = 59 * hash + Objects.hashCode(this.location);
        hash = 59 * hash + Objects.hashCode(this.timeOfSight);
        hash = 59 * hash + Objects.hashCode(this.villainsList);
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
        final Sighting other = (Sighting) obj;
        if (this.sightingId != other.sightingId) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        if (!Objects.equals(this.timeOfSight, other.timeOfSight)) {
            return false;
        }
        if (!Objects.equals(this.villainsList, other.villainsList)) {
            return false;
        }
        return true;
    }

    
   
   
}
