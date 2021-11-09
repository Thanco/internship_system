package Model;

public class RangeSalary implements SalaryType{
    private int lowRange;
    private int highRange;


    public RangeSalary(int lowRange, int highRange){
        this.lowRange = lowRange;
        this.highRange = highRange;
    }

    public String getSalary(){
        return lowRange + " - " + highRange;
    }
}
