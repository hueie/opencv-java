package chamberofsecrets;

public class MyRunnableClass implements Runnable{
	private String filepath;
	
	@Override
	public void run() {
		new HelloJNI().sayHello(filepath);		
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

}
