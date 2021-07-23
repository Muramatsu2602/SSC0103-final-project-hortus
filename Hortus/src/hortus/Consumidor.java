package hortus;

import java.util.Vector;

public class Consumidor extends Usuario {

	public Consumidor(int id, String nome, String cpf, String telefone, Endereco endereco, String email, String senha) {
		super(id, nome, cpf, telefone, endereco, email, senha);
	}

	// Retorna FALSE se o ID do produto não existe, ou ocorreu algum erro
	public void adicionarProdutoFavorito(Produto p) throws HortusException {
		SGBD sgbd = new SGBD();
		sgbd.connect();
		sgbd.insereProdutoFavorito(this, p);			
	}
}
