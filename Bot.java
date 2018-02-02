package cooku;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Bot extends TelegramLongPollingBot {

	protected SendMessage messaggio = new SendMessage();	//crea un nuovo messaggio di tipo SendMessage;
	private String str = new String();
	private File log = new File("use_log.txt"); //file per log utilizzo bot
	
	@Override
	public String getBotUsername() { //associa username bot
		return "YOUR_USERNAME_HERE";
	}

	@Override
	public String getBotToken() { //associa token bot
		return "YOUR_TOKEN_HERE";
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onUpdateReceived(Update aggiornamento) {
		if (aggiornamento.hasMessage() && aggiornamento.getMessage().hasText()) {
	        	messaggio.setChatId(aggiornamento.getMessage().getChatId());
	        	str=aggiornamento.getMessage().getFrom()+" ha scritto "+aggiornamento.getMessage().getText()+" in data "+aggiornamento.getMessage().getDate()+"\n";
	        	try {
				Savefile.logga();	             
	        	    } catch (IOException e) {
	                	e.printStackTrace();
	            		}
	        	
	      	  	System.out.println("Messaggio ricevuto da ID: "+aggiornamento.getMessage().getChatId()); //output di debug con id mittente
	        	try {
				messaggio.setText(Query.main(aggiornamento.getMessage().getText().replaceAll(" ", "+")));
			} catch (Exception e1) {
				e1.printStackTrace();
				}

			try {
			sendMessage(messaggio);
			System.out.println("Risposta inviata!"); //output di debug di conferma risposta inviata
			} catch (TelegramApiException e) {
			e.printStackTrace();
		};
	};};
}
