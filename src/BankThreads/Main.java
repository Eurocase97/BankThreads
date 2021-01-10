package BankThreads;

public class Main {

	public static void main(String[] args) {
		
		Bank bank= new Bank();
		for(int i=0; i<100; i++) {
			Runnable r= new  Transference(i, bank);	
			Thread t= new Thread(r);
			t.start();
		}
	}
}