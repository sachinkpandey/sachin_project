package test;

import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import model.PropertyMapping;

public class Read {

	public static void main(String[] args) throws IOException {
	
		
		InputStream ExcelFileToRead = new FileInputStream("D:/PropertyExel.xlsx");
		XSSFWorkbook  wb = new XSSFWorkbook(ExcelFileToRead);
		
		XSSFWorkbook test = new XSSFWorkbook(); 
		
		XSSFSheet sheet = wb.getSheetAt(0);
		XSSFRow row; 
		XSSFCell cell;

		Iterator rows = sheet.rowIterator();

		ArrayList<HashMap<String,Object>> al=new ArrayList<>();
		int r=0;
		while (rows.hasNext())
		{
			
			row=(XSSFRow) rows.next();
			Iterator cells = row.cellIterator();
			int  c=0;
			
			if(r!=0){
				
				
				HashMap<String, Object> hs=new HashMap<>();
				
				
				while (cells.hasNext())
				{
					
					cell=(XSSFCell) cells.next();
					
			
					if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING)
					{
						hs.put(sheet.getRow(0).getCell(c).getStringCellValue(),cell.getStringCellValue() );
						c++;
						//System.out.print(cell.getStringCellValue()+" ");
					}
					else if(cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC)
					{
						hs.put(sheet.getRow(0).getCell(c).getStringCellValue(),(int)cell.getNumericCellValue());
						c++;
						//System.out.print((int)cell.getNumericCellValue()+" "); 
					}
					else
					{
						//U Can Handel Boolean, Formula, Errors
					}
				}
				if(!hs.isEmpty()){
					al.add(hs);
				}
				
				
			}
			r++;
			
		}
	
		ArrayList<PropertyMapping> arl=new ArrayList<>();
		for(HashMap<String,Object> h:al){
			PropertyMapping p=new PropertyMapping();

			p.setMapping_id((int)h.get("mapping_id"));
			p.setClient_id((int)h.get("client_id"));
			p.setProperty_id((int)h.get("property_id"));
			p.setProperty_value(""+(int)h.get("property_value"));
			p.setVersion_id((String)h.get("version_id"));
			p.setModified_by((int)h.get("modified_by"));
			p.setEnvironment_id((int)h.get("environment_id"));
			p.setIs_approved((String)h.get("is_approved"));
			p.setApproved_by((int)h.get("approved_by"));
			p.setReject_comment((String)h.get("reject_comment"));
			
			arl.add(p);
			
		}
		
		
		
		
		System.out.println(arl);
		
		ApplicationContext ap=new ClassPathXmlApplicationContext("resources/Spring.xml");
	
		

	}

}
