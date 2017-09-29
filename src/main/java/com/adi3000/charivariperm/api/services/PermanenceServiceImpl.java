package com.adi3000.charivariperm.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adi3000.charivariperm.api.dao.PermanenceDao;
import com.adi3000.charivariperm.api.models.Permanence;

@Service("permanenceService")
@Transactional
public class PermanenceServiceImpl implements PermanenceService {
	
	@Autowired
    private PermanenceDao dao;
     
    public void savePermanence(Permanence permanence) {
        dao.savePermanence(permanence);
    }
 
    public List<Permanence> findAllPermanences() {
        return dao.findAllPermanences();
    }
 
    public void deletePermanenceById(Long id) {
        dao.deletePermanenceById(id);
    }
 
    public Permanence findById(Long id) {
        return dao.findById(id);
    }
 
    public void updatePermanence(Permanence permanence){
        dao.updatePermanence(permanence);
    }

}
