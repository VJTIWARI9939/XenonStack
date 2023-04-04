package com.cms.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.model.PdfFile;
import com.cms.repository.PdfRepository;

@Service
public class PdfService {

	@Autowired
	private PdfRepository pdfRepository;
	
	public String readPdf(String filePath) throws IOException {
	    File file = new File(filePath);
	    PDDocument document = PDDocument.load(file);
	    PDFTextStripper stripper = new PDFTextStripper();
	    String text = stripper.getText(document);
	    document.close();
	    return text;
	  }
	
	// retieve all pdf
			public List<PdfFile> getAllPdf(){
				
				return this.pdfRepository.findAll();
			}
	
}
