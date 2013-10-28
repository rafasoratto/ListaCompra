package br.lsitacompra.servlet;

import java.io.IOException;
import java.io.OutputStream;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.listacompra.dao.ProdutosDao;
import br.listacompra.model.Produto;

@WebServlet("/recuperaimagem")
public class RecuperaImagem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	ProdutosDao produtosDao;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) {
		String id = request.getParameter("id");
		Produto p = produtosDao.getById(Produto.class, Integer.valueOf(id));
		if (p == null || p.getImgProduto() == null
				|| p.getImgProduto().getImg() == null) {
			return;
		}
		response.setContentType("imagem/gif");
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			out.write(p.getImgProduto().getImg());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
