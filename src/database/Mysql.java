package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Mysql {
        public static Connection conexao(){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                return DriverManager.getConnection("jdbc:mysql://localhost/db_biblioteca_senac?" + "user=root&password=piterodatilo123");
                
            } 
            catch (Exception ex) {
                throw new RuntimeException("Erro ao conectar no banco de dados");
            }
        }
}