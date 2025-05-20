package org.acme;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

@Path("/city")
public class GreetingResource {
  @Inject RedissonClient client;

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String hello() {

    var map = client.getMap("city:653", StringCodec.INSTANCE);
    System.out.println(map.readAllEntrySet());

    return "nani";
  }
}
