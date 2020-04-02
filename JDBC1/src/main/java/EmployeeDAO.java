import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class EmployeeDAO {
    private Connection conn;

    public EmployeeDAO() throws SQLException, IOException {
        this.conn = new DatabaseConnectionManager().getDatabaseConnection();
    }


    public LinkedHashMap<Integer, EmployeeDTO> getAllEmployees(){
        LinkedHashMap<Integer, EmployeeDTO> employeeDTOMap = new LinkedHashMap<Integer, EmployeeDTO>();

        try{
            Statement stmt = conn.createStatement();
            ResultSet allEmp = stmt.executeQuery("select * from emp order by hiredate");

            while (allEmp.next()){
                employeeDTOMap.put(allEmp.getInt(1), new EmployeeDTO(allEmp.getString(2), allEmp.getString(3), allEmp.getInt(4), allEmp.getString(5), allEmp.getInt(6), allEmp.getInt(7), allEmp.getInt(8)));
            }

        }catch (SQLException e){
            System.out.println("A problem occurred");
            System.out.println(e.getSQLState());
            System.out.println(e.getErrorCode());
            System.out.println(e.getMessage());
        }
        return employeeDTOMap;
    }

    public EmployeeDTO getSingleEmp(int id){
        EmployeeDTO singleEmpObj = new EmployeeDTO();

        try {
            PreparedStatement pStmt = conn.prepareStatement("select * from emp where empno = ?");
            pStmt.setInt(1, id);
            ResultSet singleEmp = pStmt.executeQuery();
            while (singleEmp.next()) {
                singleEmpObj.setDeptNo(singleEmp.getInt("empno"));
                singleEmpObj.setEName(singleEmp.getString("ename"));
                singleEmpObj.setJob(singleEmp.getString("job"));
                singleEmpObj.setMgr(singleEmp.getInt("mgr"));
                singleEmpObj.setHireDate(singleEmp.getString("hiredate"));
                singleEmpObj.setSal(singleEmp.getInt("sal"));
                singleEmpObj.setComm(singleEmp.getInt("comm"));
                singleEmpObj.setDeptNo(singleEmp.getInt("deptno"));
            }
        }catch (SQLException e){
            System.out.println("Connection failed");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        return singleEmpObj;
    }

    public ArrayList<EmployeeDTO> getAllEmployeeList(){
        ArrayList<EmployeeDTO> empList = new ArrayList<EmployeeDTO>();

        try{
            Statement stmt = conn.createStatement();
            ResultSet allEmp = stmt.executeQuery("select * from emp order by hiredate");

            while (allEmp.next()){
                empList.add(new EmployeeDTO(allEmp.getInt(1), allEmp.getString(2), allEmp.getString(3), allEmp.getInt(4), allEmp.getString(5), allEmp.getInt(6), allEmp.getInt(7), allEmp.getInt(8)));
            }

        }catch (SQLException e){
            System.out.println("A problem occurred");
            System.out.println(e.getSQLState());
            System.out.println(e.getErrorCode());
            System.out.println(e.getMessage());
        }
        return empList;
    }
}
