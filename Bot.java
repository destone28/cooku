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
	private File log = new File("use_log.txt");
	
	@Override
	public String getBotUsername() {
		return "cookubot";
	}

	@Override
	public String getBotToken() { //associa token bot
		return "459037530:AAHbqRgPn68RiVDpFxxe7FFnv4XYMulXQ_s";
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onUpdateReceived(Update aggiornamento) {
		if (aggiornamento.hasMessage() && aggiornamento.getMessage().hasText()) {
	        	messaggio.setChatId(aggiornamento.getMessage().getChatId());
	        	str=aggiornamento.getMessage().getFrom()+" ha scritto "+aggiornamento.getMessage().getText()+" in data "+aggiornamento.getMessage().getDate()+"\n";
	        	try {
	        		if (log.exists()){
	                    System.out.println("Il file " + log + " esiste");
	                    try {
	                        FileWriter fw = new FileWriter(log,true); //log su file, true per append
	                        fw.append(str+"\n");
	                        fw.flush();
	                        fw.close();
	                    }
	                    catch(IOException e) {
	                        e.printStackTrace();
	                    }
	        		}
	                else if (log.createNewFile()){
	                    System.out.println("Il file " + log + " è stato creato");
	        		try {
	        	        File log = new File("use_log.txt");
	        	        FileWriter fw = new FileWriter(log,true);  //log su file, true per append
	        	        fw.append(str+"\n");
	        	        fw.flush();
	        	        fw.close();
	        	    	}
	        	    catch(IOException e) {
	        	        e.printStackTrace();
	        	    	}
	                }
	                else
	                    System.out.println("Il file " + log + " non può essere creato");
	             
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        	
	        	System.out.println("Messaggio ricevuto da ID: "+aggiornamento.getMessage().getChatId()); //output di debug con id mittente
	        	try {
					messaggio.setText(Query.main(aggiornamento.getMessage().getText().replaceAll(" ", "+")));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
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
