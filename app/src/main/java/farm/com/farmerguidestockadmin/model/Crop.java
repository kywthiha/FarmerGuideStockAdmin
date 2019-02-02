package farm.com.farmerguidestockadmin.model;

public class Crop {
    String c_Id;
    String c_name;
    String c_price;
    String count;

    public Crop(){}

    public Crop(String c_Id, String c_name, String c_price, String count) {
        this.c_Id = c_Id;
        this.c_name = c_name;
        this.c_price = c_price;
        this.count = count;
    }

    public String getCount() {
        return this.count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getC_Id() {
        return this.c_Id;
    }

    public void setC_Id(String c_Id) {
        this.c_Id = c_Id;
    }

    public String getC_name() {
        return this.c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getC_price() {
        return this.c_price;
    }

    public void setC_price(String c_price) {
        this.c_price = c_price;
    }
}
