
import io.github.siscultural.repository.ConnectionProvider;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Victor Hugo <victor.hugo.origins@gmail.com>
 */
public class teste {
    public static void main(String[] args) throws SQLException {
        ConnectionProvider c = ConnectionProvider.getInstance();
        c.getConnection();
    }
}
