public class FixedSalary implements SalaryType{
    public int salary;

    public FixedSalary(int salary){
        this.salary = salary;
    }

    public String getSalary(){
        return "" + salary;
    }
}
