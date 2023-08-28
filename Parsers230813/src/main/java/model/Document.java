package model;

public class Document {
    private int number;
    private int serial;

    public Document() {}

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    @Override
    public String toString() {
        return "Document{" +
                "number=" + number +
                ", serial=" + serial +
                '}';
    }
}
