package DataAccsess;

import DataAccessModels.IMessageRepository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class MessageRepository implements IMessageRepository {
    private static IMessageRepository messageRepository;

    public static IMessageRepository getInstance(){
        if(messageRepository == null) messageRepository = new MessageRepository();
        return messageRepository;
    }

    public void saveMessages(List<String> messages) throws IOException {
        File myFile = new File("D:/УНИВЕР/3 курс 2 сем/РИС/lab4/lab4/src/main/resources/Messages.txt");
        FileWriter writer = new FileWriter(myFile, false);
        try {
            for (String message : messages) {
                writer.write(message + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }
}
