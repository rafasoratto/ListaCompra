package br.listacompra.dao;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;

import br.listacompra.model.Produto;

@Stateless
public class ProdutosDao extends DAOImpl<Produto, Integer> {

	public Produto getProdutoByName(String name) {
		try{
		return (Produto) em.createQuery(
				"select p from Produto p where nome = '" + name + "'")
				.getSingleResult();
		}catch (NoResultException e){
			return null;
		}
	}
}
