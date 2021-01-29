package jp.ne.paypay.model;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;


/**
 * The product type in PayPay system. Generally this parameter in request is optional. For some merchants that are restricted to use only certain product types, the product type must be properly set. 
 */

@JsonAdapter(WalletType.Adapter.class)

public enum WalletType {

  PREPAID("PREPAID"),

  CASHBACK("CASHBACK");

  private String value;

  WalletType(String value) {
    this.value = value;
  }


  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }


  public static WalletType fromValue(String text) {
    for (WalletType b : WalletType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }


  public static class Adapter extends TypeAdapter<WalletType> {
    @Override
    public void write(final JsonWriter jsonWriter, final WalletType enumeration) throws IOException {
      jsonWriter.value(enumeration.getValue());
    }

    @Override
    public WalletType read(final JsonReader jsonReader) throws IOException {
      String value = jsonReader.nextString();
      return WalletType.fromValue(String.valueOf(value));
    }
  }

}



