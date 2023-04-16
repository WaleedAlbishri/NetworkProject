
import java.io.*;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class client {

    public static void main(String[] args) throws IOException {

        
        int port = 5000;
        String host = "localhost";
        DataInputStream fromServer;
        DataOutputStream toServer;
        
        //create a client socket with a port number 
        Socket clientSkt = new Socket(host, port);

        //create a data stream to convert stream into data so it can be read by the client
        fromServer = new DataInputStream(clientSkt.getInputStream());

        //create a data stream to convert data into stream so it can be sent to the server
        toServer = new DataOutputStream(clientSkt.getOutputStream());

        //create a Scanner to read input from usere
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome, Press Enter To Start The Program");
        
        //Start of the connection 
        while (true) {
            //read input from the user
            input.nextLine();
            System.out.print("Enter a Character to be searched: ");
            char ch = input.nextLine().charAt(0);
            //sent the value to the server
            toServer.writeChar(ch);;

            //read input from the user
            System.out.print("Enter a String: ");
            String str = input.nextLine();
            //sent the value to the server
            toServer.writeUTF(str);

            
            //get the response from the server
            String res = fromServer.readUTF();

            //print the response 
            System.out.println(res);
            
            //check whither the clint want to repeat or not
            char repeat;
            do {
                System.out.print("Want to repeat (Y/N): ");
                repeat = input.next().charAt(0);
            } while (repeat != 'Y' && repeat != 'N');
            
            //send the value of "repeat" to the server
            toServer.writeChar(repeat);
            System.out.println();
            if (repeat != 'Y') {
                break;
            }

        }

        //close connection
        System.out.println("\nThank you!");
        clientSkt.close();

    }

}

