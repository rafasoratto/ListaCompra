package br.listacompra.controller;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;

import br.listacompra.dao.ListaComprasDao;
import br.listacompra.dao.ProdutosDao;
import br.listacompra.model.ItemLista;
import br.listacompra.model.ListaCompras;
import br.listacompra.model.Produto;

@ManagedBean
@ViewScoped
public class ListaComprasMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	ListaComprasDao listaDao;

	@EJB
	ProdutosDao produtosDao;

	public void save() {
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
		if (lista == null){
			lista = new ListaCompras();
		}
	}

	public void addProduto(ValueChangeEvent evt) {
		if (evt.getNewValue() == null) {
			return;
		}
		Produto p = produtosDao.getProdutoByName(evt.getNewValue().toString());
		if (p == null) {
			return;
		}
		ItemLista item = new ItemLista();
		item.setProduto(p);
		lista.addItem(item);
	}
	
	private ListaCompras lista = new ListaCompras();

	public ListaCompras getLista() {
		return lista;
	}

	public void setLista(ListaCompras lista) {
		this.lista = lista;
	}

	public void novo() {
		lista = new ListaCompras();
	}
	
	public void addProduto(ItemLista item){
		lista.addItem(item);
	}
	
	public void removerProduto(ItemLista item){
		lista.getItem().remove(item);
	}
}
