package hortus;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class Compra {
	private int idCompra;
	private Consumidor consumidor;
	private Produtor produtor;
	
	// Mapa de produtos e quantidade (g, kg, unitário)
	private Map<Produto, Double> listaProdutos;
	private Double valorFinal = 0.0;

	// Endereco
	private Endereco endereco;

	// Se será em uma feira livre, mercadinho, encontro com o produtor
	private String descricao;
	
	// Data da realização da compra
	private Date dataCompra;
	
	// Compra finalizada
	private boolean compraFinalizada = false;

	public Compra(int idCompra, Consumidor consumidor, Produtor produtor, Map<Produto, Double> listaProdutos, Endereco endereco, String descricao, String dataCompra) {
		this.setIdCompra(idCompra);
		this.setConsumidor(consumidor);
		this.setProdutor(produtor);
		this.setListaProdutos(listaProdutos);
		this.setEndereco(endereco);
		this.setDescricao(descricao);
		this.setDataCompra(dataCompra);
		calculaValorFinal();
	}

	public Date getDataCompra() {
		return dataCompra;
	}

	public void setCompraFinalizada()
	{
		this.compraFinalizada = true;
	}
	
	public void setDataCompra(String dataCompra)
	{   
		try{  
    		this.dataCompra = new SimpleDateFormat("yyyy-MM-dd").parse(dataCompra);
    	} catch(Exception err) {
			System.out.println(err.getMessage());
		}
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
		System.out.println("TAMANHO DA LISTA DE PRODUTOS: "+listaProdutos.size());
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

	public Produtor getProdutor() {
		return produtor;
	}

	public void setProdutor(Produtor produtor) {
		this.produtor = produtor;
	}

}  