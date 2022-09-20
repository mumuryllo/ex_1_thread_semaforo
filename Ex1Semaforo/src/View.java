import java.util.concurrent.Semaphore;

public class View {

	public static void main(String[] args) {

		Semaphore semaforo = new Semaphore(1);
		
		
		for (int i = 1; i < 22; i++){
			Controller control = new Controller(semaforo);
			control.start();
		}
		
	}

}
