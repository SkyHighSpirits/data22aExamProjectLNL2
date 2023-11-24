package com.example.data22aexamprojectlnl2.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
@Entity
public class Operation
{   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int service_Id;
    private String service_Name;
    private String service_Desription;
    private BigDecimal price;

    @OneToOne(mappedBy = "operation")
    private Poster poster;

    public Operation(){

    }

    public Operation(int service_Id, String service_Name, String service_Desription, BigDecimal price)
    {
        this.service_Id = service_Id;
        this.service_Name = service_Name;
        this.service_Desription = service_Desription;
        this.price = price;
    }

    public int getService_Id()
    {
        return service_Id;
    }

    public void setService_Id(int service_Id)
    {
        this.service_Id = service_Id;
    }

    public String getService_Name()
    {
        return service_Name;
    }

    public void setService_Name(String service_Name)
    {
        this.service_Name = service_Name;
    }

    public String getService_Desription()
    {
        return service_Desription;
    }

    public void setService_Desription(String service_Desription)
    {
        this.service_Desription = service_Desription;
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
