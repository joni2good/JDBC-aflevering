import java.io.IOException;
import java.sql.*;
import java.util.*;

public class JDBCExample {
    public static void main(String[] args) {
        try {
            EmployeeDAO empDAO = new EmployeeDAO();
            DepartmentsDAO deptDAO = new DepartmentsDAO();

            //create connection
            DatabaseConnectionManager connectionManager = new DatabaseConnectionManager();
            Connection connectionToMySQL = connectionManager.getDatabaseConnection();

            //create statement
            Statement myStatement = connectionToMySQL.createStatement();
            Statement myStatement2 = connectionToMySQL.createStatement();

            //create query
            String getAllDept = "select * from dept";

            //excecute query
            ResultSet allDept = myStatement.executeQuery(getAllDept);
            ResultSetMetaData meta = allDept.getMetaData();


            while (allDept.next()) {
                for (int i = 1; i < meta.getColumnCount() + 1; i++) {
                    System.out.println(allDept.getString(meta.getColumnName(i)));
                }
            }

            for (Map.Entry entry : empDAO.getAllEmployees().entrySet()) {
                System.out.println(entry.getKey() + " - " + entry.getValue());
            }


            System.out.println(deptDAO.getAllDept().toString());
            System.out.println(deptDAO.getSingleDept(10));
            System.out.println(empDAO.getSingleEmp(7876));

            for (EmployeeDTO employee : empDAO.getAllEmployeeList()){
                System.out.println(employee.toString());
            }

        } catch (SQLException e) {
            System.out.println("A problem occurred");
            System.out.println(e.getSQLState());
            System.out.println(e.getErrorCode());
            System.out.println(e.getMessage());
        }catch (IOException e){
            System.out.println("FNF, A problem occurred");
            System.out.println(e.getMessage());
        }
    }
}
