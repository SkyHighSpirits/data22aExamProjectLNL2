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
    @Lob
    @Column(length = 20971520)
    private Byte[] byte_img;

    public Image(){

    }

    public Image(int id, Poster poster, Byte[] byte_img)
    {
        this.id = id;
        this.poster = poster;
        this.byte_img = byte_img;
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


}
