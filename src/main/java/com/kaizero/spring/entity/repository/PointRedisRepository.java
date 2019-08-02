package com.kaizero.spring.entity.repository;

import com.kaizero.spring.entity.redis.Point;
import org.springframework.data.repository.CrudRepository;

public interface PointRedisRepository extends CrudRepository<Point, String> {
}
