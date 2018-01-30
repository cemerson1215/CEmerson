/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supervillain.model;

import java.util.Objects;
//final
/**
 *
 * @author n0149245
 */
public class Power {
    private int powerId;
    private String description;

    public int getPowerId() {
        return powerId;
    }

    public void setPowerId(int id) {
        this.powerId = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this.powerId;
        hash = 13 * hash + Objects.hashCode(this.description);
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
        final Power other = (Power) obj;
        if (this.powerId != other.powerId) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return true;
    }

   
    
}
