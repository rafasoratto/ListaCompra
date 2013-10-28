package br.listacompra.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.annotation.MultipartConfig;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.sun.xml.ws.wsdl.writer.document.Part;

import br.listacompra.dao.ProdutosDao;
import br.listacompra.model.ImgProduto;
import br.listacompra.model.Produto;

@ManagedBean
@RequestScoped
public class ProdutosMB implements Serializable {

	private static final long serialVersionUID = -8535501117786042918L;

	private String img;
	
	private javax.servlet.http.Part part;

	@EJB
	ProdutosDao produtosDao;

	private Produto produto = new Produto();

	public void upload(){
		System.out.println("PASSA");
		System.out.println(part);
		System.out.println(img);
	}
	
	
	public void save() {
		System.out.println("PASSA");
		System.out.println(part);
		System.out.println(img);
		ImgProduto img = null;
		if (file != null){
			System.out.println("AQUI");
			img = new ImgProduto();
			img.setImg(file.getContents());
		}
		System.out.println("L");
		if (img != null){
			produto.setImgProduto(img);
		}
		produtosDao.save(produto);
		produto = new Produto();
	}
	
	private UploadedFile file;  
	  
    public UploadedFile getFile() {  
        return file;  
    }  
  
    public void setFile(UploadedFile file) {  
        this.file = file;  
    }
    
    public void handleFileUpload(FileUploadEvent event) {
    	System.out.println("PASSA");  
        System.out.println(event.getFile().getFileName());  
    }

	private byte[] recuperaFileEmByte(File inFile) {
		InputStream is = null;
		byte[] buffer = null;
		try {
			is = new FileInputStream(inFile);
			buffer = new byte[is.available()];
			is.read(buffer);
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer;
	}

	public Produto getProduto() {
		return produto;
	}

	public void retrieveProduto(ValueChangeEvent evt) {
		if (evt == null) {
			return;
		}
		if (evt.getNewValue() == null) {
			return;
		}
		produto = produtosDao.getProdutoByName(evt.getNewValue().toString());
		if (produto == null) {
			produto = new Produto();
		}
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public javax.servlet.http.Part getPart() {
		return part;
	}

	public void setPart(javax.servlet.http.Part part) {
		this.part = part;
	}

}
