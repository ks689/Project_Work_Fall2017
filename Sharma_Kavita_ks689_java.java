package wavefrontProject;

import java.awt.EventQueue;

import java.io.BufferedReader;

import java.io.BufferedWriter;

import java.io.FileNotFoundException;

import java.io.FileReader;

import java.io.FileWriter;

import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFileChooser;
import java.io.File; 


import javax.swing.JFrame;

import javax.swing.JLabel;

import javax.swing.JTextField;

import javax.swing.JButton;

import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;


//Class is to be named the same as java file.
public class wavefrontProject {



	private JFrame frame;

	private static JTextField enterFactor;

	

	/**

	 * Launch the application.

	 */
//Main Class
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			public void run() {

				try {

					wavefrontProject model = new wavefrontProject();

					model.frame.setVisible(true);					

					

				} catch (Exception e) {

					e.printStackTrace();

				}

			}

		});

	}



	/**

	 * Create the application.

	 */

	public wavefrontProject() {

		initialize();

	}



	/**

	 * Initialize the contents of the frame.

	 */

	//Model Operation are carried out in this section
	private void initialize() {

		frame = new JFrame();

		frame.setBounds(200, 200, 450, 300);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.getContentPane().setLayout(null);

		

		JLabel lblWaveObj = new JLabel("This will scale your wavefront.obj");

		lblWaveObj.setBounds(130, 16, 192, 16);

		frame.getContentPane().add(lblWaveObj);

		

		JLabel lblScalingFactor = new JLabel("Enter a factor :");

		lblScalingFactor.setBounds(100, 60, 100, 16);

		frame.getContentPane().add(lblScalingFactor);

		

		enterFactor = new JTextField();

		enterFactor.setBounds(218, 55, 130, 26);

		frame.getContentPane().add(enterFactor);

		enterFactor.setColumns(10);

		

		

		JButton btnCalculate = new JButton("Calculate");

		btnCalculate.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				

				JLabel lblFinal = new JLabel("SCALED. Check the output file");

				lblFinal.setEnabled(false);

				lblFinal.setBounds(100, 100, 438, 16);

				frame.getContentPane().add(lblFinal);

				

				String currentLine[];

			    String scale = enterFactor.getText();

			    float factor = Float.parseFloat(scale);

			    float x, y, z;

			   
				BufferedReader s;
				Scanner scanner;
				try {
					
/*The file name should be specified accordingly and the location of the 
 * file should be same as the java workspace*/
					
					s = new BufferedReader(new FileReader("octa.obj"));
					scanner = new Scanner(s);
		

	            StringBuilder sb = new StringBuilder();

	            String line;

				

					line = s.readLine();

	            

	            while (line != null) {
	            	

	                if (line.startsWith("v ")) {

	                    currentLine = line.split("  ");

	                    x = Float.parseFloat(currentLine[1]);

	                    y = Float.parseFloat(currentLine[2]);

	                    z = Float.parseFloat(currentLine[3]);
	                    
                     /*Scaling the file by input given by user. 
                      * The file will not be scaled if the user inputs 0 as factor*/
	                    if(factor!=0){
	                    x = x * factor;

	                    y = y * factor;

	                    z = z * factor;

	                    line = "v " + x + " " + y + " " + z;
	                    }

	                }

	                sb.append(line);

	                sb.append(System.lineSeparator());

	                line = s.readLine();
	                
	            

	            }

	            String printAll = sb.toString();

	            
/*Enter the output file name with .obj or .obj.txt extn.
 * .obj.txt extn will enable the file to be viewed in formattable text file*/
	            
	            BufferedWriter writeFile = new BufferedWriter(new FileWriter("output.obj")); 

	            writeFile.write(printAll);

	            writeFile.close();

	            s.close();

	            

	            lblFinal.setEnabled(true);

	     

				} catch (FileNotFoundException e1) {

					// TODO Auto-generated catch block

					e1.printStackTrace();

				} catch (IOException e1) {

					// TODO Auto-generated catch block

					e1.printStackTrace();

				}



			}

		});

		btnCalculate.setBounds(171, 172, 117, 29);

		frame.getContentPane().add(btnCalculate);

	}

}

