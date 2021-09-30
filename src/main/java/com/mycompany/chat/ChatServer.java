package com.mycompany.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    public static final int PORT = 4000;
    private ServerSocket serverSocket;
    
    public void start() throws IOException {
        serverSocket = new ServerSocket(PORT);
        System.out.println("Servidor iniciado na porta: " + PORT);
        clientConnectionLoop();
    }
    
    private void clientConnectionLoop() throws IOException {
        while(true) {
            Socket clientSocket = serverSocket.accept(); //Aguarda cliente conectar e cria socket local para a conecxão.
            System.out.println("Client" + clientSocket.getRemoteSocketAddress() + "conectou!");
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String msg = in.readLine();
            System.out.println("Mensagem recebida do cliente " + clientSocket.getRemoteSocketAddress() + ":" + msg);
        }
    }
    
    public static void main(String[] args) {
        try {
            ChatServer server = new ChatServer();
            server.start();
        } catch (IOException ex) {
            System.out.println("Erro ao iniciar o servidor: " + ex.getMessage());
        }
        System.out.println("Servidor finalizado.");
    }
}
