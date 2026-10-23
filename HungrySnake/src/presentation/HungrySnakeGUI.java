package presentation; 
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;
import javafx.scene.control.Button;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;


/**
 * Write a description of class HungrySnakeGUI here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class HungrySnakeGUI extends JFrame
{
    public static final int SIDE=20;
    private JButton btnCerrar;


    private HungrySnakeGUI() {

        prepareView();
        prepareControler();

    }
    
    private void prepareView() {
        setTitle("HungrySnake");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (dim.width)/2;
        int height = (dim.height)/2;
        setSize(width,height);
        

    }
    
    private void close(JFrame programa){
        
        programa.setVisible(false);
        programa.dispose();
        System.exit(0); 
        
    }
    
    private void prepareControler(){
        btnCerrar = new JButton("Cerrar juego");
        this.add(btnCerrar);
        btnCerrar.addActionListener(new ActionListener() {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            
            int respuesta = JOptionPane.showConfirmDialog(null,"¿Deseas ejecutar esta acción?","Confirmar acción",
                JOptionPane.YES_NO_OPTION);
                
            if(respuesta == JOptionPane.YES_OPTION){
                close(thisInstance());
            }
            
        }
    });
    }
    
    
    private JFrame thisInstance() {
    return this;
    }
    

    public static void main(String [] args){
        HungrySnakeGUI hs= new HungrySnakeGUI();
        hs.setVisible(true);
    }
    
    
}