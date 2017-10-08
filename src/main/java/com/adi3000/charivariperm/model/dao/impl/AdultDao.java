package com.adi3000.charivariperm.model.dao.impl;

import org.springframework.stereotype.Repository;

import com.adi3000.charivariperm.model.dataobject.Adult;
import com.adi3000.common.orm.dao.AbstractDAO;

@Repository("adultDao")
public class AdultDao extends AbstractDAO<Adult> implements com.adi3000.charivariperm.model.dao.AdultDao {

	private static final long serialVersionUID = 5618221216335645228L;

}
