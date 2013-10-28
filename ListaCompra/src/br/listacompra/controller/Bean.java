package br.listacompra.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

import org.primefaces.component.fileupload.FileUpload;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@RequestScoped
public class Bean {	
		private Part file;
	  private String fileContent;
	 
	  private FileUpload files;
	  
	  public FileUpload getFiles() {
		return files;
	}

	public void setFiles(FileUpload files) {
		this.files = files;
	}

	public void upload() {
		  System.out.println(files);
	    try {
	      fileContent = new Scanner(file.getInputStream())
	          .useDelimiter("\\A").next();
	      System.out.println(fileContent);
	    } catch (IOException e) {
	      // Error handling
	    }
	  }
	
	public void doUpload(FileUploadEvent fileUploadEvent) {
			System.out.println("PASSA");
			UploadedFile uploadedFile = fileUploadEvent.getFile(); 
			String fileNameUploaded = uploadedFile.getFileName();
			System.out.println(fileNameUploaded);
		}

	 
	  public Part getFile() {
	    return file;
	  }
	 
	  public void setFile(Part file) {
	    this.file = file;
	  }
	}
