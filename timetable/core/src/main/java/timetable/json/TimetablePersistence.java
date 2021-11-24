package timetable.json;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import timetable.json.serialization.TimetableModule;
import timetable.core.User;

public class TimetablePersistence {
    private ObjectMapper mapper;
    private Path filePath = null;
    

    public TimetablePersistence() {
        mapper = createMapper();
    }
    //create module
    public static SimpleModule createModule(boolean deep) {
        return new TimetableModule(deep);  
    }

    // set file path to user.home
    

    public void setFilePath(String file) {
        this.filePath = Paths.get(System.getProperty("user.home"), file);
    }

    public Path getSaveFilePath() {
        return this.filePath;
    }
    

    // register mapper
    public static ObjectMapper createMapper() {
        return new ObjectMapper()
          .registerModule(createModule(true));
      }

    
    //read method
    public User readUser(Reader reader) throws IOException {
        return mapper.readValue(reader, User.class);
    }

    // write method
    public void writeUser(User user, Writer writer) throws IOException {
        mapper.writerWithDefaultPrettyPrinter().writeValue(writer, user);
    }

    
    public User loadUser() throws IOException, IllegalStateException {
        if (filePath == null) {
            throw new IllegalStateException("No existing file path");   
        }
        try (Reader reader = new FileReader(filePath.toFile(), StandardCharsets.UTF_8)) {
            return readUser(reader);
        }
           
    }

    public void saveUser(User user) throws IOException, IllegalStateException {
        if (filePath == null) {
            throw new IllegalStateException("No existing file path");   
        }
        try (Writer writer = new FileWriter(filePath.toFile(), StandardCharsets.UTF_8)){
            writeUser(user, writer);

        }
    }
}
