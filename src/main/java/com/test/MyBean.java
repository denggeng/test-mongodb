package com.test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.stereotype.Service;

import com.mongodb.DB;

@Service
public class MyBean {

    private final MongoDbFactory mongo;

    @Autowired
    public MyBean(MongoDbFactory mongo) {
        this.mongo = mongo;
    }

    // ...

    public String example() {
        DB db = mongo.getDb();
        System.out.println(db.getName());
        return db.getName();
        // ...
    }

}