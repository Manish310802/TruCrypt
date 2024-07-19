import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import com.sun.mail.smtp.SMTPTransport;

public class SendMail extends javax.swing.JFrame {

    //Constructor to set up JFrame properties
    public SendMail() {
        initComponents();
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

     // Opens a file dialog for selecting files to attach
    private java.io.File showFileDialog(final boolean open) {
        JFileChooser fc = new JFileChooser("Open a File");
        javax.swing.filechooser.FileFilter ff = new javax.swing.filechooser.FileFilter() {
            public boolean accept(java.io.File f) {
                String name = f.getName().toLowerCase();
                if (open)
                    return f.isDirectory() || name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".png")
                            || name.endsWith(".gif") || name.endsWith(".txt") || name.endsWith(".pdf")
                            || name.endsWith(".mp3");
                return f.isDirectory() || name.endsWith(".png") || name.endsWith(".pdf") || name.endsWith(".jpeg")
                        || name.endsWith(".txt") || name.endsWith(".mp3");
            }

            public String getDescription() {
                if (open)
                    return "Image (*.jpg, *.jpeg, *.png, *.gif, *.txt, *.pdf, *.mp3)";
                return "Image (*.png, *.pdf, *.jpeg, *.txt, *.mp3 )";
            }
        };
        fc.setAcceptAllFileFilterUsed(false);
        fc.addChoosableFileFilter(ff);

        java.io.File f = null;
        if (open && fc.showOpenDialog(this) == fc.APPROVE_OPTION)
            f = fc.getSelectedFile();
        else if (!open && fc.showSaveDialog(this) == fc.APPROVE_OPTION)
            f = fc.getSelectedFile();
        return f;
    }

    // GUI components initialization and layout setup
    @SuppressWarnings("unchecked")
    private void initComponents() {

        jTextFrom = new javax.swing.JTextField();
        jTextTo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaBody = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabelAttachPath = new javax.swing.JLabel();
        jPasswordFrom = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jTextFrom.setFont(new java.awt.Font("Tahoma", 0, 16));

        jTextTo.setFont(new java.awt.Font("Tahoma", 0, 16));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel1.setText("From :");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel2.setText("To :");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 24));
        jButton1.setText("Send");
        jButton1.setBorder(null);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextAreaBody.setColumns(20);
        jTextAreaBody.setFont(new java.awt.Font("Monospaced", 0, 16));
        jTextAreaBody.setRows(5);
        jScrollPane1.setViewportView(jTextAreaBody);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel3.setText("Body :");

        jLabelAttachPath.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabelAttachPath.setForeground(new java.awt.Color(0, 0, 255));
        jLabelAttachPath.setText("Attach");
        jLabelAttachPath.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelAttachPath.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAttachPathMouseClicked(evt);
            }

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelAttachPathMouseEntered(evt);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelAttachPathMouseExited(evt);
            }
        });

        jPasswordFrom.setFont(new java.awt.Font("Tahoma", 0, 14));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel5.setText("Password");

      jPanel1.setBackground(new java.awt.Color(70, 130, 180));

   //   jPanel1.setBackground(new java.awt.Color(135, 206, 250));



        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 36));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Send Email");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(54, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jTextTo, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jPasswordFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabelAttachPath, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(37, 37, 37))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(1, 1, 1)
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jPasswordFrom)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextTo, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabelAttachPath, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29))
        );

        pack();
    }
 // Hover effect for the "Attach" label
    private void jLabelAttachPathMouseEntered(java.awt.event.MouseEvent evt) {
        jLabelAttachPath.setForeground(Color.blue);
    }
// Reset color after mouse exits
    private void jLabelAttachPathMouseExited(java.awt.event.MouseEvent evt) {
        jLabelAttachPath.setForeground(Color.black);
    }
 // Opens a file dialog when the "Attach" label is clicked
    private void jLabelAttachPathMouseClicked(java.awt.event.MouseEvent evt) {
        java.io.File f = showFileDialog(true);
        if (f == null)
            return;
        else {
            jLabelAttachPath.setText(f.getPath());
        }
    }

    // Validates and sends the email when the "Send" button is clicked
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        if (jTextFrom.getText().equals("") || jTextTo.getText().equals("") || jPasswordFrom.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Please Fill All the Form!");
        } else if (SendEmail()) {

        } else {
            JOptionPane.showMessageDialog(this,
                    "You Outlook Has 2-Step-verification! please Enter your Outlook App Password instead of Password");
        }
    }

    // Default values for the "From" and "Password" fields when the window is opened
    private void formWindowOpened(java.awt.event.WindowEvent evt) {
        jPasswordFrom.setText("Manish1510@");
        jTextFrom.setText("ManishJangid3108@outlook.com");
    }
    // Configures JavaMail properties and sends an email
    // Handles authentication, message setup, and file attachment
     public boolean SendEmail() {
    Properties properties = new Properties();
    properties.put("mail.smtp.host", "smtp.office365.com");
    properties.put("mail.smtp.port", "587");
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.ssl.protocols", "TLSv1.2");


    Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(jTextFrom.getText(), String.valueOf(jPasswordFrom.getPassword()));
        }
    });

    try {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(jTextFrom.getText()));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(jTextTo.getText()));
        message.setSubject("Subject");

        // Create the message body part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText(jTextAreaBody.getText());

        // Create the multipart message
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        // Attach the file if a path is specified
        String filePath = jLabelAttachPath.getText();
        if (!filePath.equals("Attach")) {
            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.attachFile(new File(filePath));
            multipart.addBodyPart(attachmentPart);
        }

        // Set the complete message parts
        message.setContent(multipart);

        // Send the message
        Transport.send(message);
       
       // Show dialog box for success
        JOptionPane.showMessageDialog(this, "Sent Email Successfully!");

       // Close the window
        this.dispose();

        System.out.println("Sent message successfully....");
        return true;
    } catch (MessagingException | IOException ex) {
        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        ex.printStackTrace();
        return false;
    }
}
// Main method to launch the GUI
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SendMail().setVisible(true);
            }
        });
    }
// Declarations of GUI components and other class members
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelAttachPath;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordFrom;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaBody;
    private javax.swing.JTextField jTextFrom;
    private javax.swing.JTextField jTextTo;
}
