import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Departamento {
    private String nome;

    public Departamento(String nome){
        this.nome = nome;
    }
    public void adicionarDepartamento(){
        String sql = "INSERT INTO departamento(nome) VALUES(?)";
        try(Connection conn = ConnectDb.ConnectionDb()){
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nome);
            pstmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
