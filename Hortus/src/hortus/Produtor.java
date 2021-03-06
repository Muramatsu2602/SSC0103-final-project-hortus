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
		SGBD sgbd = new SGBD();
		sgbd.connect();
		sgbd.insereProduto(p);
	}
}
