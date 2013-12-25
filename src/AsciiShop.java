import sun.print.resources.serviceui_zh_CN;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class AsciiShop  {
    /**
     * Diese mthode liefert zuruck lesbare darstelung des Argumentes von angegebende String.
     * @param str Input String , in dem sucht man argumenten
     * @return String mit argumenten
     */
    public static List getTempArguments(String str){
        String temp;
        int index=str.indexOf(" ");
        ArrayList<String> list = new ArrayList<String>(Arrays.asList(str.substring(index + 1).split(" ")));
        return list;
    }

    /**
     *
     * @param image   AsciiBild  auf dem  unterschiedliche Operationen ausgeführt werden
     * @param task    Operation auf dem Bild
     * @param flag    Variable der zegen muss ob das Bils Einlesen werde ,oder es leer ist.
     */
    public static void execute(AsciiImage image,String task,String flag) throws  java.util.NoSuchElementException{

        Scanner scan = new Scanner(System.in);
        try {


    if(task!=null){
        if(task.equals("print")){
            image.print();
            execute(image,scan.nextLine().toString(),null);
        }else if(task.contains("replace")&&task.length()<=11){

          image.replace(getTempArguments(task).get(0).toString().charAt(0),getTempArguments(task).get(1).toString().charAt(0));

          execute(image, scan.nextLine().toString(), null);
        }else if(task.equals("transpose")){
            image.transpose();
            execute(image, scan.nextLine().toString(), null);
        }else if(task.contains("fill")){
            List temp = getTempArguments(task);
            image.fill(Integer.parseInt(temp.get(0).toString()),Integer.parseInt(temp.get(1).toString()),temp.get(2).toString().charAt(0));
            execute(image, scan.nextLine().toString(), null);
        }else if(task.contains("line")){
            List temp = getTempArguments(task);
            image.drawLine(Integer.parseInt(temp.get(0).toString()),Integer.parseInt(temp.get(1).toString()),Integer.parseInt(temp.get(2).toString()),Integer.parseInt(temp.get(3).toString()),temp.get(4).toString().charAt(0));
            execute(image, scan.nextLine().toString(), null);
        }else if(task.equals("load")){
             readImage(image,flag);
        }else if(task.equals("clear")){
            image.clear();
            execute(image, scan.nextLine().toString(), null);
        }else{
            System.out.println("UNKNOWN COMMAND");
            System.exit(0);
        }
    }else{
        System.exit(0);
    }
        }catch (Exception e){
            System.exit(0);
        }
    }

    /**
     *
     * @param image AsciiBild  zu dem neue Zeilen addieren werden
     * @param flag  flag der signalisiert ende des Einlesung
     */
    public static void readImage(AsciiImage image,String flag){
        Scanner scan = new Scanner(System.in);
        String str;
        while(image.addLine(scan.next().toString(),flag)){}
        image.print();
        execute(image, scan.next()+scan.nextLine().toString(), null);
    }

    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);

        AsciiImage image;
        String tempInput="";

        if (scan.next().equals("create")){
            image= new AsciiImage(scan.nextInt(),scan.nextInt());
                tempInput=scan.next().toString();
                      if(tempInput.equals("load")){
                          execute(image,"load",scan.next().toString());
                      }else{
                          execute(image,tempInput+=scan.nextLine().toString(),null);

                      }
        }else{
            System.out.println("INPUT MISMATCH");
            System.exit(0);
        }


    }
}
