package com.mycompany.chat;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class BlockingChatClientApp implements Runnable {
   
    public static final String SERVER_ADDRESS = "127.0.0.1";
    
    private final Scanner scanner;
    
    private ClientSocket clientSocket;
 
    public static void main(String[] args) {
        try {
            BlockingChatClientApp client = new BlockingChatClientApp();
            client.start();
        } catch (IOException e) {
            System.out.println("Erro ao conectar ao servidor: " + e.getMessage());
        }
    }
    
    /**
     * Instancia um cliente, realizando o mínimo de operações necessárias.
     */
    public BlockingChatClientApp(){
        scanner = new Scanner(System.in);
    }

    private void start() throws IOException {
        final Socket socket = new Socket(SERVER_ADDRESS, BlockingChatServerApp.PORT);
        clientSocket = new ClientSocket(socket);
        System.out.println(
            "Cliente conectado ao servidor no endereço " + SERVER_ADDRESS +
            " e porta " + BlockingChatServerApp.PORT);

        new Thread(this).start();
        messageLoop();
    }

    /**
     * Inicia o loop de envio e recebimento de mensagens.
     * O loop é interrompido quando o usuário digitar "sair".
     */
    private void messageLoop() {
        String msg;
        do {
            
            System.out.print("Digite uma msg (ou 'sair' para encerrar): ");
            msg = scanner.nextLine();
            clientSocket.sendMsg(msg);
        } while(!"sair".equalsIgnoreCase(msg));
        clientSocket.close();
    }

    @Override
    public void run() {
        String msg;
        while((msg = clientSocket.getMessage())!= null) {
            System.out.println("Servidor diz: " + msg);
        }
    }
}
