import java.io.IOException;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class DepartmentsDAO {
    private Connection conn;

    public DepartmentsDAO() throws SQLException, IOException {
        try {
            this.conn = new DatabaseConnectionManager().getDatabaseConnection();
        } catch (SQLException e) {
            System.out.println("Couldn't get connection");
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    public Set getAllDept(){
        HashSet<DepartmentsDTO> deptSet = new HashSet();

        try {
            Statement stmt = conn.createStatement();
            ResultSet allDept = stmt.executeQuery("select * from dept order by deptno");

            while (allDept.next()) {
                deptSet.add(new DepartmentsDTO(allDept.getInt(1), allDept.getString(2), allDept.getString(3)));
            }
        }catch (SQLException e){
            System.out.println("A problem occurred");
            System.out.println(e.getSQLState());
            System.out.println(e.getErrorCode());
            System.out.println(e.getMessage());
        }
        return deptSet;
    }

    public DepartmentsDTO getSingleDept(int id){
        DepartmentsDTO singleDeptObj = new DepartmentsDTO();

        try {
            PreparedStatement pStmt = conn.prepareStatement("select * from dept where deptno = ?");
            pStmt.setInt(1, id);
            ResultSet singleDept = pStmt.executeQuery();
            while (singleDept.next()) {
                singleDeptObj.setDeptNo(singleDept.getInt("deptno"));
                singleDeptObj.setDName(singleDept.getString("dname"));
                singleDeptObj.setLoc(singleDept.getString("loc"));
            }
        }catch (SQLException e){
            System.out.println("Connection failed");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        return singleDeptObj;
    }
}
