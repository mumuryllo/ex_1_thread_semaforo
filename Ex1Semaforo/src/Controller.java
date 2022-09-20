import java.util.concurrent.Semaphore;

public class Controller extends Thread {
	
	private Semaphore semaforo;
	
	public Controller(Semaphore semaforo){
		this.semaforo = semaforo;
	}
	
	@Override
	public void run() {
		int processos = qtdeProcessos();
		
		for (int i = 1; i <= processos; i++){
			fazerCalculos();
			try {
				semaforo.acquire();
				transacionarBanco();
			} catch (InterruptedException e){
				e.printStackTrace();
			} finally {
				semaforo.release();
			}
		}
	}

	
	public int qtdeProcessos(){
		
		if (this.getId() % 3 == 1){
			return 2;
		} else {
			return 3;
		}
		
	}

	private void fazerCalculos() {
		double tempo;
		
		if (this.getId() % 3 == 1){
			tempo = (Math.random()) + 0.2;
		} else if (this.getId() % 3 == 2){
			tempo = (Math.random() * 1.5) + 0.5;
		} else {
			tempo = (Math.random() * 2) + 1;			
		}
		
		try {
			sleep((long) (tempo * 1000));
			System.out.println("id#" + this.getId() + " realizando cálculos...");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void transacionarBanco() {
		double tempo;
		
		if (this.getId() % 3 == 1){
			tempo = 1;
		} else if (this.getId() % 3 == 2){
			tempo = 1.5;
		} else {
			tempo = 1.5;		
		}
		
		try {
			sleep((long) (tempo * 1000));
			System.out.println("id#" + this.getId() + " transacionando no Banco de Dados...");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("id#" + this.getId() + " ------------------------------------------ fim");
	}
	

}