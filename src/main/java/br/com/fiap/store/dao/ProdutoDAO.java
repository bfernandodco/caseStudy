package br.com.fiap.store.dao;

import java.util.List;

import br.com.fiap.store.bean.Produto;
import br.com.fiap.store.exception.DatabaseException;

public interface ProdutoDAO {
	
	void cadastrarProduto(Produto produto) throws DatabaseException;
	void atualizarProduto(Produto produto) throws DatabaseException;
	void removerProduto(Integer codigoDoProduto) throws DatabaseException;
	Produto buscarProduto(Integer idDoProduto);
	List<Produto> listarProdutos();
}
