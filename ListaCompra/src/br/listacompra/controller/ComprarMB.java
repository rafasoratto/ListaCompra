package br.listacompra.controller;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;

import br.listacompra.dao.ListaComprasDao;
import br.listacompra.model.ItemLista;
import br.listacompra.model.ListaCompras;

@ManagedBean
@ViewScoped
public class ComprarMB implements Serializable {

	private static final long serialVersionUID = -8845116347565107714L;

	@EJB
	ListaComprasDao listaDao;
	
	private ListaCompras lista = new ListaCompras();
	
	public ListaCompras getLista() {
		return lista;
	}
	
	public void save(){
		lista = listaDao.save(lista);
	}

	public void retrieveLista(ValueChangeEvent evt) {
		if (evt == null) {
			return;
		}
		if (evt.getNewValue() == null) {
			return;
		}
		lista = listaDao.getListaByName(evt.getNewValue().toString());
	}
	
	public void comprar(ItemLista item) {
		System.out.println("asdsad");
	}
	
}
