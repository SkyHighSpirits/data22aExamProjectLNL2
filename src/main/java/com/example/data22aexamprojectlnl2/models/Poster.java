package com.example.data22aexamprojectlnl2.models;

import jakarta.persistence.*;

@Entity
public class Poster
{   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name = "service_id", unique = true) // sætter foreign key, fortæller hvad den hedder i databasen, og siger at den SKAL være unik
    private Operations operations;
    private String poster_Title;
    private String poster_Description;

    public Poster(){

    }

    public Poster(int id, Operations operations, String poster_Title, String poster_Description)
    {
        this.id = id;
        this.operations = operations;
        this.poster_Title = poster_Title;
        this.poster_Description = poster_Description;

    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Operations getService()
    {
        return operations;
    }

    public void setService(Operations operations)
    {
        this.operations = operations;
    }

    public String getPoster_Title()
    {
        return poster_Title;
    }

    public void setPoster_Title(String poster_Title)
    {
        this.poster_Title = poster_Title;
    }

    public String getPoster_Description()
    {
        return poster_Description;
    }

    public void setPoster_Description(String poster_Description)
    {
        this.poster_Description = poster_Description;
    }
}
