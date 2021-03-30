package WebFace;

import javax.jms.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Servlet extends HttpServlet {
    @Resource(name = "java:comp/DefaultJMSConnectionFactory")
    private ConnectionFactory connectionFactory;
    @Resource(name = "java:/jms/queue/ExpiryQueue")
    private Destination destination;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String receivedMessages = req.getParameter("messages");
        if (receivedMessages == null || receivedMessages.equals(""))
            req.setAttribute("result", "EMPTY");
        else {
            try {
                this.sendMessageToListeners(receivedMessages);
            } catch (JMSException ex) {
                Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            req.setAttribute("result", "OK");
        }
        req.getRequestDispatcher("/result.jsp").forward(req, resp);
    }

    private void sendMessageToListeners(Object messageData) throws JMSException {
        Connection connection = null;
        Session session = null;
        try {
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(destination);
            messageProducer.send(createJMSMessage(session, messageData));
        } finally {
            if (session != null) session.close();
            if (connection != null) connection.close();
        }
    }
    private Message createJMSMessage(Session session, Object messageData) throws JMSException {
        TextMessage textMessage = session.createTextMessage();
        textMessage.setText(messageData.toString());
        return textMessage;
    }
}
