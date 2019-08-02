package com.kaizero.spring;

import com.kaizero.spring.entity.redis.Point;
import com.kaizero.spring.entity.repository.PointRedisRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringToyApplicationTests {

	@Autowired
	private PointRedisRepository pointRedisRepository;

	@Test
	public void contextLoads() {
		String id = "aa";

		LocalDateTime refreshTime = LocalDateTime.now();
		Point point = Point.builder()
				.id(id)
				.amount(1000L)
				.refreshTime(refreshTime)
				.build();

		//when
		pointRedisRepository.save(point);

		//then
		Point savedPoint = pointRedisRepository.findById(id).get();
	}

}
