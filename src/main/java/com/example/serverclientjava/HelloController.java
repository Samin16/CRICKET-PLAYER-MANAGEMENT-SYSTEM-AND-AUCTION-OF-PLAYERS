package com.example.serverclientjava;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class HelloController {
    //public String clubName;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    @FXML
    private Button logIn;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    void checkLogIn(ActionEvent event) {
        Socket sc=null;
        try {
            sc=new Socket("localhost",4444);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {

            ois=new ObjectInputStream(sc.getInputStream());
            oos=new ObjectOutputStream(sc.getOutputStream());
            //clubName=username.getText().trim();
            oos.writeObject(username.getText().trim());
            oos.writeObject(password.getText().trim());
            String s1=null;
            try {
                s1=(String)ois.readObject();
                System.out.println(s1);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if(s1.equals("Correct")){
                Parent root=null;
                ServerConnection sConnection=ServerConnection.getInstance();
                sConnection.setStreams(oos,ois);
                try {
                    FXMLLoader loader=new FXMLLoader(getClass().getResource("enterPage1.fxml"));
                    root= loader.load();
                    EnterPage1 controller = loader.getController();
                    controller.setClubName(username.getText().trim());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Scene scene=new Scene(root);

                Stage stage=(Stage)logIn.getScene().getWindow();



                //loginBtn.getScene().getWindow().hide();

                stage.setScene(scene);
            }else{
                Stage stage=(Stage)logIn.getScene().getWindow();
                Alert.AlertType type=Alert.AlertType.ERROR;
                Alert alert=new Alert(type,"");
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.initOwner(stage);
                alert.getDialogPane().setContentText("WRONG PASSWORD");
                alert.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObjectInputStream getOStream(){
        return ois;
    }

    public ObjectOutputStream getIStream(){
        return oos;
    }

}


