package hortus;

public class Produtor extends Usuario {
	private String ccir;
	private int tipoProd;
	private String descricao;

	public Produtor(int id, String nome, String cpf, String telefone, Endereco endereco, String email, String senha, String ccir, int tipoProd, String descricao) {
		super(id, nome, cpf, telefone, endereco, email, senha);
		this.setCcir(ccir);
		this.setTipoProd(tipoProd);
		this.setDescricao(descricao);
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
	
	public String getTipoProdString() {
		String[] tiposProdString = new String[] { "Apicultura", "Avicultura", "Bovinos", "Caprinos", "Cogumelos", "Condimentos",
				"Conservas", "Gr\u00E3os", "HortiFruiti", "Importados", "Latic\u00EDnios", "Ovinos", "A granel",
				"Apicultura", "Avicultura", "Bovinos", "Caprinos", "Cogumelos", "Condimentos", "Conservas",
				"Gr\u00E3os", "HortiFruiti", "Importados", "Latic\u00EDnios", "Outro", "Ovinos" };
		return tiposProdString[tipoProd];
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
			SGBD sgbd = new SGBD();
			sgbd.connect();
			sgbd.insereProduto(p);
	}
	
	public void addProdutoExistente(int IDProduto) {
		// Se produto j� existe no banco de dados
			// Atualiza o produto no banco de dados incrementando em um a quantidade do produto
			/*
			SGBD sgbd = new SGBD();
			sgbd.connect();
			Produto p = sgbd.selectProduto(IDProduto);
			sgbd.atualizaProduto(p);
			*/
		// Se n�o existe
			// throw Exception("Esse produto n�o existe.");
	}
	
	public void removeProduto(int IDProduto){
		// Se o produto existe no banco de dados
			/*
			SGBD sgbd = new SGBD();
			sgbd.connect();
			*/
			// Caso a quantidade seja 1
				// removo a entrada do produto da tabela do banco de dados
				// sgbd.removeProduto(IDProduto);
			// Caso contr�rio
				// diminuo em um a quantidade do produto
				/*
				Produto p = sgbd.selectProduto(IDProduto);
				p.setQuantidade(p.getQuantidade() - 1);
				sgbd.atualizaProduto(p);
				*/
		// Caso n�o exista
			// throw Exception("Esse produto n�o existe.");
	}
}
