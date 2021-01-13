package com.orange.redis.service.broker;

import java.net.InetAddress;

import com.orange.redis.service.broker.model.RedisConfig;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("sentinel-tls")
public class RedisSentinelTlsConfigTest {
  @Autowired
  private RedisConfig redisConfig;

  @Test
  public void RedisSentinel() {
    String servers = new String();
    for (InetAddress address : redisConfig.getServers())
      servers = servers.concat(address.getHostAddress()).concat(" ");
    servers = servers.trim();
    Assert.assertEquals("192.168.56.101 192.168.56.102", servers);
    Assert.assertEquals("0", redisConfig.getPort().toString());
    Assert.assertEquals("redis_secret", redisConfig.getPassword());
    Assert.assertEquals("admin", redisConfig.getAdmin_user());
    Assert.assertEquals("admin_secret", redisConfig.getAdmin_password());
    Assert.assertFalse(redisConfig.getSentinel().isEmpty());
    Assert.assertEquals("master", redisConfig.getSentinel().getMaster_name());
    Assert.assertEquals("0", redisConfig.getSentinel().getPort().toString());
    Assert.assertEquals("redis_sentinel_secret",
        redisConfig.getSentinel().getPassword());
    Assert.assertFalse(redisConfig.getTls().isEmpty());
    Assert.assertEquals("6379", redisConfig.getTls().getPort().toString());
    Assert.assertEquals("26379", redisConfig.getTls().getHa_port().toString());
    Assert.assertEquals(
        "/home/attu7372/src/bosh/redis-orange/src/redis_broker-1.5/src/test/keys",
        redisConfig.getTls().getKeys_dir());
    Assert.assertEquals("ca.crt", redisConfig.getTls().getCa_cert_file());
    Assert.assertEquals("ca.key", redisConfig.getTls().getCa_key_file());
    Assert.assertEquals("2048",
        redisConfig.getTls().getClient_key_length().toString());
    Assert.assertEquals("redis-sentinel-tls",
        redisConfig.getTls().getClient_cert_ou());
    Assert.assertEquals("365",
        redisConfig.getTls().getClient_cert_duration().toString());
  }
}
