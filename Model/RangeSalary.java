package Model;

public class RangeSalary implements SalaryType{
    private int lowRange;
    private int highRange;


    public RangeSalary(int lowRange, int highRange){
        return;
    }
    
    @Override
    public String getSalary() {
        return "";
    }
    
    public int getLowRange() {
        return this.lowRange;
    }

    public void setLowRange(int lowRange) {
        this.lowRange = lowRange;
    }

    public int getHighRange() {
        return this.highRange;
    }

    public void setHighRange(int highRange) {
        this.highRange = highRange;
    }

}
