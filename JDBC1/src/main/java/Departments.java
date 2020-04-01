public class Departments {
    private int deptNo;
    private String dName;
    private String loc;

    public Departments(int deptNo, String dName, String loc) {
        this.deptNo = deptNo;
        this.dName = dName;
        this.loc = loc;
    }

    @Override
    public String toString() {
        return "Departments{" +
                "deptNo=" + deptNo +
                ", dName='" + dName + '\'' +
                ", loc='" + loc + '\'' +
                '}';
    }
}
