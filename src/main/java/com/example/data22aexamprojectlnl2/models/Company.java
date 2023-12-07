package com.example.data22aexamprojectlnl2.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class Company
{
    @Id // ikke nødvendigt at autoincremente, der findes kun en.
    private int id;
    private String company_Title;

    @Column(length = 2000)
    private String company_Description;
    private String CVR;
    private String telephone;

    public Company()
    {

    }

    public Company(int id, String company_Title, String company_Description, String CVR, String telephone)
    {
        this.id = id;
        this.company_Title = company_Title;
        this.company_Description = company_Description;
        this.CVR = CVR;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getCompany_Title()
    {
        return company_Title;
    }

    public void setCompany_Title(String company_Title)
    {
        this.company_Title = company_Title;
    }

    public String getCompany_Description()
    {
        return company_Description;
    }

    public void setCompany_Description(String company_Description)
    {
        this.company_Description = company_Description;
    }

    public String getCVR()
    {
        return CVR;
    }

    public void setCVR(String CVR)
    {
        this.CVR = CVR;
    }


    public String getTelephone()
    {
        return telephone;
    }

    public void setTelephone(String telephone)
    {
        this.telephone = telephone;
    }

}
