
/**
 * Created by taras on 14.12.13.
 */
public class AsciiImage {

     private    int           height;
     private    int           width;
     private    char[][]      image;


    /**
     * Constructor
     * @param h heigth
     *
     * @param w wigth
     */
    public AsciiImage(int w,int h){
        this.setHeight(h);
        this.setWidth(w);
        setImage(new char[h][w]);
        this.clear();
    }
    /**
     * Getters and Setters
     */
    public int length(){return this.length();}

    public int getHeight(){
        return this.height;
    }

    public void  setHeight(int h){this.height=h;}

    public int getWidth(){
        return this.width;
    }

    public void setWidth(int w){this.width=w;}

    public char getPixel(int x,int y){   return getImage()[y][x];}

    public void setPixel(int x ,int y,char c){ this.image[y][x]=c;}

    public char[][] getImage() {   return image;}

    public void setImage(char[][] image) {this.image = image;}
    /**
     * Hillfsmethoden
     *
     *
     */
    public String toString(char[] arr){
        String str="";
        for(int i=0;i<arr.length;i++){
            str+=arr[i];
        }
       return str;
    }
    public boolean checkIfBlank(String str,int count){

     if(count <str.length()){
         if(str.charAt(count)=='.'){
         return checkIfBlank(str,count+1);
         }else{
             return false;
         }
     }
        return true;
    }
    public void addLine(String str){
        for(int i =0 ;i< getImage().length;i++){
            if(checkIfBlank(this.toString(getImage()[i]),0)){
                 getImage()[i]=str.toCharArray();
                break;
            }
        }
    }
    public boolean addLine(String str,String flag){
        if(str.equals(flag)) return false;
        for(int i =0 ;i< getImage().length;i++){
            if(checkIfBlank(this.toString(getImage()[i]),0)){
                getImage()[i]=str.toCharArray();
                break;
            }

        }
        return true;
    }

   public void makeEquals(AsciiImage temp){
       this.setHeight(temp.getHeight());
       this.setWidth(temp.getWidth());
       this.setImage(temp.getImage());
   }

    /**
     * Methoden ,die man laut Aufgabe implementieren muss;
     */
    public void clear(){
        for(int i=0;i< getImage().length;i++){
            for(int j=0;j< getImage()[i].length;j++){
                getImage()[i][j]='.';
            }
        }
    }

     public void replace(char oldChar,char newChar){

         for(int i=0;i< getImage().length;i++){
             for(int j=0;j< getImage()[i].length;j++){
                 if(getImage()[i][j]==oldChar){
                     getImage()[i][j]=newChar;
                 }
             }
         }

    }

    public void drawLine(int x0, int y0, int x1, int y1, char c) {

        int deltaX = Math.abs(x0 - x1);
        int deltaY = Math.abs(y0 - y1);
        boolean invert = false;

        if(deltaY > deltaX) {
            int buffer = x0;
            x0= y0;
            y0 = buffer;
            buffer = x1;
            x1 = y1;
            y1 = buffer;
            buffer = deltaX;
            deltaX = deltaY;
            deltaY = buffer;
            invert = true;
        }

        if(x1 < x0) {
            int buffer = x0;
            x0 = x1;
            x1 = buffer;
            buffer = y0;
            y0 = y1;
            y1 = buffer;
        }

        double y = y0;
        int x = x0;
        while(x <= x1) {
            if(invert) {
                setPixel((int) Math.round(y), x, c);
            }
            else {
                setPixel(x, (int) Math.round(y), c);
            }

            x++;
            if(y < y1) {
                y += (double)deltaY/(double)deltaX;
            }
            else if(y > y1) {
                y -= (double)deltaY/(double)deltaX;
            }
        }
    }

    public void print(){
               for(int i=0;i< getImage().length;i++){
                   String tempStr="";
                   for(int j=0;j< getImage()[i].length;j++){
                            tempStr+= getImage()[i][j];
                   }
                   System.out.println(tempStr);
               }
    }
    public void    transpose(){
        AsciiImage returnArr = new AsciiImage(getHeight(),getWidth());
        for(int i=0 ; i < this.getWidth(); i++){
                for(int j=0 ; j < this.height ; j++){
                    returnArr.setPixel(j,i,this.getPixel(i,j));
                }
        }
       this.makeEquals(returnArr);
    }

    public void fill(int x,int y ,char c ){
        final char CHAR_FLAG=this.image[y][x];

        if(image[y][x]==CHAR_FLAG){
            image[y][x]=c;

            if (x >= 0 && y >= 0 && y <= image.length && x <= image[y].length) {

                if(x>0&&image[y][x-1]==CHAR_FLAG){
                    this.fill(x-1,y,c);
                }
                if(y>0&&image[y-1][x]==CHAR_FLAG){
                    this.fill(x,y-1,c);
                }
                if(x<image[y].length-1&&image[y][x+1]==CHAR_FLAG){
                    this.fill(x+1,y,c);
                }
                if(y<image.length-1&&image[y+1][x]==CHAR_FLAG){
                    this.fill(x,y+1,c);
                }
            }
        }
    }
}
