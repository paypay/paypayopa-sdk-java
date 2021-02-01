package jp.ne.paypay.model;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.swagger.annotations.ApiModelProperty;

/**
 * QRCodeResponse
 */

public class QRCodeResponse {

  @SerializedName("codeId")
  private String codeId = null;
  
  @SerializedName("url")
  private String url = null;
  
  @SerializedName("deeplink")
  private String deeplink = null;
  
  @SerializedName("expiryDate")
  private Integer expiryDate = null;
  
  @SerializedName("merchantPaymentId")
  private String merchantPaymentId = null;
  
  @SerializedName("amount")
  private MoneyAmount amount = null;
  
  @SerializedName("orderDescription")
  private String orderDescription = null;
  
  @SerializedName("orderItems")
  private List<MerchantOrderItemResponse> orderItems = null;
  
  @SerializedName("metadata")
  private Object metadata = null;
  
  @SerializedName("codeType")
  private String codeType = null;
  
  @SerializedName("storeInfo")
  private String storeInfo = null;
  
  @SerializedName("storeId")
  private String storeId = null;
  
  @SerializedName("terminalId")
  private String terminalId = null;
  
  @SerializedName("requestedAt")
  private Long requestedAt = null;
  
  @SerializedName("redirectUrl")
  private String redirectUrl = null;
  
  /**
   * The type of redirect after complete the payment
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
  
  @SerializedName("isAuthorization")
  private Boolean isAuthorization = null;
  
  @SerializedName("authorizationExpiry")
  private Integer authorizationExpiry = null;
  
  public QRCodeResponse codeId(String codeId) {
    this.codeId = codeId;
    return this;
  }

  
  /**
  * The Id of the Code
  * @return codeId
  **/
  @ApiModelProperty(value = "The Id of the Code")
  public String getCodeId() {
    return codeId;
  }
  public void setCodeId(String codeId) {
    this.codeId = codeId;
  }
  
  public QRCodeResponse url(String url) {
    this.url = url;
    return this;
  }

  
  /**
  * The url to open webpage (will open app if app installed)
  * @return url
  **/
  @ApiModelProperty(value = "The url to open webpage (will open app if app installed)")
  public String getUrl() {
    return url;
  }
  public void setUrl(String url) {
    this.url = url;
  }
  
  public QRCodeResponse deeplink(String deeplink) {
    this.deeplink = deeplink;
    return this;
  }

  
  /**
  * App Deeplink to invoke (Not needed in this flow)
  * @return deeplink
  **/
  @ApiModelProperty(value = "App Deeplink to invoke (Not needed in this flow)")
  public String getDeeplink() {
    return deeplink;
  }
  public void setDeeplink(String deeplink) {
    this.deeplink = deeplink;
  }
  
  public QRCodeResponse expiryDate(Integer expiryDate) {
    this.expiryDate = expiryDate;
    return this;
  }

  
  /**
  * Get expiryDate
  * @return expiryDate
  **/
  @ApiModelProperty(value = "")
  public Integer getExpiryDate() {
    return expiryDate;
  }
  public void setExpiryDate(Integer expiryDate) {
    this.expiryDate = expiryDate;
  }
  
  public QRCodeResponse merchantPaymentId(String merchantPaymentId) {
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
  
  public QRCodeResponse amount(MoneyAmount amount) {
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
  
  public QRCodeResponse orderDescription(String orderDescription) {
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
  
  public QRCodeResponse orderItems(List<MerchantOrderItemResponse> orderItems) {
    this.orderItems = orderItems;
    return this;
  }

  public QRCodeResponse addOrderItemsItem(MerchantOrderItemResponse orderItemsItem) {
    
    if (this.orderItems == null) {
      this.orderItems = new ArrayList<MerchantOrderItemResponse>();
    }
    
    this.orderItems.add(orderItemsItem);
    return this;
  }
  
  /**
  * Get orderItems
  * @return orderItems
  **/
  @ApiModelProperty(value = "")
  public List<MerchantOrderItemResponse> getOrderItems() {
    return orderItems;
  }
  public void setOrderItems(List<MerchantOrderItemResponse> orderItems) {
    this.orderItems = orderItems;
  }
  
  public QRCodeResponse metadata(Object metadata) {
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
  
  public QRCodeResponse codeType(String codeType) {
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
  
  public QRCodeResponse storeInfo(String storeInfo) {
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
  
  public QRCodeResponse storeId(String storeId) {
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
  
  public QRCodeResponse terminalId(String terminalId) {
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
  
  public QRCodeResponse requestedAt(Long requestedAt) {
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
  
  public QRCodeResponse redirectUrl(String redirectUrl) {
    this.redirectUrl = redirectUrl;
    return this;
  }

  
  /**
  * The url of redirect after complete the payment
  * @return redirectUrl
  **/
  @ApiModelProperty(value = "The url of redirect after complete the payment")
  public String getRedirectUrl() {
    return redirectUrl;
  }
  public void setRedirectUrl(String redirectUrl) {
    this.redirectUrl = redirectUrl;
  }
  
  public QRCodeResponse redirectType(RedirectTypeEnum redirectType) {
    this.redirectType = redirectType;
    return this;
  }

  
  /**
  * The type of redirect after complete the payment
  * @return redirectType
  **/
  @ApiModelProperty(value = "The type of redirect after complete the payment")
  public RedirectTypeEnum getRedirectType() {
    return redirectType;
  }
  public void setRedirectType(RedirectTypeEnum redirectType) {
    this.redirectType = redirectType;
  }
  
  public QRCodeResponse isAuthorization(Boolean isAuthorization) {
    this.isAuthorization = isAuthorization;
    return this;
  }

  
  /**
  * If the payment is an authorization.
  * @return isAuthorization
  **/
  @ApiModelProperty(value = "If the payment is an authorization.")
  public Boolean isIsAuthorization() {
    return isAuthorization;
  }
  public void setIsAuthorization(Boolean isAuthorization) {
    this.isAuthorization = isAuthorization;
  }
  
  public QRCodeResponse authorizationExpiry(Integer authorizationExpiry) {
    this.authorizationExpiry = authorizationExpiry;
    return this;
  }

  
  /**
  * In case the payment is just an authorization, this defines the expiry of the authorization
  * @return authorizationExpiry
  **/
  @ApiModelProperty(value = "In case the payment is just an authorization, this defines the expiry of the authorization")
  public Integer getAuthorizationExpiry() {
    return authorizationExpiry;
  }
  public void setAuthorizationExpiry(Integer authorizationExpiry) {
    this.authorizationExpiry = authorizationExpiry;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QRCodeResponse qrCodeResponse = (QRCodeResponse) o;
    return Objects.equals(this.codeId, qrCodeResponse.codeId) && Objects.equals(this.merchantPaymentId, qrCodeResponse.merchantPaymentId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codeId, merchantPaymentId);
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("QRCodeResponse {\n").append("    codeId: ").append(toIndentedString(codeId)).append("\n").append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("    deep Link: ").append(toIndentedString(deeplink)).append("\n").append("    expiryDate: ").append(toIndentedString(expiryDate)).append("\n");
    sb.append("    merchantPaymentId: ").append(toIndentedString(merchantPaymentId)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n").append("    orderDescription: ").append(toIndentedString(orderDescription)).append("\n");
    sb.append("    orderItems: ").append(toIndentedString(orderItems)).append("\n").append("    metadata: ").append(toIndentedString(metadata)).append("\n");
    sb.append("    codeType: ").append(toIndentedString(codeType)).append("\n").append("    storeInfo: ").append(toIndentedString(storeInfo)).append("\n");
    sb.append("    storeId: ").append(toIndentedString(storeId)).append("\n").append("    terminalId: ").append(toIndentedString(terminalId)).append("\n");
    sb.append("    requestedAt: ").append(toIndentedString(requestedAt)).append("\n").append("    redirectUrl: ").append(toIndentedString(redirectUrl)).append("\n");
    sb.append("    redirectType: ").append(toIndentedString(redirectType)).append("\n").append("    isAuthorization: ").append(toIndentedString(isAuthorization)).append("\n");
    sb.append("    authorizationExpiry: ").append(toIndentedString(authorizationExpiry)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

  
}



