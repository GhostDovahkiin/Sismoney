package com.ghostdovahkiin.sismoney.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Entry.class)
public abstract class Entry_ {

	public static volatile SingularAttribute<Entry, EntryType> entryType;
	public static volatile SingularAttribute<Entry, BigDecimal> amount;
	public static volatile SingularAttribute<Entry, String> observation;
	public static volatile SingularAttribute<Entry, Person> person;
	public static volatile SingularAttribute<Entry, LocalDate> dueDate;
	public static volatile SingularAttribute<Entry, String> entryDescription;
	public static volatile SingularAttribute<Entry, Long> id;
	public static volatile SingularAttribute<Entry, Category> category;
	public static volatile SingularAttribute<Entry, LocalDate> payDate;

	public static final String ENTRY_TYPE = "entryType";
	public static final String AMOUNT = "amount";
	public static final String OBSERVATION = "observation";
	public static final String PERSON = "person";
	public static final String DUE_DATE = "dueDate";
	public static final String ENTRY_DESCRIPTION = "entryDescription";
	public static final String ID = "id";
	public static final String CATEGORY = "category";
	public static final String PAY_DATE = "payDate";

}

