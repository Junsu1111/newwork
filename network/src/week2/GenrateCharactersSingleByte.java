package week2;

import java.io.IOException;
import java.io.OutputStream;

public class GenrateCharactersSingleByte {

    public static void main(String[] args) {
        long beforeTime=System.currentTimeMillis();
        try{
            generateCharacters(System.out);
        }catch (IOException e){

        }
        long afterTime=System.currentTimeMillis();
        long runTime=(afterTime-beforeTime);
        System.out.println("실행시간: "+runTime);
    }
    public static void generateCharacters(OutputStream out) throws IOException{
        int firstPrintableCharacter=33;
        int numberOfPrintableCharacters=94;
        int numberOfCharactersPerLine=72;
        int start=firstPrintableCharacter;
        int count=0;

        while(count++<10000){
            for(int i=start;i<start+numberOfCharactersPerLine;i++){
                out.write((byte)(i-firstPrintableCharacter)%numberOfPrintableCharacters+firstPrintableCharacter);
            }
            out.write((byte)'\r');
            out.write((byte)'\n');

            start=(start+1-firstPrintableCharacter)%numberOfPrintableCharacters+firstPrintableCharacter;
            count++;
        }
    }
}
