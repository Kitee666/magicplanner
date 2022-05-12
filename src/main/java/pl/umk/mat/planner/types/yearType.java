package pl.umk.mat.planner.types;

public enum yearType {
    ROK_I(1),
    ROK_II(2),
    ROK_III(3),
    ROK_IV(4);

    private int number;
    yearType(int i) {
        this.number = i;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
