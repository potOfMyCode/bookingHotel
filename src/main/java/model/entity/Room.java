package model.entity;

public class Room {
    private int id;
    private  int hotelNumberId;
    private String shortType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotelNumberId() {
        return hotelNumberId;
    }

    public void setHotelNumberId(int hotelNumberId) {
        this.hotelNumberId = hotelNumberId;
    }

    public String getShortType() {
        return shortType;
    }

    public void setShortType(String shortType) {
        this.shortType = shortType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        if (id != room.id) return false;
        if (hotelNumberId != room.hotelNumberId) return false;
        return shortType.equals(room.shortType);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + hotelNumberId;
        result = 31 * result + shortType.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", hotelNumberId=" + hotelNumberId +
                ", shortType='" + shortType + '\'' +
                '}';
    }
}
