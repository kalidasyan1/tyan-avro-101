package org.example;

import example.avro.User;
import java.io.File;
import java.io.IOException;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;


public class Main {
  public static void main(String[] args) throws IOException {
    System.out.println("Hello world!");
    User user1 = new User();
    user1.setName("Alyssa");
    user1.setFavoriteNumber(256);
// Leave favorite color null

// Alternate constructor
    User user2 = new User("Ben", 7, "red");

// Construct via builder
    User user3 = User.newBuilder()
        .setName("Charlie")
        .setFavoriteColor("blue")
        .setFavoriteNumber(null)
        .build();

    // Serialize user1, user2 and user3 to disk
    DatumWriter<User> userDatumWriter = new SpecificDatumWriter<User>(User.class);
    DataFileWriter<User> dataFileWriter = new DataFileWriter<User>(userDatumWriter);
    dataFileWriter.create(user1.getSchema(), new File("users.avro"));
    dataFileWriter.append(user1);
    dataFileWriter.append(user2);
    dataFileWriter.append(user3);
    dataFileWriter.close();
    System.out.println("Serialize finished!");


    // Deserialize Users from disk
    DatumReader<User> userDatumReader = new SpecificDatumReader<User>(User.class);
    DataFileReader<User> dataFileReader = new DataFileReader<User>(new File("users.avro"), userDatumReader);
    User user = null;
    while (dataFileReader.hasNext()) {
// Reuse user object by passing it to next(). This saves us from
// allocating and garbage collecting many objects for files with
// many items.
      user = dataFileReader.next(user);
      System.out.println(user);
    }

    System.out.println("Test github push");
    System.out.println("Test github push 2");
    System.out.println("Test github push 3");
    System.out.println("Test github push 4");
    System.out.println("Test github push 5");
  }
}
