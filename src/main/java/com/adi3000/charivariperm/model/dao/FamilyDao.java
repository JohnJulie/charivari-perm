package com.adi3000.charivariperm.model.dao;

import java.util.Date;
import java.util.List;

import com.adi3000.charivariperm.model.dataobject.Family;
import com.adi3000.common.orm.dao.DAO;

public interface FamilyDao extends DAO<Family> {
	public List<Family> findAllWithCurrentContract();
}
