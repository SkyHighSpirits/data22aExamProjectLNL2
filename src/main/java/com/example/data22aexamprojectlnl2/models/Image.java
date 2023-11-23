package com.example.data22aexamprojectlnl2.models;

import jakarta.persistence.*;

@Entity
public class Image
{   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name = "poster_id")  // i tvivl om der skal oprettes en relation i Poster classen til det her da der ikke skal anvendes kald den anden vej
    private Poster poster;
    private String img;


    public Image(){

    }

    public Image(int id, Poster poster, String img)
    {
        this.id = id;
        this.poster = poster;
        this.img = img;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Poster getPoster()
    {
        return poster;
    }

    public void setPoster(Poster poster)
    {
        this.poster = poster;
    }

    public String getImg()
    {
        return img;
    }

    public void setImg(String img)
    {
        this.img = img;
    }
}
