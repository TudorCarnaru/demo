package com.example.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by tcarnaru on 10/13/2016.
 */
@RestController
public class HelloController {

    @RequestMapping(value="/hello")
    public String hello() {
        try{
//step1 load the driver class
            Class.forName("oracle.jdbc.driver.OracleDriver");

//step2 create  the connection object
            Connection con=DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe","proiect","PROIECT");

//step3 create the statement object
            Statement stmt=con.createStatement();

//step4 execute query
            ResultSet rs=stmt.executeQuery("select * from users");
            while(rs.next())
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));

            String sql="{ ? = call intrebari_relevante(?) }";
            CallableStatement stm = con.prepareCall(sql);
            String username="alexandru.poputoaia";
            stm.setString(2,username);
            stm.registerOutParameter(1, java.sql.Types.INTEGER);

            stm.execute();
            Integer x = stm.getInt(1);
            System.out.println(x);
//step5 close the connection object
            con.close();

        }catch(Exception e){ System.out.println(e);}
        return "Hello, world";
    }}