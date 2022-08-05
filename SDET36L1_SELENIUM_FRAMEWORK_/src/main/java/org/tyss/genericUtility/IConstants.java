package org.tyss.genericUtility;
/**
 * This interface contain all the constant variable for the application 
 * @author dell
 *
 */
public interface IConstants {
	String ABSOLUTPATHTOPROJECT=System.getProperty("user.dir");
	/*
	 * this static final variable give the path of property file
	 */
	public String VTIGERPROPERTYFILEPATH=ABSOLUTPATHTOPROJECT+"/src/test/resources/commonData.properties";
	/*
	 * this static final variable give the path of product file
	 */
	public String VTIGEREXCELFILEPATH=ABSOLUTPATHTOPROJECT+"/src/test/resources/products.xlsx";
	
	/*
	 * this static final variable give the path of csv file
	 */
	public String CSVFILEPATH=ABSOLUTPATHTOPROJECT+"/src/test/resources/----.csv";
	/*
	 * this static final variable give the path of rmgtestdata property file
	 */
	public String RMGYANTREXCELFILEPATH=ABSOLUTPATHTOPROJECT+"/src/test/resource/rmgyantraTestData.xlsx";
	
	/*
	 * this static final variable give the path of RmgYantracommonData property file
	 */
	public String RMGYANTRAPROPERTYFILEPATH=ABSOLUTPATHTOPROJECT+"/src/test/resource/RmgYantracommonData.properties";
	}


