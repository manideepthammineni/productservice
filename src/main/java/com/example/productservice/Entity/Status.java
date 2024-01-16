package com.example.productservice.Entity;

public enum Status
{
    CLOSED(0),
    OPEN(1),
    PENDING(2);

    int value;

    Status(int value)
    {
        this.value = value;
    }

}
