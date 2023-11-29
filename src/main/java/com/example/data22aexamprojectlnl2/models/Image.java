package com.example.data22aexamprojectlnl2.models;

import jakarta.persistence.*;

@Entity
public class Image
{   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "poster_id")  // i tvivl om der skal oprettes en relation i Poster classen til det her da der ikke skal anvendes kald den anden vej
    private Poster poster;
    @Lob
    @Column(length = 20971520)
    private byte[] byte_img;

    public Image(){

    }

    public Image(byte[] byte_img){
        this.byte_img = byte_img;
    }

    public Image(int id, Poster poster, Byte[] Byte_img)
    {
        this.id = id;
        this.poster = poster;
        this.byte_img = byte_img;
    }

    public byte[] getByte_img()
    {
        return byte_img;
    }

    public void setByte_img(byte[] byte_img)
    {
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


    public void setbyte_img(byte[] bytes)
    {
    }
}
