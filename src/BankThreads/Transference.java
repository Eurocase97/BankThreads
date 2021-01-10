package BankThreads;

public class Transference implements Runnable {

	private int idAccount;
	private Bank bank;
	
	public Transference(int idAccount, Bank bank) {
		this.idAccount=idAccount;
		this.bank=bank;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				int destiny= (int) ((int) 100*Math.random());
				double amount= (double) Math.random()*2000;
				bank.transfer(idAccount, destiny, amount);
				Thread.sleep((int)Math.random()*1500+100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}		