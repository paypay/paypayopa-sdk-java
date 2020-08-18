package jp.ne.paypay.api;

import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import jp.ne.paypay.ApiClient;
import jp.ne.paypay.ApiException;
import jp.ne.paypay.JSON;
import jp.ne.paypay.Pair;
import jp.ne.paypay.model.QRCode;
import jp.ne.paypay.model.QRCodeDetails;
import jp.ne.paypay.model.ResultInfo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiClientTest {

    ApiClient apiClient = new ApiClient();

    @Test
    public void parameterToStringTest(){
        List<String> params = new ArrayList<>();
        params.add("Id");
        params.add("Name");
        String parameterToString = apiClient.parameterToString(params);
        Assert.assertEquals(parameterToString, "Id,Name");
        Date date  = new GregorianCalendar(2020, 2, 5).getTime();
        parameterToString = apiClient.parameterToString(date);
        Assert.assertTrue(parameterToString.startsWith("2020-"));
    }
    @Test
    public void parameterToPairTest(){
        List<Pair> pairs = apiClient.parameterToPair("name", "paypay");
        Assert.assertEquals(pairs.get(0).getName(), "name");
        Assert.assertEquals(pairs.get(0).getValue(), "paypay");
    }

    @Test
    public void sanitizeFileNameTest(){
        String sanitizeFilename = apiClient.sanitizeFilename("/temp/test/file.txt");
        Assert.assertEquals(sanitizeFilename, "file.txt");
    }

    @Test
    public void isJsonMimeTest(){
        boolean isJsonMime = apiClient.isJsonMime("application/json");
        Assert.assertEquals(isJsonMime, true);
        isJsonMime = apiClient.isJsonMime("application/text");
        Assert.assertEquals(isJsonMime, false);
    }

    @Test
    public void selectHeaderAcceptTest(){
        String[] accepts = {"application/json"};
        String selectHeaderAccept = apiClient.selectHeaderAccept(accepts);
        Assert.assertEquals(selectHeaderAccept, "application/json");
        selectHeaderAccept = apiClient.selectHeaderAccept(new String[1]);
        System.out.println(selectHeaderAccept);
        Assert.assertEquals("null", selectHeaderAccept);
        accepts[0] = "application/text";
        selectHeaderAccept = apiClient.selectHeaderAccept(accepts);
        Assert.assertEquals(selectHeaderAccept, "application/text");
    }

    @Test
    public void selectHeaderContentTypeTest(){
        String[] accepts = {"application/json"};
        String headerContentType = apiClient.selectHeaderContentType(accepts);
        Assert.assertEquals(headerContentType, "application/json");
        headerContentType = apiClient.selectHeaderContentType(new String[0]);
        System.out.println(headerContentType);
        Assert.assertEquals("application/json;charset=UTF-8", headerContentType);
        accepts[0] = "application/text";
        headerContentType = apiClient.selectHeaderContentType(accepts);
        Assert.assertEquals(headerContentType, "application/text");
    }

    @Test
    public void escapeStringTest(){
        String escapeString = apiClient.escapeString("temp/test");
        Assert.assertEquals(escapeString, "temp%2Ftest");
    }

    @Test
    public void deserializeTest() throws ApiException {
        Request.Builder requestBuild = new Request.Builder();
        requestBuild.url("http://paypay.ne.jp/v2/qrcode");
        requestBuild.header("content-type", "application/json");
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), "{\"merchantPaymentId\":'paymentId'}");
        requestBuild.post(requestBody);
        Request request = requestBuild.build();

        Response.Builder builder = new Response.Builder();
        builder.header("content-type","application/json");
        builder.request(request);
        builder.code(200);
        builder.protocol(Protocol.HTTP_2);

        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setMessage("SUCCESS");
        QRCodeDetails qrCodeDetails = new QRCodeDetails();
        qrCodeDetails.setResultInfo(resultInfo);
        JSON json = new JSON();
        builder.body(ResponseBody.create(MediaType.parse("application/json"), json.serialize(qrCodeDetails) ));
        Response response = builder.build();

        Type localVarReturnType = new TypeToken<QRCodeDetails>() {
        }.getType();
        Object result = apiClient.deserialize(response, localVarReturnType);
        Assert.assertTrue(result instanceof QRCodeDetails);
        //result = apiClient.handleResponse(response, localVarReturnType);
        //Assert.assertTrue(result instanceof QRCodeDetails);

        localVarReturnType = new TypeToken<File>() {
        }.getType();
        result = apiClient.deserialize(response, localVarReturnType);
        Assert.assertTrue(result instanceof File);
    }

    @Test
    public void handleResponseTest() throws ApiException {
        Request.Builder requestBuild = new Request.Builder();
        requestBuild.url("http://paypay.ne.jp/v2/qrcode");
        requestBuild.header("content-type", "application/json");
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), "{\"merchantPaymentId\":'paymentId'}");
        requestBuild.post(requestBody);
        Request request = requestBuild.build();

        Response.Builder builder = new Response.Builder();
        builder.header("content-type","application/json");
        builder.request(request);
        builder.code(200);
        builder.protocol(Protocol.HTTP_2);

        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setMessage("SUCCESS");
        QRCodeDetails qrCodeDetails = new QRCodeDetails();
        qrCodeDetails.setResultInfo(resultInfo);
        JSON json = new JSON();
        builder.body(ResponseBody.create(MediaType.parse("application/json"), json.serialize(qrCodeDetails) ));
        Response response = builder.build();

        Type localVarReturnType = new TypeToken<QRCodeDetails>() {
        }.getType();
        Object result = apiClient.handleResponse(response, localVarReturnType);
        Assert.assertTrue(result instanceof QRCodeDetails);

         result = apiClient.handleResponse(response, null);
        Assert.assertFalse(result instanceof QRCodeDetails);

        localVarReturnType = new TypeToken<File>() {
        }.getType();
        result = apiClient.handleResponse(response, localVarReturnType);
        Assert.assertTrue(result instanceof File);
    }

    @Test
    public void handleUnsuccessfulResponseTest() throws ApiException {
        Request.Builder requestBuild = new Request.Builder();
        requestBuild.url("http://paypay.ne.jp/v2/qrcode");
        requestBuild.header("content-type", "application/json");
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), "{\"merchantPaymentId\":'paymentId'}");
        requestBuild.post(requestBody);
        Request request = requestBuild.build();

        Response.Builder builder = new Response.Builder();
        builder.header("content-type","application/json");
        builder.request(request);
        builder.code(400);
        builder.protocol(Protocol.HTTP_2);

        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setMessage("FAILED");
        QRCodeDetails qrCodeDetails = new QRCodeDetails();
        qrCodeDetails.setResultInfo(resultInfo);
        JSON json = new JSON();
        builder.body(ResponseBody.create(MediaType.parse("application/json"), json.serialize(qrCodeDetails) ));
        Response response = builder.build();

        Type localVarReturnType = new TypeToken<QRCodeDetails>() {
        }.getType();
        Assert.assertThrows(ApiException.class, () -> apiClient.handleResponse(response, localVarReturnType));
        Assert.assertThrows(ApiException.class, () -> apiClient.handleResponse(response, null));
    }

    @Test
    public void serializeTest() throws ApiException {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setMessage("SUCCESS");
        QRCodeDetails qrCodeDetails = new QRCodeDetails();
        qrCodeDetails.setResultInfo(resultInfo);
        RequestBody serialize = apiClient.serialize(qrCodeDetails, "application/json");
        Assert.assertEquals("application/json; charset=utf-8",serialize.contentType().toString());
    }

    @Test
    public void buildRequest() throws ApiException {
        List<Pair> queryParams = new ArrayList<>();
        queryParams.add(new Pair("name", "paypay"));
        QRCode qrCode = new QRCode();
        qrCode.setMerchantPaymentId("maerchant_payemnt_id");
        Map<String, String> headerParams = new HashMap<>();
        headerParams.put("content-type", "application/json");
        String[] localVarAuthNames = new String[]{"HmacAuth"};
        Request request = apiClient.buildRequest("/v2/qrcode", "POST", queryParams, queryParams, qrCode, headerParams, null, localVarAuthNames);
        System.out.println(request);
        Assert.assertEquals(request.method(), "POST");
        Assert.assertTrue(request.urlString().contains("/v2/qrcode"));
    }
}
