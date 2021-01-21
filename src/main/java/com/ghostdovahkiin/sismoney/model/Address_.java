package com.ghostdovahkiin.sismoney.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Address.class)
public abstract class Address_ {

	public static volatile SingularAttribute<Address, String> zipCode;
	public static volatile SingularAttribute<Address, String> streetNumber;
	public static volatile SingularAttribute<Address, String> city;
	public static volatile SingularAttribute<Address, String> street;
	public static volatile SingularAttribute<Address, String> aditional;
	public static volatile SingularAttribute<Address, String> state;

	public static final String ZIP_CODE = "zipCode";
	public static final String STREET_NUMBER = "streetNumber";
	public static final String CITY = "city";
	public static final String STREET = "street";
	public static final String ADITIONAL = "aditional";
	public static final String STATE = "state";

}

