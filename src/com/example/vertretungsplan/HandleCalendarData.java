package com.example.vertretungsplan;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class HandleCalendarData {
public String ParseHTML (String htmlcode){
	Document doc =Jsoup.parse(htmlcode);
	Elements trs=doc.select("table tbody tr ");
	String rowspan="";
	int i=0;
	List<String> rowspanlist = new ArrayList <String>();
	Elements tds=doc.select("table tbody tr td ");
	String[] fach=new String[1000];
	i=0;
	List<String> fachlist = new ArrayList <String>();
	List<String> comparelist = new ArrayList <String>();
	String[] compares= new String [100];
	int m=0;
	for(Element font : trs){
		compares[m]=font.text();
		if(compares[m].length()>2&& compares[m].contains("1")|| compares[m].contains("2")&& compares[m].length()>2
				||compares[m].contains("3")&& compares[m].length()>2||compares[m].contains("4")&& compares[m].length()>2
				||compares[m].contains("5")&& compares[m].length()>2||compares[m].contains("6")&& compares[m].length()>2
				||compares[m].contains("7")&& compares[m].length()>2||compares[m].contains("8")&& compares[m].length()>2
				||compares[m].contains("9")&& compares[m].length()>2||compares[m].contains("10")&& compares[m].length()>2
				||compares[m].contains("11")&& compares[m].length()>2||compares[m].contains("12")&& compares[m].length()>2
				||compares[m].contains("13")&& compares[m].length()>2||compares[m].contains("14")&& compares[m].length()>2
				||compares[m].contains("15")&& compares[m].length()>2||compares[m].contains("16")&& compares[m].length()>2){
		
			comparelist.add(compares[m]);
		}
	}
	for(Element td : tds){
		if(td.hasText()){
			fach[i] = td.text();

			
			if(!fachlist.get(i-1).equals(fach[i])){
				System.out.println("if     "+fach[i]);
				fachlist.add(fach[i]);
				i++;
			}
		}
		else{
			if(i>0){

				fach[i]=" ";
				System.out.println("else   "+fach[i]);
				fachlist.add(fach[i]);
				i++;
			
			}
			else{
				fach[i]=" ";
				System.out.println("else   "+fach[i]);
				fachlist.add(fach[i]);
				i++;
			}
		}
		
	}
	int fachsize= comparelist.size();
	Elements helper;
	int k=0;
	while(k<fachsize){
		for(Element tr : trs ){
			helper= tr.select("td");
			for(Element td : helper ){
				 if(tr.text().equals(comparelist.get(k))){
						rowspan+=td.attr("rowspan")+" ";
		 		 }
			}
			rowspanlist.add(rowspan);
			rowspan=""; 
		}
		k++;
	}
	rowspanlist.removeAll(Arrays.asList(null,""));
	
	System.out.println(rowspanlist);
	
	System.out.println(fachlist);
	
	return null;
}
	
}