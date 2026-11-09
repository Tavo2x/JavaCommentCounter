
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;


public class CommentCounterGUI extends JFrame{

    
    private JTabbedPane tabs = new JTabbedPane();
    private JTextPane codePane = new JTextPane();
    private JTextPane commentPane = new JTextPane();
    private JTextPane javaDocPane = new JTextPane();
    private JTextPane originalPane = new JTextPane();
    private JButton chooseFile = new JButton("Choose File");
    private JTextPane summaryTxt = new JTextPane();

    private void processFile(File file){
        CommentCounter counter = new CommentCounter();
     try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int ch;
            while ((ch = br.read()) != -1) {
                counter.processChar((char) ch);
            }
            codePane.setText(counter.getCodeText());
            commentPane.setText(counter.getCommentText());
            javaDocPane.setText(counter.getJavaDocText());
            originalPane.setText(counter.getOriginalText());
            numberSummary(counter);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public CommentCounterGUI(){
//- Summary section 
    summaryTxt.setEditable(false);
    summaryTxt.setSize(300,300);
    add(summaryTxt, BorderLayout.SOUTH);

//- Button
    add(chooseFile,BorderLayout.NORTH);
    chooseFile.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e){
            JFileChooser fileChooser = new JFileChooser();
            int response = fileChooser.showOpenDialog(CommentCounterGUI.this);
            if (response == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                processFile(selectedFile);
            }
        }
    });
//- Summary text "settings"
    summaryTxt.setEditable(false);
    add(summaryTxt,BorderLayout.SOUTH);
//- codePane "settings"
        add(tabs, BorderLayout.CENTER);
        codePane.setEditable(false); // We dont want users to change the values
        codePane.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12)); // helps align the dots
//- commentPane "settings"
        commentPane.setEditable(false);
        commentPane.setFont(new Font(Font.MONOSPACED,Font.PLAIN, 12));
//- javaDocPane "settings"
        javaDocPane.setEditable(false);
        javaDocPane.setFont(new Font(Font.MONOSPACED,Font.PLAIN, 12));
//- originalPane "settings"
        originalPane.setEditable(false);
        originalPane.setFont(new Font(Font.MONOSPACED,Font.PLAIN, 12));
    
        tabs.add("Code", new JScrollPane(codePane));
        tabs.add("Comments", new JScrollPane(commentPane));
        tabs.add("JavaDoc", new JScrollPane(javaDocPane));
        tabs.add("Original", new JScrollPane(originalPane));
        setTitle("Comment Counter");
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true); // this is not in main I rather have this exisit in here
    }

//- Summary text box seen in every tab
    private void numberSummary(CommentCounter c){

        summaryTxt.setText("Code Characters: " + c.getCodeNum() +
                           "\nSingle Line Characters: " + c.getSingleLineNum() +
                            "\nMulti Line Characters: " + c.getMultiLineCommentNum() + 
                            "\nJava Doc Characters: " + c.getJavaDocNum()  );
    }
}
