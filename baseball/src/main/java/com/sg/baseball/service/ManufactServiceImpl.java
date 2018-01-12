/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.baseball.service;

import com.sg.baseball.dao.ManufactuerDao;
import com.sg.baseball.model.Manufactuer;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author n0149245
 */
public class ManufactServiceImpl implements ManufactService {
    
    @Inject
    ManufactuerDao manufactDao;

    public ManufactuerDao getManufactDao() {
        return manufactDao;
    }

    public void setManufactDao(ManufactuerDao manufactDao) {
        this.manufactDao = manufactDao;
    }    
    
    @Override
    public Manufactuer addManufactuer(Manufactuer manufactuer) {
        return manufactDao.addManufactuer(manufactuer);
    }

    @Override
    public void removeManufactuer(int manufactId) {
        manufactDao.removeManufactuer(manufactId);
    }

    @Override
    public Manufactuer updateManufactuer(Manufactuer manufactuer) {
        return manufactDao.updateManufactuer(manufactuer);
    }

    @Override
    public Manufactuer retrieveManufById(int manufactId) {
        return manufactDao.retrieveManufById(manufactId);
    }

    @Override
    public List<Manufactuer> retrieveAllManufactuers() {
        return manufactDao.retrieveAllManufactuers();
    }

    @Override
    public Manufactuer retrieveManufByCardId(int cardId) {
        return manufactDao.retrieveManufByCardId(cardId);
    }
    
}
