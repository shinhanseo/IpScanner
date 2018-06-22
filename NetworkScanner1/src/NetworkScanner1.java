import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;


public class NetworkScanner1 extends JFrame {
	private JPanel jp1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel jp2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel jp3 = new JPanel(new FlowLayout());
	private JPanel jp4 = new JPanel(new GridLayout(0,1));
	private JPanel jp5 = new JPanel(new GridLayout(0,1));
	private JPanel statusPanel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private	JLabel l1 = new JLabel("  IP Range:");
	private	JLabel l2 = new JLabel("to");
	private	JLabel l3 = new JLabel("Hostname:");
	private	JComboBox jc1 = new JComboBox();
	private	JComboBox jc2 = new JComboBox();
	private	JButton jb1 = new JButton("↑ IP");
	private	JButton jb2 = new JButton("→ Start");
	private JTextField t1 = new JTextField(10);
	private JTextField t2 = new JTextField(10);
	public static JTextField t3 = new JTextField(10);
	static String titles[] = new String[] {
			"IP","Ping","TTL","Hostname","Port"
	};
	public static DefaultTableModel null1= new DefaultTableModel(null,titles);
	public static JTable jTable = new JTable(null1); 
	public static JScrollPane scroll = new JScrollPane(jTable);
	static NetworkScanner1 nt;
	String fixedIp;
	static int Threadcount = 0;
	public static MakeJTable mt;
	JDialog di = new JDialog(this,"imformation");
	
	public NetworkScanner1() {
		super("NetworkScanner");
		Font myFont = new Font("Serif",Font.BOLD,16);
		JMenuBar menubar = new JMenuBar();
		
		setJMenuBar(menubar);
		
		JMenu scanMenu = new JMenu("Scan");
		scanMenu.setMnemonic('S');
		JMenu gotoMenu = new JMenu("Go to");
		gotoMenu.setMnemonic('G');
		JMenu commandsMenu = new JMenu("Commands");
		commandsMenu.setMnemonic('C');
		JMenu favoritesMenu = new JMenu("Favorites");
		favoritesMenu.setMnemonic('F');
		JMenu toolsMenu = new JMenu("Tools");
		toolsMenu.setMnemonic('T');
		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic('H');
		
		menubar.add(scanMenu);
		menubar.add(gotoMenu);
		menubar.add(commandsMenu);
		menubar.add(favoritesMenu);
		menubar.add(toolsMenu);
		menubar.add(helpMenu);
		
		JMenuItem scan1 = new JMenuItem("Load from file...");
		JMenuItem scan2 = new JMenuItem("Export all.."+"       "+"Ctrl+S");
		JMenuItem scan3 = new JMenuItem("Export selection...");
		JMenuItem scan4 = new JMenuItem("Quit"+"                   "+"Ctrl+Q");
		
		JMenuItem goto1 = new JMenuItem("Next alive host"+"                           "+"Ctrl+H");
		JMenuItem goto2 = new JMenuItem("Next open port"+"                            "+"Ctrl+J");
		JMenuItem goto3 = new JMenuItem("Next dead host"+"                            "+"Ctrl+K");
		JMenuItem goto4 = new JMenuItem("Previous alive host"+"          "+"Ctrl+Shift+H");
		JMenuItem goto5 = new JMenuItem("Previous open port"+"          "+"Ctrl+Shift+J");
		JMenuItem goto6 = new JMenuItem("Previous dead host"+"          "+"Ctrl+Shift+K");
		JMenuItem goto7 = new JMenuItem("Find.."+"                                              "+"Ctrl+F");
		
		JMenuItem commands1 = new JMenuItem("show details");
		JMenuItem commands2 = new JMenuItem("Rescan IP(s)"+"             "+"Ctrl+R");
		JMenuItem commands3 = new JMenuItem("Delete IP(s)"+"                   "+"DEL");
		JMenuItem commands4 = new JMenuItem("Copy IP"+"                       "+"Ctrl+C");
		JMenuItem commands5 = new JMenuItem("Copy details");
		JMenu commands6 = new JMenu("Open");
		
		JMenuItem openin1 = new JMenuItem("Edit openers...");
		JMenuItem openin2 = new JMenuItem("Windows Shares"+"       "+"Ctrl+1");
		JMenuItem openin3 = new JMenuItem("Web Browser"+"             "+"Ctrl+2");
		JMenuItem openin4 = new JMenuItem("FTP"+"                               "+"Ctrl+3");
		JMenuItem openin5 = new JMenuItem("Telnet"+"                          "+"Ctrl+4");
		JMenuItem openin6 = new JMenuItem("Ping"+"                             "+"Ctrl+5");
		JMenuItem openin7 = new JMenuItem("Trace route"+"                "+"Ctrl+6");
		JMenuItem openin8 = new JMenuItem("Geo locate"+"                  "+"Ctrl+7");
		JMenuItem openin9 = new JMenuItem("E-mail sample"+"            "+"Ctrl+8");
		
		JMenuItem favorites1 = new JMenuItem("Add current..."+"            "+"Ctrl+D");
		JMenuItem favorites2 = new JMenuItem("Manage favorites...");
		
		JMenuItem tools1 = new JMenuItem("Preferences..."+"           "+"Ctrl+Shift+P");
		JMenuItem tools2 = new JMenuItem("Fetchers..."+"                  "+"Ctrl+Shift+O");
		JMenuItem tools3 = new JMenuItem("Selection");
		JMenuItem tools4 = new JMenuItem("Scan statistics"+"                     "+"Ctrl+T");
		
		JMenuItem help1 = new JMenuItem("Getting Started"+"                  "+"F1");
		JMenuItem help2 = new JMenuItem("Official Website");
		JMenuItem help3 = new JMenuItem("FAQ");
		JMenuItem help4 = new JMenuItem("Report an issue");
		JMenuItem help5 = new JMenuItem("Plugins");
		JMenuItem help6 = new JMenuItem("Command-line usage");
		JMenuItem help7 = new JMenuItem("Check for newer version...");
		JMenuItem help8 = new JMenuItem("About");
		
		scanMenu.add(scan1);
		scanMenu.add(scan2);
		scanMenu.add(scan3);
		scanMenu.addSeparator();
		scanMenu.add(scan4);
		
		gotoMenu.add(goto1);
		gotoMenu.add(goto2);
		gotoMenu.add(goto3);
		gotoMenu.addSeparator();
		gotoMenu.add(goto4);
		gotoMenu.add(goto5);
		gotoMenu.add(goto6);
		gotoMenu.addSeparator();
		gotoMenu.add(goto7);
		
		commandsMenu.add(commands1);
		commandsMenu.addSeparator();
		commandsMenu.add(commands2);
		commandsMenu.add(commands3);
		commandsMenu.addSeparator();
		commandsMenu.add(commands4);
		commandsMenu.add(commands5);
		commandsMenu.addSeparator();
		commandsMenu.add(commands6);
		
		favoritesMenu.add(favorites1);
		favoritesMenu.add(favorites2);
		favoritesMenu.addSeparator();
		
		toolsMenu.add(tools1);
		toolsMenu.add(tools2);
		toolsMenu.addSeparator();
		toolsMenu.add(tools3);
		toolsMenu.add(tools4);
		
		helpMenu.add(help1);
		helpMenu.addSeparator();
		helpMenu.add(help2);
		helpMenu.add(help3);
		helpMenu.add(help4);
		helpMenu.add(help5);
		helpMenu.addSeparator();
		helpMenu.add(help6);
		helpMenu.addSeparator();
		helpMenu.add(help7);
		helpMenu.addSeparator();
		helpMenu.add(help8);	
		
		commands6.add(openin1);
		commands6.addSeparator();
		commands6.add(openin2);
		commands6.add(openin3);
		commands6.add(openin4);
		commands6.add(openin5);
		commands6.add(openin6);
		commands6.add(openin7);
		commands6.add(openin8);
		commands6.add(openin9);
		// MakeJTable();
		statusPanel1.setBorder(new BevelBorder(BevelBorder.LOWERED));
		add(scroll,BorderLayout.CENTER);
		add(jp4,BorderLayout.NORTH);	
		add(jp5,BorderLayout.SOUTH);
		jp4.add(jp1);
		jp4.add(jp2);
		jp5.add(statusPanel1);
		statusPanel1.setLayout(new BoxLayout(statusPanel1,BoxLayout.X_AXIS));
		JLabel statusLabel1 = new JLabel("Ready");
		//statusLabel1.setHorizontalAlignment(SwingConstants.LEFT);
		statusPanel1.add(statusLabel1);
		statusLabel1.setBorder(new BevelBorder(BevelBorder.RAISED));
		statusLabel1.setPreferredSize(new Dimension(280, 22));
		JLabel statusLabel2 = new JLabel("Display All");
		//statusLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		statusPanel1.add(statusLabel2);
		statusLabel2.setBorder(new BevelBorder(BevelBorder.RAISED));
		statusLabel2.setPreferredSize(new Dimension(130, 22));
		JLabel statusLabel3 = new JLabel("Threads: 0");
		//statusLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
		statusPanel1.add(statusLabel3);
		statusLabel3.setBorder(new BevelBorder(BevelBorder.RAISED));
		statusLabel3.setPreferredSize(new Dimension(130, 22));
		statusLabel1.setFont(myFont);
		statusLabel2.setFont(myFont);
		statusLabel3.setFont(myFont);
		scan4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		commands1.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {			
				JDialog di1 = new JDialog();
				JLabel ip = new JLabel("IP");
				di1.add(ip);
				di1.setSize(250,250);
				di1.setVisible(true);
				di1.setLocationRelativeTo(null);
			}
		});
		tools4.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				JLabel Scanning = new JLabel("Scanning Completed!");				
				di.add(Scanning);
				di.setSize(250,250);
				di.setVisible(true);
				di.setLocationRelativeTo(null);
			}
		});
		
		commands2.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "rescan start");
				startThread();
			}
		});
		
		jTable.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				commands3.addActionListener(new ActionListener() {				
					@Override
					public void actionPerformed(ActionEvent me) {
						//null1.removeRow(e.getY());
					}
				});	
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {	
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
			
			}
		});
		
		jp1.add(l1);
		jp1.add(t1);
		jp1.add(l2);
		jp1.add(t2);
		jc1.addItem("IP Range");
		jc1.addItem("Random");
		jc1.addItem("Text file");
		jp1.add(jc1);
		jp2.add(l3);
		jp2.add(t3);
		jp2.add(jb1);
		jc2.addItem("Netmask");
		jc2.addItem("/24");
		jc2.addItem("/26");
		jp2.add(jc2);
		jp2.add(jb2);
		
		String myIp = null;
		String myHostname = null;
		try {
			InetAddress ia = InetAddress.getLocalHost();
			
			 myHostname = ia.getHostName();
			 myIp = ia.getHostAddress();
		}catch(Exception e) {
			e.printStackTrace();
		}
			fixedIp = myIp.substring(0, myIp.lastIndexOf(".")+1);
			t1.setText(fixedIp+"0");
			t2.setText(fixedIp+"255");
			t3.setText(myHostname);
		
		
		setSize(500,580);
		
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		for (int i = 0; i < jTable.getColumnCount(); i++) {
			jTable.getColumnModel().getColumn(i).setResizable(false);
			jTable.getTableHeader().setReorderingAllowed(false);
		}
		section();
		
	}
	
	public static void main(String[] args) {
		nt =new NetworkScanner1();
	}
	
	void section(){
			jb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(jb2.toString().contains("→ Start")) {
					
					nt.startThread();
					jb2.setText("■ Stop");					
				}
				else { 
				
					//CleanJTable();
					jb2.setText("→ Start");
				}
					
			}
		});
	}
	void startThread() {
		null1.setRowCount(0);
		Object data[] = new Object[5];
		for (int i = 0; i < 255; i++) {
			data[0] = fixedIp + i;
			//JOptionPane.showMessageDialog(null, fixedIp);
			NetworkScanner1.null1.addRow(data);
		}
		
		for (int i = 0; i < 255; i++) {
			Threadcount++;
			mt = new MakeJTable(i, fixedIp + i,jb2,Threadcount);
			mt.start();	
		}
	}
	void CleanJTable() {
		
		try {
			mt.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mt.interrupt();
		
		DefaultTableModel null2= new DefaultTableModel(null,titles);
		JTable jTable1 = new JTable(null2); 
		add(jTable1,BorderLayout.CENTER);
		jTable.remove(jTable);
		
		
	}
 } 

class MakeJTable extends Thread{
	String ip;
	int data;
	int Threadcount;
	JButton jb2;
	String getPort;
	MakeJTable(int data , String ip, JButton jb2 ,int Threadcount) {
		this.data = data;
		this.ip = ip;
		this.jb2 = jb2;
		this.Threadcount =  Threadcount;
	}

	
	
	public void run() {

			Object data[] = new String[5];
			String pingCmd = "ping -a " + ip;
			try {
				
				InetAddress address = InetAddress.getByName(ip);
				if(address.isReachable(200)){ 
				Runtime r = Runtime.getRuntime();
				Process p = r.exec(pingCmd);
				BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
				String inputLine;
				while ((inputLine = in.readLine()) != null) {
					
					Pattern pattern = Pattern.compile("(\\d+ms)\\s+T", Pattern.CASE_INSENSITIVE);
					Matcher matcher = pattern.matcher(inputLine);
					if (matcher.find()) 
						data[1] = matcher.group(1);	
					
					pattern = Pattern.compile("TTL=(\\d+)",Pattern.CASE_INSENSITIVE);
					matcher = pattern.matcher(inputLine);
					if (matcher.find()) 
						data[2] = matcher.group(1);
					
					Pattern pattern1 = Pattern.compile("Ping\\s+(\\S+)\\s+",Pattern.CASE_INSENSITIVE);
					//Pattern.compile("Ping\\s+(\\w+)",Pattern.CASE_INSENSITIVE);
					Matcher matcher1 = pattern1.matcher(inputLine);
					if(matcher1.find()) {
						data[3] = matcher1.group(1);
						if( data[3].toString().contains(ip)){
							data[3] = "[n/a]";
		 
						// ip랑 동일하면 [n/a]로 처리
						}
					}
					NetworkScanner1.null1.setValueAt(data[1],this.data,1);
					NetworkScanner1.null1.setValueAt(data[2],this.data,2);
					NetworkScanner1.null1.setValueAt(data[3],this.data,3);
					//Port
					final ExecutorService es = Executors.newFixedThreadPool(20);
					final int timeout = 200;
					final List<Future<ScanResult>> futures = new ArrayList<>();
					for (int port = 1; port <=1024; port++) {
					        // for (int port = 1; port <= 80; port++) {
					        futures.add(portIsOpen(es, ip, port, timeout));
					    }
					int openPorts = 0;
					getPort = "";
					es.awaitTermination(200L, TimeUnit.MILLISECONDS);
				
					for (final Future<ScanResult> f : futures) {
				        if (f.get().isOpen()) {
				        	openPorts++;
				        	if(getPort.equals("")) {
				        		getPort = Integer.toString(f.get().getPort());
				        	}else {
				        		getPort += ", " + Integer.toString(f.get().getPort());
				        	}
				        	//data[4]=f.get().getPort();
				        	NetworkScanner1.null1.setValueAt(getPort,this.data,4);
				        
				        	//열려있는 port가 2개 이상일때 테이블에서 포트들이 번갈아가면서 테이블에 나옴
				        	//JOptionPane.showMessageDialog(null,data[4]);
				        }
				       
					}				
					if(openPorts == 0)
				        	NetworkScanner1.null1.setValueAt("[n/a]",this.data,4);
					//end
					
					
				}
				in.close();
				}
				else{
					data[1] = "[n/a]";
					data[2] = "[n/s]";
					data[3] = "[n/s]";
					data[4] = "[n/s]";	
					NetworkScanner1.null1.setValueAt(data[1],this.data,1);
					NetworkScanner1.null1.setValueAt(data[2],this.data,2);
					NetworkScanner1.null1.setValueAt(data[3],this.data,3);
					NetworkScanner1.null1.setValueAt(data[4],this.data,4);
					
				}
				
				}catch (Exception e) {
					e.printStackTrace();
				}
				finally {
					NetworkScanner1.Threadcount--;
					if(NetworkScanner1.Threadcount==0){
						JOptionPane.showMessageDialog(null, "Scanning completed");
						jb2.setText("→ Start");
					}
				
				}
	}	
	
	public static class ScanResult {
	    private int port;
	    
	    private boolean isOpen;
	    
	    public ScanResult(int port, boolean isOpen) {
	        super();
	        this.port = port;
	        this.isOpen = isOpen;
	    }

	    public int getPort() {
	        return port;
	    }

	    public void setPort(int port) {
	        this.port = port;
	    }

	    public boolean isOpen() {
	        return isOpen;
	    }

	    public void setOpen(boolean isOpen) {
	        this.isOpen = isOpen;
	    }

	}
	public static Future<ScanResult> portIsOpen(final ExecutorService es, final String ip, final int port,
	        final int timeout) {
	    return es.submit(new Callable<ScanResult>() {
	        @Override
	        public ScanResult call() {
	            try {
	                Socket socket = new Socket();
	                socket.connect(new InetSocketAddress(ip, port), timeout);
	                socket.close();
	                return new ScanResult(port, true);
	            } catch (Exception ex) {
	                return new ScanResult(port, false);
	            }
	        }
	    });
	}
}


