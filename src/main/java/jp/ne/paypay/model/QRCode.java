package jp.ne.paypay.model;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModelProperty;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * QRCode
 */

public class QRCode {

  @SerializedName("merchantPaymentId")
  @NotEmpty(message = "merchantPaymentId is required")
  @Size(max = 64, message = "maximum 64 characters are allowed for merchantPaymentId")
  private String merchantPaymentId = null;
  
  @SerializedName("amount")
  @NotNull(message = "amount is required")
  private MoneyAmount amount = null;
  
  @SerializedName("orderDescription")
  @Size(max =255 ,message = "maximum 255 characters allowed for orderDescription")
  private String orderDescription = null;
  
  @SerializedName("orderItems")
  private List<MerchantOrderItem> orderItems = null;
  
  @SerializedName("metadata")
  private Object metadata = null;
  
  @SerializedName("codeType")
  @NotEmpty(message = "codeType is required")
  private String codeType = null;
  
  @SerializedName("storeInfo")
  @Size(max =255 ,message = "maximum 255 characters allowed for storeInfo")
  private String storeInfo = null;
  
  @SerializedName("storeId")
  @Size(max =255 ,message = "maximum 255 characters allowed for storeId")
  private String storeId = null;
  
  @SerializedName("terminalId")
  @Size(max =255 ,message = "maximum 255 characters allowed for terminalId")
  private String terminalId = null;
  
  @SerializedName("requestedAt")
  private Long requestedAt = Instant.now().getEpochSecond();
  
  @SerializedName("isAuthorization")
  private Boolean isAuthorization = null;
  
  @SerializedName("authorizationExpiry")
  private Long authorizationExpiry = null;
  
  @SerializedName("redirectUrl")
  private String redirectUrl = null;
  
  /**
   * The type of redirect url
   */
  @JsonAdapter(RedirectTypeEnum.Adapter.class)
  public enum RedirectTypeEnum {
    
    WEB_LINK("WEB_LINK"),
    APP_DEEP_LINK("APP_DEEP_LINK");

    private String value;

    RedirectTypeEnum(String value) {
      this.value = value;
    }
    
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }
    
    public static RedirectTypeEnum fromValue(String text) {
      for (RedirectTypeEnum b : RedirectTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
    
    public static class Adapter extends TypeAdapter<RedirectTypeEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final RedirectTypeEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public RedirectTypeEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return RedirectTypeEnum.fromValue(String.valueOf(value));
      }
    }
  }
  
  @SerializedName("redirectType")
  private RedirectTypeEnum redirectType = null;

  @SerializedName("userAgent")
  private String userAgent = null;

  public QRCode merchantPaymentId(String merchantPaymentId) {
    this.merchantPaymentId = merchantPaymentId;
    return this;
  }

  
  /**
  * Get merchantPaymentId
  * @return merchantPaymentId
  **/
  @ApiModelProperty(value = "")
  public String getMerchantPaymentId() {
    return merchantPaymentId;
  }
  public void setMerchantPaymentId(String merchantPaymentId) {
    this.merchantPaymentId = merchantPaymentId;
  }
  
  public QRCode amount(MoneyAmount amount) {
    this.amount = amount;
    return this;
  }

  
  /**
  * Get amount
  * @return amount
  **/
  @ApiModelProperty(value = "")
  public MoneyAmount getAmount() {
    return amount;
  }
  public void setAmount(MoneyAmount amount) {
    this.amount = amount;
  }
  
  public QRCode orderDescription(String orderDescription) {
    this.orderDescription = orderDescription;
    return this;
  }

  
  /**
  * Description of the order
  * @return orderDescription
  **/
  @ApiModelProperty(value = "Description of the order")
  public String getOrderDescription() {
    return orderDescription;
  }
  public void setOrderDescription(String orderDescription) {
    this.orderDescription = orderDescription;
  }
  
  public QRCode orderItems(List<MerchantOrderItem> orderItems) {
    this.orderItems = orderItems;
    return this;
  }

  public QRCode addOrderItemsItem(MerchantOrderItem orderItemsItem) {
    
    if (this.orderItems == null) {
      this.orderItems = new ArrayList<MerchantOrderItem>();
    }
    
    this.orderItems.add(orderItemsItem);
    return this;
  }
  
  /**
  * Get orderItems
  * @return orderItems
  **/
  @ApiModelProperty(value = "")
  public List<MerchantOrderItem> getOrderItems() {
    return orderItems;
  }
  public void setOrderItems(List<MerchantOrderItem> orderItems) {
    this.orderItems = orderItems;
  }
  
  public QRCode metadata(Object metadata) {
    this.metadata = metadata;
    return this;
  }

  
  /**
  * Extra information the merchant want to add
  * @return metadata
  **/
  @ApiModelProperty(value = "Extra information the merchant want to add")
  public Object getMetadata() {
    return metadata;
  }
  public void setMetadata(Object metadata) {
    this.metadata = metadata;
  }
  
  public QRCode codeType(String codeType) {
    this.codeType = codeType;
    return this;
  }

  
  /**
  * Please pass the fixed string ORDER_QR
  * @return codeType
  **/
  @ApiModelProperty(value = "Please pass the fixed string ORDER_QR")
  public String getCodeType() {
    return codeType;
  }
  public void setCodeType(String codeType) {
    this.codeType = codeType;
  }
  
  public QRCode storeInfo(String storeInfo) {
    this.storeInfo = storeInfo;
    return this;
  }

  
  /**
  * Store info for the merchant
  * @return storeInfo
  **/
  @ApiModelProperty(value = "Store info for the merchant")
  public String getStoreInfo() {
    return storeInfo;
  }
  public void setStoreInfo(String storeInfo) {
    this.storeInfo = storeInfo;
  }
  
  public QRCode storeId(String storeId) {
    this.storeId = storeId;
    return this;
  }

  
  /**
  * Id to identify store under merchant
  * @return storeId
  **/
  @ApiModelProperty(value = "Id to identify store under merchant")
  public String getStoreId() {
    return storeId;
  }
  public void setStoreId(String storeId) {
    this.storeId = storeId;
  }
  
  public QRCode terminalId(String terminalId) {
    this.terminalId = terminalId;
    return this;
  }

  
  /**
  * Id to identify terminal device under store
  * @return terminalId
  **/
  @ApiModelProperty(value = "Id to identify terminal device under store")
  public String getTerminalId() {
    return terminalId;
  }
  public void setTerminalId(String terminalId) {
    this.terminalId = terminalId;
  }
  
  public QRCode requestedAt(Long requestedAt) {
    this.requestedAt = requestedAt;
    return this;
  }

  
  /**
  * Get requestedAt
  * @return requestedAt
  **/
  @ApiModelProperty(value = "")
  public Long getRequestedAt() {
    return requestedAt;
  }
  public void setRequestedAt(Long requestedAt) {
    this.requestedAt = requestedAt;
  }
  
  public QRCode isAuthorization(Boolean isAuthorization) {
    this.isAuthorization = isAuthorization;
    return this;
  }

  
  /**
  * By default it will be false, please set true if the amount will be captured later
  * @return isAuthorization
  **/
  @ApiModelProperty(value = "By default it will be false, please set true if the amount will be captured later")
  public Boolean isIsAuthorization() {
    return isAuthorization;
  }
  public void setIsAuthorization(Boolean isAuthorization) {
    this.isAuthorization = isAuthorization;
  }
  
  public QRCode authorizationExpiry(Long authorizationExpiry) {
    this.authorizationExpiry = authorizationExpiry;
    return this;
  }

  
  /**
  * Epoch timestamp in seconds, expiry duration must be less then the expiry granted to the merchant
  * @return authorizationExpiry
  **/
  @ApiModelProperty(value = "Epoch timestamp in seconds, expiry duration must be less then the expiry granted to the merchant")
  public Long getAuthorizationExpiry() {
    return authorizationExpiry;
  }
  public void setAuthorizationExpiry(Long authorizationExpiry) {
    this.authorizationExpiry = authorizationExpiry;
  }
  
  public QRCode redirectUrl(String redirectUrl) {
    this.redirectUrl = redirectUrl;
    return this;
  }

  
  /**
  * The url of the page/app to open after the payment is complete
  * @return redirectUrl
  **/
  @ApiModelProperty(value = "The url of the page/app to open after the payment is complete")
  public String getRedirectUrl() {
    return redirectUrl;
  }
  public void setRedirectUrl(String redirectUrl) {
    this.redirectUrl = redirectUrl;
  }
  
  public QRCode redirectType(RedirectTypeEnum redirectType) {
    this.redirectType = redirectType;
    return this;
  }

  
  /**
  * The type of redirect url
  * @return redirectType
  **/
  @ApiModelProperty(value = "The type of redirect url")
  public RedirectTypeEnum getRedirectType() {
    return redirectType;
  }
  public void setRedirectType(RedirectTypeEnum redirectType) {
    this.redirectType = redirectType;
  }

  /**
   * The User agent of the web browser
   * @return userAgent
   **/
  @ApiModelProperty(value = "User agent of the web browser")
  public String getUserAgent() {
    return userAgent;
  }
  public void setUserAgent(String userAgent) {
    this.userAgent = userAgent;
  }

  
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QRCode qrCode = (QRCode) o;
    return Objects.equals(this.merchantPaymentId, qrCode.merchantPaymentId);

  }

  @Override
  public int hashCode() {
    return Objects.hash(merchantPaymentId);
  }

  @Override
  public String toString() {
    return "QRCode{" + "merchantPaymentId='" + merchantPaymentId + '\'' + ", amount=" + amount + ", orderDescription='"
            + orderDescription + '\'' + ", orderItems=" + orderItems + ", metadata=" + metadata + ", codeType='"
            + codeType + '\'' + ", storeInfo='" + storeInfo + '\'' + ", storeId='" + storeId + '\'' + ", terminalId='"
            + terminalId + '\'' + ", requestedAt=" + requestedAt + ", isAuthorization=" + isAuthorization
            + ", authorizationExpiry=" + authorizationExpiry + ", redirectUrl='" + redirectUrl + '\''
            + ", redirectType=" + redirectType + ", userAgent=" + userAgent  + '}';
  }
  
}



