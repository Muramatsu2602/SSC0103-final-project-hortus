package hortus;

import java.util.Vector;

public class Produtor extends Usuario {
	private String ccir;
	private int tipoProd;
	private String descricao;
	Vector<Produto> produtos;

	public Produtor(int id, String nome, String cpf, String telefone, Endereco endereco, String email, String senha, String ccir, int tipoProd, String descricao) {
		super(id, nome, cpf, telefone, endereco, email, senha);
		this.setCcir(ccir);
		this.setTipoProd(tipoProd);
		this.setDescricao(descricao);
		produtos = new Vector<Produto>();
	}

	public String getCcir() {
		return ccir;
	}

	public void setCcir(String ccir) {
		this.ccir = ccir;
	}

	public int getTipoProd() {
		return tipoProd;
	}

	public void setTipoProd(int tipoProd) {
		this.tipoProd = tipoProd;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public void newProduto(Produto p) {
		// Se j� existe um produto com esse nome
			// throw Exception("J� existe um produto com esse nome.");
		// Se n�o existe
			// adiciona ao banco de dados
	}
	
	public void addProdutoExistente(int IDProduto) {
		// Se produto j� existe no banco de dados
			// Atualiza o produto no banco de dados incrementando em um a quantidade do produto
		// Se n�o existe
			// throw Exception("Esse produto n�o existe.");
	}
	
	public void removeProduto(int IDProduto){
		// Se o produto existe no banco de dados
			// Caso a quantidade seja 1
				// removo a entrada do produto da tabela do banco de dados
			// Caso contr�rio
				// diminuo em um a quantidade do produto
		// Caso n�o exista
			// throw Exception("Esse produto n�o existe.");
	}
}
