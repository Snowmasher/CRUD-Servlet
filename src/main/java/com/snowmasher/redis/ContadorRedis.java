package com.snowmasher.redis;

import redis.clients.jedis.Jedis;

public class ContadorRedis {
	private Jedis jedis;
	private static int contador = 0;
	
	
	public ContadorRedis() {
		jedis = new Jedis("localhost");
		System.out.println("Conexion redis");
		jedis.set("contador", "" + ContadorRedis.contador);
	}
	
	public void incContador() {
		jedis.incr("contador");
		System.out.println("int cont: " + jedis.get("contador"));
	}
	public String getContador() {
		return jedis.get("contador");
	}
}
