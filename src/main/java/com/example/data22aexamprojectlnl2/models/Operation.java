package com.example.data22aexamprojectlnl2.models;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Operation
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String Operation_Name;
    private String Operation_Desription;

    @OneToOne(mappedBy = "operation")
    private Poster poster;

    public Operation()
    {

    }

    public Operation(int Id, String Operation_Name, String Operation_Desription)
    {
        this.Id = Id;
        this.Operation_Name = Operation_Name;
        this.Operation_Desription = Operation_Desription;
    }

    public int getOperation_Id()
    {
        return Id;
    }

    public void setOperation_Id(int Operation_Id)
    {
        this.Id = Operation_Id;
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
}
