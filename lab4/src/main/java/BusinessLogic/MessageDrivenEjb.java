package BusinessLogic;

import DataAccessModels.IMessageRepository;
import DataAccsess.MessageRepository;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@MessageDriven(mappedName="MessageListenerImpl", activationConfig = {
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/ExpiryQueue")
})
public class MessageDrivenEjb implements MessageListener {
    private IMessageRepository messageRepository = MessageRepository.getInstance();

    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                TextMessage msg = (TextMessage) message;
                String[] receivedStringMessage = msg.getText().split("\n");

                ArrayList<String> sortedMessages = new ArrayList<>(Arrays.asList(receivedStringMessage));
                try {
                    List<String> result = sortList(sortedMessages);
                    messageRepository.saveMessages(result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    private List<String> sortList(ArrayList<String> messages){
        List<String> resultList = new ArrayList<>();
        Pattern vowels = Pattern.compile("(?iu)[аеёиоуыэюяіїєaeyuio]");
        int maxCount = -1;
        int minCount = -1;
        String minVowelsM = "";
        String maxVowelsM = "";
        for(String message: messages){
            Matcher matcher = vowels.matcher(message);
            int i = 0;
            while(matcher.find()){
                i++;
            }
            if(i<minCount || minCount==-1){
                minCount = i;
                minVowelsM = message;
            }
            if(i>maxCount){
                maxCount = i;
                maxVowelsM = message;
            }
        }
        resultList.add(maxVowelsM);
        resultList.add(minVowelsM);
        return resultList;
    }
}
