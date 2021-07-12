package hortus;
import java.util.Map;

public class Compra {
	private int idCompra;
	private Consumidor consumidor;
	
	// Mapa de produtos e quantidade (g, kg, unit�rio)
	private Map<Produto, Double> listaProdutos;
	private Double valorFinal = 0.0;

	// Endereco
	private Endereco endereco;

	// Se ser� em uma feira livre, mercadinho, encontro com o produtor
	private String descricao;

	public Compra(int idCompra, Consumidor consumidor, Map<Produto, Double> listaProdutos, Endereco endereco, String descricao) {
		
		this.setIdCompra(idCompra);
		this.setConsumidor(consumidor);
		this.setListaProdutos(listaProdutos);
		this.setEndereco(endereco);
		calculaValorFinal();
	}

	public int getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(int idCompra) {
		this.idCompra = idCompra;
	}

	public Consumidor getConsumidor() {
		return consumidor;
	}

	public void setConsumidor(Consumidor consumidor) {
		this.consumidor = consumidor;
	}

	public Map<Produto, Double> getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(Map<Produto, Double> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

	public Double getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal(Double valorFinal) {
		this.valorFinal = valorFinal;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public void calculaValorFinal() {
    	listaProdutos.forEach((k, v) -> valorFinal += k.getPrecoProduto()*v);
	}
}  