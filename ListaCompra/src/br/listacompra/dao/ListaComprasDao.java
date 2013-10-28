package br.listacompra.dao;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import br.listacompra.model.ListaCompras;
import br.listacompra.model.Produto;

@Stateless
public class ListaComprasDao extends DAOImpl<ListaCompras, Integer> {

	public ListaCompras getListaByName(String name) {
		try{
		return (ListaCompras) em.createQuery(
				"select l from ListaCompras l where descricao = '" + name + "'")
				.getSingleResult();
		}catch (NoResultException e){
			return null;
		}
	}
	
}
