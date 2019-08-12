package model.entity;

import java.util.Date;

public class Bill {
    private int id;
    private int total;
    private boolean status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
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

        Bill bill = (Bill) o;

        if (total != bill.total) return false;
        return status == bill.status;

    }

    @Override
    public int hashCode() {
        int result = total;
        Date date = new Date();
        result = 31*result + date.hashCode();
        result = 31 * result + (status ? 1 : 0);
        return Math.abs(result);
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", total=" + total +
                ", status=" + status +
                '}';
    }
}
