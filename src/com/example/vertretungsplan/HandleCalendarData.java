package com.example.vertretungsplan;



import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import android.content.Context;



public class HandleCalendarData {
public void ParseHTML (String htmlcode){
	String[][] tablecontent = new String[16][5];
	int i=0;
	int span=0;
	String rowspan="";
	String clone;
	int tablestart=htmlcode.indexOf("<TABLE");
	int tableend = htmlcode.lastIndexOf("</TABLE>");
	String tablestring= htmlcode.substring(tablestart, tableend);
	/*tabelle wird aus dem htmlcode in den substring tablestring
	 * geschreiben.
	 */
	Document doc =Jsoup.parse(tablestring);
	//tablestring wird geparset.
	Elements tables=doc.select("table tbody tr td table ");
	//liste mit in doc enhaltenen tabellen erstellen.
	String text="";
	List<String> textlist = new ArrayList<String>();
	
		//System.out.println(td1);
	for(Element table: tables){
		Elements trs = table.select("tr");
		for(Element tr : trs){
			Elements tds = tr.select("td");
			for(Element td : tds){
					//System.out.println("first if");	
				if(td.hasText()){
					text=td.text();
					textlist.add(text);					
				}
				else{
					text=" ";
					textlist.add(text);
				}
			}			
		}
		table.remove();
	}
	
	

	/*für jede table in der liste tables wird der text falls vorhanden
	 * in eine liste geschrieben. Wenn kein text vorhanden ist wird eine leerer
	 * String in die liste eingetragen.
	 * wenn der text aus der tabelle gespeichert ist wird diese gelöscht.
	 */
	Elements trs = doc.select("table tbody tr ");
	// speichert alle zeilen aus doc in einer liste aus elementen.
	for(Element tr : trs){
		if(tr.childNodeSize()==0){
			tr.remove();
		}
		else{
		Elements tds = tr.select("td");
		for(Element td : tds){
			//System.out.println(td.elementSiblingIndex());
			if(i<textlist.size()){
				if(td.hasAttr("rowspan")){
					rowspan = td.attr("rowspan");
					if(!rowspan.isEmpty()){
						rowspan.replaceAll("\\s+", "");
						span=Integer.parseInt(rowspan, 10);
						if(span==2){
							td.removeAttr("rowspan");
							td.removeAttr("colspan");
							td.removeAttr("align");
							td.removeAttr("nowrap");
							text=textlist.get(i);
							td.text(text);
						}
						else{
							td.attr("rowspan","2");
							td.removeAttr("colspan");
							td.removeAttr("align");
							td.removeAttr("nowrap");
							text=textlist.get(i);
							td.text(text);
						}											
					}
				}
				else{
					td.removeAttr("colspan");
					td.removeAttr("align");
					td.removeAttr("nowrap");
					text=textlist.get(i);
					td.text(text);
				}				
			}
			i++;
		}
		}
	}
	 Writer fw = null;
     try {
         fw = new FileWriter("/storage/sdcard0/html.txt");
         fw.write(doc.toString());
         fw.append(System.getProperty("line.separator")); // e.g. "\n"
     } catch (IOException e) {
         System.err.println("Konnte Datei nicht erstellen");
     } finally {
         if (fw != null)
             try {
                 fw.close();
             } catch (IOException e) {
            	 System.out.println("schreiben nicht möglich");
             }
     }
     List<List<Element>> stundenplan = new ArrayList<List<Element>>();
     
     trs=doc.select("table tbody tr");
     trs.remove(0);
     for(i=0; i<trs.size()-1;i++){
    	 Element tr = trs.get(i);
    	 Elements tds = tr.getAllElements();
         if(tds.size()>0){
         	tds.remove(0);
          }
         List<Element> faecher= returnNewList();
    	 for (int j=1;j<tds.size();j++){
    		 System.out.println(i+"   "+(j-1));
    		 Element td= tds.get(j);
    		 faecher.add(td);	
    	 }
    	 
    	 stundenplan.add(i, faecher);
     }

     for(i=0;i<stundenplan.size();i++){
    	 List<Element> faecher=stundenplan.get(i);
    	 for(int j=0;j<faecher.size();j++){
    		 if(faecher.get(j).hasAttr("rowspan")){
    			 faecher.get(j).removeAttr("rowspan");
    			 if(i!=stundenplan.size()-1){
    			 List<Element> helperlist = stundenplan.get(i+1);
    			 helperlist.add(j,faecher.get(j));
    			 stundenplan.set(i+1, helperlist);
    			 }
    		 }
    	 }
     }
     System.out.println(stundenplan);
}
public List<Element> returnNewList() {
	   List<Element> suggestedPhrases = new ArrayList<Element>();
	   
	   return suggestedPhrases;
	}
}
