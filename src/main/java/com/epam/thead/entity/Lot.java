package com.epam.thead.entity;

public class Lot {
    private int id;
    private int startPrice;

    public Lot(int id, int startPrice){
        this.startPrice = startPrice;
        this.id = id;
    }

    public int getStartPrice(){
        return startPrice;
    }

    public int getId(){
        return id;
    }

    public void setStartPrice(int startPrice){
        this.startPrice = startPrice;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) {
            return true;
        }
        if (!(o instanceof Lot)) {
            return false;
        }

        Lot lot = (Lot) o;

        if (id != lot.id) {
            return false;
        }
        return startPrice == lot.startPrice;
    }

    @Override
    public int hashCode(){
        int result = id;
        result = 31 * result + startPrice;
        return result;
    }

    @Override
    public String toString(){
        return "Lot{" +
                "id=" + id +
                ", lastPrice=" + startPrice +
                '}';
    }
}
