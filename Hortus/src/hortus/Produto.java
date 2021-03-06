package hortus;

public class Produto {
	private int idProduto;
	private Produtor produtor;
	private String nomeProduto;
	private String descricao;
	private double quantidade;
	private double precoProduto;
	private int unidade;
	private String ingredientes;
	private boolean organico;
	private boolean excluido = false;
	
	
	public Produto(int idProduto, Produtor produtor, String nomeProduto, String descricao, double quantidade, double precoProduto, int unidade, String ingredientes, boolean organico, boolean excluido) {	
		
		this.setIdProduto(idProduto);
		this.setProdutor(produtor);
		this.setNomeProduto(nomeProduto);
		this.setDescricao(descricao);
		this.setQuantidade(quantidade);
		this.setPrecoProduto(precoProduto);
		this.setUnidade(unidade);
		this.setIngredientes(ingredientes);
		this.setOrganico(organico);
		this.setExcluido(excluido);
	}

	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}

	public Produtor getProdutor() {
		return produtor;
	}

	public void setProdutor(Produtor produtor) {
		this.produtor = produtor;
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

	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

	public double getPrecoProduto() {
		return precoProduto;
	}

	public void setPrecoProduto(double precoProduto) {
		this.precoProduto = precoProduto;
	}

	public int getUnidade() {
		return unidade;
	}

	public void setUnidade(int unidade) {
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

	public boolean isExcluido() {
		return excluido;
	}

	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}
	
}
