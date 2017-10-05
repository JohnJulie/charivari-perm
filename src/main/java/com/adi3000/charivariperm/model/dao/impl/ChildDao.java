package com.adi3000.charivariperm.model.dao.impl;

import org.springframework.stereotype.Repository;

import com.adi3000.charivariperm.model.dataobject.Child;
import com.adi3000.common.orm.dao.AbstractDAO;

@Repository("childDao")
public class ChildDao extends AbstractDAO<Child> {

	private static final long serialVersionUID = 5618221216335645228L;
	
}
