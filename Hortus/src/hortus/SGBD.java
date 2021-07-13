package hortus;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
public class SGBD {
 	
 	/*
 		CREATE TABLE consumidor(
			 ID				 INTEGER			 PRIMARY KEY AUTOINCREMENT,
			 NOME			 CHAR(50)	 NOT NULL,
			 EMAIL			 CHAR(50)	 NOT NULL  UNIQUE,
			 SENHA			 CHAR(50)	 NOT NULL,
			 CPF			 CHAR(11)	 NOT NULL  UNIQUE,
			 TELEFONE		 CHAR(15)	 NOT NULL,
			 ID_ENDERECO	 INTEGER			 NOT NULL
		 );
		 
		 CREATE TABLE produtor(
			ID				INTEGER		PRIMARY KEY AUTOINCREMENT,
			NOME			CHAR(50)	NOT NULL,
			EMAIL			CHAR(50)	NOT NULL UNIQUE,
			SENHA			CHAR(50)	NOT NULL,
			CNPJ			CHAR(14)	NOT NULL UNIQUE,
			TELEFONE		CHAR(15)	NOT NULL,
			ID_ENDERECO		INTEGER		NOT NULL,
			CCIR			CHAR(30)	NOT NULL UNIQUE,
			TIPO_PROD		INTEGER		NOT NULL,
			DESCRICAO		CHAR(200)
		 );
		 
		 CREATE TABLE produto(
			ID		INTEGER			PRIMARY KEY AUTOINCREMENT,
			ID_PRODUTOR		INTEGER			NOT NULL,
			NOME			CHAR(50)		NOT NULL,
			DESCRICAO		CHAR(200),
			QUANTIDADE		NUMERIC(8,2)	NOT NULL,		
			PRECO			NUMERIC(8,2)	NOT NULL,
			UNIDADE			INTEGER			NOT NULL,
			INGREDIENTES	CHAR(100),
			ORGANICO		INTEGER			NOT NULL
		 );
		 
		 CREATE TABLE compra(
		 	ID				INTEGER			PRIMARY KEY AUTOINCREMENT,
		 	ID_CONSUMIDOR	INTEGER			NOT NULL,
		 	ID_PRODUTOR     INTEGER         NOT NULL,
		 	VALOR_FINAL		NUMERIC(8,2)	NOT NULL,
		 	ID_ENDERECO		INTEGER			NOT NULL,	
		 	DESCRICAO		CHAR(200)
		 );
		 
		 CREATE TABLE itens_compra (
			ID				INTEGER		PRIMARY KEY AUTOINCREMENT,
			ID_COMPRA		INTEGER		NOT NULL,
			ID_PRODUTO		INTEGER		NOT NULL,
			QTD				DECIMAL(10,2)  NOT NULL
		 );
		 
		 CREATE TABLE endereco(
			ID				INTEGER		PRIMARY KEY AUTOINCREMENT,
			ID_USUARIO		INTEGER		NOT NULL,
			RUA				CHAR(50)	NOT NULL,
			NUMERO			CHAR(10)	NOT NULL,
			COMPLEMENTO		CHAR(30),
			BAIRRO			CHAR(20)	NOT NULL,
			CEP 			CHAR(10)	NOT NULL,
			CIDADE			CHAR(30)	NOT NULL,
			ESTADO			CHAR(20)	NOT NULL
		 );
		 
		 CREATE TABLE produto_favorito(
			 ID				INTEGER		PRIMARY KEY AUTOINCREMENT,
			 ID_CONSUMIDOR	INTEGER		NOT NULL,
			 ID_PRODUTO		INTEGER		NOT NULL
		 );
 	*/
 	
 	public Connection connect(){
        Connection con = null;
        String banco = "Hortus.db";
        try{
        	File dbfile = new File("db");
            String url = "jdbc:sqlite:"+dbfile.getAbsolutePath()+"\\"+banco;
            con = DriverManager.getConnection(url);
        	return con;
            
        } catch(SQLException e){
            System.out.println("erro1 "+e.getMessage());
            //JOptionPane.showMessageDialog(null, "Erro \n"+e.getMessage());
        } catch(Exception err)
        {
        	System.out.print("erro2 "+err.getMessage());
        }
        return con;
    }
    
    public void insereConsumidor(Consumidor cons)
    {
		Connection con = this.connect();
		
		try{
			String sql = "SELECT MAX(ID) as ID FROM consumidor";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			int id = 0;
			if(rs.next()) {
				id = rs.getInt("ID") + 1;
			}
			cons.setId(id);
			
			sql = "INSERT INTO consumidor (NOME, EMAIL, SENHA, CPF, TELEFONE , ID_ENDERECO) VALUES (?, ?, ?, ?, ?, ?);";
			
			stmt = con.prepareStatement(sql);
			
			stmt.setString(1, cons.getNome());
			stmt.setString(2, cons.getEmail());
			stmt.setString(3, cons.getSenha());
			stmt.setString(4, cons.getCpf());
			stmt.setString(5, cons.getTelefone());
			stmt.setInt(6, cons.getEndereco().getIdEndereco());
			
			stmt.execute();
			con.close();
		} catch(SQLException e)
		{
			System.out.println("erro"+e.getMessage());
		}
	}
	
	public void insereProdutor(Produtor produtor) {
		try {
			Connection con = this.connect();
			
			String sql = "SELECT MAX(ID) as ID FROM produtor";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			int id = 0;
			if(rs.next())
				id = rs.getInt("ID") + 1;
			produtor.setId(id);
			
			sql = "INSERT INTO produtor(nome, cnpj, telefone, id_endereco, email, senha, ccir, tipo_prod, descricao) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
			
			stmt = con.prepareStatement(sql);
			stmt.setString(1, produtor.getNome());
			stmt.setString(2, produtor.getCpf());
			stmt.setString(3, produtor.getTelefone());
			stmt.setInt(4, produtor.getEndereco().getIdEndereco());
			stmt.setString(5, produtor.getEmail());
			stmt.setString(6, produtor.getSenha());
			stmt.setString(7, produtor.getCcir());
			stmt.setInt(8, produtor.getTipoProd());
			stmt.setString(9, produtor.getDescricao());
			
			stmt.execute();
			con.close();
		} catch(SQLException e){
            System.out.println("erro"+e.getMessage());
        }
	}
	
	public void insereProduto(Produto prod) {
		try{
			Connection con = connect();
			
			String sql = "SELECT MAX(ID) as ID FROM produto";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			int id = 0;
			if(rs.next())
				id = rs.getInt("ID") + 1;
			prod.setIdProduto(id);
		
			sql = "INSERT INTO produto (ID_PRODUTOR, NOME, DESCRICAO, QUANTIDADE, PRECO, UNIDADE, INGREDIENTES, ORGANICO) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";		   
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, prod.getIdProdutor());
			pstmt.setString(2, prod.getNomeProduto());
			pstmt.setString(3, prod.getDescricao());
			pstmt.setDouble(4, prod.getQuantidade());
			pstmt.setDouble(5, prod.getPrecoProduto());
			pstmt.setInt(6, prod.getUnidade());
			pstmt.setString(7, prod.getIngredientes());
			pstmt.setBoolean(8, prod.isOrganico());
			
			
			pstmt.execute();
			con.close();
		}catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
 
 	public void insereCompra(Compra compra) {
		try {
			Connection con = this.connect();
			
			String sql = "SELECT MAX(ID) as ID FROM compra";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			int id = 0;
			if(rs.next())	
				id = rs.getInt("ID") + 1;
			compra.setIdCompra(id);
			System.out.printf("%d\n", compra.getIdCompra());
			
			sql = "INSERT INTO compra(ID_CONSUMIDOR, ID_PRODUTOR, VALOR_FINAL, ID_ENDERECO, DESCRICAO) VALUES (?, ?, ?, ?, ?);";
			
			stmt = con.prepareStatement(sql);
			
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, compra.getConsumidor().getId());
			stmt.setInt(2, compra.getProdutor().getId());
			stmt.setDouble(3, compra.getValorFinal());
			stmt.setInt(4, compra.getEndereco().getIdEndereco());
			stmt.setString(5, compra.getDescricao());
			
			stmt.execute();
			con.close();
		} catch(SQLException e){
            System.out.println("erro"+e.getMessage());
        }
		
		// Insere item por item da compra realizada na tabela itens_compra
		/*for(Map<Produto, Double> c : compra.getListaProdutos()) {
			
		}*/
		compra.getListaProdutos().forEach((k, v) -> insereItensCompra(compra, k, v));
	}	
 	
 	public void insereItensCompra(Compra compra, Produto prod, Double qtd) {
		try {
			System.out.printf("%d\n", compra.getIdCompra());
			Connection con = this.connect();

			String sql = "INSERT INTO itens_compra(ID_COMPRA, ID_PRODUTO, QTD) VALUES (?, ?, ?);";
		
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, compra.getIdCompra());
			stmt.setInt(2, prod.getIdProduto());
			stmt.setDouble(3, qtd);
			
			stmt.execute();
			con.close();
		} catch(SQLException e){
            System.out.println("erro"+e.getMessage());
        }
	}	
 	
 	public void insereEndereco(Endereco endereco) {
		try {
			Connection con = this.connect();
			
			String sql = "SELECT MAX(ID) as ID from endereco";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			int id = 0;
			if(rs.next())
			{
				id = rs.getInt("ID") + 1;
				endereco.setIdEndereco(id);
			} else {
				endereco.setIdEndereco(id);
			}
			sql = "INSERT INTO endereco(RUA, NUMERO, COMPLEMENTO, BAIRRO, CEP, CIDADE, ESTADO) VALUES (?, ?, ?, ?, ?, ?, ?);";
		
			stmt = con.prepareStatement(sql);
			stmt.setString(1, endereco.getEndRua());
			stmt.setString(2, endereco.getEndNum());
			stmt.setString(3, endereco.getEndComplemento());
			stmt.setString(4, endereco.getEndBairro());
			stmt.setString(5, endereco.getEndCEP());
			stmt.setString(6, endereco.getEndCidade());
			stmt.setString(7, endereco.getEndEstado());
			
			stmt.execute();
			con.close();
		} catch(SQLException e){
            System.out.println("Erro Endereço "+e.getMessage());
        }
	}
 	
 	public void atualizaUsuarioEndereco(Endereco endereco, int idUsuario) {
		try {
			Connection con = this.connect();
			
			String sql = "UPDATE endereco set ID_USUARIO = ? WHERE ID = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, idUsuario);
			stmt.setInt(1, endereco.getIdEndereco());
			stmt.executeQuery();
			con.close();
		} catch(SQLException e){
            System.out.println("Erro Endereço "+e.getMessage());
        }
	}
 	
 	public void insereProdutoFavorito(Consumidor consumidor, Produto prod) {
		try {
			Connection con = this.connect();

			String sql = "INSERT INTO produto_favorito(ID_CONSUMIDOR, ID_PRODUTO) VALUES (?, ?);";
		
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, consumidor.getId());
			stmt.setInt(2, prod.getIdProduto());
			
			stmt.execute();
			con.close();
		} catch(SQLException e){
            System.out.println("erro"+e.getMessage());
        }
	}
 	
 	public Consumidor loginConsumidor(String email, String senha) throws HortusException
 	{
 		try {
			Connection con = this.connect();
 			String sql = "SELECT * FROM consumidor WHERE email = ? AND senha = ?;";
			PreparedStatement stmt = con.prepareStatement(sql);
 			stmt.setString(1, email);
 			stmt.setString(2, senha);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
			{
				Endereco end = null;
				String sql2 = "SELECT * FROM endereco WHERE ID = ?;";
				PreparedStatement stmt2 = con.prepareStatement(sql2);
				
				stmt2.setInt(1, rs.getInt("ID_ENDERECO"));
				ResultSet rs2 = stmt2.executeQuery();
				if(rs2.next())
				{
					end = new Endereco(rs2.getString("RUA"), rs2.getString("NUMERO"), rs2.getString("COMPLEMENTO"), rs2.getString("BAIRRO"), rs2.getString("CEP"), rs2.getString("CIDADE"), rs2.getString("ESTADO"));
				}
				
				// Inicializar o consumidor e no fim retorná-lo
				Consumidor cons = new Consumidor(rs.getInt("ID"), rs.getString("NOME"), rs.getString("CPF"), rs.getString("TELEFONE"), end, rs.getString("EMAIL"), rs.getString("SENHA"));
				
				con.close();
				return cons;
			} else {
				con.close();
				throw new HortusException("Usuario e/ou Senha incorretos"); 	
			}
 		} catch(SQLException e)
 		{
 			System.out.println("erro"+e.getMessage());
 		}
 		return null;
 	}
 	
 	public Produtor loginProdutor(String email, String senha) throws HortusException
 	{
 		try {
			Connection con = this.connect();
 			String sql = "SELECT * FROM produtor WHERE email = ? AND senha = ?;";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, email);
			stmt.setString(2, senha);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
			{
				Endereco end = null;
				String sql2 = "SELECT * FROM endereco WHERE ID = ?;";
				PreparedStatement stmt2 = con.prepareStatement(sql2);
				
				stmt.setInt(1, rs.getInt("ID_ENDERECO"));
				ResultSet rs2 = stmt2.executeQuery();
				if(rs2.next())
				{
					end = new Endereco(rs2.getString("RUA"), rs2.getString("NUMERO"), rs2.getString("COMPLEMENTO"), rs2.getString("BAIRRO"), rs2.getString("CEP"), rs2.getString("CIDADE"), rs2.getString("ESTADO"));
				}
				
				// Inicializar o Produtor e no fim retorná-lo
				Produtor prodt = new Produtor(rs.getInt("ID"), rs.getString("NOME"), rs.getString("CNPJ"), rs.getString("TELEFONE"), end, rs.getString("EMAIL"), rs.getString("SENHA"), rs.getString("CCIR"), rs.getInt("TIPO_PROD"), rs.getString("DESCRICAO"));
				
				con.close();
				return prodt;
			} else {
				con.close();
				throw new HortusException("Usuario e/ou Senha incorretos"); 	
			}
			
 		} catch(SQLException e)
 		{
 			System.out.println("erro"+e.getMessage());
 		}
 		return null;
 	}
 	
 	public Consumidor getConsumidorById(int id) throws HortusException
 	{
 		try {
			Connection con = this.connect();
 			String sql = "SELECT * FROM consumidor WHERE id = ?;";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
			{
				Endereco end = null;
				String sql2 = "SELECT * FROM endereco WHERE ID = ?;";
				PreparedStatement stmt2 = con.prepareStatement(sql2);
				
				stmt.setInt(1, rs.getInt("ID_ENDERECO"));
				ResultSet rs2 = stmt2.executeQuery();
				if(rs2.next())
				{
					end = new Endereco(rs2.getString("RUA"), rs2.getString("NUMERO"), rs2.getString("COMPLEMENTO"), rs2.getString("BAIRRO"), rs2.getString("CEP"), rs2.getString("CIDADE"), rs2.getString("ESTADO"));
				}
				
				// Inicializar o consumidor e no fim retorná-lo
				Consumidor cons = new Consumidor(rs.getInt("ID"), rs.getString("NOME"), rs.getString("CPF"), rs.getString("TELEFONE"), end, rs.getString("EMAIL"), rs.getString("SENHA"));
				
				con.close();
				return cons;
			} else {
				con.close();
				throw new HortusException("Usuario e/ou Senha incorretos"); 	
			}
			
 		} catch(SQLException e)
 		{
 			System.out.println("erro"+e.getMessage());
 		}
 		return null;
 	}
 	
 	public Produtor getProdutorById(int id) throws HortusException
 	{
 		try {
			Connection con = this.connect();
 			String sql = "SELECT * FROM produtor WHERE ID = ?;";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
			{
				Endereco end = null;
				String sql2 = "SELECT * FROM endereco WHERE ID = ?;";
				PreparedStatement stmt2 = con.prepareStatement(sql2);
				
				stmt.setInt(1, rs.getInt("ID_ENDERECO"));
				ResultSet rs2 = stmt2.executeQuery();
				if(rs2.next())
				{
					end = new Endereco(rs2.getString("RUA"), rs2.getString("NUMERO"), rs2.getString("COMPLEMENTO"), rs2.getString("BAIRRO"), rs2.getString("CEP"), rs2.getString("CIDADE"), rs2.getString("ESTADO"));
				}
				
				// Inicializar o Produtor e no fim retorná-lo
				Produtor prodt = new Produtor(rs.getInt("ID"), rs.getString("NOME"), rs.getString("CNPJ"), rs.getString("TELEFONE"), end, rs.getString("EMAIL"), rs.getString("SENHA"), rs.getString("CCIR"), rs.getInt("TIPO_PROD"), rs.getString("DESCRICAO"));
				
				con.close();
				return prodt;
			} else {
				con.close();
				throw new HortusException("Usuario e/ou Senha incorretos"); 	
			}
			
 		} catch(SQLException e)
 		{
 			System.out.println("erro"+e.getMessage());
 		}
 		return null;
 	}
 	
 	public Vector<Produto> getProdutosProdutor(int idProdutor) {
 		try {
 			Connection con = this.connect();
 			String sql = "SELECT * FROM produto WHERE ID_PRODUTOR = ?;";
 			PreparedStatement stmt = con.prepareStatement(sql);
 			stmt.setInt(1, idProdutor);
 			ResultSet rs = stmt.executeQuery();
 			
 			Vector<Produto> produtos = new Vector<Produto>();
 			while(rs.next())
 			{
 				produtos.add(new Produto(rs.getInt("ID"), rs.getInt("ID_PRODUTOR"), rs.getString("NOME"), rs.getString("DESCRICAO"), rs.getDouble("QUANTIDADE"), rs.getDouble("PRECO"), rs.getString("UNIDADE").charAt(0), rs.getString("INGREDIENTES"), rs.getBoolean("ORGANICO")));
 			}
 			
 			con.close();
 			return produtos;
 		} catch(SQLException e)
 		{
 			System.out.println("erro"+e.getMessage());
 		}
 		return null;
	}
 	
 	public Vector<Produto> getProdutosFavoritos(int idConsumidor) {
 		try {
 			Connection con = this.connect();
 			String sql = "SELECT * FROM produto_favorito INNER JOIN produto ON produto.ID = produto_favorito.ID_PRODUTO WHERE produto_favorito.ID_CONSUMIDOR = ?;";
 			PreparedStatement stmt = con.prepareStatement(sql);
 			stmt.setInt(1, idConsumidor);
 			ResultSet rs = stmt.executeQuery();
 			
 			Vector<Produto> produtos = new Vector<Produto>();
 			while(rs.next())
 			{
 				produtos.add(new Produto(rs.getInt("ID"), rs.getInt("ID_PRODUTOR"), rs.getString("NOME"), rs.getString("DESCRICAO"), rs.getDouble("QUANTIDADE"), rs.getDouble("PRECO"), rs.getString("UNIDADE").charAt(0), rs.getString("INGREDIENTES"), rs.getBoolean("ORGANICO")));
 			}
 			
 			con.close();
 			return produtos;
 		} catch(SQLException e)
 		{
 			System.out.println("Erro get Produtos Favoritos: "+e.getMessage());
 		}
 		return null;
	}
 	
 	public static void main(String args[])
 	{
 		// Cadastro consumidor
 		Endereco end = new Endereco("Rua da Roça", "13-40", "Fica no meio do mato", "Jd Rio Claro", "17123-023", "São Carlos", "São Paulo");
 		Consumidor consum = new Consumidor(1, "Giovanni", "536.872.752-17", "(14)99709-1009", end, "giovanni_shibaki@usp.br", "123");
 		//Produtor prod = new Produtor(1, "Seu zé", "729812782-12", "(16)99672-2712", end, "seuze.fazendeiro@gmail.com", "senhadozé", "1439821742-32", 1, "Só produto de qualidade feito pelo seu zé");
 		Produto produto = new Produto(3, 1, "Maça GOSTOSA", "Maça com gosto bom", 12.0, 5.99, 'k', "Maça, amor", true);
 		/*Produto produto2 = new Produto(4, 1, "Banana MARAVILHOSA", "Macaco gosta banana", (double)50.0, 2.00, 'k', "Banana, macaco e potassio", false);
 		Produto produto3 = new Produto(5, 1, "Pera SUPREENDENTE", "Pera ai meu caro", (double)20.0, 8.99, 'k', "Pera, to chegando", true);*/
 		
 		/*Map<Produto, Double> compras = new HashMap<Produto, Double>();
 		compras.put(produto, 2.5);
 		compras.put(produto2, 4.2);
 		compras.put(produto3, 6.9);*/
 		//Compra compra = new Compra(-1, consum, prod, compras, end, "Essa compra me deixou mais pobre");
 		
 		SGBD banco = new SGBD();
 		//banco.insereEndereco(end);
 		
 		//banco.insereConsumidor(consum);
 		//banco.insereProdutor(prod);
 		/*banco.insereProduto(produto);
 		banco.insereProduto(produto2);
 		banco.insereProduto(produto3);
 		banco.insereCompra(compra);*/
 		//banco.insereProdutoFavorito(consum, produto);
 		
 		// Login Consumidor
 		/*String email = "giovanni.shibaki@usp.br";
 		String senha = "123";
 			
 		try{
 			Consumidor consumidorLogado = banco.loginConsumidor(email, senha);
 			System.out.println("ID logado: "+consumidorLogado.getId());
 		} catch(HortusException err)
 		{
 			System.out.println(err.getMessage());
 		}*/
 		
 		// Login produtor
 		/*String email = "seuze.fazendeiro@gmail.com";
 		String senha = "senhadozé";
 			
 		try{
 			Produtor produtorLogado = banco.loginProdutor(email, senha);
 			System.out.println("ID logado: "+produtorLogado.getId());
 		} catch(HortusException err)
 		{
 			System.out.println(err.getMessage());
 		}*/
 		
 		/*try{
 			Consumidor consumidorAchado = banco.getConsumidorById(1);
 			System.out.println("Nome logado: "+consumidorAchado.getNome());
 		} catch(HortusException err)
 		{
 			System.out.println(err.getMessage());
 		}*/
 			
 		/*try{
			Produtor produtorAchado = banco.getProdutorById(1);
			System.out.println("Nome logado: "+produtorAchado.getNome());
		} catch(HortusException err)
		{
			System.out.println(err.getMessage());
		}*/
 		
 		/*Vector<Produto> produtosEncontrados = banco.getProdutosProduto(1);
 		for(Produto prod : produtosEncontrados)
 		{
 			System.out.println("Nome produto: "+prod.getNomeProduto());
 		}*/
 		
 		/*Vector<Produto> produtosEncontrados = banco.getProdutosFavoritos(1);
 		for(Produto prod : produtosEncontrados)
 		{
 			System.out.println("Nome produto: "+prod.getNomeProduto());
 		}*/
 		
 		return;
 	}
}