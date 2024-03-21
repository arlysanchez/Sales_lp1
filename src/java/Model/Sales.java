/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author JAST
 */
public class Sales {

    private int idsales;
    private int idcustomer;
    private int iduser;
    private int idproduct;
    private String description_product;
    private String serialNumber;
    private String sale_date;
    private Double price;
    private Integer cantidad;
    private Double subTotal;
    private String Total;
    private Double priceDetail;
    private String status;

    public Sales() {
    }

    public Sales(int idsales, int idcustomer, int iduser, int idproduct, String description_product, String serialNumber, String sale_date, Double price, Integer cantidad, Double subTotal, String Total, Double priceDetail, String status) {
        this.idsales = idsales;
        this.idcustomer = idcustomer;
        this.iduser = iduser;
        this.idproduct = idproduct;
        this.description_product = description_product;
        this.serialNumber = serialNumber;
        this.sale_date = sale_date;
        this.price = price;
        this.cantidad = cantidad;
        this.subTotal = subTotal;
        this.Total = Total;
        this.priceDetail = priceDetail;
        this.status = status;
    }

    

    

    public int getIdsales() {
        return idsales;
    }

    public void setIdsales(int idsales) {
        this.idsales = idsales;
    }

    public int getIdcustomer() {
        return idcustomer;
    }

    public void setIdcustomer(int idcustomer) {
        this.idcustomer = idcustomer;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(int idproduct) {
        this.idproduct = idproduct;
    }

    public String getDescription_product() {
        return description_product;
    }

    public void setDescription_product(String description_product) {
        this.description_product = description_product;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getSale_date() {
        return sale_date;
    }

    public void setSale_date(String sale_date) {
        this.sale_date = sale_date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String Total) {
        this.Total = Total;
    }

    public Double getPriceDetail() {
        return priceDetail;
    }

    public void setPriceDetail(Double priceDetail) {
        this.priceDetail = priceDetail;
    }

   

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
