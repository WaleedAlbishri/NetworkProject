

import java.io.*;
import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;

public class server {

    public static void main(String[] args) throws IOException {

        
        int port = 5000;
        DataInputStream fromClient;
        DataOutputStream toClient;

        ///create a server socket 
        ServerSocket serverSkt = new ServerSocket(port);

        //create a socket 
        Socket skt;
        
        //persistent TCP connection
        while (true) {

            //accept and listen for the connection of the client
            skt = serverSkt.accept(); //establish a connection (Handshaking)
            System.out.println("\nClient is connected.\n----------------------");

            //create a data stream to convert stream into data so it can be read by the server 
            fromClient = new DataInputStream(skt.getInputStream());
            //create a data stream to convert data into stream so it can be sent to the client
            toClient = new DataOutputStream(skt.getOutputStream());

            while (true) {

                //get the values from the client
                char ch = fromClient.readChar();
                String str = fromClient.readUTF();
                
                
                //Print the clint values
                System.out.println("The client have sent the CHAR value: " + ch);
                System.out.println("The client have sent the STRING value: " + str);
                
                //call numOfOccurrences function
                String response = numOfOccurrences(str,ch);

                //send the response to the client
                toClient.writeUTF(response);
                
                //get the value of "repeat" from the client
                char repeat = fromClient.readChar();
                if (repeat != 'Y') {
                    System.out.println();
                    break;
                }
            }

            //close connection.
            skt.close();

        }

    }

    
    
    public static String numOfOccurrences(String str, char ch) {
        int counter = 0; 
        str = str.toLowerCase();
        
        if (ch >= 'A' &&  ch <= 'Z') {
            ch += 32;
        }
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ch) {
                counter++;
            }
        }
       return "The number of Occurrences are: " + counter;
    }

    
}
