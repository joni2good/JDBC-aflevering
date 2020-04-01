import java.io.IOException;
import java.sql.*;
import java.util.*;

public class JDBCExample {
    public static void main(String[] args) {
        try {
            //create connection
            DatabaseConnectionManager connectionManager = new DatabaseConnectionManager();
            Connection connectionToMySQL = connectionManager.getDatabaseConnection();

            //create statement
            Statement myStatement = connectionToMySQL.createStatement();
            Statement myStatement2 = connectionToMySQL.createStatement();

            //create query
            String getAllDept = "select * from dept";
            String getAllEmp = "select * from emp order by hiredate asc";

            //excecute query
            ResultSet allDept = myStatement.executeQuery(getAllDept);
            ResultSet allEmp = myStatement2.executeQuery(getAllEmp);
            ResultSetMetaData meta = allDept.getMetaData();


            while (allDept.next()) {
                for (int i = 1; i < meta.getColumnCount() + 1; i++) {
                    System.out.println(allDept.getString(meta.getColumnName(i)));
                }
            }


            int tempEmpNo;
            String tempEName;
            String tempJob;
            int tempMgr;
            String tempHireDate;
            int tempSal;
            int tempComm;
            int tempDeptNo;
            TreeMap<Integer, Employee> empMap = new TreeMap<Integer, Employee>();

            while (allEmp.next()){
                tempEmpNo = allEmp.getInt(1);
                tempEName = allEmp.getString(2);
                tempJob = allEmp.getString(3);
                tempMgr = allEmp.getInt(4);
                tempHireDate = allEmp.getString(5);
                tempSal = allEmp.getInt(6);
                tempComm = allEmp.getInt(7);
                tempDeptNo = allEmp.getInt(8);

                empMap.put(tempEmpNo, new Employee(tempEName, tempJob, tempMgr, tempHireDate, tempSal, tempComm, tempDeptNo));
            }

            for (Map.Entry entry :empMap.entrySet()) {
                System.out.println(entry.getKey() + " - " + entry.getValue());
            }


            int tempPriDeptNo;
            String tempDName;
            String tempLoc;
            HashSet<Departments> deptSet = new HashSet<Departments>();

            allDept.first();
            while (allDept.next()){
                tempPriDeptNo = allDept.getInt(1);
                tempDName = allDept.getString(2);
                tempLoc = allDept.getString(3);

                deptSet.add(new Departments(tempPriDeptNo, tempDName, tempLoc));
            }
            System.out.println(deptSet.toString());

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
