package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import assets.Recursos;
import logic.GameEngine;
import logic.GameLoop;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class VPrincipal extends JFrame implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public Recursos r;
	private GameEngine game;
	private MiPanel mipanel;
	public static JLabel lblInfo;
	public int puerto;
	private Thread hilo;
	public static String host;
	private Socket clienteSocket;
	public static JLabel lblpowerup;
	public JLabel numplayer;
	public int njugador;
	private boolean fin=false;
	private BufferedReader br;
	private JButton btnjoin;
	private ArrayList<String> list = new ArrayList<String> (Arrays.asList("Cyborg","Robot","Fuego","Pez","Esqueleto","Bicho"));

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					VPrincipal frame = new VPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VPrincipal() {
		Recursos.loadAssets();
		setIconImage(Recursos.icono);
		setTitle("Pocket Morty");
		Recursos.minicio.loop();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(13, 5, 1340, 720);//para controlar la imagen
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		mipanel=new MiPanel();//panel con el fondo modificado
		mipanel.setBounds(0, 0, 1334, 650);
		contentPane.add(mipanel);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 650, 1334, 41);
		panel.setBackground(Color.BLACK);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblInfo = new JLabel();
		lblInfo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblInfo.setForeground(Color.RED);
		lblInfo.setBounds(20, 5, 490, 31);
		panel.add(lblInfo);
				
		game = new GameEngine(this);
		contentPane.add(game);
		
		btnjoin = new JButton();
		btnjoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				join();
				
			}
		});
		
		btnjoin.setBackground(Color.BLACK);
		btnjoin.setIcon(new ImageIcon(Recursos.connect));
		btnjoin.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnjoin.setBounds(600, 0, 150, 40);
		btnjoin.setBorderPainted(false);
		panel.add(btnjoin);
		
		lblpowerup = new JLabel();
		lblpowerup.setIcon(new ImageIcon(Recursos.powerup));
		lblpowerup.setBounds(1270, 1, 30, 37);
		panel.add(lblpowerup);
		
		numplayer = new JLabel();
		numplayer.setFont(new Font("Tahoma", Font.BOLD, 18));
		numplayer.setForeground(Color.RED);
		numplayer.setBounds(850, 5, 280, 31);
		panel.add(numplayer);
	}
	
	protected void join() {
		abrirdialog();
		btnjoin.setEnabled(false);
		btnjoin.setVisible(false);
		
		game.requestFocus();
		
		try {
			clienteSocket = new Socket(host, puerto);
        } catch (UnknownHostException ex) {
            System.out.println("No se ha podido conectar con el servidor (" + ex.getMessage() + ").");
        } catch (IOException ex) {
        	System.out.println("No se ha podido conectar con el servidor (" + ex.getMessage() + ").");
        }
		
	    // Creamos los streams de entrada/salida
		try {
			br = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Imposible crear canal de comunicación....");
			System.exit(0);
		}
		
		// Lanzamos el hilo que se va a encargar de escuchar todo lo que el server nos envie
		hilo = new Thread(this);
		hilo.start();
		
	}
	
	private void abrirdialog() {
		DialogoInfo dialogo= new DialogoInfo(this, true);
		dialogo.setLocationRelativeTo(this);
		dialogo.setVisible(true);
	}
	
	public void config(String h, int p) {
		host = h;
	    puerto = p;
	}
	
	@Override
	public void run() {
		while (!fin) {
			try {
				String cadena = br.readLine();
				if (cadena!=null) {
					if (cadena.contains("Eres")){
						String[] split = cadena.split(":");
						njugador = Integer.parseInt(split[1]);
						numplayer.setText("Eres el personaje: "+njugador+" "+list.get(njugador-1));
						game.njugador=njugador;
					} else if (cadena.contains("Esperando")||cadena.contains("Comenzamos") ) {
						lblInfo.setText(cadena);
						
					}else if(cadena.contains("Capturados")) {//comenzamos el juego
						lblpowerup.setVisible(false);
						Recursos.minicio.stop();
						Recursos.mjuego.loop();
						new GameLoop(game).start();
						lblInfo.setText(cadena);
						fin = true;
					}
				}
			}catch (SocketException e) {
                System.out.println("Cliente cerrado.");
                fin = true;
            }catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
