public class Employee {
    private int empNo;
    private String eName;
    private String job;
    private int mgr;
    private String hireDate;
    private int sal;
    private int comm;
    private int deptNo;

    public Employee(String eName, String job, int mgr, String hireDate, int sal, int comm, int deptNo) {
        this.eName = eName;
        this.job = job;
        this.mgr = mgr;
        this.hireDate = hireDate;
        this.sal = sal;
        this.comm = comm;
        this.deptNo = deptNo;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "eName='" + eName + '\'' +
                ", job='" + job + '\'' +
                ", mgr=" + mgr +
                ", hireDate='" + hireDate + '\'' +
                ", sal=" + sal +
                ", comm=" + comm +
                ", deptNo=" + deptNo +
                '}';
    }
}
