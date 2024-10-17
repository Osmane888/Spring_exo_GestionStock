package org.example.spring_demo_stockmanagement.dl.entities.enums;


public enum VAT {
    SIX(6),
    TWELVE(12),
    TWENTY_ONE(21);

    public final int value;

    VAT(int value){
        this.value = value;
    }
}
