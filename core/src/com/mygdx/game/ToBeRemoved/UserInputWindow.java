package com.mygdx.game.ToBeRemoved;

import com.badlogic.gdx.Gdx;

import javax.swing.*;
import java.awt.*;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInputWindow
{
    private final int userInputSizeX = 400;

    private final int userInputSizeY = 150;

    public UserInputWindow(String title)
    {
        JFrame frame = new JFrame("Name Project...");
        frame.setBounds(Gdx.graphics.getDisplayMode().width/2 -userInputSizeX/2,Gdx.graphics.getDisplayMode().height/2 -userInputSizeY/2,userInputSizeX,userInputSizeY);
        frame.getContentPane().setLayout(null);

        frame.getContentPane().setBackground(new Color(70,70,70));
        frame.setVisible(true);

        TextField textField = new TextField();

        frame.add(textField);
        textField.setBackground(Color.white);
        textField.setBounds(150,20,100,30);




        java.awt.Button buttonSubmit = new Button("Submit");
        buttonSubmit.setBackground(Color.white);
        buttonSubmit.setBounds(150,60,100,30);

        buttonSubmit.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

            }
        });
        frame.getContentPane().add(buttonSubmit);
    }

    public void close()
    {

    }
}
