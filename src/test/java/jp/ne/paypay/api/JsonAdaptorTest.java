package jp.ne.paypay.api;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import jp.ne.paypay.JSON;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class JsonAdaptorTest {

    @Test
    public void dateTypeAdaptorTest() throws IOException {
        JSON.DateTypeAdapter dateTypeAdapter = new JSON.DateTypeAdapter();
        Writer stringWriter = new StringWriter();
        JsonWriter jsonWriter = new JsonWriter(stringWriter);
        dateTypeAdapter.write(jsonWriter, new Date());
        Assertions.assertNotNull(jsonWriter);
        stringWriter.close();
        Reader in = new StringReader(new Gson().toJson("2020-12-12T13:01:01.000Z"));
        JsonReader reader = new JsonReader(in);
        Date date = dateTypeAdapter.read(reader);
        Assertions.assertNotNull(date);
        dateTypeAdapter.setFormat(new SimpleDateFormat("dd/mm/yyyy"));
        in = new StringReader(new Gson().toJson("12/12/2020"));
        reader = new JsonReader(in);
        date = dateTypeAdapter.read(reader);
        Assertions.assertNotNull(date);
    }

    @Test
    public void offsetDateTypeAdaptorTest() throws IOException {
        JSON.OffsetDateTimeTypeAdapter dateTypeAdapter = new JSON.OffsetDateTimeTypeAdapter();
        Writer stringWriter = new StringWriter();
        JsonWriter jsonWriter = new JsonWriter(stringWriter);
        dateTypeAdapter.write(jsonWriter, OffsetDateTime.now());
        Assertions.assertNotNull(jsonWriter);
        stringWriter.close();
        Reader in = new StringReader(new Gson().toJson("2020-12-12T13:01:01.000Z"));
        JsonReader reader = new JsonReader(in);
        OffsetDateTime date = dateTypeAdapter.read(reader);
        Assertions.assertNotNull(date);
        dateTypeAdapter.setFormat(DateTimeFormatter.ofPattern("yyyyMMddHHmmssZ"));
        in = new StringReader(new Gson().toJson("20140726080320+0400"));
        reader = new JsonReader(in);
        date = dateTypeAdapter.read(reader);
        Assertions.assertNotNull(date);
    }

    @Test
    public void sqlDateTypeAdaptorTest() throws IOException {
        JSON.SqlDateTypeAdapter dateTypeAdapter = new JSON.SqlDateTypeAdapter();
        Writer stringWriter = new StringWriter();
        JsonWriter jsonWriter = new JsonWriter(stringWriter);
        dateTypeAdapter.write(jsonWriter, new java.sql.Date(Instant.now().toEpochMilli()));
        Assertions.assertNotNull(jsonWriter);
        stringWriter.close();
        Reader in = new StringReader(new Gson().toJson("2020-12-12T13:01:01.000Z"));
        JsonReader reader = new JsonReader(in);
        Date date = dateTypeAdapter.read(reader);
        Assertions.assertNotNull(date);
        dateTypeAdapter.setFormat(new SimpleDateFormat("dd/mm/yyyy"));
        in = new StringReader(new Gson().toJson("12/12/2020"));
        reader = new JsonReader(in);
        date = dateTypeAdapter.read(reader);
        Assertions.assertNotNull(date);
    }
}
