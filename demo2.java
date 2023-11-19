package test06;

import java.io.*;
import java.util.*;

public class demo2 {



        public static void main() {
            Input input = new Input();
            input.input("D:\\2.txt");
            Shift shift = new Shift(input.getLineTxt());
            shift.shift();
            Alphabetizer alphabetizer = new Alphabetizer(shift.getKwicList());
            alphabetizer.sort();
            Output output = new Output(alphabetizer.getKwicList());
            output.output("D:\\output2.txt");

        }


    public static class Output {
        private ArrayList<String> kwicList;
        private BufferedWriter outputFile;

        public Output(ArrayList<String> kwicList) {
            this.kwicList = kwicList;
        }

        public void output(String filename){
            Iterator<String> it = kwicList.iterator();
            try {
                outputFile = new BufferedWriter(new FileWriter(filename));
                while (it.hasNext()) {
                    outputFile.write(it.next()+"\n");
                }
            }catch (IOException e){
                e.printStackTrace();
            }finally {
                try {
                    if (outputFile!=null) {
                        outputFile.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class Alphabetizer {
        private ArrayList<String> kwicList;

        public Alphabetizer(ArrayList<String> kwicList) {
            this.kwicList = kwicList;
        }

        public ArrayList<String> getKwicList() {
            return kwicList;
        }

        public void sort() {
            Collections.sort(this.kwicList, new AlphabetizerComparator());
        }

        private class AlphabetizerComparator implements Comparator<String> {
            @Override
            public int compare(String o1, String o2) {
                if (o1 == null && o2 == null) {
                    throw new NullPointerException();
                }
                int compareValue = 0;
                char o1c = o1.toLowerCase().charAt(0); //忽略大小写
                char o2c = o2.toLowerCase().charAt(0); //忽略大小写
                compareValue = o1c - o2c;
                return compareValue;

            }

        }
    }

    public static class Shift {
        private ArrayList<String> kwicList = new ArrayList<String>();
        private ArrayList<String> lineTxt;

        public Shift( ArrayList<String> lineTxt) {
            this.lineTxt = lineTxt;
        }

        public ArrayList<String> getKwicList() {
            return kwicList;
        }


        public ArrayList<String> getLineTxt() {
            return lineTxt;
        }


        public void shift() {
            //获取每个单词，存入tokens
            Iterator<String> it = lineTxt.iterator();
            while (it.hasNext()) {
                StringTokenizer token = new StringTokenizer(it.next());
                ArrayList<String> tokens = new ArrayList<String>();
                int i = 0;
                //循环添加单词
                int count = token.countTokens();
                while (i < count) {
                    tokens.add(token.nextToken());
                    i++;
                }

                //display(tokens);
                //切割各个单词，不断改变起始值和利用loop实现位移。
                for (i = 0; i < count; i++) {
                    StringBuffer lineBuffer = new StringBuffer();
                    int index = i;
                    for (int f = 0; f < count; f++) {
                        //从头继续位移
                        if (index >= count)
                            index = 0;
                        //存入StringBuffer
                        lineBuffer.append(tokens.get(index));
                        lineBuffer.append(" ");
                        index++;
                    }
                    String tmp = lineBuffer.toString();
                    kwicList.add(tmp);
                }
            }

        }
    }

    public static class Input {
        private ArrayList<String> lineTxt = new ArrayList<String>();

        public ArrayList<String> getLineTxt() {
            return lineTxt;
        }

        public void input(String fileName) {
            BufferedReader inputFile = null;
            try {
                inputFile = new BufferedReader(new FileReader(fileName));
            } catch (Exception e) {
                e.printStackTrace();
            }
            String line;
            try {
                while ((line = inputFile.readLine()) != null) {
                    lineTxt.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }









}
