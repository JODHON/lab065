package presentation;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JColorChooser;

import javax.swing.JFileChooser;
import java.io.File;


import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

public class HungrySnakeGUI extends JFrame {
    
    public static final int SIDE = 25;
    private static final int SIZE = 25;
    private static final int EMPTY = 0;
    private static final int WALL = 1;
    private static final int SNAKE = 2;
    private static final int APPLE = 3;

    private Color APPLE_COLOR = new Color(200, 50, 40);
    private JMenuItem itemColorComida;
    private static final Color BACKGROUND = new Color(170, 200, 150);
    private static final Color DARK = new Color(35, 45, 30);
    private static final Color WALLCOLOR= new Color(48, 82, 43);

    private JButton btnCerrar;
    private JMenuItem itemNuevo;
    private JMenuItem itemAbrir;
    private JMenuItem itemSalvar;
    private JMenuItem itemSalir;
    private JPanel boardPanel;
    private JLabel[][] cells;
    
    
    private int[][] board = createDefaultBoard();

    private HungrySnakeGUI() {
        prepareView();
        prepareController();
        refresh();
        
    }

    private void prepareView() {
        setTitle("HungrySnake");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(dim.width / 2, dim.height / 2);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        btnCerrar=new JButton("Cerrar");
        add(btnCerrar,BorderLayout.SOUTH);
        prepareViewMenu();
        
        prepareViewBoard();
    }
    
    private void prepareViewBoard()
    {
        boardPanel= new JPanel(new GridLayout(SIZE,SIZE));
        boardPanel.setPreferredSize(new Dimension(SIZE*SIDE,SIZE*SIDE));
        cells=new JLabel[SIZE][SIZE];
        
        
        for (int f=0;f<SIZE;f++){
            for (int c=0;c<SIZE;c++){
                cells[f][c]= new JLabel();
                cells[f][c].setOpaque(true);
                boardPanel.add(cells[f][c]);
            }
        }
        
        
        JPanel container =new JPanel(new GridBagLayout());
        container.setBackground(BACKGROUND);
        container.add(boardPanel);
        
        add(container,BorderLayout.CENTER);
        
    }
    
    
    public void refresh()
    {
        for (int f=0;f<SIZE;f++){
        
            for (int c=0;c<SIZE;c++){
                JLabel cell=cells[f][c];
                switch (board[f][c]){
                    case WALL:
                    case SNAKE:
                        cell.setBackground(DARK);
                        cell.setBorder(null);
                        break;
                    case APPLE:
                        cell.setBackground(DARK);
                        cell.setBorder(BorderFactory.createLineBorder(APPLE_COLOR,4));
                        break;
                    default:
                        cell.setBackground(BACKGROUND);
                        cell.setBorder(null);
                }
            }
        }
        
        boardPanel.repaint();
    }
    
    private static int[][] createDefaultBoard(){
        int[][] b =new int[SIZE][SIZE];
        addWall(b, 5, 6, 2, true);
        for (int i=0;i<SIZE;i++){
            b[0][i]=WALL;
            b[SIZE-1][i]=WALL;
            b[i][0]=WALL;
            b[i][SIZE-1]=WALL;
        }
        
        int mid=SIZE/2;
        for (int c=5;c<=8;c++){
            b[mid][c]=SNAKE;
        }
        
        b[mid][SIZE-7]=APPLE;
        return b;
    }
    
    private static void addWall(int[][] b,int row,int col,int length,boolean horizontal) {
        for (int i=0;i<length;i++) {
            if (horizontal) {
                b[row][col+i] =WALL;
            } else {
                b[row+i][col] =WALL;
            }
        }
    }

    private void prepareViewMenu() {
        JMenuBar menuBar=new JMenuBar();
        JMenu menuArchivo=new JMenu("Menu");

        itemNuevo= new JMenuItem("Nuevo");
        itemAbrir= new JMenuItem("Abrir");
        itemSalvar= new JMenuItem("Salvar");
        itemSalir=new JMenuItem("Salir");

        menuArchivo.add(itemNuevo);
        menuArchivo.add(itemAbrir);
        menuArchivo.add(itemSalvar);
        menuArchivo.addSeparator();
        menuArchivo.add(itemSalir);

        menuBar.add(menuArchivo);
        JMenu menuOpciones = new JMenu("Opciones");
        itemColorComida = new JMenuItem("Color de la comida");
        menuOpciones.add(itemColorComida);
        menuBar.add(menuOpciones);
        setJMenuBar(menuBar);
    }

    private void prepareController() {
        prepareControllerMenu();
        prepareControllerClose();
    }

    private void prepareControllerMenu() {
        itemNuevo.addActionListener(e -> showMessage("Opción Nuevo en construcción"));
        itemAbrir.addActionListener(e -> openFile());
        itemSalvar.addActionListener(e -> saveFile());
        itemColorComida.addActionListener(e -> changeFoodColor());
        
        itemSalir.addActionListener(e -> confirmClose());
        btnCerrar.addActionListener(e -> confirmClose());
    }
    
    private void openFile() {
        JFileChooser chooser=new JFileChooser();
        if (chooser.showOpenDialog(this)==JFileChooser.APPROVE_OPTION) {
            File file =chooser.getSelectedFile();
            showMessage("La función Abrir está en construcción.\nArchivo seleccionado: "+file.getName());
        }
    }

    private void saveFile() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showSaveDialog(this)==JFileChooser.APPROVE_OPTION) {
            File file =chooser.getSelectedFile();
            showMessage("La función Salvar está en construcción.\nArchivo seleccionado: "+file.getName());
        }
    }

    private void prepareControllerClose() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                confirmClose();
            }
        });
    }
    
    private void changeFoodColor() {
        Color color = JColorChooser.showDialog(this, "Color de la comida", APPLE_COLOR);
        if (color != null) {
            APPLE_COLOR = color;
            refresh();
        }
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message,"HungrySnake",JOptionPane.INFORMATION_MESSAGE);
    }

    private void confirmClose() {
        int option = JOptionPane.showConfirmDialog(
            this,
            "¿Está seguro de que desea salir?",
            "Confirmar cierre",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
        if (option ==JOptionPane.YES_OPTION) {
            dispose();
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        HungrySnakeGUI hs = new HungrySnakeGUI();
        hs.setVisible(true);
    }
}