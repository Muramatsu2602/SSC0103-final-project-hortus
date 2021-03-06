package hortus;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Vector;


import java.util.HashMap;
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
			ORGANICO		INTEGER			NOT NULL,
			EXCLUIDO        INTEGER         DEFAULT 0
		 );
		 
		 CREATE TABLE compra(
		 	ID				INTEGER			PRIMARY KEY AUTOINCREMENT,
		 	ID_CONSUMIDOR	INTEGER			NOT NULL,
		 	ID_PRODUTOR     INTEGER         NOT NULL,
		 	VALOR_FINAL		NUMERIC(8,2)	NOT NULL,
		 	ID_ENDERECO		INTEGER			NOT NULL,	
		 	DESCRICAO		CHAR(200),
		 	DATA_COMPRA     CHAR(10)        NOT NULL,
		 	FINALIZADA      INTEGER         DEFAULT 0
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
 	*/
 	
 	public Connection connect(){
        Connection con = null;
        String banco = "Hortus.db";
        try{
        	Class.forName("org.sqlite.JDBC");
        	File dbfile = new File("db");
            String url = "jdbc:sqlite:"+dbfile.getAbsolutePath()+"\\"+banco;
            con = DriverManager.getConnection(url);
        	return con;
            
        } catch(SQLException e){
            System.out.println("erro1 "+e.getMessage());
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
			
			pstmt.setInt(1, prod.getProdutor().getId());
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
			
			sql = "INSERT INTO compra(ID_CONSUMIDOR, ID_PRODUTOR, VALOR_FINAL, ID_ENDERECO, DESCRICAO, DATA_COMPRA) VALUES (?, ?, ?, ?, ?, DATE('now'));";
			
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
			
			prod.setQuantidade(prod.getQuantidade() - qtd);
			atualizaProduto(prod);
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
			sql = "INSERT INTO endereco(ID_USUARIO, RUA, NUMERO, COMPLEMENTO, BAIRRO, CEP, CIDADE, ESTADO) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
		
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, endereco.getIdUsuario());
			stmt.setString(2, endereco.getEndRua());
			stmt.setString(3, endereco.getEndNum());
			stmt.setString(4, endereco.getEndComplemento());
			stmt.setString(5, endereco.getEndBairro());
			stmt.setString(6, endereco.getEndCEP());
			stmt.setString(7, endereco.getEndCidade());
			stmt.setString(8, endereco.getEndEstado());
			
			stmt.execute();
			con.close();
		} catch(SQLException e){
            System.out.println("Erro Endere?o "+e.getMessage());
        }
	}
 	
 	public void atualizaUsuarioEndereco(Endereco endereco, int idUsuario) {
		try {
			Connection con = this.connect();
			
			String sql = "UPDATE endereco set ID_USUARIO = ? WHERE ID = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, idUsuario);
			stmt.setInt(2, endereco.getIdEndereco());
			stmt.execute();
			con.close();
		} catch(SQLException e){
            System.out.println("Erro Endere?o "+e.getMessage());
        }
	}
 	
 	public void finalizarCompra(Compra compra) {
		try {
			Connection con = this.connect();
			
			String sql = "UPDATE compra set FINALIZADA = 1 WHERE ID = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, compra.getIdCompra());
			stmt.execute();
			con.close();
		} catch(SQLException e){
            System.out.println("Erro finalizar Compra "+e.getMessage());
        }
		compra.setCompraFinalizada();
	}
	
	public void excluirProduto(Produto prod) throws HortusException
 	{
 		try {
			Connection con = this.connect();
 			String sql = "SELECT * FROM itens_compra INNER JOIN compra ON itens_compra.ID_COMPRA = compra.ID WHERE itens_compra.ID_PRODUTO = ? AND compra.FINALIZADA = 0;";
			PreparedStatement stmt = con.prepareStatement(sql);
 			stmt.setInt(1, prod.getIdProduto());
			ResultSet rs = stmt.executeQuery();
			if(!rs.next())
			{
				// N?o achou nenhum, ent?o pode excluir
				String sql2 = "UPDATE produto SET EXCLUIDO = 1 WHERE ID = ?;";
				PreparedStatement stmt2 = con.prepareStatement(sql2);
				stmt2.setInt(1, prod.getIdProduto());
				stmt2.execute();
				
				con.close();
			} else {
				con.close();
				throw new HortusException("Produto nao pode ser excluido, ha compras pendentes.");
			}
 		} catch(SQLException e)
 		{
 			System.out.println("Erro exlcuir produto: "+e.getMessage());
 		}
 		return;
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
 		Connection con = this.connect();
 		
 		try {
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
					end.setIdEndereco(rs.getInt("ID_ENDERECO"));
				}
				
				// Inicializar o consumidor e no fim retorn?-lo
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
				
				stmt2.setInt(1, rs.getInt("ID_ENDERECO"));
				ResultSet rs2 = stmt2.executeQuery();
				if(rs2.next())
				{
					end = new Endereco(rs2.getString("RUA"), rs2.getString("NUMERO"), rs2.getString("COMPLEMENTO"), rs2.getString("BAIRRO"), rs2.getString("CEP"), rs2.getString("CIDADE"), rs2.getString("ESTADO"));
					end.setIdEndereco(rs.getInt("ID_ENDERECO"));
				}
				if(end == null)
					System.out.println("Endereco nulo!!\n");
				
				// Inicializar o Produtor e no fim retorn?-lo
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
				Endereco end = getEnderecoById(rs.getInt("ID_ENDERECO"));
				
				// Inicializar o consumidor e no fim retorn?-lo
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
				
				// Inicializar o Produtor e no fim retorn?-lo
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
 	
 	public Endereco getEnderecoById(int id) throws HortusException
 	{
 		try {
			Connection con = this.connect();
			Endereco end = null;
			String sql2 = "SELECT * FROM endereco WHERE ID = ?;";
			PreparedStatement stmt2 = con.prepareStatement(sql2);
			stmt2.setInt(1, id);
			ResultSet rs2 = stmt2.executeQuery();
			if(rs2.next())
			{
				end = new Endereco(rs2.getString("RUA"), rs2.getString("NUMERO"), rs2.getString("COMPLEMENTO"), rs2.getString("BAIRRO"), rs2.getString("CEP"), rs2.getString("CIDADE"), rs2.getString("ESTADO"));
				end.setIdEndereco(rs2.getInt("ID"));
			}
			else {
				con.close();
				throw new HortusException("Endere?o n?o encontrado!"); 	
			}
			
			con.close();
			return end;
 		} catch(SQLException e)
 		{
 			System.out.println("erro get endereco by id"+e.getMessage());
 		}
 		return null;
 	}
 	
 	public Produto getProdutoById(int id) throws HortusException
 	{
 		try {
			Connection con = this.connect();
			Produto prod = null;
			String sql = "SELECT * FROM produto WHERE ID = ?;";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
			{
				prod = new Produto(rs.getInt("ID"), getProdutorById(rs.getInt("ID_PRODUTOR")), rs.getString("NOME"), rs.getString("DESCRICAO"), rs.getDouble("QUANTIDADE"), rs.getDouble("PRECO"), rs.getInt("UNIDADE"), rs.getString("INGREDIENTES"), rs.getBoolean("ORGANICO"), rs.getBoolean("EXCLUIDO"));
			}
			else {
				con.close();
				throw new HortusException("Endere?o n?o encontrado!"); 	
			}
			
			con.close();
			return prod;
 		} catch(Exception e)
 		{
 			System.out.println("erro"+e.getMessage());
 		}
 		return null;
 	}
 	
 	public Vector<Produto> getProdutosProdutor(int idProdutor, boolean pegarExcluidos) {
 		try {
 			Connection con = this.connect();
 			String sql = "SELECT * FROM produto WHERE ID_PRODUTOR = ?;";
 			PreparedStatement stmt = con.prepareStatement(sql);
 			stmt.setInt(1, idProdutor);
 			ResultSet rs = stmt.executeQuery();
 			
 			Vector<Produto> produtos = new Vector<Produto>();
 			while(rs.next())
 			{
 				if(pegarExcluidos == false && rs.getBoolean("EXCLUIDO") == false)
 					produtos.add(new Produto(rs.getInt("ID"), getProdutorById(rs.getInt("ID_PRODUTOR")), rs.getString("NOME"), rs.getString("DESCRICAO"), rs.getDouble("QUANTIDADE"), rs.getDouble("PRECO"), rs.getInt("UNIDADE"), rs.getString("INGREDIENTES"), rs.getBoolean("ORGANICO"), rs.getBoolean("EXCLUIDO")));
 			}
 			
 			con.close();
 			return produtos;
 		} catch(Exception e)
 		{
 			System.out.println("erro produtos produtor "+e.getMessage());
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
 				produtos.add(new Produto(rs.getInt("ID"), getProdutorById(rs.getInt("ID_PRODUTOR")), rs.getString("NOME"), rs.getString("DESCRICAO"), rs.getDouble("QUANTIDADE"), rs.getDouble("PRECO"), rs.getInt("UNIDADE"), rs.getString("INGREDIENTES"), rs.getBoolean("ORGANICO"), rs.getBoolean("EXCLUIDO")));
 			}
 			
 			con.close();
 			return produtos;
 		} catch(Exception e)
 		{
 			System.out.println("Erro get Produtos Favoritos: "+e.getMessage());
 		}
 		return null;
	}
 	
 	public Vector<Compra> getComprasByConsumidor(int idConsumidor)
 	{
 		try {
 			Connection con = this.connect();
 			String sql = "SELECT * FROM compra WHERE ID_CONSUMIDOR = ?;";
 			PreparedStatement stmt = con.prepareStatement(sql);
 			stmt.setInt(1, idConsumidor);
 			ResultSet rs = stmt.executeQuery();
 			
 			Vector<Compra> compras = new Vector<Compra>();
 			while(rs.next())
 			{
 				// Pegar todos os itens_compra da compra atual
 				Map<Produto, Double> listaProdutos = getItensCompra(rs.getInt("ID"));
 				Compra compra = new Compra(rs.getInt("ID"), getConsumidorById(rs.getInt("ID_CONSUMIDOR")), getProdutorById(rs.getInt("ID_PRODUTOR")), listaProdutos, getEnderecoById(rs.getInt("ID_ENDERECO")), rs.getString("DESCRICAO"), rs.getString("DATA_COMPRA"));
 				if(rs.getInt("FINALIZADA") == 1) 
 				{
					 compra.setCompraFinalizada();
				}
 				compras.add(compra);
 			}
 			con.close();
 			return compras;
 		} catch(Exception e)
 		{
 			System.out.println("Erro get compras by consumidor: "+e.getMessage());
 		}
 		return null;
 	}
 	
 	public Vector<Compra> getComprasByProdutor(int idProdutor)
 	{
 		try {
 			Connection con = this.connect();
 			String sql = "SELECT * FROM compra WHERE ID_PRODUTOR = ?;";
 			PreparedStatement stmt = con.prepareStatement(sql);
 			stmt.setInt(1, idProdutor);
 			ResultSet rs = stmt.executeQuery();
 			
 			Vector<Compra> compras = new Vector<Compra>();
 			while(rs.next())
 			{
 				// Pegar todos os itens_compra da compra atual
 				Map<Produto, Double> listaProdutos = getItensCompra(rs.getInt("ID"));
 				
 				Compra compra = new Compra(rs.getInt("ID"), getConsumidorById(rs.getInt("ID_CONSUMIDOR")), getProdutorById(rs.getInt("ID_PRODUTOR")), listaProdutos, getEnderecoById(rs.getInt("ID_ENDERECO")), rs.getString("DESCRICAO"), rs.getString("DATA_COMPRA"));
 				if(rs.getInt("FINALIZADA") == 1)
 				{
 					compra.setCompraFinalizada();
 				}
 				compras.add(compra);		
 			}
 			con.close();
 			return compras;
 		} catch(Exception e)
 		{
 			System.out.println("Erro get compras by produto: "+e.getMessage());
 		}
 		return null;
 	}
 	
	public Map<Produto, Double> getItensCompra(int idCompra) {
		try {
 			Connection con = this.connect();
 			String sql = "SELECT * FROM itens_compra INNER JOIN produto ON produto.ID = itens_compra.ID_PRODUTO WHERE itens_compra.ID_COMPRA = ?;";
 			PreparedStatement stmt = con.prepareStatement(sql);
 			stmt.setInt(1, idCompra);
 			ResultSet rs = stmt.executeQuery();
 			
 			Map<Produto, Double> itensCompra = new HashMap<Produto, Double>();
 			while(rs.next())
 			{
 				itensCompra.put(new Produto(rs.getInt("ID"), getProdutorById(rs.getInt("ID_PRODUTOR")), rs.getString("NOME"), rs.getString("DESCRICAO"), rs.getDouble("QUANTIDADE"), rs.getDouble("PRECO"), rs.getInt("UNIDADE"), rs.getString("INGREDIENTES"), rs.getBoolean("ORGANICO"), rs.getBoolean("EXCLUIDO")), rs.getDouble("QTD"));
 			}
 			
 			con.close();
 			return itensCompra;
 		} catch(Exception e)
 		{
 			System.out.println("Erro get Itens Compra: "+e.getMessage());
 		}
 		return null;
	} 
 	
 	public void atualizaProduto(Produto prod)
 	{
 		try {
			Connection con = this.connect();
			
			String sql = "UPDATE produto set NOME = ?, DESCRICAO = ?, QUANTIDADE = ?, PRECO = ?, UNIDADE = ?, INGREDIENTES = ?, ORGANICO = ?, EXCLUIDO = ? WHERE ID = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, prod.getNomeProduto());
			stmt.setString(2, prod.getDescricao());
			stmt.setDouble(3, prod.getQuantidade());
			stmt.setDouble(4, prod.getPrecoProduto());
			stmt.setInt(5, prod.getUnidade());
			stmt.setString(6, prod.getIngredientes());
			stmt.setBoolean(7, prod.isOrganico());
			stmt.setBoolean(8, prod.isExcluido());
			stmt.setInt(9, prod.getIdProduto());
			stmt.execute();
			con.close();
		} catch(SQLException e){
            System.out.println("Erro Atualiza Produto "+e.getMessage());
        }
 	}
 	
 	public void atualizaProdutor(Produtor produtor)
 	{
 		try {
			Connection con = this.connect();
			
			String sql = "UPDATE produtor set NOME = ?, EMAIL = ?, SENHA = ?, CNPJ = ?, TELEFONE = ?, CCIR = ?, TIPO_PROD = ?, DESCRICAO = ? WHERE ID = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, produtor.getNome());
			stmt.setString(2, produtor.getEmail());
			stmt.setString(3, produtor.getSenha());
			stmt.setString(4, produtor.getCpf());
			stmt.setString(5, produtor.getTelefone());
			stmt.setString(6, produtor.getCcir());
			stmt.setInt(7, produtor.getTipoProd());
			stmt.setString(8, produtor.getDescricao());
			stmt.setInt(9, produtor.getId());
			stmt.execute();
			con.close();
			
			// Atualizar agora o endere?o do produtor
			atualizaEndereco(produtor.getEndereco());
			
		} catch(SQLException e){
            System.out.println("Erro Atualiza Produtor "+e.getMessage());
        }
 	}
 	
 	public void atualizaConsumidor(Consumidor consum)
 	{
 		try {
			Connection con = this.connect();
			
			String sql = "UPDATE consumidor set NOME = ?, EMAIL = ?, SENHA = ?, CPF = ?, TELEFONE = ? WHERE ID = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, consum.getNome());
			stmt.setString(2, consum.getEmail());
			stmt.setString(3, consum.getSenha());
			stmt.setString(4, consum.getCpf());
			stmt.setString(5, consum.getTelefone());
			stmt.setInt(6, consum.getId());
			stmt.execute();
			con.close();
			
			// Atualizar agora o endere?o da pessoa
			atualizaEndereco(consum.getEndereco());
			
		} catch(SQLException e){
            System.out.println("Erro Atualiza Consumidor "+e.getMessage());
        }
 	}
 	
 	public void atualizaEndereco(Endereco end)
 	{
 		try {
			Connection con = this.connect();
			
			String sql = "UPDATE endereco set RUA = ?, NUMERO = ?, COMPLEMENTO = ?, BAIRRO = ?, CEP = ?, CIDADE = ?, ESTADO = ? WHERE ID = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, end.getEndRua());
			System.out.println("Rua: "+end.getEndRua());
			stmt.setString(2, end.getEndNum());
			stmt.setString(3, end.getEndComplemento());
			stmt.setString(4, end.getEndBairro());
			stmt.setString(5, end.getEndCEP());
			stmt.setString(6, end.getEndCidade());
			stmt.setString(7, end.getEndEstado());
			stmt.setInt(8, end.getIdEndereco());
			System.out.println("ID: "+end.getIdEndereco());
			stmt.execute();
			con.close();
		} catch(SQLException e){
            System.out.println("Erro Atualiza Endereco "+e.getMessage());
        }
 	}
 	
 	public Vector<Produtor> getAllProdutores()
 	{
 		try {
 			Connection con = this.connect();
 			String sql = "SELECT * FROM produtor;";
 			PreparedStatement stmt = con.prepareStatement(sql);
 			ResultSet rs = stmt.executeQuery();
 			
 			Vector<Produtor> produtores = new Vector<Produtor>();
 			while(rs.next())
 			{
 				Endereco end = null;
				String sql2 = "SELECT * FROM endereco WHERE ID = ?;";
				PreparedStatement stmt2 = con.prepareStatement(sql2);
				
				stmt2.setInt(1, rs.getInt("ID_ENDERECO"));
				ResultSet rs2 = stmt2.executeQuery();
				if(rs2.next())
				{
					end = new Endereco(rs2.getString("RUA"), rs2.getString("NUMERO"), rs2.getString("COMPLEMENTO"), rs2.getString("BAIRRO"), rs2.getString("CEP"), rs2.getString("CIDADE"), rs2.getString("ESTADO"));
					end.setIdEndereco(rs.getInt("ID_ENDERECO"));
				}
 				if(end == null) {
 					System.out.println("Erro end nulo");
 				}
 				Produtor produtor = new Produtor(rs.getInt("ID"), rs.getString("NOME"), rs.getString("CNPJ"), rs.getString("TELEFONE"), end, rs.getString("EMAIL"), rs.getString("SENHA"), rs.getString("CCIR"), rs.getInt("TIPO_PROD"), rs.getString("DESCRICAO"));
 				produtores.add(produtor);
 			}
 			con.close();
 			return produtores;
 		} catch(Exception e)
 		{
 			System.out.println("Erro get all produtores : "+e.getMessage());
 		}
 		return null;
 	}
}