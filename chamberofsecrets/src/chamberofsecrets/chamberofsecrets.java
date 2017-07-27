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
		System.load("C:\\Users\\Kait\\git\\opencv-java\\chamberofsecrets\\src\\hello.dll");
		
		String fullFilePath;
		ServerSocket welcomeSocket = null;
		try {
			System.out.println("The Camber Of Secrets Starts");
			welcomeSocket = new ServerSocket(6789);
			while (true) {
				Socket connectionSocket = welcomeSocket.accept();
				BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
				DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
				fullFilePath = inFromClient.readLine();
				System.out.println("Received: " + fullFilePath);
				
				String exceptExtensionPath = fullFilePath.substring(0, fullFilePath.indexOf("."));
				VideoCapture vc = new VideoCapture(fullFilePath);
				Mat frame = new Mat();
				vc.read(frame); // Get First Image
				Imgcodecs.imwrite(exceptExtensionPath+".jpg", frame);
				System.out.println("Finish Image Extract");
				//capitalizedSentence = fullFilePath.toUpperCase() + '\n';
				//outToClient.writeBytes(capitalizedSentence);
				
				//new HelloJNI().sayHello("C:/upload/sample.mp4");
				MyRunnableClass mrc = new MyRunnableClass();
				mrc.setFilepath(fullFilePath);
				new Thread(mrc).start();
				
			}
		} catch (Exception e) {
			try {
				welcomeSocket.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	
		
	}
}
