import java.sql.*;
import java.util.ArrayList;

public class Funcionario {
    private String nome;
    private String codigo;
    private int id_departamento;

    public Funcionario(String nome, String codigo, int id_departamento){
        this.nome = nome;
        this.codigo = codigo;
        this.id_departamento = id_departamento;
    }

    public void adicionarFuncionario(){
        String sql = "INSERT INTO funcionario(nome, codigo, id_departamento) VALUES(?, ?, ? )";
            try(Connection conn = ConnectDb.ConnectionDb()){
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, nome);
                pstmt.setString(2, codigo);
                pstmt.setInt(3, id_departamento);
                pstmt.executeUpdate();
            }catch(SQLException e){
                e.printStackTrace();
            }
    }
    public void removerFuncionario(){
        String sql = "DELETE FROM funcionario WHERE codigo = ?";
        try(Connection conn = ConnectDb.ConnectionDb()){
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, codigo);
            pstmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void atualizarFuncionario(String novoNome){
        String sql = ("UPDATE funcionario set nome = ? where codigo = ?");
        try(Connection conn = ConnectDb.ConnectionDb()){
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, novoNome);
            pstmt.setString(2, codigo);
            pstmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void pesquisarFuncionario(String codigo){
        String sql = "SELECT nome, id_departamento FROM funcionario WHERE codigo = ?";
        try(Connection conn = ConnectDb.ConnectionDb()){
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, codigo);
            try(ResultSet rs = pstmt.executeQuery()){
                while(rs.next()){
                    System.out.println(rs.getString("nome"));
                    System.out.println(rs.getInt("id_departamento"));
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void adicionarFuncionarios(ArrayList<Funcionario> funcionarios) {
        String sql = "INSERT INTO funcionario (nome, codigo, id_departamento) VALUES (?, ?, ?)";
        try (Connection conn = ConnectDb.ConnectionDb()){;
             PreparedStatement pstmt = conn.prepareStatement(sql);
            for (Funcionario funcionario : funcionarios) {
                pstmt.setString(1, funcionario.nome);
                pstmt.setString(2, funcionario.codigo);
                pstmt.setInt(3, funcionario.id_departamento);
                pstmt.executeUpdate();
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void buscarPorNome(String substring){
        String sql = "SELECT nome, codigo, id_departamento FROM funcionario WHERE nome LIKE ?";
        try (Connection conn = ConnectDb.ConnectionDb()){;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + substring + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println(rs.getString("nome"));
                    System.out.println(rs.getString("codigo"));
                    System.out.println(rs.getString("id_departamento"));
                }
                }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
