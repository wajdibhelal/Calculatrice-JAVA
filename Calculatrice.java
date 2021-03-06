import java.awt.* ;
import javax.swing.* ;


public class Calculatrice extends JFrame {

        private JPanel container = new JPanel();
        
        String[] tab_string = {"1", "2", "3", "4", "5", "6", "7", "8", "9", ".", "0", "=", "C", "+", "-", "*", "/"};
        JButton[] tab_button = new JButton[tab_string.length];
        private JLabel ecran = new JLabel();
        private Dimension dim = new Dimension(50, 40);
        private Dimension dim2 = new Dimension(50, 31);
        private double chiffre1;
        private boolean clicOperateur = false, update = false;
        private String operateur = "";
        private JLabel lab = new JLabel();
        public Calculatrice(){
                
                this.setSize(350, 300); 
                this.setTitle("Calculatrice"); 
                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.setLocationRelativeTo(null);
                this.setResizable(false);
                initComposant();
                lab = new JLabel("Calculatrice for dev");
               container.add(lab, BorderLayout.PAGE_END);
                this.setContentPane(container);
                this.setVisible(true);
        }
        
        private void initComposant(){

                Font police = new Font("Calibri", Font.BOLD, 15);
                ecran = new JLabel("0");
                ecran.setFont(police);
                ecran.setHorizontalAlignment(JLabel.CENTER);
                ecran.setPreferredSize(new Dimension(220, 20));
               
                JPanel operateur = new JPanel();        
                operateur.setPreferredSize(new Dimension(55, 225));
                JPanel chiffre = new JPanel();
                chiffre.setPreferredSize(new Dimension(165, 225));
                JPanel panEcran = new JPanel();
                panEcran.setPreferredSize(new Dimension(220, 30));

                
                for(int i = 0; i < tab_string.length; i++)
                {
                    tab_button[i] = new JButton(tab_string[i]);
                    tab_button[i].setPreferredSize(dim);
                    
                    switch(i){
                    
                        case 11 :
                            tab_button[i].setForeground(Color.red);
                            tab_button[i].addActionListener(new EgalListener());
                            chiffre.add(tab_button[i]);
                            break;
                        
                        case 12 :
                            tab_button[i].setForeground(Color.blue);
                            tab_button[i].addActionListener(new ResetListener());
                            tab_button[i].setPreferredSize(dim2);
                            operateur.add(tab_button[i]);
                            break;
                            
                        case 13 :
                            tab_button[i].setForeground(Color.black);
                            tab_button[i].addActionListener(new PlusListener());
                            tab_button[i].setPreferredSize(dim2);
                            operateur.add(tab_button[i]);
                            break;
                        
                        case 14 :
                            tab_button[i].setForeground(Color.black);
                            tab_button[i].addActionListener(new MoinsListener());
                            tab_button[i].setPreferredSize(dim2);
                            operateur.add(tab_button[i]);
                            break;    
                        
                        case 15 :    
                            tab_button[i].setForeground(Color.black);
                            tab_button[i].addActionListener(new MultiListener());
                            tab_button[i].setPreferredSize(dim2);
                            operateur.add(tab_button[i]);
                            break;
                        
                        case 16 :
                            tab_button[i].setForeground(Color.black);
                            tab_button[i].addActionListener(new DivListener());
                            tab_button[i].setPreferredSize(dim2);
                            operateur.add(tab_button[i]);
                            break;
                            
                        case 17 :
                            tab_button[i].setForeground(Color.black);
                            tab_button[i].addActionListener(new DivListener());
                            tab_button[i].setPreferredSize(dim2);
                            operateur.add(tab_button[i]);
                            break;    
                                                
                        default :
                            tab_button[i].addActionListener(new ChiffreListener());
                            chiffre.add(tab_button[i]);
                            break;
                    }
                }
                
                panEcran.add(ecran);
                panEcran.setBorder(BorderFactory.createLineBorder(Color.black));
 
                container.add(panEcran, BorderLayout.NORTH);
                container.add(chiffre, BorderLayout.CENTER);
                container.add(operateur, BorderLayout.EAST);
        }
        
        
        private void calcul(){
                if(operateur.equals("+"))
                {
                        chiffre1 = chiffre1 + Double.valueOf(ecran.getText()).doubleValue();
                        ecran.setText(String.valueOf(chiffre1));
                }
                        
                if(operateur.equals("-"))
                {
                        chiffre1 = chiffre1 - Double.valueOf(ecran.getText()).doubleValue();
                        ecran.setText(String.valueOf(chiffre1));
                }               
                
                if(operateur.equals("*"))
                {
                        chiffre1 = chiffre1 * Double.valueOf(ecran.getText()).doubleValue();
                        ecran.setText(String.valueOf(chiffre1));
                }       
                        
                if(operateur.equals("/"))
                {
                        try{
                                chiffre1 = chiffre1 / Double.valueOf(ecran.getText()).doubleValue();
                                ecran.setText(String.valueOf(chiffre1));
                        } catch (ArithmeticException e){
                                ecran.setText("0");
                        }
                }
        }
        
        class ChiffreListener implements ActionListener{
                public void actionPerformed(ActionEvent e) {
                        //On affiche le chiffre en plus dans le label
                        String str = ((JButton)e.getSource()).getText();
                        
                        if(update)
                        {
                                update = false;
                        }
                        else
                        {
                                if(!ecran.getText().equals("0")) 
                                        str = ecran.getText() + str;
                        }
                        
                        ecran.setText(str);
                }
        }
 
        
        class EgalListener implements ActionListener{
                public void actionPerformed(ActionEvent arg0) {
                        calcul();
                        update = true;
                        clicOperateur = false;
                }    
        }
        
        
        class PlusListener implements ActionListener{
                public void actionPerformed(ActionEvent arg0) {
                        
                        if(clicOperateur)
                        {
                                calcul();
                                ecran.setText(String.valueOf(chiffre1));
                        }
                        else
                        {
                                chiffre1 = Double.valueOf(ecran.getText()).doubleValue();
                                clicOperateur = true;
                        }
                        operateur = "+";
                        update = true;
                }
        }
        
        class MoinsListener implements ActionListener{
                public void actionPerformed(ActionEvent arg0) {
                        if(clicOperateur)
                        {
                                calcul();
                                ecran.setText(String.valueOf(chiffre1));
                        }
                        else
                        {
                                chiffre1 = Double.valueOf(ecran.getText()).doubleValue();
                                clicOperateur = true;
                        }
                        operateur = "-";
                        update = true;
                }
        }
        
        class MultiListener implements ActionListener{
                public void actionPerformed(ActionEvent arg0) {
                        if(clicOperateur)
                        {
                                calcul();
                                ecran.setText(String.valueOf(chiffre1));
                        }
                        else
                        {
                                chiffre1 = Double.valueOf(ecran.getText()).doubleValue();
                                clicOperateur = true;
                        }
                        operateur = "*";
                        update = true;
                }
        }
        
        class DivListener implements ActionListener{
                public void actionPerformed(ActionEvent arg0) {
                       
                		if(clicOperateur)
                        {
                                calcul();
                                ecran.setText(String.valueOf(chiffre1));
                        }
                        else
                        {
                                chiffre1 = Double.valueOf(ecran.getText()).doubleValue();
                                clicOperateur = true;
                        }
                        operateur = "/";
                        update = true;
                }
        }
        
        class ResetListener implements ActionListener{ 
                public void actionPerformed(ActionEvent arg0) {
                        clicOperateur = false;
                        update = true;
                        ecran.setText(""); 
                }
        }
        
}
