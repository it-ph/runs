package com.personiv.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRow;


public class DocumentBuilder {
	
	XWPFDocument document;
	XWPFParagraph title;
	
	public DocumentBuilder() {
		init();
	}
	
	private void init() {
	  	document = new XWPFDocument();
    	title = document.createParagraph();
    	title.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun titleRun = title.createRun();
		titleRun.setText("Registration");
		titleRun.setColor("009933");
		titleRun.setBold(true);
		titleRun.setFontFamily("Calibri");
		titleRun.setFontSize(20);
	}
	

	
	public byte[] createTemplate() {
		
		byte bytes[] =new byte[1024];
		
		File file = null;
		OutputStream os = null;
		
		try {
			
			file = File.createTempFile("template", "docx"); //create a temporary file that will hold the data from template
			os = new FileOutputStream(file);
			os.write(bytes);
			os.close();
			
			XWPFDocument doc = new XWPFDocument(OPCPackage.open(file));
			
//			for (XWPFParagraph p : doc.getParagraphs()) {
//			    List<XWPFRun> runs = p.getRuns();
//			    if (runs != null) {
//			        for (XWPFRun r : runs) {
//			        	
//			        	String text = r.getText(0);
//			           			            
//			            if (text != null && text.contains("?")) { //replace ? with IT name
//			            	
//			                text = text.replace("?", acc.getIt_staff());
//			                r.setText(text, 0);
//			            }
//			            
//			            if (text != null && text.contains("*")) { //replace * with employee name
//			            	
//			                text = text.replace("*", acc.getEmployee());
//			                r.setText(text, 0);
//			            }
//			        }
//			    }
//			    
//			    
//			}
			
			XWPFTable table = doc.getTables().get(0);
			

			
	
			
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			
			doc.write(bos); //write the edited data to stream
			doc.close();
			
			bytes = bos.toByteArray();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}
		
		return bytes;
	}
	
}
