package com.adi3000.charivariperm.model.dao.impl;

import org.springframework.stereotype.Repository;

import com.adi3000.charivariperm.model.dataobject.Holidays;
import com.adi3000.common.orm.dao.AbstractDAO;

@Repository("holidaysDao")
public class HolidaysDao extends AbstractDAO<Holidays> implements com.adi3000.charivariperm.model.dao.HolidaysDao {

	private static final long serialVersionUID = -5225559703545558094L;
	
}
