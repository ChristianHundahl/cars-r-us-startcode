package kea.sem3.jwtdemo.entity;

public enum CarBrand{

    VOLVO("Volvo"),
    TOYOTA("Toyota"),
    WW("Volkswagen"),
    FORD("Ford"),
    SUZUKI("Suzuki");

    public final String printName;

    private CarBrand(String printName){
        this.printName = printName;
    }
}