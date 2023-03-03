package com.climax.statistiques.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.climax.statistiques.entities.clients;
import com.climax.statistiques.service.ClientsServices;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

@Controller
public class ClientsController {
	
	@Autowired
	ClientsServices clientsServices;
	
	@RequestMapping(value="/showCreate", method = RequestMethod.GET)
	//@ResponseBody
	public String showCreate()
	{
	return "creerClients";
	}

	@RequestMapping(value="/saveclients",  method = RequestMethod.POST)
	//@ResponseBody
	public String saveclients(@ModelAttribute("client") clients client, 
	 ModelMap modelMap) throws ParseException 
	{
	
	 
	clients saveclients = clientsServices.saveclients(client);
	String msg ="client enregistré avec Id "+saveclients.getIdclient();
	modelMap.addAttribute("msg", msg);
	return "creerClients";
	}
	
	@RequestMapping(value="/ListeClients", method = RequestMethod.GET)
	//@ResponseBody
	public String listeClients(ModelMap modelMap)
	{
	List<clients> cli = clientsServices.getAllclients();
	modelMap.addAttribute("clients", cli);
	return "listeClients";
	}

	@RequestMapping(value="/supprimerClient", method = RequestMethod.GET)
	//@ResponseBody
	public String supprimerClient(@RequestParam("id") Integer id,
	 ModelMap modelMap)
	{ 
	clientsServices.deleclientsById(id);
	List<clients> cli = clientsServices.getAllclients();
	modelMap.addAttribute("clients", cli);
	return "listeClients";
	}

	@RequestMapping(value="/modifierClient", method = RequestMethod.GET)
	//@ResponseBody
	public String editerClient(@RequestParam("id") Integer id,ModelMap modelMap)
	{
	clients cli= clientsServices.getclients(id);
	modelMap.addAttribute("clients", cli);
	return "editerClients";
	}
	@RequestMapping(value="/updateclients", method = RequestMethod.POST)
	//@ResponseBody
	public String updateclients(@ModelAttribute("client") clients client,
	ModelMap modelMap) throws ParseException 
	{
	
	 clientsServices.updateclients(client);
	 List<clients> cli = clientsServices.getAllclients();
	 modelMap.addAttribute("clients", cli);
	return "listeClients";
	}
	
	//pour une recherche sur la profession
	@RequestMapping(value="/average-salary", method = RequestMethod.GET)
	//@ResponseBody
	public String getAverageSalary(@RequestParam String profession, ModelMap modelMap) {
	    double average = 0;
		try {
			average = clientsServices.averagesalary(profession);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    modelMap.addAttribute("profession", profession);
	    modelMap.addAttribute("average", average);
	    return "average-salary";
	}
	
	
	@RequestMapping(value="/average-salary2", method = RequestMethod.GET)
	//@ResponseBody
	public String getAverageSalary(ModelMap modelMap) {
	    Map<String, Double> averageSalaryByProfession = null;
	    try {
	        averageSalaryByProfession = clientsServices.allaveragesalary();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    modelMap.addAttribute("averageSalaryByProfession", averageSalaryByProfession);
	    System.out.println("Nombre de salaires moyens : " + averageSalaryByProfession.size());
	    return "average-salary2";
	}

	
	
	@RequestMapping(value="/readclients", method = RequestMethod.POST)
	//@ResponseBody
    public String uploadCsvFile(ModelMap modelMap, @RequestParam("file") MultipartFile file) throws IOException, ServletException {
		String mtype = file.getContentType();
		System.out.println("acçhdbdcb bscsb ss"+mtype);
		switch (mtype) {
		//lecture fichier csv
		case "text/csv":
			BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
	        String line = null;
	        while ((line = reader.readLine()) != null) {
	            String[] fields = line.split(",");
	            String nompartenaire = fields[0].trim();
	            String nomclient = fields[1].trim();
	            String prenomclient = fields[2].trim();
	            int age = Integer.parseInt(fields[3].trim());
	            String profession = fields[4].trim();
	            double salaire = Double.parseDouble(fields[5].trim().replace(";", ""));
	            clients cli = new clients(nompartenaire, nomclient, prenomclient, age, profession, salaire);
	            clientsServices.readclients(cli);
	        }
	        reader.close();
		break;
		//lecture fichier xml
		case "text/xml":
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = null;
			try {
				dBuilder = dbFactory.newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        Document doc = null;
			try {
				doc = dBuilder.parse(file.getInputStream());
			} catch (SAXException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        doc.getDocumentElement().normalize();
	        NodeList nodeList = doc.getElementsByTagName("client");
	        for (int i = 0; i < nodeList.getLength(); i++) {
	        Node node = nodeList.item(i);
	        	if (node.getNodeType() == Node.ELEMENT_NODE) {
	        			Element element = (Element) node;
	        			String nompartenaire = element.getElementsByTagName("nompartenaire").item(0).getTextContent();
	        			String nomclient = element.getElementsByTagName("nomclient").item(0).getTextContent();
	        			String prenomclient = element.getElementsByTagName("prenomclient").item(0).getTextContent();
	        			int age = Integer.parseInt(element.getElementsByTagName("age").item(0).getTextContent());
	        			String profession = element.getElementsByTagName("profession").item(0).getTextContent();
	        			double salaire = Double.parseDouble(element.getElementsByTagName("salaire").item(0).getTextContent());
	        			clients c = new clients(nompartenaire, nomclient, prenomclient, age, profession, salaire);
	        			clientsServices.readclients(c);
	        	}
	        }
			break;
			//lecture fichier json
		case "application/json":
			ObjectMapper objectMapper = new ObjectMapper();
		    JsonNode rootNode = objectMapper.readTree(file.getInputStream());
		    JsonNode clientsNode = rootNode.path("clients");
		    Iterator<JsonNode> clientsIterator = clientsNode.elements();
		    while (clientsIterator.hasNext()) {
		        JsonNode clientNode = clientsIterator.next();
		        String nompartenaire = clientNode.path("nompartenaire").asText();
		        String nomclient = clientNode.path("nomclient").asText();
		        String prenomclient = clientNode.path("prenomclient").asText();
		        int age = clientNode.path("age").asInt();
		        String profession = clientNode.path("profession").asText();
		        double salaire = clientNode.path("salaire").asDouble();
		        clients c = new clients(nompartenaire, nomclient, prenomclient, age, profession, salaire);
		        clientsServices.readclients(c);
		    }
		    break;
		  //lecture fichier txt
		  		case "text/plain":
		  			BufferedReader readertxt = new BufferedReader(new InputStreamReader(file.getInputStream()));
		  	        String linetxt = null;
		  	        while ((linetxt = readertxt.readLine()) != null) {
		  	            String[] fields = linetxt.split(",");
		  	            String nompartenaire = fields[0].trim();
		  	            String nomclient = fields[1].trim();
		  	            String prenomclient = fields[2].trim();
		  	            int age = Integer.parseInt(fields[3].trim());
		  	            String profession = fields[4].trim();
		  	            double salaire = Double.parseDouble(fields[5].trim().replace(";", ""));
		  	            clients cli = new clients(nompartenaire, nomclient, prenomclient, age, profession, salaire);
		  	            clientsServices.readclients(cli);
		  	        }
		  	        readertxt.close();
		  		break;
		  	//lecture fichier docx
		  		case "application/vnd.openxmlformats-officedocument.wordprocessingml.document":
		  			XWPFDocument docx = new XWPFDocument(file.getInputStream());
		  	        XWPFWordExtractor extractor = new XWPFWordExtractor(docx);
		  	        String text = extractor.getText();
		  	        String[] lines = text.split("\n");

		  	        for (String linedocx : lines) {
		  	            String[] fields = linedocx.split(",");
		  	            String nompartenaire = fields[0].trim();
		  	            String nomclient = fields[1].trim();
		  	            String prenomclient = fields[2].trim();
		  	            int age = Integer.parseInt(fields[3].trim());
		  	            String profession = fields[4].trim();
		  	            double salaire = Double.parseDouble(fields[5].trim().replace(";", ""));
		  	            clients cli = new clients(nompartenaire, nomclient, prenomclient, age, profession, salaire);
		  	            clientsServices.readclients(cli);
		  	        }
		  	        extractor.close();
		  		break;
		  	//lecture fichier xls
		  		case "application/vnd.ms-excel":
		  			Workbook workbook = null;
		  			workbook = new HSSFWorkbook(file.getInputStream());
		  			Sheet sheet = workbook.getSheetAt(0);
		  	        Iterator<Row> rowIterator = sheet.iterator();
		  	        while (rowIterator.hasNext()) {
		  	            Row row = rowIterator.next();
		  	            String nompartenaire = row.getCell(0).getStringCellValue();
		  	            String nomclient = row.getCell(1).getStringCellValue();
		  	            String prenomclient = row.getCell(2).getStringCellValue();
		  	            int age = (int) row.getCell(3).getNumericCellValue();
		  	            String profession = row.getCell(4).getStringCellValue();
		  	            double salaire = row.getCell(5).getNumericCellValue();
		  	            clients cli = new clients(nompartenaire, nomclient, prenomclient, age, profession, salaire);
		  	            clientsServices.readclients(cli);
		  	        }

		  	        workbook.close();
		  	     break;
		  	 //lecture fichier xlsx
		  		case "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet":
		  			Workbook workbook1 = null;
		  			workbook1 = new XSSFWorkbook(file.getInputStream());
		  			Sheet sheet1 = workbook1.getSheetAt(0);
		  	        Iterator<Row> rowIterator1 = sheet1.iterator();
		  	        while (rowIterator1.hasNext()) {
		  	            Row row = rowIterator1.next();
		  	            String nompartenaire = row.getCell(0).getStringCellValue();
		  	            String nomclient = row.getCell(1).getStringCellValue();
		  	            String prenomclient = row.getCell(2).getStringCellValue();
		  	            int age = (int) row.getCell(3).getNumericCellValue();
		  	            String profession = row.getCell(4).getStringCellValue();
		  	            double salaire = row.getCell(5).getNumericCellValue();
		  	            clients cli = new clients(nompartenaire, nomclient, prenomclient, age, profession, salaire);
		  	            clientsServices.readclients(cli);
		  	        }

		  	        workbook1.close();
		  	      break;
		  	      
		  	      default:
		  	    	throw new ServletException("Le fichier doit être au format CSV ou XML ou DOC ou XLSX ou TXT ou JSON");
		  			
		  			
			
		}
        
        
    
		List<clients> cli = clientsServices.getAllclients();
		 modelMap.addAttribute("clients", cli);
        return "listeClients";
        
	}	
	
}
