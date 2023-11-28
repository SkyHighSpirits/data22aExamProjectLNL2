package com.example.data22aexamprojectlnl2.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
@Entity
public class Operation
{   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Operation_Id;
    private String Operation_Name;
    private String Operation_Desription;
    private BigDecimal price;

    @OneToOne(mappedBy = "operation")
    private Poster poster;

    public Operation(){

    }

    public Operation(int Operation_Id, String Operation_Name, String Operation_Desription, BigDecimal price)
    {
        this.Operation_Id = Operation_Id;
        this.Operation_Name = Operation_Name;
        this.Operation_Desription = Operation_Desription;
        this.price = price;
    }

    public int getOperation_Id()
    {
        return Operation_Id;
    }

    public void setOperation_Id(int Operation_Id)
    {
        this.Operation_Id = Operation_Id;
    }

    public String getOperation_Name()
    {
        return Operation_Name;
    }

    public void setOperation_Name(String Operation_Name)
    {
        this.Operation_Name = Operation_Name;
    }

    public String getOperation_Desription()
    {
        return Operation_Desription;
    }

    public void setOperation_Desription(String Operation_Desription)
    {
        this.Operation_Desription = Operation_Desription;
    }

    public BigDecimal getPrice()
    {
        return price;
    }

    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }
}
