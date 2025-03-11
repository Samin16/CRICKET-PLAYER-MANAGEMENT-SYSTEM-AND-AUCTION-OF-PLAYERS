package com.example.iplplayerdatabase;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class serverIPL implements Runnable {
    private ServerSocket server;
    private HashMap<String, String> hM;
    private playerList dataBase;
    private HashMap<String,ObjectOutputStream>connectedClients;

    serverIPL() {
        hM = new HashMap<>();
        dataBase=playerList.getInstance();
        connectedClients=new HashMap<>();
        try {
            server = new ServerSocket(4444);
            System.out.println("Waiting...");
        } catch (Exception e) {
            e.printStackTrace();
        }
        loadCredentials();
        Thread t1 = new Thread(this);
        t1.start();
    }

    private void loadCredentials() {
        try (BufferedReader br = new BufferedReader(new FileReader("src\\main\\resources\\serverClient.txt"))) {
            String line = br.readLine();

            //BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/serverClient.txt")));

            while (line != null && !line.isEmpty()) {
                String[] credentials = line.split(",");

                hM.put(credentials[0].toLowerCase(), credentials[1]);
                line = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readBuyable(ArrayList<Player> buyAble){
        try (BufferedReader br = new BufferedReader(new FileReader("src\\main\\resources\\buySellList.txt"))) {
            String line = br.readLine();

            //BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/serverClient.txt")));

            while (line != null && !line.isEmpty()) {
                String[] fields = line.split(",");
                if (fields.length >= 8) {
                    try {
                        String pName = fields[0].trim();
                        String country = fields[1].trim();
                        int age = Integer.parseInt(fields[2].trim());
                        double height = Double.parseDouble(fields[3].trim());
                        String club = fields[4].trim();
                        String position = fields[5].trim();
                        int jerseyNumber = fields[6].isEmpty() ? -1 :Integer.parseInt(fields[6].trim());
                        int salary = Integer.parseInt(fields[7].trim());
                        buyAble.add(new Player(pName, country, age, height, club, position, jerseyNumber, salary));
                        line = br.readLine();
                    } catch (NumberFormatException e) {
                        System.out.println("Error parsing line: " + line);
                    }
                } else {
                    System.out.println("Formation of line is wrong : " + line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket s1 = server.accept();
                System.out.println("Waiting...");
                new ReadClient(s1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new serverIPL();
    }


    class ReadClient implements Runnable {
        private Socket sc1;
        private ObjectInputStream ois;
        private ObjectOutputStream oos;
        private  String clubName;

        public ReadClient(Socket s1) {
            System.out.println("DOOOOOOOOOOOOOOOOO");
            sc1 = s1;
            try {
                oos = new ObjectOutputStream(sc1.getOutputStream());
                ois = new ObjectInputStream(sc1.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            Thread t2 = new Thread(this);
            t2.start();
        }



        @Override
        public void run() {
            try {
                String username = (String) ois.readObject();
                String password = (String) ois.readObject();

                System.out.println(username);
                System.out.println(password);

                if (hM.containsKey(username.toLowerCase())) {
                    if (hM.get(username.toLowerCase()).equals(password)){
                        oos.writeObject("Correct");
                        connectedClients.put(username.toLowerCase(),oos);
                        listenToClientRequest(username);
                    }else{
                        oos.writeObject("InCorrect");
                        return;
                    }
                }else {
                    oos.writeObject("InCorrect");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        private  void listenToClientRequest(String name){
            clubName=name;
            try{
            while (true){

                    String request=(String)ois.readObject();
                    System.out.println(request);
                    switch (request){
                        case "showPlayer":{
                            ArrayList<Player> list=dataBase.searchByClubName(clubName);
                            Player[] arr=list.toArray(new Player[0]);
                            oos.writeObject("playerList");
                            oos.writeObject(arr);
                            break;
                        }
                        case "buyPlayer":{
                            ArrayList<Player> buyAble=new ArrayList<>();
                            readBuyable(buyAble);
                            System.out.println(buyAble+"Buyable List");
                            Player[] arr=buyAble.toArray(new Player[0]);
                            oos.writeObject("buyablePlayer");
                            oos.writeObject(clubName);
                            oos.writeObject(arr);
                            break;
                        }
                        case "selectForBuy":{
                            Player player=(Player)ois.readObject();
                            String prevClub=player.getClub();
                            if(connectedClients.containsKey(prevClub.toLowerCase())){
                                connectedClients.get(prevClub.toLowerCase()).writeObject("removeFromClubList");
                                connectedClients.get(prevClub.toLowerCase()).writeObject(player);
                                System.out.println("SEnt to owning club");
                            }

                            player.setClub(name);
                            dataBase.playerClubChanging(player,name);


                            ArrayList<Player> players=new ArrayList<>();
                            readBuyable(players);
                            for(Player p:players){
                                if(p.getName().equalsIgnoreCase(player.getName())){
                                    players.remove(p);
                                    break;
                                }
                            }
                            try (BufferedWriter writer = new BufferedWriter(new FileWriter("src\\main\\resources\\buySellList.txt"))) {
                                for (Player pLayer : players) {
                                    writer.write(pLayer.toString());
                                    writer.newLine();
                                }
                                System.out.println("Player list has been written");
                            } catch (IOException e) {
                                System.err.println("An error occurred: " + e.getMessage());
                            }
                            oos.writeObject("clubChanged");
                            oos.writeObject(player);

                            updateMarketPlaceForAllConnectedClients(player);

                            break;
                        }
                        case "selectForSell":{
                            Player player=(Player)ois.readObject();
                            System.out.println("in sell "+player);
                            ArrayList<Player> players=new ArrayList<>();
                            readBuyable(players);
                            if(!players.contains(player)) {
                                players.add(player);
                            }
                            try (BufferedWriter writer = new BufferedWriter(new FileWriter("src\\main\\resources\\buySellList.txt"))) {
                                for (Player pLayer : players) {
                                    writer.write(pLayer.toString());
                                    writer.newLine();
                                }
                                System.out.println("Player list has been written");
                            } catch (IOException e) {
                                System.err.println("An error occurred: " + e.getMessage());
                            }
                            oos.writeObject("inSellAdded");
                            oos.writeObject(player);
                            //updateMarketPlaceForAllConnectedClients(player);
                            //updateMarketPlaceForAllConnectedClub(players);
                            updateMarketPlaceForAllConnectedClients2(player);
                            break;
                        }
                    }
            }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }finally {
                try {
                    sc1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(connectedClients.containsKey(clubName.toLowerCase())){
                    connectedClients.remove(clubName.toLowerCase());
                }

            }

        }

        private void updateMarketPlaceForAllConnectedClients(Player x) {
            for(Map.Entry<String,ObjectOutputStream> entry:connectedClients.entrySet()){
                try {
                    entry.getValue().writeObject("removePlayerFromSellList");
                    entry.getValue().writeObject(x);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void updateMarketPlaceForAllConnectedClients2(Player player){
            for(Map.Entry<String,ObjectOutputStream> entry:connectedClients.entrySet()){
                try {
                    entry.getValue().writeObject("addPlayerToSellList");
                    entry.getValue().writeObject(player);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}