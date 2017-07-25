package chamberofsecrets;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

public class chamberofsecrets {

	public static void main(String[] args) {

		System.load( "D:\\workspace-sts\\chamberofsecrets\\libs\\opencv_java320.dll" );
		String clientSentence;
		String capitalizedSentence;
		ServerSocket welcomeSocket = null;
		try {
			welcomeSocket = new ServerSocket(6789);
			while (true) {
				Socket connectionSocket = welcomeSocket.accept();
				BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
				DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
				clientSentence = inFromClient.readLine();
				System.out.println("Received: " + clientSentence);
				
				VideoCapture vc = new VideoCapture(clientSentence);
				Mat frame = new Mat();
				vc.read(frame); // Get First Image
				Imgcodecs.imwrite("firstimage.jpg", frame);
				System.out.println("Finish Image Extract");
				//capitalizedSentence = clientSentence.toUpperCase() + '\n';
				//outToClient.writeBytes(capitalizedSentence);
			}
		} catch (Exception e) {
			try {
				welcomeSocket.close();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
}
