import java.util.Vector;

public class Consumidor extends Usuario {
	
	private Vector<Produto> listaProdutosFavoritos;
	
	public Consumidor(int id, String nome, String cpf, String telefone, Endereco endereco, String email, String senha) {
		super(id, nome, cpf, telefone, endereco, email, senha);
	}

	// Retorna FALSE se o ID do produto n�o existe, ou ocorreu algum erro
	public void adicionarProdutoFavorito(Produto produto) throws HortusException
	{
		 if(produto == null)
			throw new HortusException("Erro ao adicionar produto favorito para o usu�rio '"+getNome()+"'");
		
		listaProdutosFavoritos.addElement(produto);
	}
	
	public void removerProdutoFavorito(Produto produto) throws HortusException
	{
		 if(produto == null)
			throw new HortusException("Erro ao remover produto favorito do usu�rio '"+getNome()+"'");
		
		try{
			listaProdutosFavoritos.remove(listaProdutosFavoritos.indexOf(produto));	
		}catch(Exception err){
			throw new HortusException("Erro ao apagar da lista de favoritos do usu�rio '"+getNome()+"'Produto n�o encontrado!");
		}
	}

	public Vector<Produto> getListaProdutosFavoritos() {
		return listaProdutosFavoritos;
	}

	public void setListaProdutosFavoritos(Vector<Produto> listaProdutosFavoritos) {
		this.listaProdutosFavoritos = listaProdutosFavoritos;
	}
}
