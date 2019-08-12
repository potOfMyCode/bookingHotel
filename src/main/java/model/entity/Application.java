package model.entity;

import java.sql.Timestamp;
import java.util.Date;

public class Application {
    private int id;
    private int userId;
    private String name;
    private String surname;
    private int amountPeople;
    private int amounChildren;
    private String note;
    private String typeOfNumber;
    private Date dateFrom;
    private Date dateTo;
    private Timestamp dateOfBooking;
    private boolean status;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAmountPeople() {
        return amountPeople;
    }

    public void setAmountPeople(int amountPeople) {
        this.amountPeople = amountPeople;
    }

    public int getAmounChildren() {
        return amounChildren;
    }

    public void setAmounChildren(int amounChildren) {
        this.amounChildren = amounChildren;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTypeOfNumber() {
        return typeOfNumber;
    }

    public void setTypeOfNumber(String typeOfNumber) {
        this.typeOfNumber = typeOfNumber;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public Timestamp getDateOfBooking() {
        return dateOfBooking;
    }

    public void setDateOfBooking(Timestamp dateOfBooking) {
        this.dateOfBooking = dateOfBooking;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Application that = (Application) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (amountPeople != that.amountPeople) return false;
        if (amounChildren != that.amounChildren) return false;
        if (status != that.status) return false;
        if (!name.equals(that.name)) return false;
        if (!surname.equals(that.surname)) return false;
        if (!typeOfNumber.equals(that.typeOfNumber)) return false;
        if (!dateFrom.equals(that.dateFrom)) return false;
        if (!dateTo.equals(that.dateTo)) return false;
        return dateOfBooking.equals(that.dateOfBooking);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + name.hashCode();
        result = 31 * result + surname.hashCode();
        result = 31 * result + amountPeople;
        result = 31 * result + amounChildren;
        result = 31 * result + typeOfNumber.hashCode();
        result = 31 * result + dateFrom.hashCode();
        result = 31 * result + dateTo.hashCode();
        result = 31 * result + dateOfBooking.hashCode();
        result = 31 * result + (status ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", amountPeople=" + amountPeople +
                ", amounChildren=" + amounChildren +
                ", note='" + note + '\'' +
                ", typeOfNumber='" + typeOfNumber + '\'' +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", dateOfBooking=" + dateOfBooking +
                ", status=" + status +
                '}';
    }
}
