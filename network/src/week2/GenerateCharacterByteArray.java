package week2;

import java.io.IOException;
import java.io.OutputStream;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GenerateCharacterByteArray {
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
    public static void generateCharacters(OutputStream out) throws IOException {
        int firstPrintableCharacter=33;
        int numberOfPrintableCharacters=94;
        int numberOfCharactersPerLine=72;
        int start=0;
        int count=0;

        byte[] characters=new byte[188];
        for(int i=0;i<numberOfPrintableCharacters*2;i++){
            characters[i]=(byte)(i%(numberOfPrintableCharacters+1)+firstPrintableCharacter);
        }
        while(count<100000) {
            out.write(characters, start,numberOfPrintableCharacters);

            out.write((byte) '\r');
            out.write((byte) '\n');

            start = (++start - 1) % numberOfPrintableCharacters + 1;
            count++;
        }
    }
}
