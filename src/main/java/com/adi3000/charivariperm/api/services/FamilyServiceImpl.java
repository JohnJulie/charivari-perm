package com.adi3000.charivariperm.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adi3000.charivariperm.api.dao.FamilyDao;
import com.adi3000.charivariperm.api.models.Family;

@Service("familyService")
@Transactional
public class FamilyServiceImpl implements FamilyService {
	
	@Autowired
    private FamilyDao dao;
     
    public void saveFamily(Family family) {
        dao.saveFamily(family);
    }
 
    public List<Family> findAllFamilies() {
        return dao.findAllFamilies();
    }
 
    public void deleteFamilyById(Long id) {
        dao.deleteFamilyById(id);
    }
 
    public Family findById(Long id) {
        return dao.findById(id);
    }
 
    public void updateFamily(Family family){
        dao.updateFamily(family);
    }
    
}
