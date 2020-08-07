package jp.ne.paypay.model;

import com.google.gson.annotations.SerializedName;

/**
 * LinkQRCodeResponse
 */

public class LinkQRCodeResponse {

    @SerializedName("resultInfo")
    private ResultInfo resultInfo = null;

    @SerializedName("data")
    private LinkQRCodeResponseData data = null;

    /**
     * Get resultInfo
     *
     * @return resultInfo
     */
    public ResultInfo getResultInfo() {
        return resultInfo;
    }

    public LinkQRCodeResponse setResultInfo(ResultInfo resultInfo) {
        this.resultInfo = resultInfo;
        return this;
    }

    /**
     * Get data
     *
     * @return data
     */
    public LinkQRCodeResponseData getData() {
        return data;
    }

    public LinkQRCodeResponse setData(LinkQRCodeResponseData data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "LinkQRCodeResonse{" +
                "resultInfo=" + resultInfo +
                ", data=" + data +
                '}';
    }
}



