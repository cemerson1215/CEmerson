/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.baseball.dao;

import com.sg.baseball.model.Manufactuer;
import java.util.List;

/**
 *
 * @author n0149245
 */
public interface ManufactuerDao {
    public Manufactuer addManufactuer (Manufactuer manufactuer);
    public void removeManufactuer (int manufactId);
    public Manufactuer updateManufactuer (Manufactuer manufactuer);
    public Manufactuer retrieveManufById (int manufactId);
    public List<Manufactuer> retrieveAllManufactuers ();
    public Manufactuer retrieveManufByCardId (int cardId);
}
