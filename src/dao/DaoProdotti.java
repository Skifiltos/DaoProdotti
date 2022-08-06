package dao;

import model.Prodotto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DaoProdotti implements IDao{

    @Override
    public List<Prodotto> getProdotti() {

        List<Prodotto> prodotti = new ArrayList<Prodotto>();

        String sql = "select * from prodotti";

        try (
                Connection conn = DriverManager.getConnection(DBADDRESS, USER, PWD);

                PreparedStatement pr = conn.prepareStatement(sql);
             ){

                ResultSet rs = pr.executeQuery();

                    while (rs.next()){

                    prodotti.add(new Prodotto(
                                                    rs.getInt("id"),
                                                    rs.getString("marca"),
                                                    rs.getDouble("prezzo"),
                                                    rs.getString("tipo")
                                              )
                                );
                }
                    rs.close();
        }
        catch (Exception e){

            e.printStackTrace();
        }

        return prodotti;
    }
}
