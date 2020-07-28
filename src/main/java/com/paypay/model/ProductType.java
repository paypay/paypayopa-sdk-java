package com.paypay.model;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;


/**
 * The product type in PayPay system. Generally this parameter in request is optional. For some merchants that are restricted to use only certain product types, the product type must be properly set. 
 */

@JsonAdapter(ProductType.Adapter.class)

public enum ProductType {
  
  VIRTUAL_BONUS_INVESTMENT("VIRTUAL_BONUS_INVESTMENT"),
  
  PAY_LATER_REPAYMENT("PAY_LATER_REPAYMENT");

  private String value;

  ProductType(String value) {
    this.value = value;
  }


  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }


  public static ProductType fromValue(String text) {
    for (ProductType b : ProductType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }


  public static class Adapter extends TypeAdapter<ProductType> {
    @Override
    public void write(final JsonWriter jsonWriter, final ProductType enumeration) throws IOException {
      jsonWriter.value(enumeration.getValue());
    }

    @Override
    public ProductType read(final JsonReader jsonReader) throws IOException {
      String value = jsonReader.nextString();
      return ProductType.fromValue(String.valueOf(value));
    }
  }

}



