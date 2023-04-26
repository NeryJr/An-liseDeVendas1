package entities;

public class Sale {
    
    private Integer month;
    private Integer year;
    private String seller;
    private Integer items;
    private Double total;

    //Constructor
    public Sale(Integer month, Integer year, String seller, Integer items, Double total) {
        this.month = month;
        this.year = year;
        this.seller = seller;
        this.items = items;
        this.total = total;
    }

    //Setters and Getters
    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public Integer getItems() {
        return items;
    }

    public void setItems(Integer items) {
        this.items = items;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    //Method
    public Double averagePrice(){
        return getTotal() / getItems();
    }

    //toString
    @Override
    public String toString() {
        return month + "/" + year + ", " + seller + ", " + items + ", " + total + ", pm = " + String.format("%.2f", averagePrice());
    }
}
