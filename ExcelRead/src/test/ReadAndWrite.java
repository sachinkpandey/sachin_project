package test;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


import model.PropertyMapping;

public class ReadAndWrite {


	
	
	
	
	public static void readExcel(String filepath) throws IOException{
		
		InputStream ExcelFileToRead = new FileInputStream(filepath);
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
		
		
		
		Configuration cfg=new Configuration();
		cfg.configure("resources/hibernate.cfg.xml");
		
		SessionFactory sf=cfg.buildSessionFactory();
		Session s=sf.openSession();
		
		Transaction t=s.beginTransaction();
		
		for(PropertyMapping pm:arl){
			s.save(pm);
		}
		
		t.commit();
		s.close();
		
	}
	
		
		public static void main(String[] args) throws IOException, ClassNotFoundException {
			
			//readExcel("D:/PropertyExel.xlsx");
			
			writeExcel();
			
			

		}
		
		


		private static void writeExcel() throws ClassNotFoundException {
			
			Configuration cfg=new Configuration();
			cfg.configure("resources/hibernate.cfg.xml");
			
			SessionFactory sf=cfg.buildSessionFactory();
			Session s=sf.openSession();
	
			Transaction t=s.beginTransaction();
			
			String hql="from PropertyMapping";
			
			Query q=s.createQuery(hql);
			
			ArrayList<PropertyMapping> alist=(ArrayList<PropertyMapping>) q.list();
			
			System.out.println(alist);
			
			
			
			
			
			
			   try {
				   File fileName = new File("D:/Fund1.xlsx");
				   fileName.createNewFile();
			        FileOutputStream fos = new FileOutputStream(fileName);
			        XSSFWorkbook  workbook = new XSSFWorkbook();            

			        XSSFSheet sheet = workbook.createSheet("fund1");  
			        
			        PropertyMapping p=new PropertyMapping();
					Class c=Class.forName(p.getClass().getName());
					Field[] f=c.getDeclaredFields();

			        Row row = sheet.createRow(0);
			        for(int i=0;i<f.length;i++){
			        	Cell cell = row.createCell(i);
			        	cell.setCellValue(f[i].getName());
			        }
			        
			        
			        for(int i=0;i<alist.size();i++){
			        	
			        	 row = sheet.createRow(i+1);
			        	PropertyMapping pm=alist.get(i);
			        	
			        	String str=pm.toString();
			        	String[] sarr=str.split(" ");
			        	
			        	for(int j=0;j<sarr.length;j++){
			        		 Cell cell = row.createCell(j);
			        		 
			        		 try{
			        			  int num = Integer.parseInt(sarr[j]);
			        			  cell.setCellValue(num);
			        			  // is an integer!
			        			} catch (NumberFormatException e) {
			        			  // not an integer!
			        				cell.setCellValue(sarr[j]);
			        				
			        			}
						        
			        	}
			        	
			        	
			        	
			        }
			        
			       
			        workbook.write(fos);
			        fos.flush();
			        fos.close();
			    } catch (IOException e) {
			       
			        e.printStackTrace();
			    }
			}
			
			
			
		}

