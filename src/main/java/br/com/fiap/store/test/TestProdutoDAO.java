package br.com.fiap.store.test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.store.bean.Produto;
import br.com.fiap.store.dao.ProdutoDAO;
import br.com.fiap.store.exception.DatabaseException;
import br.com.fiap.store.factory.DAOFactory;

public class TestProdutoDAO {

	public static void main(String[] args) {
		ProdutoDAO produtoDAO = DAOFactory.getProdutoDAO();
		List<Produto> produtos = new ArrayList<Produto>();
		Produto produto = new Produto(0, "Caderno", 20.0, LocalDate.now(), 100);
		
		
		try {
			produtoDAO.cadastrarProduto(produto);
			System.out.println("Produto cadastrado com sucesso!");
		} catch (DatabaseException e) {
			System.err.println("Erro ao cadastrar produto.");
			e.printStackTrace();
		}
		
		produto = produtoDAO.buscarProduto(1);
		produto.setNome("Caderno Capa Dura");
		
		try {
			produtoDAO.atualizarProduto(produto);
			System.out.println("Produto atualizado com sucesso!");
		} catch (DatabaseException e) {
			System.err.println("Erro ao atualizar produto.");
			e.printStackTrace();
		}
		
		
		produtos = produtoDAO.listarProdutos();
		for(Produto produtoDaLista : produtos) {
			System.out.println(produtoDaLista);
		}
	}
}
