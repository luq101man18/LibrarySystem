package main;

import java.util.Scanner;
import java.util.NoSuchElementException;

public class libraryStore extends dataBaseFunctions{
    Scanner input = new Scanner(System.in);
    public String name;
    public String address;
    public int id_store;


    libraryStore(String name, String address, int id){
        this.name = name;
        this.address = address;
        this.id_store = id;
    }
    libraryStore(){
    }

    public void getLibraryStoreInfo(){
        System.out.printf("Libarary: %s, Address: %s, ID: %d", name, address, id_store);
    }

    public void messageToChooseStore(){
        System.out.println("\nChoose a library store from the following by typing the id: \n");
    }

    public void listLibraryStores(){
        try {
            getDataToPrint("Select * from libraryStore");
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void setLibraryStoreInfo(int idStore){
        String query = "select * from libraryStore where id = ?";
        resultSet = createPrepareStatement(query, idStore);
        try {
            while(resultSet.next()){
                name = resultSet.getString("name");
                address = resultSet.getString("address");
                id_store = resultSet.getInt("id");
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void chooseLibraryStore(){
        try {
            messageToChooseStore();
            listLibraryStores();
            if(input.hasNextLine()){
                id_store = input.nextInt();
            }
            while((id_store != 1 && id_store != 2 && id_store != 3)){
                id_store = invalidInput(id_store);
            }
            if((id_store == 1 ||  id_store == 2 || id_store == 3 ))
                setLibraryStoreInfo(id_store);
            getLibraryStoreInfo();
        }catch (NoSuchElementException elementException) {
            invalidInput(id_store);
        }catch(Exception e){
            e.getMessage();
        }
    }

    


}
