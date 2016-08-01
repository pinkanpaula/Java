package com.hsbc.carm.common.data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import com.hsbc.carm.common.util.Escape;

public class SQLCharDetectTest {
	
	String pattern1 = "\'";
	final Pattern SQLPATTERN = Pattern.compile(pattern1);
	String pattern2="'(''|[^'])*'";
	final Pattern SQLPATTERN2=Pattern.compile(pattern2);
	
	@Test
	public void isSQL(){
		String str="'KAYRIS CHILDRENS' SHOES";
		String search="";
		boolean checkStr=checkString(str);
		System.out.println("first string "+str);
		if(checkStr){
			System.out.println("SQL DETECTED AND WILL BE ESCAPED");
			str = Escape.forSQL(str);
			
		}
			
		else{
			System.out.println("NOT AN SQL");
			str = Escape.forSQLForSpecialName(str);
		}		
		System.out.println("final string "+str);			
	}
	
	public Boolean checkString(String str){
		return patternMatch(str, SQLPATTERN2);		
	}
	
	public Boolean patternMatch(final String str, final Pattern p){
		java.util.regex.Matcher m=p.matcher(str);
		return m.matches();
		
	}
		
	public String patternFind(final String str, final Pattern p){
		java.util.regex.Matcher m=p.matcher(str);
		StringBuffer sb=new StringBuffer();
		while(m.find()){
			sb.append(m.group());
			//System.out.format("String %s found at %d to %d.%n ", m.group(),m.start(),m.end());
		}
		return sb.toString();
	}
	
	public void bla(){
		String search="";
		  String singleQuote="'";
          if(search.indexOf(singleQuote)>0 && search.indexOf(singleQuote) < search.length()){
          	System.out.println("Search customer name waived from SQL filtering");
          }
          else{
          	search = Escape.forSQL(search);    			
          }
	}

}
