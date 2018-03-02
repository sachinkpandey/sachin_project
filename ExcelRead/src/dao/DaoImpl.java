package dao;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

import model.PropertyMapping;

public class DaoImpl implements Dao {

	@Autowired
	HibernateTemplate hibernateTemplate;
	
	
	
	@Override
	public void saveDate(ArrayList<PropertyMapping> ab) {
		
		
	}

}
