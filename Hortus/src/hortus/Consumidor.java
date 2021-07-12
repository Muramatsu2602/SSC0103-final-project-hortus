package hortus;

import java.util.Vector;

public class Consumidor extends Usuario {

	public Consumidor(int id, String nome, String cpf, String telefone, Endereco endereco, String email, String senha) {
		super(id, nome, cpf, telefone, endereco, email, senha);
	}
	
	public Consumidor() {
		
	}

	// Retorna FALSE se o ID do produto não existe, ou ocorreu algum erro
	public void adicionarProdutoFavorito(Produto p) throws HortusException {
		SGBD sgbd = new SGBD();
		sgbd.connect();
		// caso o produto já esteja nos favoritos (verificar pelo nome)
			// aviso que o produto já está nos favoritos
		// caso não esteja
			// Se o produto não existe
				// throw Exception("Produto não existe.");
			// Caso exista
				sgbd.insereProdutoFavorito(this, p);
				// adiciono esse produto na tabela de favoritos				
	}
	
	public void removerProdutoFavorito(int IDProduto) throws HortusException {
		 // se o produto não estiver nos favoritos
		 	// aviso que o produto não está nos favoritos
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
