package CigarMarket.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CigarStorage {
    ArrayList<Cigar> cigarList = new ArrayList<>();
    final int MAX_QUANTITY = 30;
    private String cigarFilename = "cigarlist.txt";
    private int lastId;
    private boolean isSaved;

    public CigarStorage() throws IOException {
        loadCigarListFromFile();
        generateLastId();
        isSaved = true;
    }

    private void generateLastId() {
        lastId = 0;
        for (Cigar cigar : cigarList) {
            int id = cigar.getCigarId();
            if (id > lastId) lastId = id;
        }
    }

    private void loadCigarListFromFile() throws IOException {
        FileReader fr;
        try {
            fr = new FileReader(cigarFilename);
            BufferedReader br = new BufferedReader(fr);
            String idStr;
            while ((idStr = br.readLine()) != null && !idStr.equals("")) {
                int id = Integer.parseInt(idStr);
                String name = br.readLine();
                String tar = br.readLine();
                String nicotine = br.readLine();
                int price = Integer.parseInt(br.readLine());
                cigarList.add(new Cigar(id, name, tar, nicotine, price));
            }

            fr.close();
            br.close();

        } catch (FileNotFoundException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    public int getMaxQuantity() {
        return MAX_QUANTITY;
    }

    public void addCigar(String name, String tar, String nicotine, int price) {
        tar = "타르 : " + tar;
        nicotine = "니코틴 : " + nicotine;
        Cigar cigar = new Cigar(++lastId, name, tar, nicotine, price);
        cigarList.add(cigar);
        isSaved = false;
    }

    public boolean isEmpty() {
        return cigarList.size() == 0;
    }

    public void deleteItem(int cigarId) {
        cigarList.remove(getCigarById(cigarId));
        isSaved = false;
    }

    public Cigar getCigarById(int cigarId) {
        for (Cigar cigar : cigarList) {
            if (cigar.getCigarId() == cigarId)
                return cigar;
        }
        return null;
    }

    public boolean isSaved() {
        return isSaved;
    }

    public void saveCigarList2File() {
        try {
            FileWriter fw = new FileWriter(cigarFilename);
            for (Cigar cigar : cigarList) {
                fw.write(cigar.getCigarId() + "\n");
                fw.write(cigar.getName() + "\n");
                fw.write(cigar.getTar() + "\n");
                fw.write(cigar.getNicotine() + "\n");
                fw.write(cigar.getPrice() + "\n");
            }
            fw.close();
            isSaved = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getNumCigars() {
        return cigarList.size();
    }

    public String getCigarInfo(int i) {
        return cigarList.get(i).toString();
    }

    public boolean isValidItem(int cigarId) {
        for (Cigar cigar : cigarList) {
            if (cigar.getCigarId() == cigarId) return true;
        }
        return false;
    }
}
