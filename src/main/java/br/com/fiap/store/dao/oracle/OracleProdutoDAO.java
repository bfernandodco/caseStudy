package br.com.fiap.store.dao.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.store.bean.Produto;
import br.com.fiap.store.dao.ProdutoDAO;
import br.com.fiap.store.exception.DatabaseException;
import br.com.fiap.store.singleton.ConnectionManager;

public class OracleProdutoDAO implements ProdutoDAO {

	private Connection connection;
	private PreparedStatement pstmt;
	private ResultSet rs;

	@Override
	public void cadastrarProduto(Produto produto) throws DatabaseException {
		String sqlQuery = "INSERT INTO TB_PRODUTO"
				+ "(cd_produto, nm_produto, qt_produto, vl_produto, dt_fabricacao)"
				+ "VALUES(SQ_TB_PRODUTO.NEXTVAL, ?, ?, ?, ?)";
		
		try {
			connection = ConnectionManager.getInstance().getConnection();
			pstmt = connection.prepareStatement(sqlQuery);
			pstmt.setString(1, produto.getNome());
			pstmt.setInt(2, produto.getQuantidade());
			pstmt.setDouble(3, produto.getValor());
			pstmt.setDate(4, java.sql.Date.valueOf(produto.getDataDeFabricacao()));
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
			throw new DatabaseException("Erro ao cadastrar produto.");
		} finally {
			try {
				connection.close();
				pstmt.close();
			} catch(SQLException e) {
				System.err.println("Erro ao fechar conexão em OracleProdutoDAO.cadastrarProduto()");
				e.printStackTrace();
			}
		}
	}

	@Override
	public void atualizarProduto(Produto produto) throws DatabaseException {
		String sqlQuery = "UPDATE TB_PRODUTO SET "
				+ "nm_produto = ?, qt_produto = ?, vl_produto = ?, dt_fabricacao = ? "
				+ "WHERE cd_produto = ?";
		
		try {
			connection = ConnectionManager.getInstance().getConnection();
			pstmt = connection.prepareStatement(sqlQuery);
			pstmt.setString(1, produto.getNome());
			pstmt.setInt(2,produto.getQuantidade());
			pstmt.setDouble(3, produto.getValor());
			pstmt.setDate(4, java.sql.Date.valueOf(produto.getDataDeFabricacao()));
			pstmt.setInt(5, produto.getCodigo());
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
			throw new DatabaseException("Erro ao atualizar produto em OracleProdutoDAO");
		} finally {
			try {
				connection.close();
				pstmt.close();
			} catch(SQLException e) {
				System.err.println("Erro ao fechar conexão em OracleProdutoDAO.atualizarProduto()");
				e.printStackTrace();
			}
		}
	}

	@Override
	public void removerProduto(Integer codigoDoProduto) throws DatabaseException {
		String sqlQuery = "DELETE FROM TB_PRODUTO WHERE cd_produto = ?";
		
		try {
			connection = ConnectionManager.getInstance().getConnection();
			pstmt = connection.prepareStatement(sqlQuery);
			pstmt.setInt(1, codigoDoProduto);
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
			throw new DatabaseException("Erro ao excluir produto em OracleProdutoDAO");
		} finally {
			try {
				connection.close();
				pstmt.close();
			} catch(SQLException e) {
				System.err.println("Erro ao fechar conexão em OracleProdutoDAO.removerProduto()");
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public Produto buscarProduto(Integer idDoProduto) {
		String sqlQuery = "SELECT * FROM TB_PRODUTO WHERE cd_Produto = ?";
		
		try {
			connection = ConnectionManager.getInstance().getConnection();
			pstmt = connection.prepareStatement(sqlQuery);
			pstmt.setInt(1, idDoProduto);
			rs = pstmt.executeQuery();
			Produto produto = new Produto();
			
			if(rs.next()) {
				
				produto.setCodigo(rs.getInt("cd_produto"));
				produto.setNome(rs.getString("nm_produto"));
				produto.setQuantidade(rs.getInt("vl_produto"));
				produto.setValor(rs.getDouble("vl_produto"));
				produto.setDataDeFabricacao(rs.getDate("dt_fabricacao").toLocalDate());
			}
			
			return produto;
		} catch(SQLException e) {
			System.err.println("ERRO AO BUSCAR PRODUITO EM OracleProdutoDAO.buscarProduto()");
			e.printStackTrace();
			return null;
		} finally {
			try {
				connection.close();
				pstmt.close();
			} catch(SQLException e) {
				System.err.println("Erro ao fechar conexão em OracleProdutoDAO.buscarProduto()");
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Produto> listarProdutos() {
		String sqlQuery = "SELECT * FROM TB_PRODUTO";
		List<Produto> produtos = new ArrayList<Produto>();	
		Produto produto = new Produto();
		
		try {
			connection = ConnectionManager.getInstance().getConnection();
			pstmt = connection.prepareStatement(sqlQuery);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				produto.setCodigo(rs.getInt("cd_produto"));
				produto.setNome(rs.getString("nm_produto"));
				produto.setQuantidade(rs.getInt("vl_produto"));
				produto.setValor(rs.getDouble("vl_produto"));
				produto.setDataDeFabricacao(rs.getDate("dt_fabricacao").toLocalDate());
				produtos.add(produto);
			}
			
			return produtos;
		} catch(SQLException e) {
			System.err.println("Erro ao listar produtos em OracleProdutoDAO.listarProduto()");
			e.printStackTrace();
			return null;
		} finally {
			try {
				connection.close();
				pstmt.close();
			} catch(SQLException e) {
				System.err.println("Erro ao fechar conexão em OracleProdutoDAO.listarProduto()");
				e.printStackTrace();
				return null;
			}
		}
	}

}