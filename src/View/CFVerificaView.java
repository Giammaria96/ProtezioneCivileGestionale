package View;

import javax.swing.*;

public class CFVerificaView {

    private JPanel Intermedio0;
    private JButton paginaLoginButton;
    private JButton okButton;
    private JTextField textField1;

    public CFVerificaView() {

        return;

    }

    public void ErrorMessage(BasicFrameView frame,String nomeErrore){
        JOptionPane.showMessageDialog(frame, nomeErrore, "Warning!", JOptionPane.ERROR_MESSAGE);
    }

    public JPanel getIntermedio0() {

        return Intermedio0;

    }

    public String getText(){

        return textField1.getText();

    }

    public JButton getOkButton() {

        return okButton;

    }

    public JButton getpaginaLoginButton() {

        return paginaLoginButton;

    }
}
