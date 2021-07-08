import java.util.Vector;

public class Consumidor extends Usuario {
	
	private Vector<Produto> listaProdutosFavoritos;
	
	public Consumidor(int id, String nome, String cpf, String telefone, Endereco endereco, String email, String senha) {
		super(id, nome, cpf, telefone, endereco, email, senha);
	}

	// Retorna FALSE se o ID do produto não existe, ou ocorreu algum erro
	public void adicionarProdutoFavorito(Produto produto) throws HortusException
	{
		 if(produto == null)
			throw new HortusException("Erro ao adicionar produto favorito para o usuário '"+getNome()+"'");
		
		listaProdutosFavoritos.addElement(produto);
	}
	
	public void removerProdutoFavorito(Produto produto) throws HortusException
	{
		 if(produto == null)
			throw new HortusException("Erro ao remover produto favorito do usuário '"+getNome()+"'");
		
		try{
			listaProdutosFavoritos.remove(listaProdutosFavoritos.indexOf(produto));	
		}catch(Exception err){
			throw new HortusException("Erro ao apagar da lista de favoritos do usuário '"+getNome()+"'Produto não encontrado!");
		}
	}

	public Vector<Produto> getListaProdutosFavoritos() {
		return listaProdutosFavoritos;
	}

	public void setListaProdutosFavoritos(Vector<Produto> listaProdutosFavoritos) {
		this.listaProdutosFavoritos = listaProdutosFavoritos;
	}
}
