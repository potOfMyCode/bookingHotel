package model.entity;

import java.util.Date;
import java.sql.Timestamp;

public class ReservedRoom {
    private int id;
    private int userId;
    private int roomId;
    private int billId;
    private int applicationId;
    private Date date_from;
    private Date date_before;
    private Timestamp dateOfBooking;

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public Date getDate_from() {
        return date_from;
    }

    public void setDate_from(Date date_from) {
        this.date_from = date_from;
    }

    public Date getDate_before() {
        return date_before;
    }

    public void setDate_before(Date date_before) {
        this.date_before = date_before;
    }

    public Timestamp getDateOfBooking() {
        return dateOfBooking;
    }

    public void setDateOfBooking(Timestamp dateOfBooking) {
        this.dateOfBooking = dateOfBooking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReservedRoom that = (ReservedRoom) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (roomId != that.roomId) return false;
        if (billId != that.billId) return false;
        if (applicationId != that.applicationId) return false;
        if (!date_from.equals(that.date_from)) return false;
        if (!date_before.equals(that.date_before)) return false;
        return dateOfBooking.equals(that.dateOfBooking);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + roomId;
        result = 31 * result + billId;
        result = 31 * result + applicationId;
        result = 31 * result + date_from.hashCode();
        result = 31 * result + date_before.hashCode();
        result = 31 * result + dateOfBooking.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ReservedRoom{" +
                "id=" + id +
                ", userId=" + userId +
                ", roomId=" + roomId +
                ", billId=" + billId +
                ", applicationId=" + applicationId +
                ", date_from=" + date_from +
                ", date_before=" + date_before +
                ", dateOfBooking=" + dateOfBooking +
                '}';
    }
}
