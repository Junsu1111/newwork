import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class final_exam {
    public static void main(String[] args) {
        String host="203.252.148.148";
        int port=2022;
        String studentNumber="202211284";
        try{
            SocketChannel client=SocketChannel.open();
            client.configureBlocking(false);
            InetSocketAddress address=new InetSocketAddress(host,port);
            client.connect(address);
            while(!client.finishConnect()){
                ;
            }

            ByteBuffer buffer=ByteBuffer.allocate(1024);
            byte[] bytes=studentNumber.getBytes(StandardCharsets.UTF_16);
            buffer.put(bytes);

            buffer.flip();
            buffer.position(2);

            while(buffer.hasRemaining()){
                client.write(buffer);
            }
            buffer.clear();
//130=x+10*4+10*4+31
//->x=19
            while(true){
                int n=client.read(buffer);
                if(n>0){
                    buffer.flip();
                    int[] ints=new int[10];
                    float[] floats=new float[10];
                    handleResponse(buffer,ints,floats);
                    System.out.println("최대 int: "+getMaxFromInts(ints)+", 최대 float: "+getMaxFromFloats(floats));
                }else if(n==-1){
                    break;
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private static void handleResponse(ByteBuffer buffer, int[] ints, float[] floats){
        int totalSize=buffer.limit();
        int sizeOfHan2=31;
        int totalSizeOfIntNFloat=4*10+4*10;
        int sizeOfHan1=totalSize-totalSizeOfIntNFloat-sizeOfHan2;
        try {
            WritableByteChannel out = Channels.newChannel(System.out);
            buffer.limit(sizeOfHan1);
            out.write(buffer);
            buffer.limit(sizeOfHan1 + totalSizeOfIntNFloat);
            for (int i = 0; i < 10; i++) {
                System.out.print((ints[i] = buffer.getInt()) + " ");
                System.out.print((floats[i] = buffer.getFloat()) + " ");
            }
            buffer.limit(totalSize);
            out.write(buffer);
            System.out.println();
            buffer.clear();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private static int getMaxFromInts(int[] ints){
        int max=ints[0];
        for(int i=1;i<ints.length;i++){
            if (ints[i]>max) {
                max=ints[i];
            }
        }
        return max;
    }

    private static float getMaxFromFloats(float[] floats){
        float max=floats[0];
        for(int i=1;i<floats.length;i++){
            if(floats[i]>max){
                max=floats[i];
            }
        }
        return max;
    }
}
