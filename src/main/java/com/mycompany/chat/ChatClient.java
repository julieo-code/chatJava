package com.mycompany.chat;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
    private final String SERVER_ADDRESS = "127.0.0.1";
    private Socket clientSocket;
    private Scanner scanner;
    private PrintWriter out; //Objeto de saida de dados (enviar dados).
    
    public ChatClient() {
        scanner = new Scanner(System.in);
    }
    
    public void start() throws IOException {
        clientSocket = new Socket(SERVER_ADDRESS, ChatServer.PORT);
        this.out = new PrintWriter(clientSocket.getOutputStream(), true);
        System.out.println("Cliente conectado ao servidor em: " + SERVER_ADDRESS + ":" + ChatServer.PORT);   
        messageLoop();
    }
    
    private void messageLoop() throws IOException {
        String msg;
        do {
            System.out.println("Digite uma mensagem (ou sair para finalizar: )");
            msg = scanner.nextLine();
            out.println(msg); // envia a mensagem com quebra de linha.
        }while(!msg.equalsIgnoreCase("sair")); //Digite uma mensagem até digitar sair independente de maiusculas ou minusculas.
    }
    public static void main(String[] args) {
        try {
            ChatClient client = new ChatClient();
            client.start();
        } catch (IOException ex) {
            System.out.println("Erro ao iniciar cliente: " + ex.getMessage());
        }
        System.out.println("Cliente finalizado!");
    }
}
