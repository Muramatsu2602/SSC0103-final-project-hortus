package hortus;

import java.util.Vector;

public class Consumidor extends Usuario {

	public Consumidor(int id, String nome, String cpf, String telefone, Endereco endereco, String email, String senha) {
		super(id, nome, cpf, telefone, endereco, email, senha);
	}
	
	public Consumidor() {
		
	}

	// Retorna FALSE se o ID do produto n�o existe, ou ocorreu algum erro
	public void adicionarProdutoFavorito(Produto p) throws HortusException {
		SGBD sgbd = new SGBD();
		sgbd.connect();
		// caso o produto j� esteja nos favoritos (verificar pelo nome)
			// aviso que o produto j� est� nos favoritos
		// caso n�o esteja
			// Se o produto n�o existe
				// throw Exception("Produto n�o existe.");
			// Caso exista
				sgbd.insereProdutoFavorito(this, p);
				// adiciono esse produto na tabela de favoritos				
	}
	
	public void removerProdutoFavorito(int IDProduto) throws HortusException {
		 // se o produto n�o estiver nos favoritos
		 	// aviso que o produto n�o est� nos favoritos
		 // caso esteja
		 	/*
		 	removo dos favoritos
		 	SGBD sgbd = new SGBD();
		 	sgbd.connect();
		 	sgbd.removeFavorito(IDProduto);
		 	*/
	}

	/*
	public Vector<Produto> getListaProdutosFavoritos() {
		// faz um SELECT * da tabela de produtos e retorna
	}
	*/
	
	/*public void insereConsumidor()
	{
		JsonDBTemplate jsonDBTemplate = hortus.SGBD.inicializarDB();
		jsonDBTemplate.insert(this);
	}*/
	
}
