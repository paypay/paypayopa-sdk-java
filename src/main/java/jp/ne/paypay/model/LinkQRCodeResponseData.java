package jp.ne.paypay.model;

import com.google.gson.annotations.SerializedName;

/**
 * LinkQRCodeResponseData
 */

public class LinkQRCodeResponseData {

    @SerializedName("linkQRCodeURL")
    private String linkQRCodeURL;

    /**
     * Get linkQRCodeURL
     *
     * @return linkQRCodeURL
     */
    public String getLinkQRCodeURL() {
        return linkQRCodeURL;
    }

    public LinkQRCodeResponseData setLinkQRCodeURL(String linkQRCodeURL) {
        this.linkQRCodeURL = linkQRCodeURL;
        return this;
    }

    @Override
    public String toString() {
        return "LinkQRCodeResponseData{" +
                "linkQRCodeURL='" + linkQRCodeURL + '\'' +
                '}';
    }
}



