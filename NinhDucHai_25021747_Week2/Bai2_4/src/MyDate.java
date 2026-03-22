public class MyDate {
    private int day;
    private int month;
    private int year;

    public MyDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public MyDate(MyDate other){
        this.day = other.day;
        this.month = other.month;
        this.year = other.year;
    }

    public int getDay(){
        return day;
    }
    public int getMonth(){
        return month;
    }
    public int getYear(){
        return year;
    }

    public void setDay(int day){this.day = day;}
    public void setMonth(int month){this.month = month;}
    public void setYear(int year){this.year = year;}

    public String toString() {
        return day + "/" + month + "/" + year;
    }
}
