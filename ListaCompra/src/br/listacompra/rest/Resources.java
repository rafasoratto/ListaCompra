package br.listacompra.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.listacompra.dao.ListaComprasDao;
import br.listacompra.dao.ProdutosDao;
import br.listacompra.model.ListaCompras;
import br.listacompra.model.Produto;

@Stateless
@Path("/lista")
public class Resources {
	
	@EJB
	ProdutosDao produtosDao;
	
	@EJB
	ListaComprasDao ListaCompraDao;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/produtos")
	public Response getProdutos(){
		List<String> list = produtosDao.getAllNames(Produto.class);
		Response response = Response.ok(list).build();
		return response;
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/teste")
	public String getString(){
		return "Oi rest";
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/listas")
	public List<ListaCompras> getListaProdutos(){
		return ListaCompraDao.getAll(ListaCompras.class);
	}

}
