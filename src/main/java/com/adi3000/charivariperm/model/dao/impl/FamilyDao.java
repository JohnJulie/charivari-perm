package com.adi3000.charivariperm.model.dao.impl;

import org.springframework.stereotype.Repository;

import com.adi3000.charivariperm.model.dataobject.Family;
import com.adi3000.common.orm.dao.AbstractDAO;

@Repository("familyDao")
public class FamilyDao extends AbstractDAO<Family>  implements com.adi3000.charivariperm.model.dao.FamilyDao {

	private static final long serialVersionUID = -5225559703545558094L;
	
	
}
