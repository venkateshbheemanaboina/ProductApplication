package com.sathya.product;

import java.sql.Date;
import java.util.Base64;

public class Product {
    private String proId;
    private String proName;
    private double proCost;
    private String proBrand;
    private Date proMfgDate;
    private Date proExpDate;
    private String proMadeIn;
    private byte[] proImage;
    private String base64Image;

    // Constructors
    public Product() {
        // Default constructor
    }

    public Product(String proId, String proName, Double proCost, String proBrand, Date proMfgDate, Date proExpDate, String proMadeIn, byte[] proImage) {
        this.proId = proId;
        this.proName = proName;
        this.proCost = proCost;
        this.proBrand = proBrand;
        this.proMfgDate = proMfgDate;
        this.proExpDate = proExpDate;
        this.proMadeIn = proMadeIn;
        this.proImage = proImage;
        this.base64Image = Base64.getEncoder().encodeToString(proImage);
    }

    // Getters and Setters
    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public Double getProCost() {
        return proCost;
    }

    public void setProCost(Double proCost) {
        this.proCost = proCost;
    }

    public String getProBrand() {
        return proBrand;
    }

    public void setProBrand(String proBrand) {
        this.proBrand = proBrand;
    }

    public Date getProMfgDate() {
        return proMfgDate;
    }

    public void setProMfgDate(Date proMfgDate) {
        this.proMfgDate = proMfgDate;
    }

    public Date getProExpDate() {
        return proExpDate;
    }

    public void setProExpDate(Date proExpDate) {
        this.proExpDate = proExpDate;
    }

    public String getProMadeIn() {
        return proMadeIn;
    }

    public void setProMadeIn(String proMadeIn) {
        this.proMadeIn = proMadeIn;
    }

    public byte[] getProImage() {
        return proImage;
    }

    public void setProImage(byte[] proImage) {
        this.proImage = proImage;
        this.base64Image = Base64.getEncoder().encodeToString(proImage);
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    // toString method for debugging or logging
    @Override
    public String toString() {
        return "Product{" +
                "proId='" + proId + '\'' +
                ", proName='" + proName + '\'' +
                ", proCost='" + proCost + '\'' +
                ", proBrand='" + proBrand + '\'' +
                ", proMfgDate='" + proMfgDate + '\'' +
                ", proExpDate='" + proExpDate + '\'' +
                ", proMadeIn='" + proMadeIn + '\'' +
                ", base64Image='" + base64Image + '\'' +
                '}';
    }
}

