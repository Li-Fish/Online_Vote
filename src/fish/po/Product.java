package fish.po;

import java.util.Date;

public class Product {
    private String productID;
    private String productName;
    private Integer safeStock;
    private Date lastPurchaseDate;
    private Date lastDeliveryDateDate;
    private Integer quantity;
    private Float suggestBuyPrice;
    private Float suggestSalePrice;

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getSafeStock() {
        return safeStock;
    }

    public void setSafeStock(Integer safeStock) {
        this.safeStock = safeStock;
    }

    public void setSafeStock(String safeStock) {
        try {
            this.safeStock = Integer.parseInt(safeStock);
        } catch (Exception e) {
            this.safeStock = null;
        }
    }

    public Date getLastPurchaseDate() {
        return lastPurchaseDate;
    }

    public void setLastPurchaseDate(Date lastPurchaseDate) {
        this.lastPurchaseDate = lastPurchaseDate;
    }

    public Date getLastDeliveryDateDate() {
        return lastDeliveryDateDate;
    }

    public void setLastDeliveryDateDate(Date lastDeliveryDateDate) {
        this.lastDeliveryDateDate = lastDeliveryDateDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Float getSuggestBuyPrice() {
        return suggestBuyPrice;
    }

    public void setSuggestBuyPrice(float suggestBuyPrice) {
        this.suggestBuyPrice = suggestBuyPrice;
    }

    public void setSuggestBuyPrice(String suggestBuyPrice) {
        try {
            this.suggestBuyPrice = Float.parseFloat(suggestBuyPrice);
        } catch (Exception e) {
            this.suggestBuyPrice = null;
        }
    }

    public Float getSuggestSalePrice() {
        return suggestSalePrice;
    }

    public void setSuggestSalePrice(Float suggestSalePrice) {
        this.suggestSalePrice = suggestSalePrice;
    }

    public void setSuggestSalePrice(String suggestSalePrice) {
        try {
            this.suggestSalePrice = Float.parseFloat(suggestSalePrice);
        } catch (Exception e) {
            this.suggestSalePrice = null;
        }
    }
}
