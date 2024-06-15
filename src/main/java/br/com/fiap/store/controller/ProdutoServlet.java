package br.com.fiap.store.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.store.bean.Produto;
import br.com.fiap.store.dao.ProdutoDAO;
import br.com.fiap.store.exception.DatabaseException;
import br.com.fiap.store.factory.DAOFactory;

@WebServlet("/cadastro-de-produto")
public class ProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ProdutoDAO produtoDAO;
	
    @Override
    public void init() throws ServletException {
    	super.init();
    	produtoDAO = DAOFactory.getProdutoDAO();
    }
    
    public ProdutoServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String nome = request.getParameter("nome");
			Integer quantidade = Integer.parseInt(request.getParameter("quantidade"));
			Double valor = Double.parseDouble(request.getParameter("valor"));
			LocalDate fabricacao = LocalDate.parse(request.getParameter("fabricacao"), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			Produto produto = new Produto(2, nome, valor, fabricacao, quantidade);
			produtoDAO.cadastrarProduto(produto);
			
			request.setAttribute("message", "Produto cadastrado com sucesso!");
		} catch(DatabaseException e) {
			e.printStackTrace();
			request.setAttribute("error", "Erro ao cadastgrar produto.");
		} catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Dados inválidos.");
		}
		request.getRequestDispatcher("cadastro-de-produto.jsp").forward(request, response);
	}

}
