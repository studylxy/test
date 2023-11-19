package test06;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Client extends JFrame {
    private JLabel inputLabel;
    private JTextField inputFileField;
    private JButton browseButton;
    private JComboBox<String> methodComboBox;
    private JTextArea outputTextArea;
    private JButton processButton;
    public Output output= new Output();
    public Client() {
        setTitle("经典软件体系结构教学软件");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 创建输入文件选择部分
        inputLabel = new JLabel("选择文件:");
        inputFileField = new JTextField(25);
        browseButton = new JButton("浏览");
        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(Client.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    inputFileField.setText(selectedFile.getAbsolutePath());
                }
            }
        });
        JPanel inputPanel = new JPanel();
        inputPanel.add(inputLabel);
        inputPanel.add(inputFileField);
        inputPanel.add(browseButton);

        // 创建处理方法选择部分
        String[] methods = {"主程序-子程序", "面向对象", "事件系统", "管道-过滤器"};
        methodComboBox = new JComboBox<>(methods);
        JPanel methodPanel = new JPanel();
        methodPanel.add(new JLabel("执行方法:"));
        methodPanel.add(methodComboBox);

        // 创建输出结果显示部分
        outputTextArea = new JTextArea(10, 30);
        outputTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);

        // 创建处理按钮部分
        processButton = new JButton("处理");
        processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                String method = (String) methodComboBox.getSelectedItem();
                File inputFile=new File("");

                // 根据选择的方法进行处理
                String result=null;
                try {
                    result = processFile(method, inputFile);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });

        // 将各部分添加到主界面中
        add(inputPanel, BorderLayout.NORTH);
        add(methodPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.WEST);
        add(processButton, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null); // 居中显示窗口
    }

    private String processFile(String method, File inputFile) throws IOException {

        String output = "";
        if (method.equals("主程序-子程序")) {
            // 调用给定的代码作为 Method 1 的实现处理逻辑
            demo1 demo = new demo1();
            demo.main();
            outputTextArea.setText(this.output.outPut("D:\\output.txt"));
        } else if (method.equals("面向对象")) {
            demo2 demo = new demo2();
            demo.main();
            outputTextArea.setText(this.output.outPut("D:\\output2.txt"));
        }
        else if (method.equals("事件系统")) {
            demo3 demo = new demo3();
            demo.main();
            outputTextArea.setText(this.output.outPut("D:\\output3.txt"));
        } else if (method.equals("管道-过滤器")) {
            demo4 demo = new demo4();
            demo.main();
            outputTextArea.setText(this.output.outPut("D:\\output4.txt"));
        }

        return "Invalid method";
    }


    private class AlphabetizerComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            if (o1 == null && o2 == null) {
                throw new NullPointerException();
            }
            int compareValue = 0;
            char o1c = o1.toLowerCase().charAt(0);
            char o2c = o2.toLowerCase().charAt(0);
            compareValue = o1c - o2c;
            return compareValue;
        }
    }

    private List<String> readFile(String inputFile) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                Client app = new Client();
                app.setVisible(true);
            }
        });
    }

    public static class Output {
        public String outPut(String path) {
            StringBuilder sb=new StringBuilder();
            try
                    (BufferedReader br = new BufferedReader(new FileReader(path))){

                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append('\n');
                }
            }catch(IOException e){
                e.printStackTrace();
            }

            return sb.toString();
        }
    }
}