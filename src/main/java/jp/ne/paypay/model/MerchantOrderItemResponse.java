package jp.ne.paypay.model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

import io.swagger.annotations.ApiModelProperty;

/**
 * MerchantOrderItemResponse
 */

public class MerchantOrderItemResponse {

  @SerializedName("name")
  private String name = null;
  
  @SerializedName("category")
  private String category = null;
  
  @SerializedName("quantity")
  private Integer quantity = null;
  
  @SerializedName("productId")
  private String productId = null;
  
  @SerializedName("unit_price")
  private MoneyAmount unitPrice = null;
  
  public MerchantOrderItemResponse name(String name) {
    this.name = name;
    return this;
  }

  
  /**
  * Name of the item
  * @return name
  **/
  @ApiModelProperty(required = true, value = "Name of the item")
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  
  public MerchantOrderItemResponse category(String category) {
    this.category = category;
    return this;
  }

  
  /**
  * Category of the item
  * @return category
  **/
  @ApiModelProperty(value = "Category of the item")
  public String getCategory() {
    return category;
  }
  public void setCategory(String category) {
    this.category = category;
  }
  
  public MerchantOrderItemResponse quantity(Integer quantity) {
    this.quantity = quantity;
    return this;
  }

  
  /**
  * Quantity of this item in the current order
  * minimum: 1
  * @return quantity
  **/
  @ApiModelProperty(required = true, value = "Quantity of this item in the current order")
  public Integer getQuantity() {
    return quantity;
  }
  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }
  
  public MerchantOrderItemResponse productId(String productId) {
    this.productId = productId;
    return this;
  }

  
  /**
  * Product Id in merchant’s system
  * @return productId
  **/
  @ApiModelProperty(value = "Product Id in merchant’s system")
  public String getProductId() {
    return productId;
  }
  public void setProductId(String productId) {
    this.productId = productId;
  }
  
  public MerchantOrderItemResponse unitPrice(MoneyAmount unitPrice) {
    this.unitPrice = unitPrice;
    return this;
  }

  
  /**
  * Get unitPrice
  * @return unitPrice
  **/
  @ApiModelProperty(required = true, value = "")
  public MoneyAmount getUnitPrice() {
    return unitPrice;
  }
  public void setUnitPrice(MoneyAmount unitPrice) {
    this.unitPrice = unitPrice;
  }
  
  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MerchantOrderItemResponse merchantOrderItemResponse = (MerchantOrderItemResponse) o;
    return Objects.equals(this.name, merchantOrderItemResponse.name) &&
        Objects.equals(this.category, merchantOrderItemResponse.category) &&
        Objects.equals(this.quantity, merchantOrderItemResponse.quantity) &&
        Objects.equals(this.productId, merchantOrderItemResponse.productId) &&
        Objects.equals(this.unitPrice, merchantOrderItemResponse.unitPrice);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, category, quantity, productId, unitPrice);
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MerchantOrderItemResponse {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
    sb.append("    productId: ").append(toIndentedString(productId)).append("\n");
    sb.append("    unitPrice: ").append(toIndentedString(unitPrice)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

  
}



