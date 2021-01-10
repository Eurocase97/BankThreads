package BankThreads;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {

	private double accounts[];
	private Lock lock;
	private Condition condition;
	
	public Bank() {
		
		accounts= new double[100];
		
		for(int i=0; i<100; i++) {
			accounts[i]=2000;
		}
		lock = new ReentrantLock();
		condition= lock.newCondition();
	}
	
	public void transfer(int origin, int destiny, double amount) throws InterruptedException {
		lock.lock();
		System.out.println("La cuenta "+ origin+" Trata de enviar dinero a "+ destiny+ " Por valor de "+ amount+" ");
		if(accounts[origin]>=amount) {
			accounts[origin]-=amount;
			accounts[destiny]+=amount;
			System.out.println("TRANSACION EXITOSA");
			condition.signalAll();
		}else {
			System.out.println("Saldo insuficiente ");
			condition.await();
		}
		System.out.println("Saldo total en el bnaco: " + Math.round(totalAmount())+"\n");
		lock.unlock();
	}
	
	public double totalAmount() {
		double total=0;
		for(Double a: accounts) {
			total+=a;
		}
	return total;
	}
}