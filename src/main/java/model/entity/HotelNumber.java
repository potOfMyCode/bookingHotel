package model.entity;

import java.util.ArrayList;
import java.util.List;

public class HotelNumber {
    private int id;
    private String shortType;
    private int amount;
    private int price;
    private String type;
    private String typeUkr;
    private List<Room> rooms = new ArrayList<>();

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShortType() {
        return shortType;
    }

    public void setShortType(String shortType) {
        this.shortType = shortType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeUkr() {
        return typeUkr;
    }

    public void setTypeUkr(String typeUkr) {
        this.typeUkr = typeUkr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HotelNumber that = (HotelNumber) o;

        if (id != that.id) return false;
        if (amount != that.amount) return false;
        if (price != that.price) return false;
        if (!shortType.equals(that.shortType)) return false;
        if (!type.equals(that.type)) return false;
        return typeUkr.equals(that.typeUkr);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + shortType.hashCode();
        result = 31 * result + amount;
        result = 31 * result + price;
        result = 31 * result + type.hashCode();
        result = 31 * result + typeUkr.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "HotelNumber{" +
                "id=" + id +
                ", shortType='" + shortType + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                ", type='" + type + '\'' +
                ", typeUkr='" + typeUkr + '\'' +
                ", rooms=" + rooms +
                '}';
    }
}
