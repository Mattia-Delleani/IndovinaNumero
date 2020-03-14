package it.polito.tdp.indovinanumero.model;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;

public class Model {
	//
	private final int NMAX = 100;
	private final int TMAX = 8;
	private int segreto;
	private int tentativiFatti;
	private boolean inGioco;
	
	private Set<Integer> tentativi;

	public Model() {
		this.inGioco = false;
		this.tentativiFatti = 0;
	}
	
	public void nuovaPartita() {
		//gestione dell'inizio di una nuova partita - Logica del gioco
    	this.segreto = (int)(Math.random() * NMAX) + 1;
    	this.tentativiFatti = 0;
    	this.inGioco = true; 
    	this.tentativi = new HashSet<Integer>();
    	
    	
	}
	
	public int tentativo(int tentativo) {
		//deve ritornare qualcosa possiamo ritornare 0 se giosto, 1 se troppo alto , -1 se troppo basso
		
		//controllo se è in gioco
		if(!inGioco) {
			throw new IllegalStateException("La partita è gia terminata");
		}
		
		//controllo l'input
		if(!tentativoValido(tentativo))
			throw new InvalidParameterException("Devi inserire un numero che non hai ancora utilizzato tra 1 e "+ NMAX+"\n");
		
		//tentativo valido --> proviamo
		this.tentativiFatti ++;
		//aggiungo il tentativo fatto
		this.tentativi.add(tentativo);
		
		//controllo numero di tentativi
		if(this.tentativiFatti==TMAX)
			this.inGioco = false;
		
		
		if(tentativo == this.segreto) {
    		//HO INDOVINATO!
    		this.inGioco = false;
    		return 0;
    	}else if(tentativo < this.segreto)
			return -1;
		else
			return 1;
		
	
	}
	
	private boolean tentativoValido(int tentativo) {
		if(tentativo<1 || tentativo > NMAX)
			return false;
		else {
			if (this.tentativi.contains(tentativo))
				return false;
			return true;
		}
	}

	public int getSegreto() {
		return segreto;
	}

	public int getTentativiFatti() {
		return tentativiFatti;
	}

	public int getTMAX() {
		return TMAX;
	}
	
	
	
}
