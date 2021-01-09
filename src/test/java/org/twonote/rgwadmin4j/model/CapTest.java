package org.twonote.rgwadmin4j.model;

import static org.junit.Assert.assertEquals;

import com.google.gson.Gson;
import org.junit.Test;

/**
 * Created by hrchu on 2017/4/8.
 */
public class CapTest {

  private final Gson gson = new Gson();

  @Test
  public void test() {
    Cap capBucketsRead = new Cap(Cap.Type.BUCKETS, Cap.Perm.READ);
    Cap capBucketsReadWrite = new Cap(Cap.Type.BUCKETS, Cap.Perm.READ_WRITE);
    String strBucketsRead = "buckets=read";
    String strBucketsReadStar = "buckets=*";

    // format Cap to request parameter
    assertEquals(strBucketsRead, capBucketsRead.toString());
    assertEquals(strBucketsReadStar, capBucketsReadWrite.toString());

    // serialize and deserialize
    assertEquals(capBucketsRead, gson.fromJson(gson.toJson(capBucketsRead), Cap.class));
    assertEquals(capBucketsReadWrite, gson.fromJson(gson.toJson(capBucketsReadWrite), Cap.class));

    // more test on perm read write
    assertEquals(
        capBucketsReadWrite, gson.fromJson("{\"type\":\"buckets\",\"perm\":\"*\"}", Cap.class));
    assertEquals(
        capBucketsReadWrite,
        gson.fromJson("{\"type\":\"buckets\",\"perm\":\"read,write\"}", Cap.class));
    assertEquals(
        capBucketsReadWrite,
        gson.fromJson("{\"type\":\"buckets\",\"perm\":\"read, write\"}", Cap.class));
    assertEquals(
        capBucketsReadWrite,
        gson.fromJson("{\"type\":\"buckets\",\"perm\":\"write,read\"}", Cap.class));
    assertEquals(
        capBucketsReadWrite,
        gson.fromJson("{\"type\":\"buckets\",\"perm\":\"write, read\"}", Cap.class));
  }
}
