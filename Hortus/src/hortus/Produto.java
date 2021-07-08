
public class Produto {
	private int idProduto;
	private int idProdutor;
	private String nomeProduto;
	private String descricao;
	private int quantidade;
	private double precoProduto;
	private char unidade;
	private String ingredientes;
	private boolean organico;
	
	public Produto(int idProduto, int idProdutor, String nomeProduto, String descricao, int quantidade, double precoProduto, char unidade, String ingredientes, boolean organico) {	
		
		this.setIdProduto(idProduto);
		this.setIdProdutor(idProdutor);
		this.setNomeProduto(nomeProduto);
		this.setDescricao(descricao);
		this.setQuantidade(quantidade);
		this.setPrecoProduto(precoProduto);
		this.setUnidade(unidade);
		this.setIngredientes(ingredientes);
		
	}

	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}

	public int getIdProdutor() {
		return idProdutor;
	}

	public void setIdProdutor(int idProdutor) {
		this.idProdutor = idProdutor;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getPrecoProduto() {
		return precoProduto;
	}

	public void setPrecoProduto(double precoProduto) {
		this.precoProduto = precoProduto;
	}

	public char getUnidade() {
		return unidade;
	}

	public void setUnidade(char unidade) {
		this.unidade = unidade;
	}

	public String getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(String ingredientes) {
		this.ingredientes = ingredientes;
	}

	public boolean isOrganico() {
		return organico;
	}

	public void setOrganico(boolean organico) {
		this.organico = organico;
	}
}
