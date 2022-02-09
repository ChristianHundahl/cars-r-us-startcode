package kea.sem3.jwtdemo.entity;

public enum CarBrand{
    //Enum = can only match the exact Strings provided via enum.(brand)
    VOLVO("Volvo"),
    TOYOTA("Toyota"),
    VOLKSWAGEN("Volkswagen"),
    FORD("Ford"),
    SUZUKI("Suzuki");

    public final String printName;

    private CarBrand(String printName){
        this.printName = printName;
    }
}