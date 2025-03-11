package com.example.iplplayerdatabase;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class playerList {
    private static final String INPUT_FILE_NAME = "src\\main\\resources\\players.txt";
    private static  playerList database;
    private ArrayList<Player> playersIPL = new ArrayList<>();

    private playerList(){
        try (BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME))) {
            loadPlayersFromFile(br);
        } catch (Exception e) {
            System.out.println("Error loading players: " + e.getMessage());
        }
    }

    public static  playerList getInstance(){
        if(database==null){
            database=new playerList();
        }
        return database;
    }

    public void addPlayer(Player playerI) {
        playersIPL.add(playerI);
    }
    // public void loadPlayersFromFile(BufferedReader br) throws Exception{
    // String line;
    // while ((line = br.readLine()) != null) {
    // String[] details = line.split(",");
    // String name = details[0];
    // String country = details[1];
    // int age = Integer.parseInt(details[2].trim());
    // double height = Double.parseDouble(details[3].trim());
    // String club = details[4];
    // String position = details[5];
    // int number = Integer.parseInt(details[6]);
    // int weeklySalary = Integer.parseInt(details[7].trim());

    // Player player = new Player(name, country, age, height, club, position,
    // number, weeklySalary);
    // playersIPL.add(player);
    // }
    // }
    public void loadPlayersFromFile(BufferedReader br) {
        String line;
        try {
            while ((line = br.readLine()) != null) {
                //System.out.println(line);
                String[] fields = line.split(",");
                if (fields.length >= 8) {
                    try {
                        String name = fields[0].trim();
                        String country = fields[1].trim();
                        int age = Integer.parseInt(fields[2].trim());
                        double height = Double.parseDouble(fields[3].trim());
                        String club = fields[4].trim();
                        String position = fields[5].trim();
                        int jerseyNumber = fields[6].isEmpty() ? -1 :Integer.parseInt(fields[6].trim());
                        int salary = Integer.parseInt(fields[7].trim());
                        playersIPL.add(new Player(name, country, age, height, club, position, jerseyNumber, salary));
                    } catch (NumberFormatException e) {
                        System.out.println("Error parsing line: " + line);
                    }
                } else {
                    System.out.println("Formation of line is wrong : " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public boolean savePlayerListToDatabase() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(INPUT_FILE_NAME))) { // 'true' appends to the file
            try {
                saveToFile(bw);
                System.out.println("Player list saved to file successfully.");
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error saving players: " + e.getMessage());
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error with file operation: " + e.getMessage());
            return false;
        }
    }
    

    public void saveToFile(BufferedWriter bw) throws Exception {
        for (Player player : playersIPL) {
            bw.write(player.toString());
            bw.newLine();
        }
    }
    // Search a player Input: Player name
    public Player searchByName(String name) {
        for (Player player : playersIPL) {
            if (player.getName().equalsIgnoreCase(name)) {
                //System.out.println(player);
                return player;
            }
        }
        return null;
    }
    // Search by player Input:club and country
    public ArrayList<Player> searchByClubAndCountry(String country, String club) {
        ArrayList<Player> sameCountry = new ArrayList<>();
        for (Player player : playersIPL) {
            if (player.getCountry().equalsIgnoreCase(country)) {
                sameCountry.add(player);
            }
        }
        if (club.equalsIgnoreCase("ANY")) {
            return sameCountry;
        }
        ArrayList<Player> sameCountryAndClub = new ArrayList<>();
        if (sameCountry.isEmpty()) {
            return sameCountry;
        }
        for (Player player : sameCountry) {
            if (player.getClub().equals(club)) {
                sameCountryAndClub.add(player);
            }
        }
        return sameCountryAndClub;
    }
    // Search player Input:Position
    public ArrayList<Player> searchByPosition(String position) {
        ArrayList<Player> samePosition = new ArrayList<>();
        for (Player player : playersIPL) {
            if (player.getPosition().equalsIgnoreCase(position)) {
                samePosition.add(player);
            }
        }
        return samePosition;
    }
    // Search player Input:Salary Range
    public ArrayList<Player> searchBySalaryRange(double lowerBound, double upperBound) {
        ArrayList<Player> sameSalaryRange = new ArrayList<>();
        for (Player player : playersIPL) {
            if (player.getWeeklySalary() >= lowerBound && player.getWeeklySalary() <= upperBound) {
                sameSalaryRange.add(player);
            }
        }
        return sameSalaryRange;
    }

    // Total Player from a country
    public Map<String, Integer> countryWisePlayerCount() {
//        StringIndexedList<Integer> count = new StringIndexedList<>();
//        for (Player player : playersIPL) {
//            String country = player.getCountry();
//            Integer countPlayer = count.get(country);
//            if (countPlayer == null) {
//                count.put(country, 1);
//            } else {
//                count.put(country, countPlayer + 1);
//            }
//        }
//        return count;
        Map<String, Integer> count = new HashMap<>();

        for (Player player : playersIPL) {
            String country = player.getCountry();

            // Get the current count or default to 0 if the key doesn't exist
            count.put(country, count.getOrDefault(country, 0) + 1);
        }

        return count;
    }


    // Search for highest paid player for a club
    public ArrayList<Player> searchByClubSalary(String club) {
        int salary = Integer.MIN_VALUE;
        ArrayList<Player> clubPlayer = new ArrayList<>();
        for (Player player : playersIPL) {
            if (player.getClub().equalsIgnoreCase(club)) {
                clubPlayer.add(player);
                if (player.getWeeklySalary() > salary) {
                    salary = player.getWeeklySalary();
                }
            }
        }
        if (clubPlayer.isEmpty()) {
            return clubPlayer;
        }
        ArrayList<Player> highestSalary = new ArrayList<>();

        for (Player player : clubPlayer) {
            if (player.getWeeklySalary() == salary) {
                highestSalary.add(player);
            }
        }
        return highestSalary;
    }
    //Search for Oldest player
    public ArrayList<Player> searchByClubPlayerAge(String club) {
        int age = Integer.MIN_VALUE;
        ArrayList<Player> clubPlayer = new ArrayList<>();
        for (Player player : playersIPL) {
            if (player.getClub().equalsIgnoreCase(club)) {
                clubPlayer.add(player);
                if (player.getAge() > age) {
                    age = player.getAge();
                }
            }
        }
        if (clubPlayer.isEmpty()) {
            return clubPlayer;
        }
        ArrayList<Player> maximumAge = new ArrayList<>();

        for (Player player : clubPlayer) {
            if (player.getAge() == age) {
                maximumAge.add(player);
            }
        }
        return maximumAge;
    }
    // Search for tallest player
    public ArrayList<Player> searchByClubPlayerHeight(String club) {
        double height = Double.MIN_VALUE;
        ArrayList<Player> clubPlayer = new ArrayList<>();
        for (Player player : playersIPL) {
            if (player.getClub().equalsIgnoreCase(club)) {
                clubPlayer.add(player);
                if (player.getHeight() > height) {
                    height = player.getHeight();
                }
            }
        }
        if (clubPlayer.isEmpty()) {
            return clubPlayer;
        }
        ArrayList<Player> maximumHeight = new ArrayList<>();

        for (Player player : clubPlayer) {
            if (player.getHeight() == height) {
                maximumHeight.add(player);
            }
        }
        return maximumHeight;
    }

    public int totalClubSalary(String club) {
        ArrayList<Player> clubPlayer = new ArrayList<>();
        for (Player player : playersIPL) {
            if (player.getClub().equalsIgnoreCase(club)) {
                clubPlayer.add(player);
            }
        }
        if (clubPlayer.isEmpty()) {
            return -1;
        }
        // int[] yearlySalary=new int[clubPlayer.size()];
        // int index=0;
        // for(Player player:playersIPL){
        // yearlySalary[index]=player.getWeeklySalary();
        // index++;
        // }
        int totalSalary = 0;
        // for(int i=0;i<clubPlayer.size();i++){
        // totalSalary+=(yearlySalary[i]);
        // }
        for (Player player : clubPlayer) {
            totalSalary += player.getWeeklySalary();
        }
        return totalSalary;
    }

    public ArrayList<Player> searchByClubName(String clubName){
        ArrayList<Player> playerClub=new ArrayList<>();
        for(Player player:playersIPL){
            if(player.getClub().equalsIgnoreCase(clubName)){
                playerClub.add(player);
            }
        }
        return playerClub;
    }

    public void  playerClubChanging(Player player,String clubName){
        Player target=null;
        player.setClub(clubName);

        for(Player p:playersIPL){
            if(p.getName().equals(player.getName())){
                target=p;
                break;
            }
        }
        playersIPL.remove(target);
        playersIPL.add(player);
        savePlayerListToDatabase();
        System.out.println("Samin");
    }
}
