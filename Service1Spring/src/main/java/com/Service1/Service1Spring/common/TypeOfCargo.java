package com.Service1.Service1Spring.common;

public enum TypeOfCargo {
    BULKER,
    CONTAINER,
    TANKER;

    public double getProductivityOfCrane(){
        return switch (this) {
            case BULKER -> 6;
            case TANKER -> 5;
            case CONTAINER -> 4;
        };
    }

    @Override
    public String toString(){
        return switch (this) {
            case BULKER -> "Bulker";
            case TANKER -> "Tanker";
            case CONTAINER -> "Container";
        };
    }
}
