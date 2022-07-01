package task_02;

import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        System.out.println("Connecting to localhost at port 80");
        // Connect the server
        // 127.0.0.1 - localhost
        Socket sock = new Socket("68.183.239.26", 80);
        System.out.println("Connected ...");

        OutputStream os = sock.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(os);

        InputStream is = sock.getInputStream();
        ObjectInputStream ois = new ObjectInputStream(is);

        String request = ois.readUTF();
        System.out.println("Request is: " + request);

        String[] terms = request.split(" ");
        String requestId = terms[0];
        String integers = terms[1];
        
        System.out.println(requestId);
        System.out.println(integers);

        float sum = 0; 
        float average =0;
        String[] numbers = integers.split(",");
        float numbersLength = numbers.length;
        for (int i = 0; i <numbers.length; i++){
            //System.out.println(numbers[i]);
            //System.out.println(numbers.length);
            sum += Float.parseFloat(numbers[i]);
        }
        System.out.println(sum);

        //calculate average
        average = sum/numbersLength;
        System.out.println(average);


        //write back to server 
        oos.writeUTF(requestId);
        oos.writeUTF("Chia Shu Teng Charmaine");
        oos.writeUTF("charmainechiashuting@gmail.com");
        oos.writeFloat(average);
        oos.flush();

        Boolean responseBoolean = ois.readBoolean();
        if (responseBoolean){
            System.out.println("SUCCESS");
            sock.close();
            
        }
        else if (!responseBoolean){
            String responseFailed = ois.readUTF();
            System.out.println("FAILED");
            System.out.println(responseFailed);
            sock.close();
        }
       
        // // Get input from user
        // Console cons = System.console();
        // String input = cons.readLine("Say something to the server ");

        // // Write to server
        // dos.writeUTF(input);
        // dos.flush();

        // // Wait for response from server
        // String response = dis.readUTF();
        //System.out.printf(">> %s\n", response);

        // close the strams
        is.close();
        os.close();

        // close the socket
        //sock.close();
    }

    
}