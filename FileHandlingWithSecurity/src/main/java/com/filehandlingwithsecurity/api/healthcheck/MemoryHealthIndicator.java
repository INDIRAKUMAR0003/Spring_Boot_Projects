package com.filehandlingwithsecurity.api.healthcheck;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class MemoryHealthIndicator implements HealthIndicator {

	@Override
	public Health health() {
		long lngFreeMemory= Runtime.getRuntime().freeMemory();
		long lngTotalMemory= Runtime.getRuntime().totalMemory();
		double dblFreeMemoryPercent= (lngFreeMemory/lngTotalMemory)/100;
		
		if(dblFreeMemoryPercent > 25)
		{
			return Health.up()
						 .withDetail("Free_Memory", lngFreeMemory+" bytes")
						 .withDetail("Total_Memory", lngTotalMemory+" bytes")
						 .withDetail("Free_Memory_Percent", dblFreeMemoryPercent+" %")
						 .build();
		}else {
			return Health.down()
						 .withDetail("Free_Memory", lngFreeMemory+" bytes")
						 .withDetail("Total_Memory", lngTotalMemory+" bytes")
						 .withDetail("Free_Memory_Percent", dblFreeMemoryPercent+" %")
						 .build();
		}
	}
	
}
